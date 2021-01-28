package org.palladiosimulator.probeframework.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringpointFactory;
import org.palladiosimulator.edp2.models.measuringpoint.ResourceURIMeasuringPoint;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.CalculatorProbeSet;
import org.palladiosimulator.probeframework.calculator.CalculatorRegistryListener;
import org.palladiosimulator.probeframework.calculator.DefaultCalculatorProbeSets;
import org.palladiosimulator.probeframework.calculator.IGenericCalculatorFactory;
import org.palladiosimulator.probeframework.calculator.IObservableCalculatorRegistry;
import org.palladiosimulator.probeframework.calculator.internal.IdentityCalculator;
import org.palladiosimulator.probeframework.probes.Probe;

class ProbeFrameworkContextTest {
    Probe mockProbe;
    ResourceURIMeasuringPoint mockMeasuringPoint;
    Calculator mockCalculator;
    
    @BeforeEach
    void setup() {
        mockProbe = new Probe(MetricDescriptionConstants.RESOURCE_DEMAND_METRIC) {};
        mockMeasuringPoint = MeasuringpointFactory.eINSTANCE.createResourceURIMeasuringPoint();
        mockMeasuringPoint.setResourceURI("dummy.mock");
        mockMeasuringPoint.setMeasuringPoint("dummy.mock");
        mockCalculator = new IdentityCalculator(mockMeasuringPoint, mockProbe);
    }

    @Test
    void testProbeFrameworkDecoration() {
        var factoryMock = new IGenericCalculatorFactory() {
            @Override
            public Calculator buildCalculator(MetricDescription metric, MeasuringPoint measuringPoint,
                    CalculatorProbeSet probeConfiguration) {
                return mockCalculator;
            }
        };
        var underTest = new ProbeFrameworkContext(factoryMock);
        assertEquals(mockCalculator, underTest.getGenericCalculatorFactory()
            .buildCalculator(MetricDescriptionConstants.RESOURCE_DEMAND_METRIC, mockMeasuringPoint,
                    DefaultCalculatorProbeSets.createSingularProbeConfiguration(mockProbe)));
        assertEquals(mockCalculator, underTest.getCalculatorRegistry()
            .getCalculatorByMeasuringPointAndMetricDescription(mockMeasuringPoint,
                    MetricDescriptionConstants.RESOURCE_DEMAND_METRIC));
        
        assertNotEquals(factoryMock, underTest.getCalculatorRegistry());
    }
    
    public static class MockFactory implements IGenericCalculatorFactory, IObservableCalculatorRegistry {
        @Override
        public Calculator buildCalculator(MetricDescription metric, MeasuringPoint measuringPoint,
                CalculatorProbeSet probeConfiguration) {
            return null;
        }

        @Override
        public Collection<Calculator> getRegisteredCalculators() {
            return Collections.emptyList();
        }

        @Override
        public Collection<Calculator> getCalculatorsForMeasuringPoint(MeasuringPoint measuringPoint) {
            return Collections.emptyList();
        }

        @Override
        public Calculator getCalculatorByMeasuringPointAndMetricDescription(MeasuringPoint mp,
                MetricDescription metric) {
            return null;
        }

        @Override
        public void addObserver(CalculatorRegistryListener observer) {
        }

        @Override
        public void removeObserver(CalculatorRegistryListener observer) {
        }

        @Override
        public Collection<CalculatorRegistryListener> getObservers() {
            return null;
        }
        
    }
    @Test
    void testProbeFrameworkNonDecoration() {
        var factoryMock = new MockFactory();
        var underTest = new ProbeFrameworkContext(factoryMock);
        assertEquals(factoryMock, underTest.getCalculatorRegistry());
    }

}
