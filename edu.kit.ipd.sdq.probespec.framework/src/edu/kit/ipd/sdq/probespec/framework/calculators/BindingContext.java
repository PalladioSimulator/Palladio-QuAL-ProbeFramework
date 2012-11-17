package edu.kit.ipd.sdq.probespec.framework.calculators;

import java.util.HashMap;
import java.util.Map;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.BindableBinaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.IBinaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.BindableUnaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.IUnaryCalculator;

public class BindingContext<U> {

    private IBlackboard<U> blackboard;

    private Map<IUnaryCalculator<?, ?, U>, BindableUnaryCalculator<?, ?, U>> unaryBindings;

    private Map<IBinaryCalculator<?, ?, ?, U>, BindableBinaryCalculator<?, ?, ?, U>> binaryBindings;

    public BindingContext(IBlackboard<U> blackboard) {
        this.blackboard = blackboard;
        this.unaryBindings = new HashMap<IUnaryCalculator<?, ?, U>, BindableUnaryCalculator<?, ?, U>>();
        this.binaryBindings = new HashMap<IBinaryCalculator<?, ?, ?, U>, BindableBinaryCalculator<?, ?, ?, U>>();
    }

    public <IN, OUT> void bind(IUnaryCalculator<IN, OUT, U> calculator, Probe<IN> sourceProbe) {
        BindableUnaryCalculator<IN, OUT, U> boundCalculator = new BindableUnaryCalculator<IN, OUT, U>(calculator);
        boundCalculator.bind(blackboard, sourceProbe);
        unaryBindings.put(calculator, boundCalculator);
    }

    public <IN1, IN2, OUT> void bind(IBinaryCalculator<IN1, IN2, OUT, U> calculator, Probe<IN1> sourceProbe1,
            Probe<IN2> sourceProbe2) {
        BindableBinaryCalculator<IN1, IN2, OUT, U> boundCalculator = new BindableBinaryCalculator<IN1, IN2, OUT, U>(
                calculator);
        boundCalculator.bind(blackboard, sourceProbe1, sourceProbe2);
        binaryBindings.put(calculator, boundCalculator);
    }

    public <IN1, IN2, OUT> void unbind(IBinaryCalculator<IN1, IN2, OUT, U> calculator) {
        BindableBinaryCalculator<?, ?, ?, U> boundCalculator = binaryBindings.get(calculator);

        // TODO implement method
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
