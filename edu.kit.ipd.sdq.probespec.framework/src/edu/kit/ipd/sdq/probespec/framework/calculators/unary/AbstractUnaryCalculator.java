package edu.kit.ipd.sdq.probespec.framework.calculators.unary;

import edu.kit.ipd.sdq.probespec.DerivedProbe;
import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.calculators.AbstractCalculator;

public abstract class AbstractUnaryCalculator<IN, OUT, T> extends AbstractCalculator<OUT> implements
        IUnaryCalculator<IN, OUT, T> {

    private Class<IN> inClass;

    private Class<OUT> outClass;
    
    protected Probe<IN> inProbe;

    public AbstractUnaryCalculator(DerivedProbe<OUT> outputProbe, Class<IN> inClass, Class<OUT> outClass) {
        super(outputProbe);
        this.inClass = inClass;
        this.outClass = outClass;
    }
    
    @Override
    public void setupBinding(Probe<IN> inProbe) {
        this.inProbe = inProbe;
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
