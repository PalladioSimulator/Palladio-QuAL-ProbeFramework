package de.uka.ipd.sdq.pipesandfilters.framework.recorder;

import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorListener;


/**
 * This class is the super class of any recorder implementations. A recorder is
 * responsible of making the measurements persistent, using a specified
 * WriteStrategy. The measurements can either be aggregated before storing or be
 * stored as raw measurements.
 * 
 * @author Baum, Sebastian Lehrig
 * 
 */
public abstract class Recorder implements IWriteStrategy, ICalculatorListener {

	/**
	 * The default constructor for a recorder.
	 * 
	 * @param writeStrategy
	 *            The write strategy of the recorder.
	 */
	public Recorder() {
		super();
	}

	@Override
	public void calculated(List<Measure<?, ? extends Quantity>> resultTuple) {
		this.writeData(resultTuple);
	}
	
	@Override
    public void preUnregister() {
		this.flush();
	}
}
