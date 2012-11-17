package edu.kit.ipd.sdq.probespec.framework.calculators.unary;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;

public class BindableUnaryCalculator<IN, OUT, U> {

    private IBlackboardListener<IN, U> inListener;

    private IUnaryCalculator<IN, OUT, U> calculator;

    public BindableUnaryCalculator(IUnaryCalculator<IN, OUT, U> calculator) {
        this.calculator = calculator;
        this.inListener = new ProbeListener();
    }

    public void bind(IBlackboard<U> blackboard, Probe<IN> sourceProbe) {
        blackboard.addMeasurementListener(inListener, sourceProbe);
    }

    public void unbind(IBlackboard<U> blackboard) {
        blackboard.removeMeasurementListener(inListener);
    }

    protected class ProbeListener implements IBlackboardListener<IN, U> {

        @Override
        public void measurementArrived(IBlackboard<U> blackboard, Measurement<IN, U> measurement, Probe<IN> probe, IMeasurementContext... contexts) {
            calculator.measurementArrived(measurement, probe, blackboard);
        }

        @Override
        public Class<IN> getGenericType() {
            return calculator.getInClass();
        }

    }

}
