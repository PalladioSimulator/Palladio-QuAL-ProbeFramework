package edu.kit.ipd.sdq.probespec.framework.calculators.unary;

import edu.kit.ipd.sdq.probespec.framework.blackboard.ProbeMeasurementsProxy;
import edu.kit.ipd.sdq.probespec.framework.calculators.ICalculator;

public interface IUnaryCalculator<IN, OUT, T> extends ICalculator<OUT> {

    public void setMeasurementsProxy(ProbeMeasurementsProxy<IN, T> blackboard);
    
    public OUT calculate();
    
    public Class<IN> getInClass();

    public Class<OUT> getOutClass();

}
