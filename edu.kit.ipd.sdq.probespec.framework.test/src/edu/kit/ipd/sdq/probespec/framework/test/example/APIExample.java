package edu.kit.ipd.sdq.probespec.framework.test.example;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.DerivedIntegerProbe;
import edu.kit.ipd.sdq.probespec.DoubleProbe;
import edu.kit.ipd.sdq.probespec.IntegerProbe;
import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.ProbeFactory;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.test.util.LoggingUtils;
import edu.kit.ipd.sdq.probespec.java.DifferenceCalculator;
import edu.kit.ipd.sdq.probespec.java.JavaProbeManager;
import edu.kit.ipd.sdq.probespec.java.PlusOneCalculator;

public class APIExample {

    private static final Logger logger = Logger.getLogger(APIExample.class);

    public static void main(String[] args) {
        // initialise logging
        LoggingUtils.configureLogging();

        // ////////////////////////////////////////////////////////////////////
        // CREATE PROBESPEC MODEL
        //
        // create some basic probes
        IntegerProbe firstProbe = ProbeFactory.createIntegerProbe("firstBasicProbe");
        DoubleProbe secondProbe = ProbeFactory.createDoubleProbe("secondBasicProbe");
        IntegerProbe thirdProbe = ProbeFactory.createIntegerProbe("thirdBasicProbe");
        //
        // create some derived probes (without binding them to a calculator for the moment)
        DerivedIntegerProbe plusOneProbe = ProbeFactory.createDerivedIntegerProbe("plusOneProbe");
        DerivedIntegerProbe plusOneProbeTwo = ProbeFactory.createDerivedIntegerProbe("plusOneProbeTwo");
        DerivedIntegerProbe differenceProbe = ProbeFactory.createDerivedIntegerProbe("differenceProbe");
        //
        // ////////////////////////////////////////////////////////////////////

        long t1 = System.nanoTime();

        // initialize ProbeSpec
        JavaProbeManager pm = new JavaProbeManager(BlackboardType.SIMPLE);

        // register a listener for Integer measurements
        pm.addMeasurementListener(new PrintMeasurementsListener());

        // now bind each derived probe to exactly one calculator
        pm.installCalculator(new PlusOneCalculator()).bindInput(thirdProbe).bindOutput(plusOneProbe);
        pm.installCalculator(new PlusOneCalculator()).bindInput(plusOneProbe).bindOutput(plusOneProbeTwo);
        pm.installCalculator(new DifferenceCalculator()).bindInput(plusOneProbe, thirdProbe).bindOutput(differenceProbe);

        // create an unbound calculator, which should raise a warning
//         pm.installCalculator(new PlusOneCalculator(plusOneProbe));

        // generate some dummy measurements for demonstration purposes
        for (int i = 0; i < 5; i++) {
            pm.addMeasurement(i, firstProbe);
        }
        pm.addMeasurement(5.0, secondProbe);
        pm.addMeasurement(5, thirdProbe);

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

}
