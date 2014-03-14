package de.uka.ipd.sdq.pipesandfilters.framework.recorder;

import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import de.uka.ipd.sdq.pipesandfilters.framework.MetaDataInit;

public class ConsoleWriteStrategy implements IRawWriteStrategy {

	@Override
	public void flush() {
		// do nothing
	}

	@Override
	public void initialize(MetaDataInit metaData) {
//		System.out.println("Initialize WriteStrategy: " + metaData.)
	}

	@Override
	public void writeData(List<Measure<?, ? extends Quantity>> data) {
		String out = "";
		for (int i = 0; i<data.size(); i++) {
			out += data.get(i).toString() + "\t";
		}
		System.out.println(out);
	}

}
