package edu.kit.ipd.sdq.probespec.framework.calculators.binary;

import edu.kit.ipd.sdq.probespec.DerivedProbe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.ProbeMeasurementsProxy;
import edu.kit.ipd.sdq.probespec.framework.calculators.AbstractCalculator;

public abstract class AbstractBinaryCalculator<IN1, IN2, OUT, T> extends AbstractCalculator<OUT> implements
        IBinaryCalculator<IN1, IN2, OUT, T> {

    private Class<IN1> in1Class;

    private Class<IN2> in2Class;

    private Class<OUT> outClass;
    
    private ProbeMeasurementsProxy<IN1, T> proxy1;
    
    private ProbeMeasurementsProxy<IN2, T> proxy2;

    public AbstractBinaryCalculator(DerivedProbe<OUT> outputProbe, Class<IN1> in1Class, Class<IN2> in2Class,
            Class<OUT> outClass) {
        super(outputProbe);
        this.in1Class = in1Class;
        this.in2Class = in2Class;
        this.outClass = outClass;
    }

    @Override
    public OUT calculate() {
        return calculate(proxy1, proxy2);
    }
    
    public abstract OUT calculate(ProbeMeasurementsProxy<IN1, T> proxy1, ProbeMeasurementsProxy<IN2, T> proxy2);

    @Override
    public Class<IN1> getIn1Class() {
        return in1Class;
    }

    @Override
    public Class<IN2> getIn2Class() {
        return in2Class;
    }

    @Override
    public Class<OUT> getOutClass() {
        return outClass;
    }
    
    @Override
    public void setFirstMeasurementsProxy(ProbeMeasurementsProxy<IN1, T> proxy) {
        proxy1 = proxy;
    }

    @Override
    public void setSecondMeasurementsProxy(ProbeMeasurementsProxy<IN2, T> proxy) {
        proxy2 = proxy;
    }

}
