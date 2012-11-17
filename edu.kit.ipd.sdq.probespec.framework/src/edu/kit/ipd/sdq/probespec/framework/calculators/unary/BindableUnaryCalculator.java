package edu.kit.ipd.sdq.probespec.framework.calculators.unary;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;

public class BindableUnaryCalculator<IN, OUT, T> {

    private IBlackboard<T> blackboard; 
    
    private IBlackboardListener<IN, T> inListener;

    private IUnaryCalculator<IN, OUT, T> calculator;

    public BindableUnaryCalculator(IUnaryCalculator<IN, OUT, T> calculator, IBlackboard<T> blackboard) {
        this.blackboard = blackboard;
        this.calculator = calculator;
        this.inListener = new ProbeListener();
    }

    public void bind(Probe<IN> sourceProbe) {
        blackboard.addMeasurementListener(inListener, sourceProbe);
    }

    public void unbind(IBlackboard<T> blackboard) {
        blackboard.removeMeasurementListener(inListener);
    }

    protected class ProbeListener implements IBlackboardListener<IN, T> {

        @Override
        public void measurementArrived(IBlackboard<T> blackboard, Measurement<IN, T> measurement, Probe<IN> probe,
                IMeasurementContext... contexts) {
            calculator.measurementArrived(measurement, probe, blackboard);
        }

        @Override
        public Class<IN> getGenericType() {
            return calculator.getInClass();
        }

    }

}
