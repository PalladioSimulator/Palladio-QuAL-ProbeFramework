package edu.kit.ipd.sdq.probespec.palladio;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import probespec.ProbeSpecRepository;
import probespec.ProbespecPackage;
import probespec.calculatortype.CalculatorTypeRepository;
import probespec.probetype.ProbeTypeRepository;
import edu.kit.ipd.sdq.probespec.framework.AbstractProbeManager;
import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.probes.ProbeStateListener;
import edu.kit.ipd.sdq.probespec.palladio.sensorframework.SensorFrameworkAdapter;

public class PalladioProbeManager extends AbstractProbeManager<Double> {

    private ProbeTypeRepository probeTypeRepository;

    private CalculatorTypeRepository calculatorTypeRepository;

    private SensorFrameworkAdapter sfa;

    public PalladioProbeManager(PalladioTimestampBuilder timestampBuilder, ProbeTypeRepository probeTypeRepository,
            CalculatorTypeRepository calculatorTypeRepository) {
        super(timestampBuilder);
        this.probeTypeRepository = probeTypeRepository;
        this.calculatorTypeRepository = calculatorTypeRepository;
    }

    public PalladioProbeManager(PalladioTimestampBuilder timestampBuilder, ProbeTypeRepository probeTypeRepository,
            CalculatorTypeRepository calculatorTypeRepository, SensorFrameworkAdapter sfa) {
        super(timestampBuilder);
        this.probeTypeRepository = probeTypeRepository;
        this.calculatorTypeRepository = calculatorTypeRepository;
        this.sfa = sfa;
    }

    public PalladioProbeManager(PalladioTimestampBuilder timestampBuilder, ProbeTypeRepository probeTypeRepository,
            CalculatorTypeRepository calculatorTypeRepository, SensorFrameworkAdapter sfa, BlackboardType blackboardType) {
        super(timestampBuilder, blackboardType);
        this.probeTypeRepository = probeTypeRepository;
        this.calculatorTypeRepository = calculatorTypeRepository;
        this.sfa = sfa;
    }

//    @Override
//    public <V> void mountProbe(final Probe<V> probe, MeasurableEntity entity, Object mountPoint) {
//        super.mountProbe(probe, entity, mountPoint);
//        connectToSensorFramework(probe);
//    }

//    @Override
//    public <V> void mountProbe(final Probe<V> probe, MeasurableEntity entity) {
//        super.mountProbe(probe, entity);
////        connectToSensorFramework(probe);
//    }
//
//    @Override
//    public <V> void mountCalculatedProbe(Probe<V> probe, Calculator<V> calculator) {
//        super.mountCalculatedProbe(probe, calculator);
////        connectToSensorFramework(probe);
//    }

    private <V> void connectToSensorFramework(final Probe<V> probe) {
        if (probe.isPersistent()) {
            sfa.addProbe(probe);
        }
        probe.addProbeStateListener(new ProbeStateListener() {

            @Override
            public void persistenceChanged(boolean persistent) {
                if (!persistent) {
                    sfa.removeProbe(probe);
                } else {
                    sfa.addProbe(probe);
                }
            }

            @Override
            public void activationChanged(boolean active) {
                // nothing to do; no measurements appear for deactivated probes
            }
        });
    }

    @Override
    public void shutdown() {
        super.shutdown();

        ConfigurationWriter<Double> writer = new ConfigurationWriter<Double>(probeTypeRepository,
                calculatorTypeRepository, probeRegistry, calculatorRegistry);

        ProbeSpecRepository repository = writer.createConfiguration();

        save(repository);

    }

    private void save(ProbeSpecRepository repository) {
//        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
//        Map<String, Object> m = reg.getExtensionToFactoryMap();
//        m.put("probespec", new XMIResourceFactoryImpl());

        // Obtain a new resource set
        ResourceSet resSet = new ResourceSetImpl();

        // Create a resource
        Resource resource = resSet.createResource(URI.createURI("file:/C:/Workspaces/runtime-New_configuration2/PSTest/out.probespec")); // TODO
        // Get the first model element and cast it to the right type, in my
        // example everything is hierarchical included in this first node
        resource.getContents().add(repository);

        // Now save the content.
        try {
            resource.save(Collections.EMPTY_MAP);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private ProbeSpecRepository load() {
        // Initialize the model
        ProbespecPackage.eINSTANCE.eClass();

        // Register the XMI resource factory for the .website extension

//        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
//        Map<String, Object> m = reg.getExtensionToFactoryMap();
//        m.put("probespec", new XMIResourceFactoryImpl());

        // Obtain a new resource set
        ResourceSet resSet = new ResourceSetImpl();

        // Get the resource
        Resource resource = resSet.getResource(URI.createURI("file:/C:/Workspaces/runtime-New_configuration2/PSTest/out.probespec"), true);
        // Get the first model element and cast it to the right type, in my
        // example everything is hierarchical included in this first node
        ProbeSpecRepository result = (ProbeSpecRepository) resource.getContents().get(0);
        return result;
    }

    @Override
    public void initialise() {
        File f = new File(URI.createURI("file:/C:/Workspaces/runtime-New_configuration2/PSTest/out.probespec").toFileString());
        if (f.exists()) {
            ProbeSpecRepository probeSpecRepository = load();

            ConfigurationReader<Double> reader = new ConfigurationReader<Double>(probeSpecRepository,
                    probeTypeRepository, calculatorTypeRepository, probeRegistry, calculatorRegistry);
            reader.adjustProbes();
        }
        
        for(Probe<?> p : probeRegistry.getProbes()) {
            connectToSensorFramework(p);
        }
    }

}
