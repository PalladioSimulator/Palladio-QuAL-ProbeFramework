package de.uka.ipd.sdq.probespec.framework;

public class AbstractProbeSampleFactory {

	private static IProbeSampleFactory probeSamplefactory;

	/**
	 * Returns the {@link IProbeSampleFactory} to be able to create and delete
	 * {@link ProbeSample}s.
	 * 
	 * @return  the {@link IProbeSampleFactory} to be able to create and delete
	 * {@link ProbeSample}s.
	 */
	public static IProbeSampleFactory getFactory() {
		return probeSamplefactory;
	}

	/**
	 * Sets the {@link IProbeSampleFactory} that shall be used globally to 
	 * create and delete {@link ProbeSample}s.
	 * 
	 * @param factory
	 */
	public static synchronized void setFactory(IProbeSampleFactory factory) {
		probeSamplefactory = factory;
	}
}
