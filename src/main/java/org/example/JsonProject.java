package org.example;

import com.github.javafaker.Faker;
import com.google.gson.JsonObject;

import java.util.Random;
import java.util.UUID;

public class JsonProject {
    public static void main(String[] args) {
        System.out.println(new JsonProject().setAgent(Agents.CONTRAGENT).build());
    }

    private JsonObject contrAgentObject = new JsonObject();
    private JsonObject contractObject = new JsonObject();

    public String build() {
        JsonObject businessDataObject = new JsonObject();
        businessDataObject.addProperty("BaseProductName", "MKASKO");
        businessDataObject.add("ContrAgent", contrAgentObject);
        businessDataObject.add("Contract", contractObject);

        JsonObject requestBodyObject = new JsonObject();
        requestBodyObject.add("TechData", getRandomTechData());
        requestBodyObject.add("BusinessData", businessDataObject);
        return requestBodyObject.toString();
    }

    public JsonProject setAgent(Agents contragent) {
        contrAgentObject.addProperty("AgentKPP", contragent.getAgentKPP());
        contrAgentObject.addProperty("AgentINN", contragent.getAgentINN());
        contrAgentObject.addProperty("BranchCode", contragent.getBranchCode());
        contrAgentObject.addProperty("RegionIdCalc", contragent.getRegionIdCalc());
        contrAgentObject.addProperty("SkName", contragent.getSkName());
        return this;
    }

    public static JsonObject getRandomTechData() {
        JsonObject techDataObject = new JsonObject();
        techDataObject.addProperty("ActionId", UUID.randomUUID().toString());
        techDataObject.addProperty("CorrelationId", UUID.randomUUID().toString());
        return techDataObject;
    }
}

enum CONTACTS {

    CONTACT(
            Faker.instance().date().birthday().toInstant().toString().subSequence(0, 10).toString(),
            "M",
            true,
            Faker.instance().name().firstName(),
            Faker.instance().name().lastName(),
            Faker.instance().name().firstName()

    );

    private String birthday;
    private String gender;
    private boolean isResident;
    private String firstNameRus;
    private String lastNameRus;
    private String middleNameRus;

    CONTACTS(String birthday, String gender, boolean isResident, String firstNameRus, String lastNameRus, String middleNameRus) {
        this.birthday = birthday;
        this.gender = gender;
        this.isResident = isResident;
        this.firstNameRus = firstNameRus;
        this.lastNameRus = lastNameRus;
        this.middleNameRus = middleNameRus;
    }

    @Override
    public String toString() {
        return "PERSONS{" +
                "birthday='" + birthday + '\'' +
                ", gender='" + gender + '\'' +
                ", isResident=" + isResident +
                ", firstNameRus='" + firstNameRus + '\'' +
                ", lastNameRus='" + lastNameRus + '\'' +
                ", middleNameRus='" + middleNameRus + '\'' +
                '}';
    }

    public String getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public boolean isResident() {
        return isResident;
    }

    public String getFirstNameRus() {
        return firstNameRus;
    }

    public String getLastNameRus() {
        return lastNameRus;
    }

    public String getMiddleNameRus() {
        return middleNameRus;
    }
}

enum Agents {
    CONTRAGENT(
            (new Random().nextInt(10_000_000, 99_999_999)),
            (new Random().nextInt(10_000_000, 99_999_999) + ""),
            (new Random().nextInt(10_000_000, 99_999_999) + ""),
            (new Random().nextInt(10_000_000, 99_999_999) + ""),
            (new Random().nextInt(999) + ""),
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


