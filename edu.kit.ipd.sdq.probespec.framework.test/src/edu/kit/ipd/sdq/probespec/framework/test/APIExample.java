package edu.kit.ipd.sdq.probespec.framework.test;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.DerivedIntegerProbe;
import edu.kit.ipd.sdq.probespec.DoubleProbe;
import edu.kit.ipd.sdq.probespec.IntegerProbe;
import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.ProbeFactory;
import edu.kit.ipd.sdq.probespec.framework.ProbeSpecContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardFactory;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.concurrent.ThreadManager;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.java.DifferenceCalculator;
import edu.kit.ipd.sdq.probespec.java.JavaTimestampBuilder;
import edu.kit.ipd.sdq.probespec.java.PlusOneCalculator;

public class APIExample {

    private static final Logger logger = Logger.getLogger(APIExample.class);

    public static void main(String[] args) {
        long t1 = System.nanoTime();

        // initialise logging
        LoggingUtils.configureLogging();

        // initialize ProbeSpec
        ThreadManager m = new ThreadManager();
        IBlackboard<Long> bb = BlackboardFactory.createBlackboard(BlackboardType.CONCURRENT,
                new JavaTimestampBuilder(), m);
        ProbeSpecContext<Long> ctx = new ProbeSpecContext<Long>(bb);

        // register a listener for Integer measurements
        ctx.getBlackboard().addMeasurementListener(new PrintMeasurementsListener());

        // create some basic probes
        IntegerProbe firstProbe = ProbeFactory.createIntegerProbe("firstBasicProbe");
        DoubleProbe secondProbe = ProbeFactory.createDoubleProbe("secondBasicProbe");
        IntegerProbe thirdProbe = ProbeFactory.createIntegerProbe("thirdBasicProbe");

        // create some derived probes (without binding them to a calculator for the moment)
        DerivedIntegerProbe derivedProbe = ProbeFactory.createDerivedIntegerProbe("plusOneProbe");
        DerivedIntegerProbe derivedProbe2 = ProbeFactory.createDerivedIntegerProbe("differenceProbe");

        // now bind each derived probe to exactly one calculator
        ctx.getBindingContext().bind(new PlusOneCalculator(derivedProbe), thirdProbe);
        ctx.getBindingContext().bind(new DifferenceCalculator(derivedProbe2), derivedProbe, thirdProbe);

        // generate some dummy measurements for demonstration purposes
        for (int i = 0; i < 30; i++) {
            ctx.getBlackboard().addMeasurement(i, firstProbe);
        }
        ctx.getBlackboard().addMeasurement(5.0, secondProbe);
        ctx.getBlackboard().addMeasurement(5, thirdProbe);

        // ((ConcurrentBlackboard)ctx.getBlackboard()).synchronise();
        System.out.println(ctx.getBlackboard().getLatestMeasurement(thirdProbe));

        long t2 = System.nanoTime();
        long diff = t2 - t1;

        System.out.println("Took " + diff / (1000 * 1000) + " ms.");

        m.stopThreads();

        // TODO: support probe.addMeasurement() ?
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
