package logic;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ParseJsonFile {

    private static final String manufactureFilePath = "manufacturers.json";
    private static final String souvenirsFilePath = "souvenirs.json";
    private static final String NAME = "name";
    private static final String COUNTRY = "country";
    private static final String RELEASE_DATE = "releaseDate";
    private static final String MANUFACTURE_NAME = "manufacturerName";
    private static final String PRISE = "price";

    public static List<Data> readDataFromFile(Class clazz){
        List<Data> result = new ArrayList<>();
        String filePath = getFilePath(clazz);

        try (BufferedReader reader = java.nio.file.Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)){
            String line;
            while (true){
                try{
                    if((line = reader.readLine()) == null) break;

                    JSONObject object = new JSONObject(line);
                    JSONArray jsonArray = object.getJSONArray(clazz.getName());
                    for (Object obj : jsonArray) {
                        Data data = getObjectByJsonObject((JSONObject) obj, clazz);
                        result.add(data);
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            System.out.println("Error while read from file " + e);
        }


        return result;
    }

    public static void writeDataToFile(List<Data> data, Class clazz){

        String filePath = getFilePath(clazz);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(clazz.getName(), data);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            writer.write(jsonObject.toString());
        } catch (Exception e) {
            System.out.println("Error during write to file " + e);
        }
    }

    private static Data getObjectByJsonObject(JSONObject jsonObject, Class clazz) {
        Data result = null;
        if("Manufactures".contains(clazz.getSimpleName())){
            result = new Manufactures(
                    jsonObject.getString(NAME),
                    jsonObject.getString(COUNTRY)
            );
        } else if ("Souvenirs".contains(clazz.getSimpleName())) {
            result = new Souvenirs(
                    jsonObject.getString(NAME),
                    jsonObject.getString(MANUFACTURE_NAME),
                    LocalDate.parse(jsonObject.getString(RELEASE_DATE), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    Double.parseDouble(String.valueOf(jsonObject.getInt(PRISE)))
            );
        }
        return result;
    }

    private static String getFilePath(Class clazz) {
        if ("Manufactures".contains(clazz.getSimpleName())){
            return manufactureFilePath;
        } else if ("Souvenirs".contains(clazz.getSimpleName())) {
            return souvenirsFilePath;
        }else return "";
    }
}
