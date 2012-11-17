package edu.kit.ipd.sdq.probespec.framework.test;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.DerivedIntegerProbe;
import edu.kit.ipd.sdq.probespec.DoubleProbe;
import edu.kit.ipd.sdq.probespec.IntegerProbe;
import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.ProbeFactory;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.concurrent.ConcurrentBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.java.DifferenceCalculator;
import edu.kit.ipd.sdq.probespec.java.JavaProbeSpecContext;
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
        DerivedIntegerProbe differenceProbe = ProbeFactory.createDerivedIntegerProbe("differenceProbe");
        //
        // ////////////////////////////////////////////////////////////////////

        long t1 = System.nanoTime();

        // initialize ProbeSpec
        JavaProbeSpecContext ps = new JavaProbeSpecContext(BlackboardType.CONCURRENT);

        // register a listener for Integer measurements
        ps.addMeasurementListener(new PrintMeasurementsListener());

        // now bind each derived probe to exactly one calculator
        ps.addCalculator(new PlusOneCalculator(plusOneProbe)).bind(thirdProbe);
        ps.addCalculator(new DifferenceCalculator(differenceProbe)).bind(plusOneProbe, thirdProbe);

        // generate some dummy measurements for demonstration purposes
        for (int i = 0; i < 5; i++) {
            ps.addMeasurement(i, firstProbe);
        }
        ps.addMeasurement(5.0, secondProbe);
        ps.addMeasurement(5, thirdProbe);

        if (ps.getBlackboardType().equals(BlackboardType.CONCURRENT)) {
            ((ConcurrentBlackboard<?>) ps.getBlackboard()).synchronise();
        }

        Integer v1 = ps.getLatestMeasurement(plusOneProbe).getValue();
        Integer v2 = ps.getLatestMeasurement(thirdProbe).getValue();
        Integer v3 = ps.getLatestMeasurement(differenceProbe).getValue();
        System.out.println("Calculated difference between " + v1 + " and " + v2 + ": " + v3);

        // stop all threads spawned by the ProbeSpec
        ps.getThreadManager().stopThreads();

        // calculate and print processing time
        long t2 = System.nanoTime();
        long diff = t2 - t1;
        System.out.println("Took " + diff / (1000 * 1000) + " ms.");
    }

    private static class PrintMeasurementsListener implements IBlackboardListener<Integer, Long> {

        @Override
        public void measurementArrived(IBlackboard<Long> blackboard, Measurement<Integer, Long> measurement,
                Probe<Integer> probe, IMeasurementContext... contexts) {
            logger.info("Encountered " + measurement + " for probe " + probe.getId());
        }

        @Override
        public Class<Integer> getGenericType() {
            return Integer.class;
        }

    }

}
