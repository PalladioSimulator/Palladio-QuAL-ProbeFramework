package edu.kit.ipd.sdq.probespec.framework.calculators.unary.binding;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.framework.DerivedProbe;
import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.IBlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.IBlackboardWriter;
import edu.kit.ipd.sdq.probespec.framework.calculators.ICalculatorBinding;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.IUnaryCalculator;

public class UnaryCalculatorBinding<IN, OUT, T> implements ICalculatorBinding, IUnaryUnboundCalculator<IN, OUT>,
        IUnaryPartiallyBoundCalculator<OUT>, IUnaryBoundCalculator {

    private static final Logger logger = Logger.getLogger(UnaryCalculatorBinding.class);

    private IBlackboard<T> blackboard;

    private IBlackboardListener<IN, T> inListener;

    private IUnaryCalculator<IN, OUT, T> calculator;

    private boolean isInputBound;

    private boolean isOutputBound;

    public UnaryCalculatorBinding(IUnaryCalculator<IN, OUT, T> calculator, IBlackboard<T> blackboard) {
        this.blackboard = blackboard;
        this.calculator = calculator;
        this.inListener = new ProbeListener();
    }

    public IUnaryBoundCalculator bindOutput(DerivedProbe<OUT> outProbe) {
        // setup blackboard writer
        IBlackboardWriter<OUT> writer = blackboard.getWriter(outProbe);
        calculator.setupBlackboardWriter(writer);

        isOutputBound = true;

        return this;
    }

    public IUnaryPartiallyBoundCalculator<OUT> bindInput(Probe<IN> inProbe) {
        if (inProbe == null) {
            throw new IllegalArgumentException("Cannot bind calculator to probe because it is null.");
        }

        // setup binding
        blackboard.addMeasurementListener(inListener, inProbe);
        calculator.setupBinding(inProbe);

        // setup blackboard reader
        IBlackboardReader<IN, T> reader = blackboard.getReader(inProbe);
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
