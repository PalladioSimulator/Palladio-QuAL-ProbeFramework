package edu.kit.ipd.sdq.probespec.framework.calculators.unary;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.ProbeMeasurementsProxy;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardConsumer;
import edu.kit.ipd.sdq.probespec.framework.calculators.IBindableCalculator;

public class BindableUnaryCalculator<IN, OUT, T> implements IBindableCalculator {

    private static final Logger logger = Logger.getLogger(BindableUnaryCalculator.class);

    private IBlackboard<T> blackboard;

    private IBlackboardConsumer<IN, T> inListener;

    private IUnaryCalculator<IN, OUT, T> calculator;

    private boolean isBound;

    public BindableUnaryCalculator(IUnaryCalculator<IN, OUT, T> calculator, IBlackboard<T> blackboard) {
        this.blackboard = blackboard;
        this.calculator = calculator;
        this.inListener = new ProbeListener();
    }

    public void bind(Probe<IN> sourceProbe) {
        blackboard.addMeasurementListener(inListener, sourceProbe);
        isBound = true;
    }

    @Override
    public boolean isBound() {
        return isBound;
    }

    @Override
    public void unbind() {
        if (isBound) {
            blackboard.removeMeasurementListener(inListener);
            isBound = false;
        } else {
            logger.warn("Tried to unbind a calculator which has not been bound before: " + calculator);
        }
    }

    @Override
    public String toString() {
        return calculator.toString();
    }

    protected class ProbeListener implements IBlackboardConsumer<IN, T> {

        private ProbeMeasurementsProxy<IN, T> blackboard;

        @Override
        public void setBlackboardProxy(ProbeMeasurementsProxy<IN, T> blackboard) {
            this.blackboard = blackboard;
            calculator.setMeasurementsProxy(blackboard);
        }
        
        @Override
        public void measurementArrived(IMeasurementContext... contexts) {
            OUT result = calculator.calculate();
            if (result != null) {
                blackboard.addMeasurement(result, calculator.getOutputProbe());
            }
        }

        @Override
        public Class<IN> getGenericType() {
            return calculator.getInClass();
        }

    }

}
