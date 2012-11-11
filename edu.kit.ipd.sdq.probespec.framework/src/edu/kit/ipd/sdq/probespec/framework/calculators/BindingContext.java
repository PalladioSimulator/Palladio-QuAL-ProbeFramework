package edu.kit.ipd.sdq.probespec.framework.calculators;

import java.util.HashMap;
import java.util.Map;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.BindableBinaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.IBinaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.BindableUnaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.IUnaryCalculator;

public class BindingContext {

    private IBlackboard blackboard;

    private Map<IUnaryCalculator<?, ?>, BindableUnaryCalculator<?, ?>> unaryBindings;

    private Map<IBinaryCalculator<?, ?, ?>, BindableBinaryCalculator<?, ?, ?>> binaryBindings;

    public BindingContext(IBlackboard blackboard) {
        this.blackboard = blackboard;
        this.unaryBindings = new HashMap<IUnaryCalculator<?, ?>, BindableUnaryCalculator<?, ?>>();
        this.binaryBindings = new HashMap<IBinaryCalculator<?, ?, ?>, BindableBinaryCalculator<?, ?, ?>>();
    }

    public <IN, OUT> void bind(IUnaryCalculator<IN, OUT> calculator, Probe<IN> sourceProbe) {
        BindableUnaryCalculator<IN, OUT> boundCalculator = new BindableUnaryCalculator<IN, OUT>(calculator);
        boundCalculator.bind(blackboard, sourceProbe);
        unaryBindings.put(calculator, boundCalculator);
    }

    public <IN1, IN2, OUT> void bind(IBinaryCalculator<IN1, IN2, OUT> calculator, Probe<IN1> sourceProbe1,
            Probe<IN2> sourceProbe2) {
        BindableBinaryCalculator<IN1, IN2, OUT> boundCalculator = new BindableBinaryCalculator<IN1, IN2, OUT>(
                calculator);
        boundCalculator.bind(blackboard, sourceProbe1, sourceProbe2);
        binaryBindings.put(calculator, boundCalculator);
    }

    public <IN1, IN2, OUT> void unbind(IBinaryCalculator<IN1, IN2, OUT> calculator) {
        BindableBinaryCalculator<?, ?, ?> boundCalculator = binaryBindings.get(calculator);

        // TODO implement method
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
