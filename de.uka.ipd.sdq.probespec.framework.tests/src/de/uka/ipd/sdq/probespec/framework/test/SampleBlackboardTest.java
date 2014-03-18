package de.uka.ipd.sdq.probespec.framework.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import de.uka.ipd.sdq.probespec.framework.BlackboardVote;
import de.uka.ipd.sdq.probespec.framework.ProbeSetAndRequestContext;
import de.uka.ipd.sdq.probespec.framework.ProbeSetSample;
import de.uka.ipd.sdq.probespec.framework.RequestContext;
import de.uka.ipd.sdq.probespec.framework.SampleBlackboard;
import de.uka.ipd.sdq.probespec.framework.test.mockup.BlackboardListenerMockup;
import de.uka.ipd.sdq.probespec.framework.test.utils.TestUtils;

/**
 * Tests the {@link SampleBlackboard}.
 * 
 * @author Faber
 * @author Philipp Merkle
 * @author Steffen Becker
 * 
 */
@RunWith(JUnit4.class)
public class SampleBlackboardTest {

    private static final BlackboardListenerMockup BLACKBOARD_DISCARD_LISTENER = new BlackboardListenerMockup(BlackboardVote.DISCARD);
    private static final BlackboardListenerMockup BLACKBOARD_RETAIN_LISTENER = new BlackboardListenerMockup(BlackboardVote.RETAIN);
    private SampleBlackboard blackboard = null;

    @Before
    public void setUp() {
        this.blackboard = new SampleBlackboard();
    }

    @After
    public void tearDown() {
        this.blackboard = null;
    }

    @Test
    public void testGetProbeSetSample() {
        // blackboard should retain added samples
        blackboard.addBlackboardListener(BLACKBOARD_RETAIN_LISTENER);

        /*
         * Build a context hierarchy as follows:
         * 
         * Context1 Context1-1 Context1-2 Context1-2-1
         */
        final RequestContext context1 = new RequestContext("Context1");
        final RequestContext context1_1 = new RequestContext("Context1-1", context1);
        final RequestContext context1_2 = new RequestContext("Context1-2", context1);
        final RequestContext context1_2_1 = new RequestContext("Context1-2-1", context1_2);

        // create sample in context1 and publish
        final ProbeSetSample sample1 = new ProbeSetSample(TestUtils.createDummyProbeSample(1), context1, null, 1);
        blackboard.addSample(sample1);

        assertEquals(sample1, blackboard.getSample(new ProbeSetAndRequestContext(1, context1)));
        assertEquals(sample1, blackboard.getSample(new ProbeSetAndRequestContext(1, context1_1)));
        assertEquals(sample1, blackboard.getSample(new ProbeSetAndRequestContext(1, context1_2)));
        assertEquals(sample1, blackboard.getSample(new ProbeSetAndRequestContext(1, context1_2_1)));

        // create sample in context 1-2 and publish
        final ProbeSetSample sample1_2 = new ProbeSetSample(TestUtils.createDummyProbeSample(2), context1_2, null, 2);
        blackboard.addSample(sample1_2);

        assertNull(blackboard.getSample(new ProbeSetAndRequestContext(2, context1)));
        assertNull(blackboard.getSample(new ProbeSetAndRequestContext(2, context1_1)));
        assertEquals(sample1_2, blackboard.getSample(new ProbeSetAndRequestContext(2, context1_2)));
        assertEquals(sample1_2, blackboard.getSample(new ProbeSetAndRequestContext(2, context1_2_1)));
    }

    /**
     * Let some listeners vote for discard and one listener vote for retain. The added sample should
     * be retained.
     */
    @Test
    public void testRetainVote() {
        blackboard.addBlackboardListener(BLACKBOARD_DISCARD_LISTENER);
        blackboard.addBlackboardListener(BLACKBOARD_RETAIN_LISTENER);
        blackboard.addBlackboardListener(BLACKBOARD_DISCARD_LISTENER);

        final ProbeSetSample sample = new ProbeSetSample(TestUtils.createDummyProbeSample(1),
                RequestContext.EMPTY_REQUEST_CONTEXT, null, 1);
        blackboard.addSample(sample);

        assertEquals(sample,
                blackboard.getSample(new ProbeSetAndRequestContext(1, RequestContext.EMPTY_REQUEST_CONTEXT)));
    }

    /**
     * Let some listeners vote for discard, none listener vote for retain. The added sample should
     * be discarded.
     */
    @Test
    public void testDiscardVote() {
        blackboard.addBlackboardListener(BLACKBOARD_DISCARD_LISTENER);
        blackboard.addBlackboardListener(BLACKBOARD_DISCARD_LISTENER);

        final ProbeSetSample sample = new ProbeSetSample(TestUtils.createDummyProbeSample(1),
                RequestContext.EMPTY_REQUEST_CONTEXT, null, 1);
        blackboard.addSample(sample);

        assertNull(blackboard.getSample(new ProbeSetAndRequestContext(1, RequestContext.EMPTY_REQUEST_CONTEXT)));
    }

    @Test
    public void testSamePSSID() {
        // blackboard should retain added samples
        blackboard.addBlackboardListener(BLACKBOARD_RETAIN_LISTENER);

        final ProbeSetSample pss1 = new ProbeSetSample(null, new RequestContext("1"), "1", 1);
        final ProbeSetSample pss2 = new ProbeSetSample(null, new RequestContext("1"), "1", 2);
        final ProbeSetSample pss3 = new ProbeSetSample(null, new RequestContext("2"), "1", 1);
        final ProbeSetSample pss4 = new ProbeSetSample(null, new RequestContext("2"), "1", 2);
        blackboard.addSample(pss1);
        blackboard.addSample(pss2);
        blackboard.addSample(pss3);
        blackboard.addSample(pss4);

        assertSame(pss1, blackboard.getSample(pss1.getProbeSetAndRequestContext()));
        assertSame(pss2, blackboard.getSample(pss2.getProbeSetAndRequestContext()));
        assertSame(pss3, blackboard.getSample(pss3.getProbeSetAndRequestContext()));
        assertSame(pss4, blackboard.getSample(pss4.getProbeSetAndRequestContext()));
    }

    @Test
    public void testDifferentPSSID() {
        // blackboard should retain added samples
        blackboard.addBlackboardListener(BLACKBOARD_RETAIN_LISTENER);

        final ProbeSetSample pss1 = new ProbeSetSample(null, new RequestContext("1"), "1", 1);
        final ProbeSetSample pss2 = new ProbeSetSample(null, new RequestContext("2"), "1", 1);
        blackboard.addSample(pss1);
        blackboard.addSample(pss2);
        final ProbeSetAndRequestContext pssID1 = new ProbeSetAndRequestContext(1, new RequestContext("1"));
        final ProbeSetAndRequestContext pssID2 = new ProbeSetAndRequestContext(1, new RequestContext("2"));
        assertSame(pss1, blackboard.getSample(pssID1));
        assertNotSame(pss2, blackboard.getSample(pssID1));

        assertSame(pss2, blackboard.getSample(pssID2));
        assertNotSame(pss1, blackboard.getSample(pssID2));
    }

    @Test
    public void testSize() {
        // blackboard should retain added samples
        blackboard.addBlackboardListener(BLACKBOARD_RETAIN_LISTENER);

        // test empty blackboard
        assertEquals(0, blackboard.size());

        // add one element and test the size
        final ProbeSetSample pss1 = new ProbeSetSample(TestUtils.createDummyProbeSample(0), new RequestContext("context1"),
                null, 1);
        blackboard.addSample(pss1);
        assertEquals(1, blackboard.size());

        // add a second element and test the size
        final ProbeSetSample pss2 = new ProbeSetSample(TestUtils.createDummyProbeSample(1), new RequestContext("context1"),
                null, 2);
        blackboard.addSample(pss2);
        assertEquals(2, blackboard.size());

        // add a third element, now in a new context
        final ProbeSetSample pss3 = new ProbeSetSample(TestUtils.createDummyProbeSample(2), new RequestContext("context2"),
                null, 3);
        blackboard.addSample(pss3);
        assertEquals(3, blackboard.size());
    }

}
