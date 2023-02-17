package Json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Builder {
    private final JsonObject rootObject;
    private final TechData techData;
    private final BusinessData businessData;

    public Builder() {
        rootObject = new JsonObject();
        techData = new TechData();
        businessData = new BusinessData();
    }

    public Builder setContrAgent() {
        businessData.addContrAgent();
        return this;
    }

    public Builder addContract() {
        businessData.addContract();
        return this;
    }

    public Builder addPerson() {
        businessData.addPerson();
        return this;
    }

    public String build() {
        Gson gson = new Gson();
        JsonObject techDataObject = techData.build();
        JsonObject businessDataObject = businessData.build();
        rootObject.add("TechData", techDataObject);
        rootObject.add("BusinessData", businessDataObject);
        return gson.toJson(rootObject);
    }
}