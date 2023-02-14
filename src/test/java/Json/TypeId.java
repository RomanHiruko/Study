package Json;

import com.google.gson.JsonObject;

public class TypeId {
    public static JsonObject createId(){
        JsonObject idObject = new JsonObject();
        idObject.addProperty("Id", "1");
        idObject.addProperty("SysId", "GWPC");
        idObject.addProperty("Type", "EMAIL_TYPE");
        return idObject;
    }
}
