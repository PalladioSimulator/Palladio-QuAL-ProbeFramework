package edu.kit.ipd.sdq.probespec.framework.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.probes.AbstractProbe;
import edu.kit.ipd.sdq.probespec.framework.probes.SimpleProbeRegistryRegion;

public class SimpleProbeRegistryRegionTest {

    private SimpleProbeRegistryRegion<Integer> region; 
    
    @Before
    public void before() {
        region = new SimpleProbeRegistryRegion<Integer>();
    }
    
    @Test
    public void testDifferentProbeTypesPerKey() {   
        Probe<Integer> probeA = new TestProbeA("probeA");
        region.mountProbe(probeA, "entity1");
        
        Probe<Integer> probeB = new TestProbeB("probeB");
        region.mountProbe(probeB, "entity1");
        
        Probe<Integer> retrievedProbeA = region.getProbe("entity1", TestProbeA.class);
        Probe<Integer> retrievedProbeB = region.getProbe("entity1", TestProbeB.class);
        
        Assert.assertEquals(probeA, retrievedProbeA);
        Assert.assertEquals(probeB, retrievedProbeB);
    }
    
    @Test
    public void testDifferentEntities() {
        Probe<Integer> probeA_1 = new TestProbeA("probeA_1");
        region.mountProbe(probeA_1, "entity1");
        
        Probe<Integer> probeA_2 = new TestProbeA("probeA_2");
        region.mountProbe(probeA_2, "entity2");
        
        Probe<Integer> retrievedProbeA_1 = region.getProbe("entity1", TestProbeA.class);
        Probe<Integer> retrievedProbeA_2 = region.getProbe("entity2", TestProbeA.class);
        
        Assert.assertEquals(probeA_1, retrievedProbeA_1);
        Assert.assertEquals(probeA_2, retrievedProbeA_2);
    }
    
    @Test
    public void testEntitiesWithMountPoints() {
        Probe<Integer> probeA_1 = new TestProbeA("probeA_1");
        region.mountProbe(probeA_1, "entity1", "mountPointX");
        
        Probe<Integer> probeA_2 = new TestProbeA("probeA_2");
        region.mountProbe(probeA_2, "entity1", "mountPointY");
        
        Probe<Integer> retrievedProbeA_1 = region.getProbe("entity1", "mountPointX", TestProbeA.class);
        Probe<Integer> retrievedProbeA_2 = region.getProbe("entity1", "mountPointY", TestProbeA.class);
        
        Assert.assertEquals(probeA_1, retrievedProbeA_1);
        Assert.assertEquals(probeA_2, retrievedProbeA_2);
    }
    
    private static class TestProbeA extends AbstractProbe<Integer>  {

        public TestProbeA(String name) {
            super(name);
        }

        @Override
        public Class<Integer> getGenericClass() {
            return Integer.class;
        }
        
    }
    
    private static class TestProbeB extends AbstractProbe<Integer>  {

        public TestProbeB(String name) {
            super(name);
        }

        @Override
        public Class<Integer> getGenericClass() {
            return Integer.class;
        }
        
    }
    
}
