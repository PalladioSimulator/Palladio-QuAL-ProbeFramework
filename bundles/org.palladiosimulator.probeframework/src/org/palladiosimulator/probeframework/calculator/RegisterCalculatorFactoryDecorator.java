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
import org.palladiosimulator.probeframework.probes.ProbeConfiguration;

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
public class RegisterCalculatorFactoryDecorator extends AbstractObservable<CalculatorRegistryListener> implements IGenericCalculatorFactory {

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
     * Gets the currently registered calculators.
     *
     * @return An <b>unmodifiable</b> collection containing the currently registered calculators.
     */
    public Collection<Calculator> getRegisteredCalculators() {
        return Collections.unmodifiableCollection(this.calculatorRegister);
    }

    /**
     * Convenience method to get the calculator that is associated with the given measuring point
     * and metric.<br>
     * <br>
     * <b>Important note</b>: If a base metric such as 'Response Time' is passed here, and an
     * 'Response Time Tuple' calculator is available at the given measuring point it is <b>NOT</b>
     * found by the current implementation of this method, since these are different metric (base
     * metric vs. metric set)!. <br>
     * In such a case, better use {@link #getCalculatorsForMeasuringPoint(MeasuringPoint)} and
     * filter the resulting collection manually for the desired base metric.
     *
     * @param mp
     *            A {@link MeasuringPoint} instance which is associated with a calculator.
     * @param metric
     *            A {@link MetricDescription} denoting the metric the calculator is accepting.
     * @return A {@link Calculator} instance associated with the given measuring point and metric,
     *         or {@code null} if no matching {@link Calculator} is found.
     * @see #getCalculatorsForMeasuringPoint(MeasuringPoint)
     */
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
     * Convenience method to get all calculators associated with the given measuring point.
     *
     * @param measuringPoint
     *            A {@link MeasuringPoint} instance which is associated with a calculator.
     * @return An UNMODIFIABLE @{@link Collection} of {@link Calculator}s which are associated with
     *         the given measuring point, might be empty but never {@code null}.
     * @throws NullPointerException
     *             In case {@code measuringPoint == null}.
     * @see #getCalculatorByMeasuringPointAndMetricDescription(MeasuringPoint, MetricDescription)
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
    		ProbeConfiguration probeConfiguration) {
    	return register(this.decoratedFactory.buildCalculator(metric, measuringPoint, probeConfiguration));
    }
}
