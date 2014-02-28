package de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2;

import java.util.Date;
import java.util.Map;
import java.util.Vector;

import javax.xml.bind.TypeConstraintException;

import de.uka.ipd.sdq.edp2.impl.RepositoryManager;
import de.uka.ipd.sdq.edp2.models.ExperimentData.ExperimentDataFactory;
import de.uka.ipd.sdq.edp2.models.ExperimentData.ExperimentGroup;
import de.uka.ipd.sdq.edp2.models.ExperimentData.ExperimentRun;
import de.uka.ipd.sdq.edp2.models.ExperimentData.ExperimentSetting;
import de.uka.ipd.sdq.edp2.models.Repository.Repository;
import de.uka.ipd.sdq.pipesandfilters.framework.MeasurementMetric;
import de.uka.ipd.sdq.pipesandfilters.framework.MetaDataInit;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2.launch.EDP2Config;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.launch.IRecorderConfiguration;

/**
 * This class contains all meta data information necessary for EDP2 data. This
 * includes the specification of an EDP2 experimentGroup, experimentSetting and
 * experimentGroup.
 * 
 * @author Baum, Sebastian Lehrig
 * 
 */
public class EDP2MetaDataInit extends MetaDataInit {

    private Repository repository;
    private ExperimentGroup experimentGroup;
    private ExperimentSetting experimentSetting;
	private ExperimentRun experimentRun;
	

	/**
	 * The constructor of EDP2MetaDataInit. This constructor also instantiate
	 * the experiment setting, experiment setting and experiment group.
	 * 
	 * @param measuredMetrics
	 *            A vector of all measured metrics of a calculator.
	 */
	public EDP2MetaDataInit(Vector<MeasurementMetric> measuredMetrics, IRecorderConfiguration recorderConfiguration) {
	    super(measuredMetrics, recorderConfiguration);
	    
	    initRepository(recorderConfiguration);
		experimentRun = ExperimentDataFactory.eINSTANCE.createExperimentRun();
		experimentRun.setStartTime(new Date());
		experimentSetting = ExperimentDataFactory.eINSTANCE.createExperimentSetting();
		experimentGroup = ExperimentDataFactory.eINSTANCE.createExperimentGroup();		
		repository.getExperimentGroups().add(experimentGroup);
	}
		

    public EDP2MetaDataInit(Vector<MeasurementMetric> measuredMetrics,
            IRecorderConfiguration recorderConfiguration, Map<Integer, String> executionResultTypes) {
        super(measuredMetrics, recorderConfiguration, executionResultTypes);
        
        initRepository(recorderConfiguration);
        experimentRun = ExperimentDataFactory.eINSTANCE.createExperimentRun();
        experimentRun.setStartTime(new Date());
        experimentSetting = ExperimentDataFactory.eINSTANCE.createExperimentSetting();
        experimentGroup = ExperimentDataFactory.eINSTANCE.createExperimentGroup();
        repository.getExperimentGroups().add(experimentGroup);
    }

	/**
	 * The constructor of EDP2MetaDataInit. This constructor also instantiate
	 * the experiment setting, experiment setting and experiment group.
	 * 
	 * @param measuredMetrics
	 *            A vector of all measured metrics of a calculator.
	 * @param metricName
	 *            The name of the metric that is measured.
	 * @param measurementName
	 *            The name of the performed measurement.
	 * @param experimentName
	 *            The name of the performed experiment.
	 */
	public EDP2MetaDataInit(Vector<MeasurementMetric> measuredObjects, IRecorderConfiguration recorderConfiguration,
			String metricName, String measurementName, String experimentName) {
	    super(measuredObjects, recorderConfiguration, metricName, measurementName, experimentName);

	    initRepository(recorderConfiguration);
		experimentRun = ExperimentDataFactory.eINSTANCE.createExperimentRun();
		experimentRun.setStartTime(new Date());
		experimentSetting = ExperimentDataFactory.eINSTANCE.createExperimentSetting();
		experimentSetting.setDescription(experimentName);
		experimentGroup = ExperimentDataFactory.eINSTANCE.createExperimentGroup();
		repository.getExperimentGroups().add(experimentGroup);
	}

	/**
	 * The constructor of EDP2MetaDataInit.
	 * 
	 * @param measuredMetrics
	 *            A vector of all measured metrics of a calculator.
	 * @param experimentGroup
	 *            The experiment group of the initialized EDP2 experiment run.
	 * @param experimentSetting
	 *            The experiment setting of the initialized EDP2 experiment run.
	 */
	public EDP2MetaDataInit(Vector<MeasurementMetric> measuredMetrics,
			ExperimentGroup experimentGroup, ExperimentSetting experimentSetting, IRecorderConfiguration recorderConfiguration) {
		super(measuredMetrics, recorderConfiguration);

		initRepository(recorderConfiguration);
		experimentRun = ExperimentDataFactory.eINSTANCE.createExperimentRun();
		experimentRun.setStartTime(new Date());
		this.experimentSetting = experimentSetting;
		setExperimentName(experimentSetting.getDescription());
		this.experimentGroup = experimentGroup;
		repository.getExperimentGroups().add(experimentGroup);
	}

	/**
	 * Initializes an EDP2 Repository based on the given recorder configuration.
	 * 
	 * @param recorderConfiguration Recorder configuration for EDP2 (type EDP2Config expected)
	 */
	private void initRepository(IRecorderConfiguration recorderConfiguration) {
	    if(recorderConfiguration == null) {
	        throw new NullPointerException("RecorderConfiguration null! EDP2 needs a recorderConfiguration to initialize a Repository!");
	    }
	    if(!(recorderConfiguration instanceof EDP2Config)) {
            throw new TypeConstraintException("RecorderConfiguration is of wrong type! EDP2 needs an EDP2Config!");
        }
	    
	    EDP2Config edp2Config = (EDP2Config) recorderConfiguration;
	    String repositoryID = edp2Config.getRepositoryID();

        this.repository = RepositoryManager.getRepositoryFromUUID(repositoryID);
    }
	
	/**
	 * Sets the name of the experiment.
	 * 
	 * @param experimentName
	 *            The name of the experiment.
	 */
	public void setExperimentName(String experimentName) {
		super.setExperimentName(experimentName);
		experimentSetting.setDescription(experimentName);
	}

	/**
	 * Returns the experiment run.
	 * 
	 * @return The experiment run object.
	 */
	public ExperimentRun getExperimentRun() {
		return experimentRun;
	}

	/**
	 * Returns the experiment setting.
	 * 
	 * @return The experiment setting object.
	 */
	public ExperimentSetting getExperimentSetting() {
		return experimentSetting;
	}

	/**
	 * Returns the experiment group.
	 * 
	 * @return The experiment group object.
	 */
	public ExperimentGroup getExperimentGroup() {
		return experimentGroup;
	}
}
