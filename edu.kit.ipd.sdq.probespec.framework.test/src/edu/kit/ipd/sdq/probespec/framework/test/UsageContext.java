package edu.kit.ipd.sdq.probespec.framework.test;

import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;

public class UsageContext implements IMeasurementContext {

    private String userName;

    public UsageContext(String name) {
        this.userName = name;
    }

    @Override
    public String getId() {
        return userName;
    }

}
