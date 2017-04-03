package ru.altarix;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HelloWorldFreemarker {
    public static void main(String[] args) {
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

        try {
            Template template = conf.getTemplate("test.ftl");
            StringWriter writer = new StringWriter();
            template.process(data, writer);

            System.out.println(writer);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
