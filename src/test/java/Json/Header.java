package Json;

import com.google.gson.JsonObject;

import java.time.LocalDate;

public class Header {
    private final JsonObject headerObject = new JsonObject();

    public Header addBranchCode(String branchCode) {
        headerObject.addProperty("BranchCode", branchCode);
        return this;
    }

    public Header isProlonged(boolean isProlonged) {
        headerObject.addProperty("IsProlonged", isProlonged);
        return this;
    }

    public Header addHeader() {
        headerObject.add("ProductId", TypeId.createId());
        headerObject.add("EntityTypeId", TypeId.createId());
        headerObject.addProperty("StartDate", String.valueOf(LocalDate.now().plusDays(1)));
        headerObject.addProperty("EndDate", String.valueOf(LocalDate.now().plusYears(1).minusDays(1)));
        return this;
    }

    public JsonObject build() {
        return headerObject;
    }
}
