package org.palladiosimulator.probeframework.calculator.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;
import org.palladiosimulator.probeframework.measurement.RequestContext;
import org.palladiosimulator.probeframework.probes.Probe;

/**
 * This abstract class represents an n-ary calculator. An n-ary calculator
 * expects n probes to be measured before it can do its calculation. For
 * example, for response time calculation, two probe measurements are needed
 * (one for start time and one for end time of an operation call). For this
 * measurement series, it maintains a memory to store measurements of the n
 * observed probes. As soon as the n-th sample arrives, the n-ary calculator
 * starts its calculation by invoking the template method {@link
 * #calculate(List<ProbeMeasurement> measurementMemory)}.
 * <p>
 * When a sample originating from the first probe arrives, an exception is
 * thrown because the first series of measurements has to be finished first.
 * 
 * @author Sebastian Lehrig, Steffen Becker
 * @see Calculator
 * 
 */
public abstract class NaryCalculator extends Calculator {
	/** List of n probes **/
	protected final List<Probe> probes;

	/** Maintained memory of probe measurements */
	private final Map<RequestContext, List<ProbeMeasurement>> arrivedMeasurementMemory = new HashMap<RequestContext, List<ProbeMeasurement>>();

	/**
	 * Default constructor. Creates the observed list of n probes.
	 * 
	 * @param ctx
	 *            ProbeFrameworkContext as needed by the superclass.
	 * @param metricDescription
	 *            MetricDescriptions as needed by the superclass.
	 * @param childProbes
	 *            List of n probes.
	 */
	public NaryCalculator(final ProbeFrameworkContext ctx,
			final MetricDescription metricDescription,
			final List<Probe> childProbes) {
		super(ctx, metricDescription);
		probes = Collections
				.unmodifiableList(new ArrayList<Probe>(childProbes));
		for (final Probe probe : childProbes) {
			probe.addObserver(this);
		}
	}

	/**
	 * @param probeMeasurement
	 *            The last ProbeMeasurement that was received from the observed
	 *            probe.
	 * @see org.palladiosimulator.probeframework.probes.listener.IProbeListener#newProbeMeasurementAvailable
	 */
	@Override
	public void newProbeMeasurementAvailable(
			final ProbeMeasurement probeMeasurement) {
		if (isMeasurementFromFirstProbe(probeMeasurement)) {
			if (arrivedMeasurementMemory.containsKey(probeMeasurement
					.getSourceAndContext().getRequestContext())) {
				throw new IllegalStateException(
						"First measurement to the same context arrived while previous series of the same context did not complete.");
			}
			arrivedMeasurementMemory.put(probeMeasurement.getSourceAndContext()
					.getRequestContext(), new LinkedList<ProbeMeasurement>());
		}
		final List<ProbeMeasurement> measurementMemory = arrivedMeasurementMemory
				.get(probeMeasurement.getSourceAndContext().getRequestContext());
		measurementMemory.add(probeMeasurement);
		if (isMeasurementFromLastProbe(probeMeasurement)) {
			fireCalculated(measurementMemory);
			arrivedMeasurementMemory.remove(probeMeasurement
					.getSourceAndContext().getRequestContext());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.palladiosimulator.probeframework.calculator.Calculator#detachProbes()
	 */
	@Override
	public void detachProbes() {
		for (final Probe probe : probes) {
			probe.removeObserver(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.palladiosimulator.probeframework.calculator.Calculator#releaseMemory
	 * (org.palladiosimulator.measurementspec.requestcontext.RequestContext)
	 */
	@Override
	public void releaseMemory(final RequestContext requestContext) {
		arrivedMeasurementMemory.remove(requestContext);
	}

	private boolean isMeasurementFromLastProbe(
			final ProbeMeasurement probeMeasurement) {
		return (probeMeasurement.getSourceAndContext().getMeasurementSource() == probes
				.get(probes.size() - 1));
	}

	private boolean isMeasurementFromFirstProbe(
			final ProbeMeasurement probeMeasurement) {
		return (probeMeasurement.getSourceAndContext().getMeasurementSource() == probes
				.get(0));
	}
}
