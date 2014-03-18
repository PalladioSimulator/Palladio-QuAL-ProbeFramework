package de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2;

import java.util.Map;

import org.palladiosimulator.edp2.models.ExperimentData.MetricSetDescription;

import de.uka.ipd.sdq.pipesandfilters.framework.MetaDataInit;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2.launch.EDP2Config;

/**
 * This class contains all meta data information necessary for EDP2 data. This
 * includes the specification of an EDP2 experimentGroup, experimentSetting and
 * experimentGroup.
 * 
 * @author Baum, Sebastian Lehrig
 * 
 */
public class EDP2MetaDataInit extends MetaDataInit {

	/**
	 * The constructor of EDP2MetaDataInit. This constructor also instantiate
	 * the experiment setting, experiment setting and experiment group.
	 * 
	 * @param measuredMetrics A list of all measured metrics of a calculator
	 * @param edp2Config An EDP2Config recorder configuration
     * @param metricName Name of the metric
     * @param measurementName Name of the measurement
     * @param experimentName Name of the experiment
     * @param experimentRunName Name of the experiment run 
     * @param modelElementID Unique ID of the model element
     * @param executionResultTypes The mapping of numerical values to strings representing the
     *            possible execution results
	 */
	public EDP2MetaDataInit(MetricSetDescription metricDescriptions,
			EDP2Config edp2Config,
			String experimentName,
			String experimentRunName, String modelElementID,
			Map<Integer, String> executionResultTypes) {
		super(metricDescriptions, edp2Config,
				experimentName, experimentRunName, modelElementID, executionResultTypes);
	}
}
