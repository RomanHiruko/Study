package Json;

import com.google.gson.JsonObject;

public class ContrAgent {
    private final JsonObject contrAgentObject = new JsonObject();

    public ContrAgent addContrAgent(Agents agents) {
        contrAgentObject.addProperty("AgentKPP", agents.getAgentKPP());
        contrAgentObject.addProperty("AgentINN", agents.getAgentINN());
        contrAgentObject.addProperty("BranchCode", agents.getBranchCode());
        return this;
    }

    public ContrAgent addRegionIdCalc(Agents regionIdCalc) {
        contrAgentObject.addProperty("RegionIdCalc", regionIdCalc.getRegionIdCalc());
        return this;
    }

    public ContrAgent addSkName(Agents skName) {
        contrAgentObject.addProperty("SkName", skName.getSkName());
        return this;
    }

    public JsonObject build() {
        contrAgentObject.addProperty("RegionIdCalc", "77");
        contrAgentObject.addProperty("SkName", "RGSRU");
        return contrAgentObject;
    }
}
