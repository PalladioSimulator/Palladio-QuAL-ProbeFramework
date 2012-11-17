package edu.kit.ipd.sdq.probespec.framework.calculators;

import java.util.ArrayList;
import java.util.List;

import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.BindableBinaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.IBinaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.BindableUnaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.IUnaryCalculator;

public class CalculatorRegistry<T> {

    private IBlackboard<T> blackboard;

    private List<IBindableCalculator> calculators;

    public CalculatorRegistry(IBlackboard<T> blackboard) {
        this.blackboard = blackboard;
        calculators = new ArrayList<IBindableCalculator>();
    }

    public <IN, OUT> BindableUnaryCalculator<IN, OUT, T> add(IUnaryCalculator<IN, OUT, T> calculator) {
        BindableUnaryCalculator<IN, OUT, T> bindableCalculator = new BindableUnaryCalculator<IN, OUT, T>(calculator,
                blackboard);
        calculators.add(bindableCalculator);
        return bindableCalculator;
    }

    public <IN1, IN2, OUT> BindableBinaryCalculator<IN1, IN2, OUT, T> add(IBinaryCalculator<IN1, IN2, OUT, T> calculator) {
        BindableBinaryCalculator<IN1, IN2, OUT, T> bindableCalculator = new BindableBinaryCalculator<IN1, IN2, OUT, T>(
                calculator, blackboard);
        calculators.add(bindableCalculator);
        return bindableCalculator;
    }

    public <IN1, IN2, OUT> void remove(IBinaryCalculator<IN1, IN2, OUT, T> calculator) {
        // TODO implement method
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public List<IBindableCalculator> getUnboundCalculators() {
        List<IBindableCalculator> unboundCalculators = new ArrayList<IBindableCalculator>();
        for (IBindableCalculator c : calculators) {
            if (!c.isBound()) {
                unboundCalculators.add(c);
            }
        }
        return unboundCalculators;
    }

}
