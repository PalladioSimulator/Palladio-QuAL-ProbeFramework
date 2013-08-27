package edu.kit.ipd.sdq.probespec.framework.calculators.unary;

import edu.kit.ipd.sdq.probespec.framework.calculators.AbstractCalculator;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public abstract class AbstractUnaryCalculator<IN, OUT, T> extends AbstractCalculator<OUT> implements
        IUnaryCalculator<IN, OUT, T> {

    private Class<IN> inClass;

    private Class<OUT> outClass;
    
    protected Probe<IN> inProbe;

    public AbstractUnaryCalculator(Class<IN> inClass, Class<OUT> outClass) {
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
