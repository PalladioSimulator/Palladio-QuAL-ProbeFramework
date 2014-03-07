package de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2;

import java.util.Date;

import javax.measure.Measure;
import javax.measure.unit.SI;

import org.palladiosimulator.edp2.impl.DataNotAccessibleException;
import org.palladiosimulator.edp2.impl.MeasurementsUtility;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataFactory;
import org.palladiosimulator.edp2.models.ExperimentData.RawMeasurements;

import de.uka.ipd.sdq.pipesandfilters.framework.recorder.IRawWriteStrategy;

/**
 * This class provides methods necessary to write raw measurements to the EDP2.
 * 
 * @author Baum, Sebastian Lehrig
 */
public class Edp2RawWriteStrategy extends Edp2WriteStrategy implements IRawWriteStrategy {

    /** Shortcut to experiment data factory. */
    private final static ExperimentDataFactory experimentDataFactory = ExperimentDataFactory.eINSTANCE;

    /**
     * In this method, an EDP2 experiment run is prepared by initializing
     * EDP2's MeasurementRange
     */
    @Override
    protected void prepareExperimentRun() {
        measurementsRange = experimentDataFactory.createMeasurementsRange(measurements);
        final RawMeasurements rawMeasurements = experimentDataFactory.createRawMeasurements(measurementsRange);
        MeasurementsUtility.createDAOsForRawMeasurements(rawMeasurements);
    }

    /**
     * This method will end the current experiment and close the data output
     * stream.
     */
    @Override
    public void flush() {

        final long startTime = experimentRun.getStartTime().getTime();
        final long endTime = new Date().getTime();
        experimentRun.setDuration(Measure.valueOf(endTime - startTime,
                SI.SECOND));
        measurementsRange.setStartTime(Measure.valueOf(startTime, SI.SECOND));
        measurementsRange.setEndTime(Measure.valueOf(endTime, SI.SECOND));

        try {
            MeasurementsUtility.ensureClosedRepository(repository);
            MeasurementsUtility.ensureOpenRepository(repository);
        } catch (final DataNotAccessibleException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
