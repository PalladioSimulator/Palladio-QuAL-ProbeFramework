package edu.kit.ipd.sdq.probespec.framework.calculators.unary.binding;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.framework.Measurement;
import edu.kit.ipd.sdq.probespec.framework.MeasurementListener;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Blackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.BlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.BlackboardWriter;
import edu.kit.ipd.sdq.probespec.framework.calculators.CalculatorBinding;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.UnaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public class UnaryCalculatorBinding<IN, OUT, T> implements CalculatorBinding, UnaryUnboundCalculator<IN, OUT>,
        UnaryPartiallyBoundCalculator<OUT>, UnaryBoundCalculator {

    private static final Logger logger = Logger.getLogger(UnaryCalculatorBinding.class);

    private Blackboard<T> blackboard;

    private MeasurementListener<IN, T> inListener;

    private UnaryCalculator<IN, OUT, T> calculator;

    private boolean isInputBound;

    private boolean isOutputBound;

    public UnaryCalculatorBinding(UnaryCalculator<IN, OUT, T> calculator, Blackboard<T> blackboard) {
        this.blackboard = blackboard;
        this.calculator = calculator;
        this.inListener = new ProbeListener();
    }

    public UnaryBoundCalculator bindOutput(Probe<OUT> outProbe) {
        // setup blackboard writer
        BlackboardWriter<OUT> writer = blackboard.getWriter(outProbe);
        calculator.setupBlackboardWriter(writer);

        isOutputBound = true;

        return this;
    }

    public UnaryPartiallyBoundCalculator<OUT> bindInput(Probe<IN> inProbe) {
        if (inProbe == null) {
            throw new IllegalArgumentException("Cannot bind calculator to probe because it is null.");
        }

        // setup binding
        blackboard.addMeasurementListener(inListener, inProbe);
        calculator.setupBinding(inProbe);

        // setup blackboard reader
        BlackboardReader<IN, T> reader = blackboard.getReader(inProbe);
        calculator.setupBlackboardReader(reader);

        isInputBound = true;

        return this;
    }

    @Override
    public boolean isBound() {
        return isInputBound && isOutputBound;
    }

    @Override
    public void unbind() {
        if (isInputBound) {
            blackboard.removeMeasurementListener(inListener);
            isInputBound = false;
        } else {
            logger.warn("Tried to unbind a calculator which has not been bound before: " + calculator);
        }
    }

    @Override
    public String toString() {
        return calculator.toString();
    }

    protected class ProbeListener implements MeasurementListener<IN, T> {

        @Override
        public void measurementArrived(Measurement<IN, T> measurement, Probe<IN> probe, MeasurementContext... contexts) {
            calculator.calculate(probe, contexts);
        }

        @Override
        public Class<IN> getGenericType() {
            return calculator.getInClass();
        }

    }

}
