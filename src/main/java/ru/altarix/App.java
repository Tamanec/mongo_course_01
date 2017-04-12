package ru.altarix;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.bson.Document;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App 
{
    public static void main( String[] args ) {
        Configuration conf = new Configuration(Configuration.VERSION_2_3_26);
        conf.setClassForTemplateLoading(HelloWorldFreemarker.class, "/templates/");
        conf.setDefaultEncoding("UTF-8");

        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        MongoDatabase db = client.getDatabase("test");
        final MongoCollection<Document> test = db.getCollection("test");

        test.drop();
        test.insertOne(new Document("name", "MongoDB"));

        get("/", ((request, response) -> {
            Document doc = test.find().first();

            Map<String, Object> data = new HashMap<>();
            data.put("user", doc.getString("name"));

            StringWriter writer = new StringWriter();
            try {
                Template template = conf.getTemplate("test.ftl");
                template.process(data, writer);
            } catch (Exception e) {
                halt(500, e.getMessage());
            }

            return writer;
        }));
    }
}
