package edu.kit.ipd.sdq.probespec.framework.test;

import edu.kit.ipd.sdq.probespec.DerivedIntegerProbe;
import edu.kit.ipd.sdq.probespec.DoubleProbe;
import edu.kit.ipd.sdq.probespec.IntegerProbe;
import edu.kit.ipd.sdq.probespec.framework.ProbeSpecContext;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.example.DifferenceCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.example.PlusOneCalculator;

public class APIExample {

    public static void main(String[] args) {
        // initialize ProbeSpec
        ProbeSpecContext ctx = new ProbeSpecContext();

        // create some basic probes
        IntegerProbe firstProbe = ctx.getProbeFactory().createIntegerProbe("firstBasicProbe");
        DoubleProbe secondProbe = ctx.getProbeFactory().createDoubleProbe("secondBasicProbe");
        IntegerProbe thirdProbe = ctx.getProbeFactory().createIntegerProbe("thirdBasicProbe");

        // create some derived probes (without binding them to a calculator for the moment)
        DerivedIntegerProbe derivedProbe = ctx.getProbeFactory().createDerivedIntegerProbe("calculatePlusOneProbe");
        DerivedIntegerProbe derivedProbe2 = ctx.getProbeFactory().createDerivedIntegerProbe("calculateDifferenceProbe");

        // now bind each derived probe to exactly one calculator
        ctx.bind(derivedProbe, new PlusOneCalculator(ctx), thirdProbe);
        ctx.bind(derivedProbe2, new DifferenceCalculator(ctx), derivedProbe, thirdProbe);

        // generate some dummy measurements for demonstration purposes
        for (int i = 0; i < 3; i++) {
            ctx.getBlackboard().addMeasurement(i, firstProbe);
        }
        ctx.getBlackboard().addMeasurement(5.0, secondProbe);
        ctx.getBlackboard().addMeasurement(5, thirdProbe);
    }

}
