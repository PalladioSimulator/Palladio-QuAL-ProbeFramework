package de.uka.ipd.sdq.pipesandfilters.framework.recorder;

import java.util.Map;

import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;

import de.uka.ipd.sdq.pipesandfilters.framework.recorder.launch.IRecorderConfiguration;

public abstract class AbstractRecorderConfiguration implements IRecorderConfiguration {

    public static final String EXPERIMENT_NAME = "experimentName";
    public static final String RECORDER_ACCEPTED_METRIC = "recorderAcceptedMetric";
    public static final String EXPERIMENT_RUN_NAME = "experimentRunName";

    /**
     * This list should hold one MeasuredMetric with measurement information
     * for each tuple that is inducted to the pipe by the calculators.
     */
    private MetricDescription recorderAcceptedMetric;

    /**
     * The name of the experiment.
     */
    private String experimentName;

    /**
     * The name of the experiment run.
     */
    private String experimentRunName;

    @Override
    public void setConfiguration(final Map<String, Object> configuration) {
        experimentName = getValue(configuration, EXPERIMENT_NAME, String.class);
        experimentRunName = getValue(configuration, EXPERIMENT_RUN_NAME, String.class);
        recorderAcceptedMetric = getValue(configuration, RECORDER_ACCEPTED_METRIC, MetricDescription.class);
    }

    /**
     * @return the recorderAcceptedMetric
     */
    public final MetricDescription getRecorderAcceptedMetric() {
        return recorderAcceptedMetric;
    }

    /**
     * @param recorderAcceptedMetric the recorderAcceptedMetric to set
     */
    public final void setRecorderAcceptedMetric(final MetricDescription recorderAcceptedMetric) {
        this.recorderAcceptedMetric = recorderAcceptedMetric;
    }

    /**
     * @return the experimentName
     */
    public final String getExperimentName() {
        return experimentName;
    }

    /**
     * @param experimentName the experimentName to set
     */
    public final void setExperimentName(final String experimentName) {
        this.experimentName = experimentName;
    }

    /**
     * @return the experimentRunName
     */
    public final String getExperimentRunName() {
        return experimentRunName;
    }

    /**
     * @param experimentRunName the experimentRunName to set
     */
    public final void setExperimentRunName(final String experimentRunName) {
        this.experimentRunName = experimentRunName;
    }

    protected <T> T getValue(final Map<String, Object> configuration, final String configurationAttributeID, final Class<T> dataType) {
        @SuppressWarnings("unchecked")
        final
        T result = (T) configuration.get(configurationAttributeID);
        if (result == null) {
            throw new RuntimeException("Expected configuation entry not found");
        }
        if (!dataType.isInstance(result)) {
            throw new RuntimeException("Data in configuration does not have expected type");
        }

        return result;
    }
}
