package edu.kit.ipd.sdq.probespec.framework.calculators.binary;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.ProbeSpecContext;
import edu.kit.ipd.sdq.probespec.framework.calculators.AbstractCalculator;

public abstract class AbstractBinaryCalculator<IN1, IN2, OUT> extends AbstractCalculator<OUT> implements
        IBinaryCalculator<IN1, IN2, OUT> {

    private IBlackboardListener<IN1> listener1;

    private IBlackboardListener<IN2> listener2;

    private Class<IN1> in1Class;

    private Class<IN2> in2Class;

    private Class<OUT> outClass;

    public AbstractBinaryCalculator(ProbeSpecContext ctx, Class<IN1> in1Class, Class<IN2> in2Class, Class<OUT> outClass) {
        super(ctx);
        this.in1Class = in1Class;
        this.in2Class = in2Class;
        this.outClass = outClass;
        this.listener1 = new Probe1Listener();
        this.listener2 = new Probe2Listener();
    }

    public abstract void firstMeasurementArrived(IN1 measurement, Probe<IN1> probe);

    public abstract void secondMeasurementArrived(IN2 measurement, Probe<IN2> probe);

    @Override
    public IBlackboardListener<IN1> getFirstListener() {
        return listener1;
    }

    @Override
    public IBlackboardListener<IN2> getSecondListener() {
        return listener2;
    }

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

    protected class Probe1Listener implements IBlackboardListener<IN1> {

        @Override
        public Class<IN1> getGenericType() {
            return getIn1Class();
        }

        @Override
        public void measurementArrived(IN1 measurement, Probe<IN1> probe) {
            firstMeasurementArrived(measurement, probe);
        }

    }

    protected class Probe2Listener implements IBlackboardListener<IN2> {

        @Override
        public Class<IN2> getGenericType() {
            return getIn2Class();
        }

        @Override
        public void measurementArrived(IN2 measurement, Probe<IN2> probe) {
            secondMeasurementArrived(measurement, probe);
        }

    }

}
