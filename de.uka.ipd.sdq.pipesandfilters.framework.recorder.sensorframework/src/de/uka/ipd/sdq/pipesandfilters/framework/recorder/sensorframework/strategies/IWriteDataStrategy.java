package de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.strategies;

import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import de.uka.ipd.sdq.pipesandfilters.framework.MetaDataInit;

public interface IWriteDataStrategy {

	public void initialise(MetaDataInit metaData);
	
	public void writeData(List<Measure<?, ? extends Quantity>> data);
	
}
