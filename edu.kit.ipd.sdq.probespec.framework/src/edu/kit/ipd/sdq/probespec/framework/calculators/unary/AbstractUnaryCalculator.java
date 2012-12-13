package edu.kit.ipd.sdq.probespec.framework.calculators.unary;

import edu.kit.ipd.sdq.probespec.DerivedProbe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.ProbeMeasurementsProxy;
import edu.kit.ipd.sdq.probespec.framework.calculators.AbstractCalculator;

public abstract class AbstractUnaryCalculator<IN, OUT, T> extends AbstractCalculator<OUT> implements
        IUnaryCalculator<IN, OUT, T> {

    private Class<IN> inClass;

    private Class<OUT> outClass;
    
    private ProbeMeasurementsProxy<IN, T> proxy;

    public AbstractUnaryCalculator(DerivedProbe<OUT> outputProbe, Class<IN> inClass, Class<OUT> outClass) {
        super(outputProbe);
        this.inClass = inClass;
        this.outClass = outClass;
        
    }    

    @Override
    public OUT calculate() {
        return calculate(proxy);
    }
    
    public abstract OUT calculate(ProbeMeasurementsProxy<IN, T> proxy);
    
    @Override
    public Class<IN> getInClass() {
        return inClass;
    }

    @Override
    public Class<OUT> getOutClass() {
        return outClass;
    }

    @Override
    public void setMeasurementsProxy(ProbeMeasurementsProxy<IN, T> proxy) {
        this.proxy = proxy;
    }

}
