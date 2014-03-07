package de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2;

import java.util.Date;

import de.uka.ipd.sdq.edp2.impl.Measurement;
import de.uka.ipd.sdq.edp2.impl.MeasurementsUtility;
import de.uka.ipd.sdq.edp2.impl.RepositoryManager;
import de.uka.ipd.sdq.edp2.models.ExperimentData.BaseMetricDescription;
import de.uka.ipd.sdq.edp2.models.ExperimentData.Description;
import de.uka.ipd.sdq.edp2.models.ExperimentData.Edp2Measure;
import de.uka.ipd.sdq.edp2.models.ExperimentData.ExperimentDataFactory;
import de.uka.ipd.sdq.edp2.models.ExperimentData.ExperimentGroup;
import de.uka.ipd.sdq.edp2.models.ExperimentData.ExperimentRun;
import de.uka.ipd.sdq.edp2.models.ExperimentData.ExperimentSetting;
import de.uka.ipd.sdq.edp2.models.ExperimentData.Measurements;
import de.uka.ipd.sdq.edp2.models.ExperimentData.MeasurementsRange;
import de.uka.ipd.sdq.edp2.models.ExperimentData.MetricSetDescription;
import de.uka.ipd.sdq.edp2.models.ExperimentData.Monotonic;
import de.uka.ipd.sdq.edp2.models.ExperimentData.NumericalBaseMetricDescription;
import de.uka.ipd.sdq.edp2.models.ExperimentData.PersistenceKindOptions;
import de.uka.ipd.sdq.edp2.models.Repository.Repository;
import de.uka.ipd.sdq.pipesandfilters.framework.MeasurementMetric;
import de.uka.ipd.sdq.pipesandfilters.framework.MetaDataInit;
import de.uka.ipd.sdq.pipesandfilters.framework.PipeData;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2.launch.EDP2Config;

/**
 * This abstract class provides methods necessary to write raw or aggregated measurements to the EDP2.
 * It follows the typical three steps of the ProbeSpec Framework:
 * 
 * 1) initialize(MetaDataInit metaData)
 *    Sets up the whole experiment by specifying the EDP2 repository, an experiment group,
 *    EDP2Measure objects, an ExperimentSetting, an ExperimentRun, and Measurements.
 * 
 * 2) writeData(PipeData pipeData)
 *    Writes measurements into EDP2.
 * 
 * 3) flush()
 *    Ends the experiment after writing.
 * 
 * @author Baum, Sebastian Lehrig
 * 
 */
public abstract class Edp2WriteStrategy {

    /** EDP2 Repository where data should be stored. */
    protected static Repository repository;

    /** EDP2 experiment group. */
    protected static ExperimentGroup experimentGroup;

    /** EDP2 experiment setting. */
    protected static ExperimentSetting experimentSetting;

    /** EDP2 experiment run. */
    protected static ExperimentRun experimentRun;

    /** String holding ProbeSpec's experiment run name */
    protected static String experimentRunName;

    /** EDP2 metric of the measured data. */
    protected static MetricSetDescription metricSetDescription;

    /** EDP2 measure. */
    protected static Edp2Measure measure;

    /** EDP2 measurements. */
    protected static Measurements measurements;

    /** EDP2 measurements range. */
    protected static MeasurementsRange measurementsRange;

    /**
     * The constructor of Edp2WriteStrategy.
     */
    public Edp2WriteStrategy() {
    }

    /**
     * In this method, an EDP2 experiment run is prepared by initializing
     * EDP2's MeasurementRange.
     */
    protected abstract void prepareExperimentRun();

    /**
     * This method should end the current experiment and close the data output
     * stream.
     */
    public abstract void flush();

    /**
     * The initializing meta data provided by the recorder is used to define all
     * EDP2 metrics and the experiment setup.
     * 
     * @param metaData The meta data provided by the recorder; has to be of type EDP2MetaDataInit
     */
    public void initialize(final MetaDataInit metaData) {
        // Initialize EDP2-specific recorder objects
        final EDP2MetaDataInit edp2MetaData = getEDP2MetaDataInit(metaData);
        final EDP2Config edp2Config = getEDP2RecorderConfiguration(edp2MetaData);

        // Initialize static EDP2 experiment elements
        initializeRepository(edp2Config);
        initializeExperimentGroup(edp2MetaData);
        initializeExperimentSetting();
        initializeExperimentRun(edp2MetaData);
        initializeMetricSetDescription(edp2MetaData);
        initializeEDP2Measure(edp2MetaData);
        initializeMeasurements(edp2MetaData);

        // Call template method for setting up MeasurementsRange
        prepareExperimentRun();
    }

    /**
     * Get EDP2MetaDataInit object.
     * 
     * @param metaData A MetaDataInit to be casted to EDP2MetaDataInit
     * @return An EDP2MetaDataInit object
     */
    private EDP2MetaDataInit getEDP2MetaDataInit(final MetaDataInit metaData) {
        if (!(metaData instanceof EDP2MetaDataInit)) {
            throw new IllegalArgumentException(
                    "Argument metaData must be of the type EDP2MetaDataInit");
        }
        final EDP2MetaDataInit edp2MetaData = (EDP2MetaDataInit) metaData;
        return edp2MetaData;
    }

    /**
     * Get EDP2 recorder configuration object.
     * 
     * @param edp2MetaData An EDP2MetaDataInit object to get the recorder from
     * @return An EDP2 recorder configuration
     */
    private EDP2Config getEDP2RecorderConfiguration(
            final EDP2MetaDataInit edp2MetaData) {
        final EDP2Config edp2Config = (EDP2Config) edp2MetaData.getRecorderConfiguration();
        return edp2Config;
    }

    /**
     * Initializes an EDP2 Repository based on the given recorder configuration.
     * 
     * @param edp2Config Recorder configuration for EDP2
     */
    private static void initializeRepository(final EDP2Config edp2Config) {
        final String repositoryID = edp2Config.getRepositoryID();
        if (repository == null || !repository.getUuid().equals(repositoryID)) {
            repository = RepositoryManager.getRepositoryFromUUID(repositoryID);
            experimentGroup = null;
        }
    }

    /**
     * Initializes an EDP2 ExperimentGroup if not present.
     * @param edp2MetaData
     */
    private static void initializeExperimentGroup(final EDP2MetaDataInit edp2MetaData) {
        if(experimentGroup == null || !experimentGroup.getPurpose().equals(edp2MetaData.getExperimentName())) {
            experimentGroup = ExperimentDataFactory.eINSTANCE.createExperimentGroup();
            experimentGroup.setPurpose(edp2MetaData.getExperimentName());
            repository.getExperimentGroups().add(experimentGroup);
            experimentSetting = null;
        }
    }

    /**
     * Initializes an EDP2 ExperimentSetting if not present.
     */
    private static void initializeExperimentSetting() {
        if(experimentSetting == null) {
            experimentSetting = ExperimentDataFactory.eINSTANCE.createExperimentSetting();
            experimentSetting.setDescription("Basic Experiment Setting"); // TODO Enable sensitivity Analysis here?
            experimentGroup.getExperimentSettings().add(experimentSetting);
            experimentRun = null;
        }
    }

    /**
     * Initializes an EDP2 ExperimentRun if not present.
     */
    private static void initializeExperimentRun(final EDP2MetaDataInit edp2MetaData) {
        if(experimentRun == null || !experimentRunName.equals(edp2MetaData.getExperimentRunName())) {
            experimentRun = ExperimentDataFactory.eINSTANCE.createExperimentRun();
            experimentRun.setStartTime(new Date());
            experimentSetting.getExperimentRuns().add(experimentRun);
            experimentRunName = edp2MetaData.getExperimentRunName();
        }
    }

    /**
     * Initialize metric set description.
     * 
     * @param edp2MetaData Meta data object that specifies the metric of interest
     */
    private static void initializeMetricSetDescription(final EDP2MetaDataInit edp2MetaData) {
        // TODO Lehrig: Add support for UUIDs into ProbeSpec. Then, we wouldn't have to assume unique names here.
        final String metricName = edp2MetaData.getMetricName();

        // Find existing description based on metric name (assumed to be unique)
        for(final Description d: repository.getDescriptions()) {
            if(d.getName().equals(metricName)) {
                if(d instanceof MetricSetDescription) {
                    metricSetDescription = (MetricSetDescription) d;
                    return;
                }
            }
        }

        // Create new metric set description
        metricSetDescription = ExperimentDataFactory.eINSTANCE.createMetricSetDescription();
        metricSetDescription.setName(metricName);
        metricSetDescription.setRepository(repository);

        // Add subsumed metrics
        for (final MeasurementMetric measuredObject : edp2MetaData.getMeasuredMetrics()) {
            final String subsumedMetricName = measuredObject.getName();
            BaseMetricDescription desc = null;

            // Find existing subsumed metric based on metric name (assumed to be unique)
            for(final Description d: repository.getDescriptions()) {
                if(d.getName().equals(subsumedMetricName)) {
                    if(d instanceof BaseMetricDescription) {
                        desc = (BaseMetricDescription) d;
                        break;
                    }
                }
            }

            // Create new subsumed metric if it was not found
            if (desc == null) {
                desc = convertMeasurementMetricToBaseMetricDescription(measuredObject);
                repository.getDescriptions().add(desc);
            }

            // link to metric set description
            metricSetDescription.getSubsumedMetrics().add(desc);
        }
    }

    /**
     * Initialize EDP2 measure.
     * 
     * @param edp2MetaData Meta data object that holds the object to measure
     */
    private static void initializeEDP2Measure(final EDP2MetaDataInit edp2MetaData) {
        // Important: Identifiers are not supported by the probespec so far
        // because ordinal values are used instead to represent nominal values.
        // If identifiers should be allowed, the initial identifier must
        // be set here.
        final String measuredObject = edp2MetaData.getMeasurementName();

        // Check for existing Edp2Measures in the experimentGroup
        for(final Edp2Measure edp2Measure : experimentGroup.getMeasure()) {
            if(edp2Measure.getMetric().equals(metricSetDescription) && edp2Measure.getMeasuredObject().equals(measuredObject)) {
                measure = edp2Measure;
                return;
            }
        }

        // Create new Edp2Measure
        measure = ExperimentDataFactory.eINSTANCE.createEdp2Measure();
        measure.setMeasuredObject(measuredObject);
        measure.setMetric(metricSetDescription);
        measure.setExperimentGroup(experimentGroup);
        measure.getExperimentSettings().add(experimentSetting);
    }

    /**
     * Initialize EDP2 measurements.
     * 
     * @param edp2MetaData Meta data object that holds the model element ID to measure
     */
    private static void initializeMeasurements(final EDP2MetaDataInit edp2MetaData) {
        measurements = ExperimentDataFactory.eINSTANCE.createMeasurements();
        measurements.setMeasure(measure);
        measurements.getAdditionalInformation().put("ModelElementID",
                edp2MetaData.getModelElementID());

        experimentRun.getMeasurements().add(measurements);
    }

    /**
     * Converts a MeasurementMetric object to a BaseMetricDescription object.
     * TODO Lehrig: check whether NumericalBaseMetricDescription is the only case or whether TextualBaseMetricDescription can also occur.
     * 
     * @param measuredObject
     *            The MeasurementMetric object.
     * @return The converted BaseMetricDescription object.
     */
    protected static BaseMetricDescription convertMeasurementMetricToBaseMetricDescription(
            final MeasurementMetric measuredObject) {
        final NumericalBaseMetricDescription desc = ExperimentDataFactory.eINSTANCE.createNumericalBaseMetricDescription();

        // CaptureType:
        if (measuredObject.getCaptureType() == de.uka.ipd.sdq.pipesandfilters.framework.CaptureType.NATURAL_NUMBER) {
            desc
            .setCaptureType(de.uka.ipd.sdq.edp2.models.ExperimentData.CaptureType.INTEGER_NUMBER);
        } else if (measuredObject.getCaptureType() == de.uka.ipd.sdq.pipesandfilters.framework.CaptureType.REAL_NUMBER) {
            desc
            .setCaptureType(de.uka.ipd.sdq.edp2.models.ExperimentData.CaptureType.REAL_NUMBER);
        }
        else {
            throw new IllegalArgumentException("Unsupported CaptureType: "+measuredObject.getCaptureType());
        }

        // DefaultUnit:
        desc.setDefaultUnit(measuredObject.getUnit());

        // PersistenceKind:
        desc.setPersistenceKind(PersistenceKindOptions.BINARY_PREFERRED);

        // Monotonic:
        if (measuredObject.isStrongMonotonic()) {
            desc.setMonotonic(Monotonic.STRONG);
        } else if (measuredObject.isMonotonic()) {
            desc.setMonotonic(Monotonic.YES);
        } else {
            desc.setMonotonic(Monotonic.NO);
        }

        // Name:
        desc.setName(measuredObject.getName());

        // Scale:
        if (measuredObject.getScale() == de.uka.ipd.sdq.pipesandfilters.framework.Scale.INTERVAL) {
            desc.setScale(de.uka.ipd.sdq.edp2.models.ExperimentData.Scale.INTERVAL);
        } else if (measuredObject.getScale() == de.uka.ipd.sdq.pipesandfilters.framework.Scale.NOMINAL) {
            desc.setScale(de.uka.ipd.sdq.edp2.models.ExperimentData.Scale.NOMINAL);
        } else if (measuredObject.getScale() == de.uka.ipd.sdq.pipesandfilters.framework.Scale.ORDINAL) {
            desc.setScale(de.uka.ipd.sdq.edp2.models.ExperimentData.Scale.ORDINAL);
        } else if (measuredObject.getScale() == de.uka.ipd.sdq.pipesandfilters.framework.Scale.RATIO) {
            desc.setScale(de.uka.ipd.sdq.edp2.models.ExperimentData.Scale.RATIO);
        }

        // Description:
        desc.setTextualDescription(measuredObject.getDescription());

        return desc;
    }



    /**
     * This method writes given measurement data to the EDP2.
     * 
     * FIXME Lehrig: I just hacked a measurement object into that code (before there was just the Object[] array). No idea whether this is correct.
     * FIXME Lehrig: For Waiting & Holding Time metrics, an exception is thrown (java.lang.IndexOutOfBoundsException for setMeasuredValue)
     */
    public void writeData(final PipeData pipeData) {
        final Measurement measurement = new Measurement(metricSetDescription);

        for(int i = 0; i < pipeData.getTupleSize(); i++) {
            measurement.setMeasuredValue(i, pipeData.getTupleElement(i));
        }
        MeasurementsUtility.storeMeasurement(measurements, measurement);
    }
}
