package org.example;

import com.google.gson.JsonObject;

import java.util.UUID;

public class SearchPaymentBuilder {
    private JsonObject contrAgentObject = new JsonObject();
    private JsonObject searchPaymentRequestObject = new JsonObject();

    private String skName;
    private String expPaymentId;
    private String submissionNumber;

    public SearchPaymentBuilder setSkName(String skName) {
        this.skName = skName;
        return this;
    }

    public SearchPaymentBuilder setExpPaymentId(String expPaymentId) {
        this.expPaymentId = expPaymentId;
        return this;
    }

    public SearchPaymentBuilder setSubmissionNumber(String submissionNumber) {
        this.submissionNumber = submissionNumber;
        return this;
    }

    public SearchPaymentBuilder searchPayment() {
        return new SearchPaymentBuilder();
    }

    public String build() {
        contrAgentObject.addProperty("SkName", skName);
        JsonObject businessDataObject = new JsonObject();
        businessDataObject.add("ContrAgent", contrAgentObject);
        businessDataObject.add("SearchPaymentRequest", searchPaymentRequestObject);

        JsonObject requestBodyObject = new JsonObject();
        requestBodyObject.add("BusinessData", businessDataObject);
        requestBodyObject.add("TechData", getRandomTechData());
        return requestBodyObject.toString();
    }

    public SearchPaymentBuilder setAgent(Users user) {
        contrAgentObject.addProperty("BranchCode", user.getBranchCode());
        contrAgentObject.addProperty("AgentLNR", user.getLnr());
        return this;
    }

    public SearchPaymentBuilder setPartner(Partner partner) {
        contrAgentObject.addProperty("BranchCode", partner.getBranchCode());
        contrAgentObject.addProperty("AgentINN", partner.getINN());
        contrAgentObject.addProperty("AgentKPP", partner.getKPP());
        return this;
    }

    public SearchPaymentBuilder setRegionIdCal(String regionIdCal) {
        contrAgentObject.addProperty("RegionIdCalc", regionIdCal);
        return this;
    }

    public SearchPaymentBuilder addPaymentData() {
        JsonObject contractObject = new JsonObject();
        contractObject.addProperty("ContractId", submissionNumber);
        searchPaymentRequestObject.add("Contract", contractObject);
        searchPaymentRequestObject.addProperty("ExpPaymentId", expPaymentId);
        searchPaymentRequestObject.addProperty("PaymentScheduleId", "1");
        return this;
    }


    public static JsonObject getRandomTechData() {
        JsonObject techDataObject = new JsonObject();
        techDataObject.addProperty("ActionId", UUID.randomUUID().toString());
        techDataObject.addProperty("CorrelationId", UUID.randomUUID().toString());
        return techDataObject;
    }
}

enum Users {
    AT_AGENT("ATAgent", "4444450", 45207600, "Достоевский Федор Михайлович"),
    AT_CANCELLATION_APPROVER("ATCancellationApprover", "22230030", 0, "Самойлов Илья Сергеевич");
    String userName;
    String lnr;
    long branchCode;
    String fullName;

    Users(String userName, String lnr, long branchCode, String fullName) {
        this.userName = userName;
        this.lnr = lnr;
        this.branchCode = branchCode;
        this.fullName = fullName;
    }

    public String getLnrWithFullName() {
        return String.format("%s - %s", lnr, fullName);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLnr() {
        return lnr;
    }

    public void setLnr(String lnr) {
        this.lnr = lnr;
    }

    public long getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(long branchCode) {
        this.branchCode = branchCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

enum Partner {
    PARTNER(17714041, "7707067683", "502701001");
    long branchCode;
    String agentINN;
    String agentKPP;

    Partner(long branchCode, String agentINN, String agentKPP) {
        this.branchCode = branchCode;
        this.agentINN = agentINN;
        this.agentKPP = agentKPP;
    }

    public long getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(long branchCode) {
        this.branchCode = branchCode;
    }

    public String getINN() {
        return agentINN;
    }

    public void setINN(String agentINN) {
        this.agentINN = agentINN;
    }

    public String getKPP() {
        return agentKPP;
    }

    public void setKPP(String agentKPP) {
        this.agentKPP = agentKPP;
    }
}
