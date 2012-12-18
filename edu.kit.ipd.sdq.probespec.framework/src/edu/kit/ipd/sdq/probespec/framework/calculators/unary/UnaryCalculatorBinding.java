package edu.kit.ipd.sdq.probespec.framework.calculators.unary;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.IBlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.IBlackboardWriter;
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

    public void bind(Probe<IN> inProbe) {
        // setup binding
        blackboard.addMeasurementListener(inListener, inProbe);
        calculator.setupBinding(inProbe);

        // setup blackboard reader
        IBlackboardReader<IN, T> reader = blackboard.getReader(inProbe);
        calculator.setupBlackboardReader(reader);

        // setup blackboard writer
        IBlackboardWriter<OUT> writer = blackboard.getWriter(calculator.getOutputProbe());
        calculator.setupBlackboardWriter(writer);

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
            calculator.calculate(probe, contexts);
        }

        @Override
        public Class<IN> getGenericType() {
            return calculator.getInClass();
        }

    }

}
