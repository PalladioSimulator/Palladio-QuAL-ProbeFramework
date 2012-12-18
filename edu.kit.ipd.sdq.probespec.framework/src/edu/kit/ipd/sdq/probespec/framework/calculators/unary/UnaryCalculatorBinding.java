package edu.kit.ipd.sdq.probespec.framework.calculators.unary;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.BlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.IBlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.calculators.ICalculatorBinding;

public class UnaryCalculatorBinding<IN, OUT, T> implements ICalculatorBinding {

    private static final Logger logger = Logger.getLogger(UnaryCalculatorBinding.class);

    private IBlackboard<T> blackboard;

    private IBlackboardListener<IN, T> inListener;

    private IUnaryCalculator<IN, OUT, T> calculator;

    private boolean isBound;

    public UnaryCalculatorBinding(IUnaryCalculator<IN, OUT, T> calculator, IBlackboard<T> blackboard) {
        this.blackboard = blackboard;
        this.calculator = calculator;
        this.inListener = new ProbeListener();
    }

    public void bind(Probe<IN> sourceProbe) {
        blackboard.addMeasurementListener(inListener, sourceProbe);
        IBlackboardReader<IN, T> reader1 = blackboard.getReader(sourceProbe);
        
        calculator.setupBlackboardAccess(reader1);
        
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

    protected class ProbeListener implements IBlackboardListener<IN, T> {
        
        @Override
        public void measurementArrived(Measurement<IN, T> measurement, Probe<IN> probe, IMeasurementContext... contexts) {
            OUT result = calculator.calculate(probe, contexts);
            if (result != null) {
                blackboard.addMeasurement(result, calculator.getOutputProbe(), contexts);
            }
        }

        @Override
        public Class<IN> getGenericType() {
            return calculator.getInClass();
        }

    }

}
