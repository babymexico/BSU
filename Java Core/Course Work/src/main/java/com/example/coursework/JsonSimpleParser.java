package com.example.coursework;

import com.example.coursework.test_json.Expressions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.example.coursework.test_json.Root;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonSimpleParser {

    public List<String> parse(String fileName) {
        Root root = new Root();
        List expressions = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try(FileReader reader = new FileReader(fileName);) {
            JSONObject rootJsonObject = (JSONObject) parser.parse(reader);
            String name = (String) rootJsonObject.get("name");

            JSONArray peopleJsonArray = (JSONArray) rootJsonObject.get("expressions");
            List<Expressions> expressionsList = new ArrayList<>();
            for(Object it: peopleJsonArray) {
                JSONObject peopleJsonObject = (JSONObject) it;

                String exp = (String) peopleJsonObject.get("exp");

                expressions.add(exp);
            }

            root.setName(name);
            root.setExpressions(expressionsList);
            return expressions;
        } catch (Exception e) {
            System.out.println("Parsing error!" + e.toString());
        }

        return null;
    }

    public void writeInJson(String filename, String results) throws IOException {
        JSONObject sampleObject = new JSONObject();
        sampleObject.put("results", results);

        Files.write(Paths.get(filename), sampleObject.toJSONString().getBytes());
    }
}
