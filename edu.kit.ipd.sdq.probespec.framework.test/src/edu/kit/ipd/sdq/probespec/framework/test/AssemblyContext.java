package edu.kit.ipd.sdq.probespec.framework.test;

import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;

public class AssemblyContext implements IMeasurementContext {
    
    private String assemblyName;
    
    public AssemblyContext(String name) {
        this.assemblyName = name;
    }

    @Override
    public String getId() {
        return assemblyName;
    }
    
}
