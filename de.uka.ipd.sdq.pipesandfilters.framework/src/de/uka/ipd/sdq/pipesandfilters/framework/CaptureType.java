package de.uka.ipd.sdq.pipesandfilters.framework;

/**
 * An enumeration of the allowed capture types of measures identifying their
 * domain. Note: Identifiers are not supported yet.
 * 
 * @author Baum
 * 
 */
public enum CaptureType {
	NATURAL_NUMBER(Long.class), REAL_NUMBER(Double.class);

	/**
	 * The value data type of the measured object, e.g., <code>Double.class</code> for time measurements
	 */
	private final Class<?> valueType;

	CaptureType(Class<?> valueType) {
		this.valueType = valueType;
	}
	
	public Class<?> getValueType() {
		return valueType;
	}
}
