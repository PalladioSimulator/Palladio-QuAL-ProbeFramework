package org.palladiosimulator.probeframework.calculator;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.palladiosimulator.commons.designpatterns.AbstractObservable;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.metricspec.MetricDescription;

/**
 * Decorates an existing ICalculatorFactory by a register for its calculators. In case a calculator
 * is registered in this register, this factory directly returns the calculator from it. Otherwise,
 * a new calculator is instantiated, put into the register, and returned.
 *
 * Furthermore, this class provides convenience methods for cleaning up calculators (detach their
 * probes and cleaning the register; c.f., <code>finish</code> method). Clients can register with 
 * this class to be notified upon registrations of new calculators(@see CalculatorRegistryListener).
 *
 * @see IGenericCalculatorFactory
 *
 * @author Steffen Becker, Sebastian Lehrig, Matthias Becker, Florian Rosenthal
 */
public class RegisterCalculatorFactoryDecorator extends AbstractObservable<CalculatorRegistryListener> implements IGenericCalculatorFactory, IObservableCalculatorRegistry {

    /** Factory to be decorated by a register. */
    private final IGenericCalculatorFactory decoratedFactory;

    /** Register for calculators. */
    private final Set<Calculator> calculatorRegister = new HashSet<Calculator>();

    /**
     * Default constructor. Decorates given factory by a register.
     *
     * @param decoratedFactory
     *            The calculator factory to be decorated.
     */
    public RegisterCalculatorFactoryDecorator(final IGenericCalculatorFactory decoratedFactory) {
        super();
        this.decoratedFactory = decoratedFactory;
    }

    /**
     * Gets the decorated factory.
     *
     * @return The {@link ICalculatorFactory} that is decorated by this instance.
     */
    public IGenericCalculatorFactory getDecoratedFactory() {
        return this.decoratedFactory;
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Calculator> getRegisteredCalculators() {
        return Collections.unmodifiableCollection(this.calculatorRegister);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator getCalculatorByMeasuringPointAndMetricDescription(final MeasuringPoint mp,
            final MetricDescription metric) {
        Calculator result = null;
        final String measuringPointString = mp.getStringRepresentation();
        for (final Calculator calc : this.calculatorRegister) {
            if (calc.isCompatibleWith(metric)
                    && measuringPointString.equals(calc.getMeasuringPoint().getStringRepresentation())) {
                result = calc;
                break;
            }
        }
        return result;
    }
    
    /**
     * {@inheritDoc}
     */
    public Collection<Calculator> getCalculatorsForMeasuringPoint(MeasuringPoint measuringPoint) {
        String measuringPointString = Objects.requireNonNull(measuringPoint, "Measuring point must not be null")
                .getStringRepresentation();
        return this.calculatorRegister.stream()
                .filter(calc -> calc.getMeasuringPoint().getStringRepresentation().equals(measuringPointString))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    /**
     * Cleans up all registered calculators informing each calculator about being unregistered and
     * subsequently cleaning the register.
     */
    public void finish() {
        for (final Calculator calculator : this.calculatorRegister) {
            calculator.preUnregister();
        }
        this.calculatorRegister.clear();
    }

    /**
     * Registers the given calculator in the register.
     *
     * @param calculator
     *            The calculator to be registered.
     * @return The unmodified calculator (allows for fluent API).
     * @throws IllegalArgumentException
     *             If the given calculator is already registered.
     */
    private Calculator register(final Calculator calculator) {
        if (this.calculatorRegister.contains(calculator)) {
            Calculator found = null;
            for (final Calculator calc : this.calculatorRegister) {
                if (calc.equals(calculator)) {
                    found = calc;
                    break;
                }
            }

            throw new IllegalArgumentException("Calculator \"" + calculator.getClass().getName() + " ["
                    + calculator.getMeasuringPoint().getStringRepresentation() + "; "
                    + calculator.getMetricDesciption().getName() + "]\" already in calculator registry as \""
                    + found.getClass().getName() + " [" + found.getMeasuringPoint().getStringRepresentation() + "; "
                    + found.getMetricDesciption().getName() + "]\"");
        }
        this.calculatorRegister.add(calculator);
        this.getEventDispatcher().notifyCalculatorRegistration(calculator);
        return calculator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildCalculator(MetricDescription metric, MeasuringPoint measuringPoint,
            CalculatorProbeSet probeConfiguration) {
        return register(this.decoratedFactory.buildCalculator(metric, measuringPoint, probeConfiguration));
    }
}
