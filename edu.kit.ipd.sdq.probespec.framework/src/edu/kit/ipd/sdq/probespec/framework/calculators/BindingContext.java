package edu.kit.ipd.sdq.probespec.framework.calculators;

import java.util.HashMap;
import java.util.Map;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.BindableBinaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.IBinaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.BindableUnaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.IUnaryCalculator;

public class BindingContext<T> {

    private IBlackboard<T> blackboard;

    private Map<IUnaryCalculator<?, ?, T>, BindableUnaryCalculator<?, ?, T>> unaryBindings;

    private Map<IBinaryCalculator<?, ?, ?, T>, BindableBinaryCalculator<?, ?, ?, T>> binaryBindings;

    public BindingContext(IBlackboard<T> blackboard) {
        this.blackboard = blackboard;
        this.unaryBindings = new HashMap<IUnaryCalculator<?, ?, T>, BindableUnaryCalculator<?, ?, T>>();
        this.binaryBindings = new HashMap<IBinaryCalculator<?, ?, ?, T>, BindableBinaryCalculator<?, ?, ?, T>>();
    }

    public <IN, OUT> void bind(IUnaryCalculator<IN, OUT, T> calculator, Probe<IN> sourceProbe) {
        BindableUnaryCalculator<IN, OUT, T> boundCalculator = new BindableUnaryCalculator<IN, OUT, T>(calculator);
        boundCalculator.bind(blackboard, sourceProbe);
        unaryBindings.put(calculator, boundCalculator);
    }

    public <IN1, IN2, OUT> void bind(IBinaryCalculator<IN1, IN2, OUT, T> calculator, Probe<IN1> sourceProbe1,
            Probe<IN2> sourceProbe2) {
        BindableBinaryCalculator<IN1, IN2, OUT, T> boundCalculator = new BindableBinaryCalculator<IN1, IN2, OUT, T>(
                calculator);
        boundCalculator.bind(blackboard, sourceProbe1, sourceProbe2);
        binaryBindings.put(calculator, boundCalculator);
    }

    public <IN1, IN2, OUT> void unbind(IBinaryCalculator<IN1, IN2, OUT, T> calculator) {
        BindableBinaryCalculator<?, ?, ?, T> boundCalculator = binaryBindings.get(calculator);

        // TODO implement method
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
