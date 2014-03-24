package de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2;

import java.util.Date;

import org.palladiosimulator.edp2.impl.Measurement;
import org.palladiosimulator.edp2.impl.MeasurementsUtility;
import org.palladiosimulator.edp2.impl.RepositoryManager;
import org.palladiosimulator.edp2.models.ExperimentData.Description;
import org.palladiosimulator.edp2.models.ExperimentData.Edp2Measure;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataFactory;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentGroup;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentRun;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentSetting;
import org.palladiosimulator.edp2.models.ExperimentData.Measurements;
import org.palladiosimulator.edp2.models.ExperimentData.MeasurementsRange;
import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;
import org.palladiosimulator.edp2.models.ExperimentData.MetricSetDescription;
import org.palladiosimulator.edp2.models.Repository.Repository;

import de.uka.ipd.sdq.pipesandfilters.framework.recorder.Recorder;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.launch.IRecorderConfiguration;

/**
 * This abstract class provides methods necessary to write raw or aggregated measurements to the
 * EDP2. It follows the typical three steps of the ProbeSpec Framework:
 * 
 * 1) initialize(MetaDataInit metaData) Sets up the whole experiment by specifying the EDP2
 * repository, an experiment group, EDP2Measure objects, an ExperimentSetting, an ExperimentRun, and
 * Measurements.
 * 
 * 2) writeData(PipeData pipeData) Writes measurements into EDP2.
 * 
 * 3) flush() Ends the experiment after writing.
 * 
 * @author Baum, Sebastian Lehrig
 * 
 */
public abstract class AbstractEDP2Recorder extends Recorder {

    /** EDP2 Repository where data should be stored. */
    protected Repository repository;

    /** EDP2 experiment group. */
    protected ExperimentGroup experimentGroup;

    /** EDP2 experiment setting. */
    protected ExperimentSetting experimentSetting;

    /** EDP2 experiment run. */
    protected ExperimentRun experimentRun;

    /** String holding ProbeSpec's experiment run name */
    protected String experimentRunName;

    /** EDP2 metric of the measured data. */
    protected MetricDescription metricDescription;

    /** EDP2 measure. */
    protected Edp2Measure measure;

    /** EDP2 measurements. */
    protected Measurements measurements;

    /** EDP2 measurements range. */
    protected MeasurementsRange measurementsRange;

    /**
     * The constructor of Edp2WriteStrategy.
     */
    public AbstractEDP2Recorder() {
    }

    /**
     * In this method, an EDP2 experiment run is prepared by initializing EDP2's MeasurementRange.
     */
    protected abstract void prepareExperimentRun();

    /**
     * This method should end the current experiment and close the data output stream.
     */
    @Override
    public abstract void flush();

    /**
     * The initializing meta data provided by the recorder is used to define all EDP2 metrics and
     * the experiment setup.
     * 
     * @param metaData
     *            The meta data provided by the recorder; has to be of type EDP2MetaDataInit
     */
    @Override
    public void initialize(final IRecorderConfiguration recorderConfiguration) {
        // Initialize EDP2-specific recorder objects
        final EDP2RecorderConfiguration edp2RecorderConfig = (EDP2RecorderConfiguration) recorderConfiguration;

        // Initialize static EDP2 experiment elements
        initializeRepository(edp2RecorderConfig);
        initializeExperimentGroup(edp2RecorderConfig);
        initializeExperimentSetting();
        initializeExperimentRun(edp2RecorderConfig);
        initializeMetricSetDescription(edp2RecorderConfig);
        initializeEDP2Measure(edp2RecorderConfig);
        initializeMeasurements(edp2RecorderConfig);

        // Call template method for setting up MeasurementsRange
        prepareExperimentRun();
    }

    /**
     * Initializes an EDP2 Repository based on the given recorder configuration.
     * 
     * @param edp2Config
     *            Recorder configuration for EDP2
     */
    private void initializeRepository(final EDP2RecorderConfiguration edp2Config) {
        final String repositoryID = edp2Config.getRepositoryID();
        if (repository == null || !repository.getUuid().equals(repositoryID)) {
            repository = RepositoryManager.getRepositoryFromUUID(repositoryID);
            experimentGroup = null;
        }
    }

    /**
     * Initializes an EDP2 ExperimentGroup if not present.
     * 
     * @param edp2MetaData
     */
    private void initializeExperimentGroup(final EDP2RecorderConfiguration edp2Config) {
        if (experimentGroup == null || !experimentGroup.getPurpose().equals(edp2Config.getExperimentName())) {
            experimentGroup = ExperimentDataFactory.eINSTANCE.createExperimentGroup();
            experimentGroup.setPurpose(edp2Config.getExperimentName());
            repository.getExperimentGroups().add(experimentGroup);
            experimentSetting = null;
        }
    }

    /**
     * Initializes an EDP2 ExperimentSetting if not present.
     */
    private void initializeExperimentSetting() {
        if (experimentSetting == null) {
            experimentSetting = ExperimentDataFactory.eINSTANCE.createExperimentSetting();
            experimentSetting.setDescription("Basic Experiment Setting"); // TODO Enable sensitivity
            // Analysis here?
            experimentGroup.getExperimentSettings().add(experimentSetting);
            experimentRun = null;
        }
    }

    /**
     * Initializes an EDP2 ExperimentRun if not present.
     */
    private void initializeExperimentRun(final EDP2RecorderConfiguration edp2MetaData) {
        if (experimentRun == null || !experimentRunName.equals(edp2MetaData.getExperimentRunName())) {
            experimentRun = ExperimentDataFactory.eINSTANCE.createExperimentRun();
            experimentRun.setStartTime(new Date());
            experimentSetting.getExperimentRuns().add(experimentRun);
            experimentRunName = edp2MetaData.getExperimentRunName();
        }
    }

    /**
     * Initialize metric set description.
     * 
     * @param edp2MetaData
     *            Meta data object that specifies the metric of interest
     */
    private void initializeMetricSetDescription(final EDP2RecorderConfiguration edp2MetaData) {
        final MetricDescription msd = edp2MetaData.getRecorderAcceptedMetric();
        metricDescription = addMetricDescriptionToRepository(msd);
    }

    /**
     * @param msd
     * @return
     */
    private MetricDescription addMetricDescriptionToRepository(final MetricDescription msd) {
        // Find existing description based on metric UUID
        for (final Description d : repository.getDescriptions()) {
            if (d.getUuid().equals(msd.getUuid())) {
                return (MetricDescription) d;
            }
        }

        msd.setRepository(repository);
        if (msd instanceof MetricSetDescription) {
            for (final MetricDescription md : ((MetricSetDescription) msd).getSubsumedMetrics()) {
                addMetricDescriptionToRepository(md);
            }
        }
        return msd;
    }

    /**
     * Initialize EDP2 measure.
     * 
     * @param edp2MetaData
     *            Meta data object that holds the object to measure
     */
    private void initializeEDP2Measure(final EDP2RecorderConfiguration edp2MetaData) {
        // Important: Identifiers are not supported by the probespec so far
        // because ordinal values are used instead to represent nominal values.
        // If identifiers should be allowed, the initial identifier must
        // be set here.
        final String measuredObject = edp2MetaData.getRecorderAcceptedMetric().getTextualDescription();

        // Check for existing Edp2Measures in the experimentGroup
        for (final Edp2Measure edp2Measure : experimentGroup.getMeasure()) {
            if (edp2Measure.getMetric().equals(metricDescription)
                    && edp2Measure.getMeasuredObject().equals(measuredObject)) {
                measure = edp2Measure;
                return;
            }

        }

        // Create new Edp2Measure
        measure = ExperimentDataFactory.eINSTANCE.createEdp2Measure();
        measure.setMeasuredObject(measuredObject);
        measure.setMetric(metricDescription);
        measure.setExperimentGroup(experimentGroup);
        measure.getExperimentSettings().add(experimentSetting);
    }

    /**
     * Initialize EDP2 measurements.
     * 
     * @param edp2MetaData
     *            Meta data object that holds the model element ID to measure
     */
    private void initializeMeasurements(final EDP2RecorderConfiguration edp2MetaData) {
        measurements = ExperimentDataFactory.eINSTANCE.createMeasurements();
        measurements.setMeasure(measure);
        measurements.getAdditionalInformation().put("ModelElementID", edp2MetaData.getModelElementID());

        experimentRun.getMeasurements().add(measurements);
    }

    /**
     * This method writes given measurement data to the EDP2.
     */
    @Override
    public void writeData(final de.uka.ipd.sdq.probespec.framework.measurements.Measurement data) {
        final Measurement measurement = new Measurement(metricDescription);

        for (int i = 0; i < ((MetricSetDescription)metricDescription).getSubsumedMetrics().size(); i++) {
            measurement.setMeasuredValue(i, data.getMeasureForMetric(((MetricSetDescription)metricDescription).getSubsumedMetrics().get(i)));
        }

        MeasurementsUtility.storeMeasurement(measurements, measurement);
    }
}
