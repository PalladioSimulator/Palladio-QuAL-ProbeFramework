package de.uka.ipd.sdq.pipesandfilters.framework.test;

import static de.uka.ipd.sdq.probespec.framework.constants.MeasurementMetricConstants.POINT_IN_TIME_METRIC;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;
import javax.measure.unit.SI;

import org.palladiosimulator.edp2.impl.RepositoryManager;
import org.palladiosimulator.edp2.models.Repository.LocalDirectoryRepository;

import junit.framework.TestCase;
import de.uka.ipd.sdq.pipesandfilters.framework.MeasurementMetric;
import de.uka.ipd.sdq.pipesandfilters.framework.PipeData;
import de.uka.ipd.sdq.pipesandfilters.framework.PipesAndFiltersManager;
import de.uka.ipd.sdq.pipesandfilters.framework.filters.ExampleFilter;
import de.uka.ipd.sdq.pipesandfilters.framework.filters.Filter;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.RawRecorder;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2.EDP2MetaDataInit;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2.launch.EDP2Config;

/**
 * This TestCase is supposed to check the performance of a filter chain
 * consisting of 10 filters and a raw recorder provided with a million data
 * objects.
 * 
 * @author Baum, Sebastian Lehrig
 * 
 */
public class PipesAndFiltersPerformanceTest extends TestCase {

    /** Directory for test case measurements */
    private static final String TEST_CASE_MEASUREMENTS = "TestCaseMeasurements";
    
	private final Filter[] filter = new ExampleFilter[9];
	private PipesAndFiltersManager manager;
	private DummyRawWriteStrategy writeStrategy;
	private RawRecorder recorder;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		// Initializing meta data for the recorders is set.
		Vector<MeasurementMetric> measuredObjects = new Vector<MeasurementMetric>();
		measuredObjects.add(POINT_IN_TIME_METRIC);
		
		// Create repository and suitable recorder configuration.
        LocalDirectoryRepository repo = RepositoryManager.initializeLocalDirectoryRepository(new File(TEST_CASE_MEASUREMENTS));
        RepositoryManager.addRepository(RepositoryManager.getCentralRepository(), repo);
        EDP2Config edp2Config = new EDP2Config();
        Map<String, Object> configuration = new HashMap<String, Object>(1);
        configuration.put(EDP2Config.REPOSITORY_ID, repo.getUuid());
        edp2Config.setConfiguration(configuration);
        
		EDP2MetaDataInit metaInit = new EDP2MetaDataInit(
				measuredObjects,
				edp2Config,
				"Test Metric Name",
                "Test Measurement Name",
                "Test Experiment Name",
                "Test ExperimentRun Name",
                "Test modelElementID",
                new HashMap<Integer, String>(0)
			);

		manager = new PipesAndFiltersManager(new ExampleFilter());
		for (int i = 0; i < 9; i++) {
			// Add filters
			filter[i] = new ExampleFilter();
			manager.addElement(filter[i]);

			if (i > 0)
				manager.addConnection(filter[i - 1], filter[i]);
			else
				manager.addConnection(manager.getStartElement(), filter[i]);
		}

		writeStrategy = new DummyRawWriteStrategy();
		recorder = new RawRecorder(writeStrategy);
		manager.addElement(recorder);
		manager.addConnection(filter[8], recorder);

		manager.initialize(metaInit);
	}

	public void testPipesAndFiltersManager() {
		PipeData data;

		for (int i = 0; i < 1000000; i++) {
			Vector<Measure<?, ? extends Quantity>> m = new Vector<Measure<?, ? extends Quantity>>();
			m.add(Measure.valueOf(i, SI.SECOND));
			data = new PipeData(m);
			manager.processData(data);
		}
		manager.flush();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
}
