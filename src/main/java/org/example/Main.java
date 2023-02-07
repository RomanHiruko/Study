package org.example;

import com.google.gson.*;

public class Main {
    public static void main(String[] args) {
        String agentKPP = "502701001";
        String agentINN = "7707067683";
        int branchCode = 17714041;
        String regionIdCalc = "77";
        String skName = "RGSRU";

        String contractId = "000006073";

        ContrAgentBuilder contrAgentBuilder = new ContrAgentBuilder()
                .setAgentKPP(agentKPP)
                .setAgentINN(agentINN)
                .setBranchCode(branchCode)
                .setRegionIdCalc(regionIdCalc)
                .setSkName(skName);
        ContrAgent contrAgent = contrAgentBuilder.build();

        SubmissionServiceRequestBuilder submissionServiceRequestBuilder = new SubmissionServiceRequestBuilder()
                .setContractId(contractId);

        SubmissionServiceRequest submissionServiceRequest = submissionServiceRequestBuilder.build();

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("ContrAgent", JsonParser.parseString(gson.toJson(contrAgent)));
        jsonObject.add("SubmissionServiceRequest", JsonParser.parseString(gson.toJson(submissionServiceRequest)));
        JsonObject jsonObject1 = new JsonObject();
        gson.toJson(jsonObject);

        jsonObject1.add("BusinessData", JsonParser.parseString(gson.toJson(jsonObject)));

        System.out.println(jsonObject1);
    }
}

class ContrAgent {
    Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
    private String agentKPP;
    private String agentINN;
    private int branchCode;
    private String regionIdCalc;
    private String skName;

    public ContrAgent(String agentKPP, String agentINN, int branchCode, String regionIdCalc, String skName) {
        this.agentKPP = agentKPP;
        this.agentINN = agentINN;
        this.branchCode = branchCode;
        this.regionIdCalc = regionIdCalc;
        this.skName = skName;
    }

    public String getAgentKPP() {
        return agentKPP;
    }

    public void setAgentKPP(String agentKPP) {
        this.agentKPP = agentKPP;
    }

    public String getAgentINN() {
        return agentINN;
    }

    public void setAgentINN(String agentINN) {
        this.agentINN = agentINN;
    }

    public int getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(int branchCode) {
        this.branchCode = branchCode;
    }

    public String getRegionIdCalc() {
        return regionIdCalc;
    }

    public void setRegionIdCalc(String regionIdCalc) {
        this.regionIdCalc = regionIdCalc;
    }

    public String getSkName() {
        return skName;
    }

    public void setSkName(String skName) {
        this.skName = skName;
    }

    public ContrAgent() {
    }
}

class ContrAgentBuilder {
    private String agentKPP;
    private String agentINN;
    private int branchCode;
    private String regionIdCalc;
    private String skName;

    public ContrAgentBuilder() {
    }

    public ContrAgentBuilder setAgentKPP(String agentKPP) {
        this.agentKPP = agentKPP;
        return this;
    }

    public ContrAgentBuilder setAgentINN(String agentINN) {
        this.agentINN = agentINN;
        return this;
    }

    public ContrAgentBuilder setBranchCode(int branchCode) {
        this.branchCode = branchCode;
        return this;
    }

    public ContrAgentBuilder setRegionIdCalc(String regionIdCalc) {
        this.regionIdCalc = regionIdCalc;
        return this;
    }

    public ContrAgentBuilder setSkName(String skName) {
        this.skName = skName;
        return this;
    }

    public ContrAgent build() {
        ContrAgent contrAgent = new ContrAgent();
        contrAgent.setAgentINN(this.agentINN);
        contrAgent.setAgentKPP(this.agentKPP);
        contrAgent.setBranchCode(this.branchCode);
        contrAgent.setSkName(this.skName);
        contrAgent.setRegionIdCalc(this.regionIdCalc);
        return contrAgent;
    }
}

class SubmissionServiceRequest {
    private String contractId;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public SubmissionServiceRequest() {
    }

    public SubmissionServiceRequest(String contractId) {
        this.contractId = contractId;
    }
}

class SubmissionServiceRequestBuilder {
    private String contractId;

    public SubmissionServiceRequestBuilder() {
    }

    public SubmissionServiceRequestBuilder setContractId(String contractId) {
        this.contractId = contractId;
        return this;
    }

    public SubmissionServiceRequest build() {
        SubmissionServiceRequest submissionServiceRequest = new SubmissionServiceRequest();
        submissionServiceRequest.setContractId(this.contractId);
        return submissionServiceRequest;
    }
}

