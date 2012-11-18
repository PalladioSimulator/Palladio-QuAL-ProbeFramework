package edu.kit.ipd.sdq.probespec.framework.calculators.binary;

import edu.kit.ipd.sdq.probespec.framework.blackboard.ProbeMeasurementsProxy;
import edu.kit.ipd.sdq.probespec.framework.calculators.ICalculator;

public interface IBinaryCalculator<IN1, IN2, OUT, T> extends ICalculator<OUT> {
    
    public void setFirstMeasurementsProxy(ProbeMeasurementsProxy<IN1, T> proxy);
    
    public void setSecondMeasurementsProxy(ProbeMeasurementsProxy<IN2, T> proxy);
    
    public OUT calculate();

    public Class<IN1> getIn1Class();

    public Class<IN2> getIn2Class();

    public Class<OUT> getOutClass();

}
