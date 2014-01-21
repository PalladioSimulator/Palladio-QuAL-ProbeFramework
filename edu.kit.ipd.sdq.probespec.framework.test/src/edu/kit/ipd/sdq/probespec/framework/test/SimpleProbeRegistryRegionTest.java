package edu.kit.ipd.sdq.probespec.framework.test;

import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import edu.kit.ipd.sdq.probespec.framework.MeasurableEntity;
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
    public void testProbeRetrievalWithMultipleProbeTypesPerEntity() {
        MeasurableTestEntity entity = new MeasurableTestEntity("entity");

        Probe<Integer> probeA = new TestProbeA("probeA");
        region.mountProbe(probeA, entity);

        Probe<Integer> probeB = new TestProbeB("probeB");
        region.mountProbe(probeB, entity);

        Probe<Integer> retrievedProbeA = region.getProbe(entity, TestProbeA.class);
        Probe<Integer> retrievedProbeB = region.getProbe(entity, TestProbeB.class);

        Assert.assertEquals(probeA, retrievedProbeA);
        Assert.assertEquals(probeB, retrievedProbeB);
    }

    @Test
    public void testProbeRetrievalWithMultipleEntities() {
        MeasurableTestEntity entity1 = new MeasurableTestEntity("entity1");
        Probe<Integer> probeA = new TestProbeA("probeA");
        region.mountProbe(probeA, entity1);

        MeasurableTestEntity entity2 = new MeasurableTestEntity("entity2");
        Probe<Integer> probeB = new TestProbeA("probeB");
        region.mountProbe(probeB, entity2);

        Probe<Integer> retrievedProbeA = region.getProbe(entity1, TestProbeA.class);
        Probe<Integer> retrievedProbeB = region.getProbe(entity2, TestProbeA.class);

        Assert.assertEquals(probeA, retrievedProbeA);
        Assert.assertEquals(probeB, retrievedProbeB);
    }

    @Test
    public void testProbeRetrievalWithMultipleMeasuringPoints() {
        MeasurableTestEntity entity = new MeasurableTestEntity("entity");

        Probe<Integer> probeA = new TestProbeA("probeA");
        region.mountProbe(probeA, entity, "mountPoint1");

        Probe<Integer> probeB = new TestProbeA("probeB");
        region.mountProbe(probeB, entity, "mountPoint2");

        Probe<Integer> retrievedProbeA = region.getProbe(entity, "mountPoint1", TestProbeA.class);
        Probe<Integer> retrievedProbeB = region.getProbe(entity, "mountPoint2", TestProbeA.class);

        Assert.assertEquals(probeA, retrievedProbeA);
        Assert.assertEquals(probeB, retrievedProbeB);
    }
    
    @Test
    public void testProbeRetrievalAllProbes() {
        MeasurableTestEntity entity = new MeasurableTestEntity("entity");
        
        // create and register probe w/o measuring point
        Probe<Integer> probeA = new TestProbeA("probeA");
        region.mountProbe(probeA, entity);

        // create an register probe w/ measuring point
        Probe<Integer> probeB = new TestProbeA("probeB");
        region.mountProbe(probeB, entity, "mountPoint2");
        
        // TODO calculators
        
        Set<Probe<Integer>> probes = region.getProbes();
        Assert.assertTrue(probes.contains(probeA));
        Assert.assertTrue(probes.contains(probeB));
    }

    private static class TestProbeA extends AbstractProbe<Integer> {

        public TestProbeA(String name) {
            super(name);
        }

        @Override
        public Class<Integer> getGenericClass() {
            return Integer.class;
        }

    }

    private static class TestProbeB extends AbstractProbe<Integer> {

        public TestProbeB(String name) {
            super(name);
        }

        @Override
        public Class<Integer> getGenericClass() {
            return Integer.class;
        }

    }

    private class MeasurableTestEntity implements MeasurableEntity {

        private String name;

        public MeasurableTestEntity(String name) {
            this.name = name;
        }

        @Override
        public String getMeasurableEntityId() {
            return name;
        }

        @Override
        public String getMeasurableEntityName() {
            return name;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            MeasurableTestEntity other = (MeasurableTestEntity) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.equals(other.name))
                return false;
            return true;
        }

        private SimpleProbeRegistryRegionTest getOuterType() {
            return SimpleProbeRegistryRegionTest.this;
        }

    }

}
