package Json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class Contract {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Person person = new Person();
        Header header = new Header();
        JsonObject personsObj = person.addEmails().addPhones().build();
        JsonObject headerObj = header.addHeader();

        JsonArray contractArray = new JsonArray();
        JsonObject prshdr = new JsonObject();
        prshdr.add("Persons", personsObj);
        prshdr.add("Header", headerObj);
        contractArray.add(prshdr);
        JsonObject contractsObj = new JsonObject();
        contractsObj.add("Contract", contractArray);
        System.out.println(gson.toJson(contractsObj));
    }
}
