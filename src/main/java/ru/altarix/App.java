package ru.altarix;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App 
{
    public static void main( String[] args ) {
        get("/", ((request, response) -> {
            Configuration conf = new Configuration(Configuration.VERSION_2_3_26);
            conf.setClassForTemplateLoading(HelloWorldFreemarker.class, "/templates/");
            conf.setDefaultEncoding("UTF-8");

            Map<String, Object> data = new HashMap<>();
            data.put("user", "John Dou");

            Map<String, Object> animal = new HashMap<>();
            animal.put("name", "Snowy");
            animal.put("price", 1200);

            List<Map<String, Object>> animals = new LinkedList<>();
            animals.add(animal);

            data.put("animals", animals);

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
