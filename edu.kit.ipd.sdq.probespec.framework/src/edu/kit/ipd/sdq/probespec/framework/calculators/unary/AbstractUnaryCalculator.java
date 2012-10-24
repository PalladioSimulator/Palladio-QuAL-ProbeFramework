package edu.kit.ipd.sdq.probespec.framework.calculators.unary;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.ProbeSpecContext;
import edu.kit.ipd.sdq.probespec.framework.calculators.AbstractCalculator;

public abstract class AbstractUnaryCalculator<IN, OUT> extends AbstractCalculator<OUT> implements
        IUnaryCalculator<IN, OUT> {

    private IBlackboardListener<IN> listener;

    private Class<IN> inClass;

    private Class<OUT> outClass;

    public AbstractUnaryCalculator(ProbeSpecContext ctx, Class<IN> inClass, Class<OUT> outClass) {
        super(ctx);
        this.inClass = inClass;
        this.outClass = outClass;
        this.listener = new ProbeListener();
    }

    @Override
    public IBlackboardListener<IN> getListener() {
        return listener;
    }

    @Override
    public Class<IN> getInClass() {
        return inClass;
    }

    @Override
    public Class<OUT> getOutClass() {
        return outClass;
    }

    public abstract void singleMeasurementArrived(IN measurement, Probe<IN> probe);

    protected class ProbeListener implements IBlackboardListener<IN> {

        @Override
        public Class<IN> getGenericType() {
            return getInClass();
        }

        @Override
        public void measurementArrived(IN measurement, Probe<IN> probe) {
            singleMeasurementArrived(measurement, probe);
        }

    }

}
