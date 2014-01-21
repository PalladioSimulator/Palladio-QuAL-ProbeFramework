package edu.kit.ipd.sdq.probespec.framework.calculators;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.kit.ipd.sdq.probespec.framework.Calculator;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Blackboard;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.BinaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.binding.BinaryCalculatorBinding;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.UnaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.binding.UnaryCalculatorBinding;

public class CalculatorRegistry<T> {

    private Blackboard<T> blackboard;

    private List<Calculator<?>> calculators;
    
    private List<CalculatorBinding> calculatorBindings;

    public CalculatorRegistry(Blackboard<T> blackboard) {
        this.blackboard = blackboard;
        calculators = new ArrayList<Calculator<?>>();
        calculatorBindings = new ArrayList<CalculatorBinding>();
    }

    public <IN, OUT> UnaryCalculatorBinding<IN, OUT, T> add(UnaryCalculator<IN, OUT, T> calculator) {
        UnaryCalculatorBinding<IN, OUT, T> bindableCalculator = new UnaryCalculatorBinding<IN, OUT, T>(calculator,
                blackboard);
        calculators.add(calculator);
        calculatorBindings.add(bindableCalculator);
        return bindableCalculator;
    }

    public <IN1, IN2, OUT> BinaryCalculatorBinding<IN1, IN2, OUT, T> add(BinaryCalculator<IN1, IN2, OUT, T> calculator) {
        BinaryCalculatorBinding<IN1, IN2, OUT, T> bindableCalculator = new BinaryCalculatorBinding<IN1, IN2, OUT, T>(
                calculator, blackboard);
        calculators.add(calculator);
        calculatorBindings.add(bindableCalculator);
        return bindableCalculator;
    }

    public <IN1, IN2, OUT> void remove(BinaryCalculator<IN1, IN2, OUT, T> calculator) {
        // TODO implement method
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public List<CalculatorBinding> getUnboundCalculators() {
        List<CalculatorBinding> unboundCalculators = new ArrayList<CalculatorBinding>();
        for (CalculatorBinding c : calculatorBindings) {
            if (!c.isBound()) {
                unboundCalculators.add(c);
            }
        }
        return unboundCalculators;
    }
    
    public Set<Calculator<?>> getCalculators() {
        Set<Calculator<?>> result = new HashSet<Calculator<?>>();
        result.addAll(calculators);
        return result;
    }
    
    public Set<CalculatorBinding> getCalculatorBindings() {
        Set<CalculatorBinding> result = new HashSet<CalculatorBinding>();
        result.addAll(calculatorBindings);
        return result;
    }

}
