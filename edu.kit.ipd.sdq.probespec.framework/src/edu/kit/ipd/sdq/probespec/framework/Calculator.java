package edu.kit.ipd.sdq.probespec.framework;

import java.util.Map;

import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.BlackboardWriter;

public interface Calculator<OUT> {

    public void setupBlackboardWriter(BlackboardWriter<OUT> writer);
    
    public void calculate(Probe<?> probe, MeasurementContext... contexts);
    
    public String getParameter(String parameterName);
    
    public Map<String, String> getParameters();
    
}
