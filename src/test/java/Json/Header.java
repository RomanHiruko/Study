package Json;

import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Header {
    private JsonObject headerObject = new JsonObject();
    public JsonObject addHeader(){
        headerObject.addProperty("BranchCode", "67008130");
        headerObject.addProperty("IsProlonged", true);
        headerObject.add("ProductId", TypeId.createId());
        headerObject.add("EntityTypeId", TypeId.createId());
        headerObject.addProperty("StartDate",String.valueOf(LocalDate.now().plusDays(1)));
        headerObject.addProperty("EndDate",String.valueOf(LocalDate.now().plusYears(1).minusDays(1)));
        return headerObject;
    }
}
