package Json;

import com.google.gson.JsonObject;

import java.util.UUID;

public class TechData {
    JsonObject techDataObject = new JsonObject();

    public JsonObject build() {
        techDataObject.addProperty("ActionId", UUID.randomUUID().toString());
        techDataObject.addProperty("CorrelationId", UUID.randomUUID().toString());
        return techDataObject;
    }
}
