package edu.kit.ipd.sdq.probespec.framework.blackboard.context;

import java.util.List;

public interface IMeasurementContext {

    public String getId();
    
    public IMeasurementContext getParent();
    
    public IMeasurementContext getRoot();

    public boolean isRoot();
    
    public List<IMeasurementContext> getChildren();
    
}
