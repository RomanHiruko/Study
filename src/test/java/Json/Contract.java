package Json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Contract {
    JsonObject jsonInsideContractArrayObject = new JsonObject();
    private final JsonObject upperContractObject = new JsonObject();


    public Contract addHeader() {
        Header header = new Header();
        JsonObject headerObj = header
                .addBranchCode("123")
                .isProlonged(true)
                .addHeader()
                .build();
        jsonInsideContractArrayObject.add("Header", headerObj);
        return this;
    }

    public Contract addPersons() {
        Persons persons = new Persons();
        JsonArray personsObj = persons
                .addBirthday("1979-06-23")
                .addGender("M")
                .isResident(true)
                .addFirstNameRus("Клиент")
                .addLastNameRus("Тестовый")
                .addMiddleNameRus("Тестович")
                .addEmails()
                .addPhones()
                .build();
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

    public static void main(String[] args) {
        Contract contract = new Contract();
        Gson gson = new Gson();
        System.out.println(gson.toJson(contract.addHeader().addPersons().build()));
    }
}
