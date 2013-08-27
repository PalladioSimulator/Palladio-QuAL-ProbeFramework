package edu.kit.ipd.sdq.probespec.palladio;

import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;
import edu.kit.ipd.sdq.probespec.framework.ITimestampGenerator;

public class PalladioTimestampBuilder implements ITimestampGenerator<Double> {

    private ISimulationControl simulationControl;
    
    public PalladioTimestampBuilder(ISimulationControl simulationControl) {
        this.simulationControl = simulationControl;
    }

    @Override
    public Double now() {
        return simulationControl.getCurrentSimulationTime();
    }
    
}
