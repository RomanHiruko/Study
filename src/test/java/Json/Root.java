package Json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Root {
    public String build() {
        JsonObject rootObject = new JsonObject();
        TechData techData = new TechData();
        BusinessData businessData = new BusinessData();
        Gson gson = new Gson();

        JsonObject techDataObject = techData.build();
        JsonObject businessDataObject = businessData.addContrAgent().addContract().build();
        rootObject.add("TechData", techDataObject);
        rootObject.add("BusinessData", businessDataObject);

        return gson.toJson(rootObject);
    }

    public static void main(String[] args) {
        Root root = new Root();
        System.out.println(root.build());
    }
}
