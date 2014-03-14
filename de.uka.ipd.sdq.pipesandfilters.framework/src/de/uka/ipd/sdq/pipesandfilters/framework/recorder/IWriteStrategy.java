package de.uka.ipd.sdq.pipesandfilters.framework.recorder;

import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import de.uka.ipd.sdq.pipesandfilters.framework.MetaDataInit;

/**
 * A WriteStrategy is responsible for storing the measurements 
 * to any external, independent device
 *
 * @author pmerkle
 * @author Baum
 * @uathor Sebastian Lehrig
 */
public interface IWriteStrategy {

	/**
	 * This method contains meta data of the measurements to initialize the
	 * writer.
	 * 
	 * @param metaData
	 *            The meta data of the measurements.
	 */
	public abstract void initialize(MetaDataInit metaData);	

	/**
	 * Writes data into the storing devices.
	 * 
	 * @param data
	 *            The measurement that should be stored.
	 */
	public abstract void writeData(List<Measure<?, ? extends Quantity>> data);
	
	/**
	 * This method is called at the end of the writing process.
	 */
	public abstract void flush();
}
