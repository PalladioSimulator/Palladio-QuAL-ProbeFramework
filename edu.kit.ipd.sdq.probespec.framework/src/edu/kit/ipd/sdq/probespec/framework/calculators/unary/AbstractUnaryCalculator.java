package edu.kit.ipd.sdq.probespec.framework.calculators.unary;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.calculators.AbstractCalculator;

public abstract class AbstractUnaryCalculator<IN, OUT> extends AbstractCalculator<OUT> implements
        IUnaryCalculator<IN, OUT> {

    private Class<IN> inClass;

    private Class<OUT> outClass;

    public AbstractUnaryCalculator(Probe<OUT> boundedProbe, Class<IN> inClass, Class<OUT> outClass) {
        super(boundedProbe);
        this.inClass = inClass;
        this.outClass = outClass;
        
    }    

    @Override
    public Class<IN> getInClass() {
        return inClass;
    }

    @Override
    public Class<OUT> getOutClass() {
        return outClass;
    }

}
