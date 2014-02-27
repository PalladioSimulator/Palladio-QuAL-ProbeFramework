package de.uka.ipd.sdq.probespec.framework.test;

import java.util.Vector;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import junit.framework.TestCase;
import de.uka.ipd.sdq.pipesandfilters.framework.MetaDataInit;
import de.uka.ipd.sdq.pipesandfilters.framework.PipeData;
import de.uka.ipd.sdq.pipesandfilters.framework.PipesAndFiltersManager;
import de.uka.ipd.sdq.pipesandfilters.framework.filters.ExampleFilter;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.ConsoleWriteStrategy;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.RawRecorder;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2.EDP2MetaDataInit;
import de.uka.ipd.sdq.probespec.framework.ISampleBlackboard;
import de.uka.ipd.sdq.probespec.framework.ProbeSample;
import de.uka.ipd.sdq.probespec.framework.ProbeSetSample;
import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.ProbeType;
import de.uka.ipd.sdq.probespec.framework.RequestContext;
import de.uka.ipd.sdq.probespec.framework.SampleBlackboard;
import de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorListener;
import de.uka.ipd.sdq.probespec.framework.calculator.ResponseTimeCalculator;
import de.uka.ipd.sdq.probespec.framework.probes.ProbeStrategyRegistry;
import de.uka.ipd.sdq.probespec.framework.probes.example.ExampleProbeStrategyRegistry;
import de.uka.ipd.sdq.probespec.framework.probes.example.SimpleSimulationContext;

/**
 * This test case demonstrates the functioning of the ProbeSpec framework. Therefore a very simple
 * architecture model consisting of two model entities is used. Each of them is annotated by a
 * ProbeSet containing a CurrentTimeProbe. A ResponseTimeCalculator will then calculate the time
 * span between both points of time in order to obtain the response time. In the end the result is
 * passed to a Pipes-and-Filters chain.
 * <p>
 * <img src="doc-files/calcandpipes.png" width="750">
 * <p>
 * As shown in the picture a fictitious request with the RequestContextID "1" is measured. It passes
 * Component 1 at the time t=100ms and Component 2 at the time t=154ms. Afterwards both
 * ProbeSetSamples are published on the blackboard and the ResponseTimeCalculator is notified. As
 * soon as both ProbeSetSamples are available the difference is calculated and the result tuple is
 * passed to the Pipes-and-Filters chain.
 * 
 * @author pmerkle
 * @author Faber
 * 
 */
public class CalculatorAndPipesTest extends TestCase {

    private ProbeSpecContext psCtx;

    private SimpleSimulationContext simCtx;

    private PipesAndFiltersManager manager;

    private Integer PROBE_SET_ONE_ID = 1;

    private Integer PROBE_SET_TWO_ID = 2;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // initialise ProbeSpec
        ISampleBlackboard blackboard = new SampleBlackboard();
        ProbeStrategyRegistry factory = new ExampleProbeStrategyRegistry();
        psCtx = new ProbeSpecContext();
        psCtx.initialise(blackboard, factory, null);

        // initialise simulation context mockup
        simCtx = new SimpleSimulationContext();

        // create a filter and a recorder (data sink)
        ExampleFilter filter = new ExampleFilter();
        RawRecorder recorder = new RawRecorder(new ConsoleWriteStrategy());

        // builds up filter chain: filter > recorder
        manager = new PipesAndFiltersManager(filter);
        manager.addElement(recorder);
        manager.addConnection(filter, recorder);

        // create a calculator and make sure that calculations are fed back into the filter chain
        ResponseTimeCalculator calculator = new ResponseTimeCalculator(psCtx, PROBE_SET_ONE_ID, PROBE_SET_TWO_ID);
        calculator.addCalculatorListener(new ICalculatorListener() {
            @Override
            public void calculated(Vector<Measure<?, ? extends Quantity>> resultTuple) {
                manager.processData(new PipeData(resultTuple));
            }
        });

        // initialize filter chain
        MetaDataInit metaInit = new EDP2MetaDataInit(calculator.getMeasurementMetrics(),
                new DummyRecorderConfiguration());
        manager.initialize(metaInit);
    }

    public void testSimulation() {

        // advance "simulated" time
        simCtx.setSimulatedTime(100);

        // measure simulated time, then wrap the result into a ProbeSetSample, and finally post it
        // to the blackboard
        Vector<ProbeSample<?, ? extends Quantity>> psv1 = new Vector<ProbeSample<?, ? extends Quantity>>();
        psv1.add(psCtx.getProbeStrategyRegistry().getProbeStrategy(ProbeType.CURRENT_TIME, null)
                .takeSample("theProbeID_1", simCtx));
        ProbeSetSample pss1 = new ProbeSetSample(psv1, new RequestContext("1"), "Model_1", PROBE_SET_ONE_ID);
        psCtx.getSampleBlackboard().addSample(pss1);

        // once again, advance "simulated" time
        simCtx.setSimulatedTime(154);

        // measure simulated time, then wrap the result into a ProbeSetSample, and finally post it
        // to the blackboard
        Vector<ProbeSample<?, ? extends Quantity>> psv2 = new Vector<ProbeSample<?, ? extends Quantity>>();
        psv2.add(psCtx.getProbeStrategyRegistry().getProbeStrategy(ProbeType.CURRENT_TIME, null)
                .takeSample("theProbeID_2", simCtx));
        ProbeSetSample pss2 = new ProbeSetSample(psv2, new RequestContext("1"), "Model_2", PROBE_SET_TWO_ID);
        psCtx.getSampleBlackboard().addSample(pss2);

        // check if the calculator supplied the right value, i.e. the correct difference between the
        // time measurements
        ExampleFilter exFilter = (ExampleFilter) manager.getStartElement();
        assertEquals(54d, exFilter.getLastArrivedData().getTupleElement(0).getValue());

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        manager.flush();

        // tear down ProbeSpec
        psCtx.finish();
    }

}
