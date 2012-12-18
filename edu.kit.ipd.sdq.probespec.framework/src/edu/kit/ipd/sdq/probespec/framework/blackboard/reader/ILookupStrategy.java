package edu.kit.ipd.sdq.probespec.framework.blackboard.reader;

import java.util.List;

import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;

public interface ILookupStrategy {

    Iterable<List<IMeasurementContext>> lookup(IMeasurementContext... contexts);

}
