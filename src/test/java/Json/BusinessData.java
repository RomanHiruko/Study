package Json;

import com.google.gson.JsonObject;

public class BusinessData {
    private final JsonObject businessDataObject = new JsonObject();

    public BusinessData addContrAgent() {
        ContrAgent contrAgent = new ContrAgent();
        JsonObject contrAgentObject = contrAgent
                .addContrAgent(Agents.CONTRAGENT)
                .build();
        businessDataObject.add("ContrAgent", contrAgentObject);
        return this;
    }

    public BusinessData addContract() {
        Contract contract = new Contract();
        JsonObject contractObject = contract
                .addHeader()
                .addPersons()
                .build();
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
