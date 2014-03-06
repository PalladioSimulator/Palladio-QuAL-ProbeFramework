package de.uka.ipd.sdq.pipesandfilters.framework.test;

import static de.uka.ipd.sdq.probespec.framework.constants.MeasurementMetricConstants.POINT_IN_TIME_METRIC;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;
import javax.measure.unit.SI;

import junit.framework.TestCase;
import de.uka.ipd.sdq.edp2.impl.RepositoryManager;
import de.uka.ipd.sdq.edp2.models.Repository.LocalDirectoryRepository;
import de.uka.ipd.sdq.pipesandfilters.framework.MeasurementMetric;
import de.uka.ipd.sdq.pipesandfilters.framework.MetaDataInit;
import de.uka.ipd.sdq.pipesandfilters.framework.PipeData;
import de.uka.ipd.sdq.pipesandfilters.framework.PipesAndFiltersManager;
import de.uka.ipd.sdq.pipesandfilters.framework.filters.ExampleFilter;
import de.uka.ipd.sdq.pipesandfilters.framework.filters.Filter;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.RawRecorder;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.Recorder;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2.EDP2MetaDataInit;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2.Edp2RawWriteStrategy;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2.launch.EDP2Config;

/**
 * A simple pipes and filters test, consisting of only an example filter and a
 * raw recorder. This test case actually stores the created measurements to
 * EDP2, so new files are generated whenever the test is executed.
 * 
 * @author Baum, Sebastian Lehrig, Steffen Becker
 */
public class PipeTest extends TestCase {

    /** Directory for test case measurements */
	private static final String TEST_CASE_MEASUREMENTS = "TestCaseMeasurements";
	
    private Filter testfilter;
	private Recorder recorder;
	private Edp2RawWriteStrategy writeStrat;
	private PipesAndFiltersManager manager;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		// Create a description of our calculator (the measured object)
		// Initializing meta data for the recorders is set.
		List<MeasurementMetric> measuredObjects = Arrays.asList(
					POINT_IN_TIME_METRIC, 
					POINT_IN_TIME_METRIC,
					POINT_IN_TIME_METRIC
				);

		// Create repository and suitable recorder configuration.
		File tempFile = File.createTempFile(TEST_CASE_MEASUREMENTS, "edp2Dir");
		tempFile.delete();
		if (tempFile.mkdir() == true) {
			tempFile.deleteOnExit();
		}
		
		LocalDirectoryRepository repo = RepositoryManager.initializeLocalDirectoryRepository(tempFile);
        RepositoryManager.addRepository(RepositoryManager.getCentralRepository(), repo);
        EDP2Config edp2Config = new EDP2Config();
        Map<String, Object> configuration = new HashMap<String, Object>();
        configuration.put(EDP2Config.REPOSITORY_ID, repo.getUuid());
        edp2Config.setConfiguration(configuration);
        
		// Create filters and recorders for the chain.
		MetaDataInit metaInit = new EDP2MetaDataInit(
				measuredObjects,
				edp2Config,
				"Metric 1",
				"Calculator 1",
				"Experiment 1",
				"ExperimentRun 1",
				"modelElementID 1",
				new HashMap<Integer, String>(0)
			);
		
		// Set filters and recorders properties.
		testfilter = new ExampleFilter();
		writeStrat = new Edp2RawWriteStrategy();
		recorder = new RawRecorder(writeStrat);

		manager = new PipesAndFiltersManager(testfilter);
		manager.addElement(recorder);
		manager.addConnection(testfilter, recorder);
		manager.initialize(metaInit);
	}

	public void testPipe() {
		PipeData data;

		for (int i = 0; i < 10; i++) {
			List<Measure<?, ? extends Quantity>> m = new ArrayList<Measure<?, ? extends Quantity>>(3);
			m.add(Measure.valueOf(i, SI.SECOND));
			m.add(Measure.valueOf(200, SI.SECOND));
			m.add(Measure.valueOf(10 - i, SI.SECOND));

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
