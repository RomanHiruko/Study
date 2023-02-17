package Json;

import com.google.gson.JsonObject;

public class BusinessData {

    private final JsonObject businessDataObject = new JsonObject();
    private final ContrAgent contrAgent = new ContrAgent();
    private final Contract contract = new Contract();
    private final Persons persons = new Persons();

    public BusinessData addContrAgent() {
        contrAgent.addContrAgent(Agents.CONTRAGENT);
        JsonObject contrAgentObject = contrAgent.build();
        businessDataObject.add("ContrAgent", contrAgentObject);
        return this;
    }

    public BusinessData addContract() {
        contract.addHeader();
        JsonObject contractObject = contract.build();
        businessDataObject.add("Contract", contractObject);
        return this;
    }

    public BusinessData addPerson() {
        contract.addPersons();
        JsonObject contractObject = contract.build();
        businessDataObject.add("Contract", contractObject);
        return this;
    }

    public BusinessData addBaseProductName(String baseProductName){
        businessDataObject.addProperty("BaseProductName", baseProductName);
        return this;
    }

    public JsonObject build() {
        businessDataObject.addProperty("BaseProductName", "MKASKO");
        return businessDataObject;
    }
}
