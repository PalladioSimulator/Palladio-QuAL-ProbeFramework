package de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.strategies;

import java.util.HashMap;
import java.util.Map;

import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import de.uka.ipd.sdq.pipesandfilters.framework.recorder.launch.IRecorderConfiguration;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.SensorFrameworkRecorderConfiguration;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.SensorHelper;
import de.uka.ipd.sdq.probespec.framework.constants.MetricDescriptionConstants;
import de.uka.ipd.sdq.probespec.framework.measurements.Measurement;
import de.uka.ipd.sdq.sensorframework.entities.Experiment;
import de.uka.ipd.sdq.sensorframework.entities.ExperimentRun;
import de.uka.ipd.sdq.sensorframework.entities.State;
import de.uka.ipd.sdq.sensorframework.entities.StateSensor;
import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;

/**
 * Realizes a write strategy for reliability sensors.
 * 
 * @author brosch
 * 
 */
public class ExecutionResultWriteDataStrategy extends AbstractWriteDataStrategy {

    /**
     * Stores the dynamically created set of states.
     */
    private final HashMap<Integer, State> statesCache = new HashMap<Integer, State>();

    /**
     * Constructor for the strategy.
     * 
     * @param daoFactory
     *            the DAO factory
     * @param experiment
     *            the current experiment
     * @param run
     *            the simulation run
     */
    public ExecutionResultWriteDataStrategy(final IDAOFactory daoFactory,
            final Experiment experiment, final ExperimentRun run) {
        super(daoFactory, experiment, run);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.strategies
     * .
     * AbstractWriteDataStrategy#initialise(de.uka.ipd.sdq.pipesandfilters.framework
     * .MetaDataInit)
     */
    @Override
    public void initialise(final IRecorderConfiguration recorderConfiguration) {
        final SensorFrameworkRecorderConfiguration sensorFrameworkRecorderConfig = (SensorFrameworkRecorderConfiguration) recorderConfiguration;
        initStatesCache(sensorFrameworkRecorderConfig);
        initSensor(sensorFrameworkRecorderConfig.getRecorderAcceptedMetric().getTextualDescription());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.strategies
     * .IWriteDataStrategy#writeData(de.uka.ipd.sdq.pipesandfilters.framework.
     * PipeData)
     */
    @Override
    public void writeData(final Measurement data) {
        final Measure<Double, Duration> measurementTimeMeasure = data.getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        final Measure<Integer, Dimensionless> numericStateMeasure = data.getMeasureForMetric(MetricDescriptionConstants.CPU_STATE_METRIC);
        final double measurementTime = measurementTimeMeasure.doubleValue(SI.SECOND);
        final int stateId = numericStateMeasure.intValue(Dimensionless.UNIT);
        run.addStateMeasurement((StateSensor) sensor, statesCache.get(stateId),
                measurementTime);
    }

    /**
     * Finds or creates the success state for the sensor to be used by this
     * strategy.
     * 
     * @return the success state
     */
    private State findSuccessState() {

        // Assume that the success state is named "Success":
        for (final State state : statesCache.values()) {
            if (state.getStateLiteral().equals("Success")) {
                return state;
            }
        }

        // Assume that the success state has ID = 0:
        final State state = statesCache.get(0);
        if (state != null) {
            return state;
        }

        // Return an additional success state:
        int newID = 0;
        while(statesCache.containsKey(newID)){
            newID++;
        }
        final State newState = SensorHelper.createOrReuseState(daoFactory, "Success");
        statesCache.put(newID, newState);
        return newState;
    }

    /**
     * Initializes the state sensor to be used by the strategy.
     * 
     * @param sensorId
     *            the id of the sensor to be used
     */
    private void initSensor(final String sensorId) {
        sensor = SensorHelper.createOrReuseStateSensor(daoFactory, experiment,
                sensorId, findSuccessState());
        for (final State state : statesCache.values()) {
            if (!((StateSensor) sensor).getSensorStates().contains(state)) {
                ((StateSensor) sensor).addSensorState(state);
            }
        }
    }

    /**
     * Initializes the cache of execution result states.
     * 
     * @param metaData
     *            the meta data for the initialization of the strategy
     */
    private void initStatesCache(final SensorFrameworkRecorderConfiguration recorderConfiguration) {
        final Map<Integer, String> resultTypes = recorderConfiguration.getExecutionResultTypes();
        for (final Integer key : resultTypes.keySet()) {
            final State state = SensorHelper.createOrReuseState(daoFactory,
                    resultTypes.get(key));
            statesCache.put(key, state);
        }
    }
}
