package edu.kit.ipd.sdq.probespec.framework.calculators;

import java.util.ArrayList;
import java.util.List;

import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.IBinaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.binding.BinaryCalculatorBinding;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.IUnaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.binding.UnaryCalculatorBinding;

public class CalculatorRegistry<T> {

    private IBlackboard<T> blackboard;

    private List<ICalculatorBinding> calculators;

    public CalculatorRegistry(IBlackboard<T> blackboard) {
        this.blackboard = blackboard;
        calculators = new ArrayList<ICalculatorBinding>();
    }

    public <IN, OUT> UnaryCalculatorBinding<IN, OUT, T> add(IUnaryCalculator<IN, OUT, T> calculator) {
        UnaryCalculatorBinding<IN, OUT, T> bindableCalculator = new UnaryCalculatorBinding<IN, OUT, T>(calculator,
                blackboard);
        calculators.add(bindableCalculator);
        return bindableCalculator;
    }

    public <IN1, IN2, OUT> BinaryCalculatorBinding<IN1, IN2, OUT, T> add(IBinaryCalculator<IN1, IN2, OUT, T> calculator) {
        BinaryCalculatorBinding<IN1, IN2, OUT, T> bindableCalculator = new BinaryCalculatorBinding<IN1, IN2, OUT, T>(
                calculator, blackboard);
        calculators.add(bindableCalculator);
        return bindableCalculator;
    }

    public <IN1, IN2, OUT> void remove(IBinaryCalculator<IN1, IN2, OUT, T> calculator) {
        // TODO implement method
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public List<ICalculatorBinding> getUnboundCalculators() {
        List<ICalculatorBinding> unboundCalculators = new ArrayList<ICalculatorBinding>();
        for (ICalculatorBinding c : calculators) {
            if (!c.isBound()) {
                unboundCalculators.add(c);
            }
        }
        return unboundCalculators;
    }

}
