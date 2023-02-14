package Json;

public enum Agents {
    CONTRAGENT(
            (1),
            (1 + ""),
            (1 + ""),
            (1 + ""),
            (1 + ""),
            "RGSRU"
    );
    private final int branchCode;
    private final String agentLNR;
    private final String agentINN;
    private final String agentKPP;
    private final String regionIdCalc;
    private final String skName;

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