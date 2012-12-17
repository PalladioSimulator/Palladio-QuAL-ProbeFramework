package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.util.List;

public interface ILookupStrategy {

    Iterable<List<IMeasurementContext>> lookup(IMeasurementContext... contexts);

}
