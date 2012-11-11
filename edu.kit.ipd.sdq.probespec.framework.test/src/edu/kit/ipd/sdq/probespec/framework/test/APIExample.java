package edu.kit.ipd.sdq.probespec.framework.test;

import edu.kit.ipd.sdq.probespec.DerivedIntegerProbe;
import edu.kit.ipd.sdq.probespec.DoubleProbe;
import edu.kit.ipd.sdq.probespec.IntegerProbe;
import edu.kit.ipd.sdq.probespec.framework.ProbeFactory;
import edu.kit.ipd.sdq.probespec.framework.ProbeSpecContext;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.example.DifferenceCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.example.PlusOneCalculator;

public class APIExample {

    public static void main(String[] args) {
        // initialize ProbeSpec
        ProbeSpecContext ctx = new ProbeSpecContext();
        
        // create some basic probes
        IntegerProbe firstProbe = ProbeFactory.createIntegerProbe("firstBasicProbe");
        DoubleProbe secondProbe = ProbeFactory.createDoubleProbe("secondBasicProbe");
        IntegerProbe thirdProbe = ProbeFactory.createIntegerProbe("thirdBasicProbe");

        // create some derived probes (without binding them to a calculator for the moment)
        DerivedIntegerProbe derivedProbe = ProbeFactory.createDerivedIntegerProbe("plusOneProbe");
        DerivedIntegerProbe derivedProbe2 = ProbeFactory.createDerivedIntegerProbe("differenceProbe");

        // now bind each derived probe to exactly one calculator
        ctx.registerUnaryCalculator(new PlusOneCalculator(derivedProbe), thirdProbe);
        ctx.registerBinaryCalculator(new DifferenceCalculator(derivedProbe2), derivedProbe, thirdProbe);

        // generate some dummy measurements for demonstration purposes
        for (int i = 0; i < 3; i++) {
            ctx.getBlackboard().addMeasurement(i, firstProbe);
        }
        ctx.getBlackboard().addMeasurement(5.0, secondProbe);
        ctx.getBlackboard().addMeasurement(5, thirdProbe);
        
        System.out.println(ctx.getBlackboard().getLatestMeasurement(thirdProbe));
        
        //TODO: support probe.addMeasurement() ?
    }

}
