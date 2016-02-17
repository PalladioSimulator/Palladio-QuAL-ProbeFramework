package org.palladiosimulator.probeframework.calculator;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.probeframework.probes.Probe;

/**
 * Decorates an existing ICalculatorFactory by a register for its calculators. In case a calculator
 * is registered in this register, this factory directly returns the calculator from it. Otherwise,
 * a new calculator is instantiated, put into the register, and returned.
 *
 * Furthermore, this class provides convenience methods for cleaning up calculators (detach their
 * probes and cleaning the register; c.f., <code>finish</code> method).
 *
 * @see ICalculatorFactory
 *
 * @author Steffen Becker, Sebastian Lehrig, Matthias Becker, Florian Rosenthal
 */
public class RegisterCalculatorFactoryDecorator implements ICalculatorFactory {

    /** Factory to be decorated by a register. */
    private final ICalculatorFactory decoratedFactory;

    /** Register for calculators. */
    private final Set<Calculator> calculatorRegister = new HashSet<Calculator>();

    /**
     * Default constructor. Decorates given factory by a register.
     *
     * @param decoratedFactory
     *            The calculator factory to be decorated.
     */
    public RegisterCalculatorFactoryDecorator(final ICalculatorFactory decoratedFactory) {
        super();
        this.decoratedFactory = decoratedFactory;
    }

    /**
     * Gets the decorated factory.
     *
     * @return The {@link ICalculatorFactory} that is decorated by this instance.
     */
    public ICalculatorFactory getDecoratedFactory() {
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
     * and metric.
     *
     * @param mp
     *            A {@link MeasuringPoint} instance which is associated with a calculator.
     * @param metric
     *            A {@link MetricDescription} denoting the metric the calculator is accepting.
     * @return A {@link Calculator} instance associated with the given measuring point and metric,
     *         or {@code null} if no matching {@link Calculator} is found.
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
        return calculator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildResponseTimeCalculator(final MeasuringPoint measuringPoint, final List<Probe> probes) {
        return register(this.decoratedFactory.buildResponseTimeCalculator(measuringPoint, probes));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildDemandBasedWaitingTimeCalculator(final MeasuringPoint measuringPoint,
            final List<Probe> probes) {
        return register(this.decoratedFactory.buildDemandBasedWaitingTimeCalculator(measuringPoint, probes));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildWaitingTimeCalculator(final MeasuringPoint measuringPoint, final List<Probe> probes) {
        return register(this.decoratedFactory.buildWaitingTimeCalculator(measuringPoint, probes));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildHoldingTimeCalculator(final MeasuringPoint measuringPoint, final List<Probe> probes) {
        return register(this.decoratedFactory.buildHoldingTimeCalculator(measuringPoint, probes));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildStateOfActiveResourceCalculator(final MeasuringPoint measuringPoint, final Probe probe) {
        return register(this.decoratedFactory.buildStateOfActiveResourceCalculator(measuringPoint, probe));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildOverallStateOfActiveResourceCalculator(final MeasuringPoint measuringPoint,
            final Probe probe) {
        return register(this.decoratedFactory.buildOverallStateOfActiveResourceCalculator(measuringPoint, probe));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildStateOfPassiveResourceCalculator(final MeasuringPoint measuringPoint, final Probe probe) {
        return register(this.decoratedFactory.buildStateOfPassiveResourceCalculator(measuringPoint, probe));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildOverallStateOfPassiveResourceCalculator(final MeasuringPoint measuringPoint,
            final Probe probe) {
        return register(this.decoratedFactory.buildOverallStateOfPassiveResourceCalculator(measuringPoint, probe));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildResourceDemandCalculator(final MeasuringPoint measuringPoint, final Probe probe) {
        return register(this.decoratedFactory.buildResourceDemandCalculator(measuringPoint, probe));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildExecutionResultCalculator(final MeasuringPoint measuringPoint, final Probe probe) {
        return register(this.decoratedFactory.buildExecutionResultCalculator(measuringPoint, probe));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildNumberOfResourceContainersCalculator(final MeasuringPoint measuringPoint,
            final Probe probe) {
        return register(this.decoratedFactory.buildNumberOfResourceContainersCalculator(measuringPoint, probe));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildReconfigurationTimeCalculator(final MeasuringPoint measuringPoint, final Probe probe) {
        return register(this.decoratedFactory.buildReconfigurationTimeCalculator(measuringPoint, probe));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildCostOverTimeCalculator(final MeasuringPoint measuringPoint, final Probe probe) {
        return register(this.decoratedFactory.buildCostOverTimeCalculator(measuringPoint, probe));
    }

    @Override
    public Calculator buildOptimisationTimeCalculator(final MeasuringPoint measuringPoint, final Probe probe) {
        return register(this.decoratedFactory.buildOptimisationTimeCalculator(measuringPoint, probe));
    }

    @Override
    public Calculator buildAggregatedCostOverTimeCalculator(final MeasuringPoint measuringPoint, final Probe probe) {
        return register(this.decoratedFactory.buildAggregatedCostOverTimeCalculator(measuringPoint, probe));
    }
}
