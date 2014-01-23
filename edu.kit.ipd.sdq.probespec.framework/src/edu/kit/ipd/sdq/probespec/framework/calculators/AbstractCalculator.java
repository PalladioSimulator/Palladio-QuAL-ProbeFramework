package edu.kit.ipd.sdq.probespec.framework.calculators;

import java.util.HashMap;
import java.util.Map;

import edu.kit.ipd.sdq.probespec.framework.Calculator;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.BlackboardWriter;

public abstract class AbstractCalculator<OUT> implements Calculator<OUT> {

    protected BlackboardWriter<OUT> outWriter;

    protected Map<String, String> parameters;

    public AbstractCalculator() {
        this.parameters = new HashMap<String, String>();
    }

    @Override
    public void setupBlackboardWriter(BlackboardWriter<OUT> writer) {
        this.outWriter = writer;
    }

    protected void setParameter(String parameterName, String value) {
        parameters.put(parameterName, value);
    }

    @Override
    public String getParameter(String parameterName) {
        return parameters.get(parameterName);
    }

    @Override
    public Map<String, String> getParameters() {
        return parameters;
    }

}
