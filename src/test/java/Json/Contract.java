package Json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Contract {
    JsonObject jsonInsideContractArrayObject = new JsonObject();
    private final JsonObject upperContractObject = new JsonObject();


    public Contract addHeader() {
        Header header = new Header();
        JsonObject headerObj = header.build();
        jsonInsideContractArrayObject.add("Header", headerObj);
        return this;
    }

    public Contract addPersons() {
        Persons persons = new Persons();
        JsonArray personsObj = persons.build();
        jsonInsideContractArrayObject.add("Persons", personsObj);
        return this;
    }

    public JsonObject build() {
        JsonObject contractsObject = new JsonObject();
        JsonArray contractArray = new JsonArray();
        contractArray.add(jsonInsideContractArrayObject);
        contractsObject.add("Contract", contractArray);
        upperContractObject.add("Contracts", contractsObject);
        upperContractObject.addProperty("ContractsCount", contractArray.size());
        upperContractObject.addProperty("SchemaVersion", "1.0");
        return upperContractObject;
    }
}
