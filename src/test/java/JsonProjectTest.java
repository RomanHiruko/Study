
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class JsonProjectTest {
    private JsonObject contrAgentObject = new JsonObject();
    private JsonObject contractObject = new JsonObject();
    private JsonObject businessDataObject = new JsonObject();
    private JsonObject requestBodyObject = new JsonObject();
    private Gson gson = new Gson();
    private JsonObject cnr = new JsonObject();
    private JsonObject headerObject = new JsonObject();
    private JsonObject idObject = new JsonObject();
    JsonObject personAttributeObject = new JsonObject();

    private List<JsonObject> person = new ArrayList<>();
    private List<JsonObject> email = new ArrayList<>();
    private List<JsonObject> phone = new ArrayList<>();

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @Test
    public void whenPostJson_thenCorrect() throws IOException {
        OkHttpClient client = new OkHttpClient();
        String BASE_URL = "https://webhook.site/03a8a977-fd97-4549-9021-162dd7f2c4ef";
        String json = new JsonProjectTest().setAgent(Agents.CONTRAGENT).addContract().build();

        System.out.println(json);
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), json);

        Request request = new Request.Builder()
                .url(BASE_URL)
                .post(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();
    }

    public String build() {
        businessDataObject.addProperty("BaseProductName", "MKASKO");
        businessDataObject.add("ContrAgent", contrAgentObject);
        businessDataObject.add("Contract", contractObject);

        requestBodyObject.add("TechData", getRandomTechData());
        requestBodyObject.add("BusinessData", businessDataObject);
        return requestBodyObject.toString();
    }

    public JsonProjectTest setAgent(Agents contrAgent) {
        contrAgentObject.addProperty("AgentKPP", contrAgent.getAgentKPP());
        contrAgentObject.addProperty("AgentINN", contrAgent.getAgentINN());
        contrAgentObject.addProperty("BranchCode", contrAgent.getBranchCode());
        contrAgentObject.addProperty("RegionIdCalc", contrAgent.getRegionIdCalc());
        contrAgentObject.addProperty("SkName", contrAgent.getSkName());
        return this;
    }

    public JsonProjectTest addContract() {
        List<JsonObject> contractInContracts = new ArrayList<>();

        cnr.add("Header", addHeader());
        cnr.add("Persons", gson.toJsonTree(addPerson(person)));
        contractInContracts.add(cnr);

        JsonObject contractsObject = new JsonObject();
        contractsObject.add("Contract", gson.toJsonTree(contractInContracts));
        contractObject.add("Contracts", contractsObject);
        contractObject.addProperty("ContractsCount", 1);
        contractObject.addProperty("SchemaVersion", "1.0");
        return this;
    }

    public List<JsonObject> addPerson(List<JsonObject> person) {
        int day = new Random().nextInt(30);
        int month = new Random().nextInt(12);
        int year = new Random().nextInt(81) + 18;

        personAttributeObject.addProperty("Birthday", String.valueOf(LocalDate.now().minusYears(year).minusDays(day).minusMonths(month)));
        personAttributeObject.addProperty("Gender", "М");
        personAttributeObject.addProperty("IsResident", (new Random().nextBoolean()));
        personAttributeObject.addProperty("FirstNameRus", Faker.instance(Locale.forLanguageTag("ru")).name().firstName());
        personAttributeObject.addProperty("LastNameRus", Faker.instance(Locale.forLanguageTag("ru")).name().lastName());
        personAttributeObject.addProperty("MiddleNameRus", Faker.instance(Locale.forLanguageTag("ru")).name().firstName());
        addEmail();
        addPhone();
        person.add(personAttributeObject);
        return person;
    }

    public JsonElement addHeader() {
        headerObject.addProperty("BranchCode", String.valueOf(new Random().nextInt(1_000_000)));
        headerObject.addProperty("IsProlonged", (new Random().nextBoolean()));
        headerObject.add("ProductId", addIds("301", "SCC2", "PRODUCT_TYPE"));
        headerObject.add("EntityTypeId", addIds("1", "SCC2", "ENTITY_TYPE"));
        headerObject.addProperty("StartDate", String.valueOf(LocalDate.now().plusDays(1)));
        headerObject.addProperty("EndDate", String.valueOf(LocalDate.now().plusYears(1).minusDays(1)));
        return headerObject;
    }

    public JsonElement addIds(String id, String sysId, String type) {
        idObject.addProperty("Id", id);
        idObject.addProperty("SysId", sysId);
        idObject.addProperty("Type", type);
        return idObject;
    }

    public void addEmail() {
        JsonObject emailObject = new JsonObject();
        JsonObject emailProperties = new JsonObject();
        emailProperties.addProperty("EmailAddress", Faker.instance().bothify("????##@gmail.com"));
        emailProperties.add("EmailTypeId", addIds("1", "GWPC", "EMAIL_TYPE"));
        email.add(emailProperties);
        emailObject.add("Email", gson.toJsonTree(email));
        personAttributeObject.add("Emails", emailObject);
    }

    public void addPhone() {
        JsonObject phoneObject = new JsonObject();
        JsonObject phoneProperties = new JsonObject();
        phoneProperties.addProperty("FullNumber", Faker.instance().bothify("9#########"));
        phoneProperties.addProperty("CountryCode", Faker.instance().number().numberBetween(1, 15) + "");
        phoneProperties.add("TypeId", addIds("4", "SCC2", "PHONE_TYPE"));
        phone.add(phoneProperties);
        phoneObject.add("Phone", gson.toJsonTree(phone));
        personAttributeObject.add("Phones", phoneObject);
    }

    public static JsonObject getRandomTechData() {
        JsonObject techDataObject = new JsonObject();
        techDataObject.addProperty("ActionId", UUID.randomUUID().toString());
        techDataObject.addProperty("CorrelationId", UUID.randomUUID().toString());
        return techDataObject;
    }
}

enum Agents {
    CONTRAGENT(
            (1),
            (1 + ""),
            (1 + ""),
            (1 + ""),
            (1 + ""),
            "RGSRU"
    );
    private int branchCode;
    private String agentLNR;
    private String agentINN;
    private String agentKPP;
    private String regionIdCalc;
    private String skName;

    Agents(int branchCode, String agentLNR, String agentINN, String agentKPP, String regionIdCalc, String skName) {
        this.branchCode = branchCode;
        this.agentLNR = agentLNR;
        this.agentINN = agentINN;
        this.agentKPP = agentKPP;
        this.regionIdCalc = regionIdCalc;
        this.skName = skName;
    }

    public int getBranchCode() {
        return branchCode;
    }

    public String getAgentLNR() {
        return agentLNR;
    }

    public String getAgentINN() {
        return agentINN;
    }

    public String getAgentKPP() {
        return agentKPP;
    }

    public String getRegionIdCalc() {
        return regionIdCalc;
    }

    public String getSkName() {
        return skName;
    }
}


