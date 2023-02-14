package Json;

import com.google.gson.JsonObject;

public class ContrAgent {
    private final JsonObject contrAgentObject = new JsonObject();
    private final Agents agents = Agents.CONTRAGENT;

    public ContrAgent getBranchCode() {
        contrAgentObject.addProperty("BranchCode", agents.getBranchCode());
        return this;
    }

    public ContrAgent getAgentINN() {
        contrAgentObject.addProperty("AgentINN", agents.getAgentINN());
        return this;
    }

    public ContrAgent getAgentKPP() {
        contrAgentObject.addProperty("AgentKPP", agents.getAgentKPP());
        return this;
    }

    public ContrAgent getRegionIdCalc() {
        contrAgentObject.addProperty("RegionIdCalc", agents.getRegionIdCalc());
        return this;
    }

    public ContrAgent getSkName() {
        contrAgentObject.addProperty("SkName", agents.getSkName());
        return this;
    }

    public JsonObject build() {
        return contrAgentObject;
    }
}
