package edu.kit.ipd.sdq.probespec.framework.test.example;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.framework.Measurement;
import edu.kit.ipd.sdq.probespec.framework.MeasurementListener;
import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.test.mockup.MockupMeasurableEntity;
import edu.kit.ipd.sdq.probespec.framework.test.util.IntegerProbe;
import edu.kit.ipd.sdq.probespec.framework.test.util.LoggingUtils;
import edu.kit.ipd.sdq.probespec.java.DifferenceCalculator;
import edu.kit.ipd.sdq.probespec.java.JavaProbeManager;

public class APIExample {

    private static final Logger logger = Logger.getLogger(APIExample.class);

    public static void main(String[] args) {
        // Set up logging
        LoggingUtils.configureLogging();

        // Create timestamp for calculating the overall runtime later on
        long t1 = System.nanoTime();

        // 1) Initialize ProbeSpec by creating a probe manager. We use a simple (i.e.
        // non-concurrent) blackboard here, meaning that measurements will be processed
        // synchronously in the same thread that delivers the measurements.
        JavaProbeManager pm = new JavaProbeManager(BlackboardType.SIMPLE);

        // 2) Create two measurable entities for demonstration purposes. A measurable entity is an
        // arbitrary object to which probes refer to (see step 3).
        MockupMeasurableEntity startAction = new MockupMeasurableEntity("StartAction");
        MockupMeasurableEntity stopAction = new MockupMeasurableEntity("StopAction");

        // 3) Create and register probes. For each measurable entity from step 2, we set up
        // exactly one probe.
        pm.mountProbe(new IntegerProbe("StartProbe"), startAction);
        pm.mountProbe(new IntegerProbe("StopProbe"), stopAction);

        // 4) Create and install calculators. We set up a sort of response time calculator that
        // calculates the time difference between measurements provided for our
        // StartProbe and for the StopProbe, respectively. The calculated difference is fed into the
        // responseTimeProbe.
        IntegerProbe responseTimeProbe = new IntegerProbe("responseTimeProbe");
        Probe<Integer> startProbe = pm.getProbe(startAction, IntegerProbe.class);
        Probe<Integer> stopProbe = pm.getProbe(stopAction, IntegerProbe.class);
        pm.installCalculator(new DifferenceCalculator()).bindInput(startProbe, stopProbe).bindOutput(responseTimeProbe);

        // Create an unbound calculator, which should raise a warning
        // pm.installCalculator(new PlusOneCalculator(plusOneProbe));

        // Finally, register a listener for Integer measurements that simply prints each arriving
        // measurement to the console
        pm.addMeasurementListener(new PrintMeasurementsListener());

        // Generate some dummy measurements for demonstration purposes
        for (int i = 0; i < 5; i++) {
            startProbe.addMeasurement(i);
        }
        stopProbe.addMeasurement(7);

        // TODO synchronisation should not be exposed to API clients
        pm.synchronise();

        // Integer v1 = ps.getLatestMeasurement(plusOneProbe).getValue();
        // Integer v2 = ps.getLatestMeasurement(thirdProbe).getValue();
        // Integer v3 = ps.getLatestMeasurement(differenceProbe).getValue();
        // System.out.println("Calculated difference between " + v1 + " and " + v2 + ": " + v3);

        // stop all threads spawned by the ProbeSpec
        pm.shutdown();

        // calculate and print processing time
        long t2 = System.nanoTime();
        long diff = t2 - t1;
        System.out.println("Took " + diff / (1000 * 1000) + " ms.");
    }

    private static class PrintMeasurementsListener implements MeasurementListener<Integer, Long> {

        @Override
        public void measurementArrived(Measurement<Integer, Long> measurement, Probe<Integer> probe,
                MeasurementContext... contexts) {
            logger.info("Encountered " + measurement + " for probe " + probe.getName());
        }

        @Override
        public Class<Integer> getGenericType() {
            return Integer.class;
        }

    }

}
