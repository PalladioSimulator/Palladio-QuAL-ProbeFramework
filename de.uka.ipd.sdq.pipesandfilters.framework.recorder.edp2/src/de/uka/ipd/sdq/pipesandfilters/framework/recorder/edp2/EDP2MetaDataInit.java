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

	/** Repository where data should be stored. */
    private Repository repository;
    
    /** This experiment's experiment group that is attached to the repository. */
    private ExperimentGroup experimentGroup;
    
    /** This experiment's experiment setting that is attached to the experiment group and links to requested EDP2Measures. */
    private ExperimentSetting experimentSetting;
    
    /** This experiment's run, attached to the experiment setting. */
	private ExperimentRun experimentRun;
	
	/**
	 * The constructor of EDP2MetaDataInit. This constructor also instantiate
	 * the experiment setting, experiment setting and experiment group.
	 * 
	 * @param measuredMetrics
	 *            A vector of all measured metrics of a calculator.
	 * @param recorderConfiguration
	 * 			  An EDP2Config recorder configuration.
	 */
	public EDP2MetaDataInit(Vector<MeasurementMetric> measuredMetrics, IRecorderConfiguration recorderConfiguration) {
		this(measuredMetrics, recorderConfiguration, null);
	}


	/**
	 * The constructor of EDP2MetaDataInit. This constructor also instantiate
	 * the experiment setting, experiment setting and experiment group.
	 * 
	 * @param measuredMetrics
	 *            A vector of all measured metrics of a calculator.
	 * @param recorderConfiguration
	 * 			  An EDP2Config recorder configuration.
	 * @param executionResultTypes
	 * 			  Execution result types of an EDP2 run.
	 */
    public EDP2MetaDataInit(Vector<MeasurementMetric> measuredMetrics,
            IRecorderConfiguration recorderConfiguration, Map<Integer, String> executionResultTypes) {
        super(measuredMetrics, recorderConfiguration, executionResultTypes);        
        
        experimentRun = ExperimentDataFactory.eINSTANCE.createExperimentRun();
        experimentRun.setStartTime(new Date());
        
        experimentSetting = ExperimentDataFactory.eINSTANCE.createExperimentSetting();
        experimentSetting.getExperimentRuns().add(experimentRun);
        
        experimentGroup = ExperimentDataFactory.eINSTANCE.createExperimentGroup();
        experimentGroup.getExperimentSettings().add(experimentSetting);
        
        initRepository(recorderConfiguration);
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
