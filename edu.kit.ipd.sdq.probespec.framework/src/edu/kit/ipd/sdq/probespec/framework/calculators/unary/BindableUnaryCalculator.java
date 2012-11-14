package edu.kit.ipd.sdq.probespec.framework.calculators.unary;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;

public class BindableUnaryCalculator<IN, OUT> {

    private IBlackboardListener<IN> inListener;

    private IUnaryCalculator<IN, OUT> calculator;

    public BindableUnaryCalculator(IUnaryCalculator<IN, OUT> calculator) {
        this.calculator = calculator;
        this.inListener = new ProbeListener();
    }

    public void bind(IBlackboard blackboard, Probe<IN> sourceProbe) {
        blackboard.addMeasurementListener(inListener, sourceProbe);
    }

    public void unbind(IBlackboard blackboard) {
        blackboard.removeMeasurementListener(inListener);
    }

    protected class ProbeListener implements IBlackboardListener<IN> {

        @Override
        public void measurementArrived(IBlackboard blackboard, IN measurement, Probe<IN> probe, IMeasurementContext... contexts) {
            calculator.measurementArrived(measurement, probe, blackboard);
        }

        @Override
        public Class<IN> getGenericType() {
            return calculator.getInClass();
        }

    }

}
