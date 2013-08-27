package edu.kit.ipd.sdq.probespec.framework.blackboard.context;

import java.util.List;

public interface MeasurementContext {

    public String getId();
    
    public MeasurementContext getParent();
    
    public MeasurementContext getRoot();

    public boolean isRoot();
    
    public List<MeasurementContext> getChildren();
    
}
