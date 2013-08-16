package edu.kit.ipd.sdq.probespec.framework.test.example;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.framework.DerivedProbe;
import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.test.util.IntegerProbe;
import edu.kit.ipd.sdq.probespec.framework.test.util.LoggingUtils;
import edu.kit.ipd.sdq.probespec.java.DifferenceCalculator;
import edu.kit.ipd.sdq.probespec.java.JavaProbeManager;

public class APIExample {

    private static final Logger logger = Logger.getLogger(APIExample.class);

    public static void main(String[] args) {
        // initialise logging
        LoggingUtils.configureLogging();

        // initialize ProbeSpec
        long t1 = System.nanoTime();
        JavaProbeManager pm = new JavaProbeManager(BlackboardType.SIMPLE);
        
        // create and register probes
        pm.mountProbe(new IntegerProbe("StartProbe"), "StartAction");
        pm.mountProbe(new IntegerProbe("StopProbe"), "StopAction");
        
        // register a listener for Integer measurements
        pm.addMeasurementListener(new PrintMeasurementsListener());

        // create and install an calculator which stores calculated results to an additional probe
        DerivedIntegerProbe responseTimeProbe = new DerivedIntegerProbe("responseTimeProbe");
        Probe<Integer> startProbe = pm.getProbe("StartAction", IntegerProbe.class);
        Probe<Integer> stopProbe = pm.getProbe("StopAction", IntegerProbe.class);
        pm.installCalculator(new DifferenceCalculator()).bindInput(startProbe, stopProbe).bindOutput(responseTimeProbe);

        // create an unbound calculator, which should raise a warning
//         pm.installCalculator(new PlusOneCalculator(plusOneProbe));

        // generate some dummy measurements for demonstration purposes
        for (int i = 0; i < 5; i++) {
            startProbe.addMeasurement(i);
        }
        stopProbe.addMeasurement(7);

        // TODO synchronisation should not be exposed to API clients
        pm.synchronise();

//        Integer v1 = ps.getLatestMeasurement(plusOneProbe).getValue();
//        Integer v2 = ps.getLatestMeasurement(thirdProbe).getValue();
//        Integer v3 = ps.getLatestMeasurement(differenceProbe).getValue();
//        System.out.println("Calculated difference between " + v1 + " and " + v2 + ": " + v3);

        // stop all threads spawned by the ProbeSpec
        pm.shutdown();

        // calculate and print processing time
        long t2 = System.nanoTime();
        long diff = t2 - t1;
        System.out.println("Took " + diff / (1000 * 1000) + " ms.");
    }

    private static class PrintMeasurementsListener implements IBlackboardListener<Integer, Long> {
        
        @Override
        public void measurementArrived(Measurement<Integer, Long> measurement, Probe<Integer> probe,
                IMeasurementContext... contexts) {
            logger.info("Encountered " + measurement + " for probe "
                    + probe.getName());
        }

        @Override
        public Class<Integer> getGenericType() {
            return Integer.class;
        }

    }
    
    private static class DerivedIntegerProbe extends IntegerProbe implements DerivedProbe<Integer>  {
        
        public DerivedIntegerProbe(String name) {
            super(name);
        }

        @Override
        public Class<Integer> getGenericClass() {
            return Integer.class;
        }
        
    }

}
