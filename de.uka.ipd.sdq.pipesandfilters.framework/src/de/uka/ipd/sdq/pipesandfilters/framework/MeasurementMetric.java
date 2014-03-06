package de.uka.ipd.sdq.pipesandfilters.framework;

import javax.measure.quantity.Dimensionless;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

/**
 * This class holds information about the results that come from one single
 * ProbeSet.
 * 
 * @author Baum, Sebastian Lehrig, Steffen Becker
 * 
 */
public class MeasurementMetric {

	/**
	 * Capture Type value of a measured object
	 */
	private final CaptureType captureType;
	/**
	 * Specifies whether the measured values are monotonic
	 */
	private final boolean isMonotonic;
	/**
	 * Specifies whether the measured values are strong monotonic
	 */
	private final boolean isStrongMonotonic;
	/**
	 * The measurement unit
	 */
	private final Unit<?> unit;
	/**
	 * The name of the measured object
	 */
	private final String name;
	/**
	 * A textual description of what is measured
	 */
	private final String description;
	/**
	 * The scale of the measured object
	 */
	private final Scale scale;
	/**
	 * The value data type of the measured object, e.g., <code>Double.class</code> for time measurements
	 */
	private final Class<?> valueType;

	/**
	 * The constructor of MeasurementMetric, in which all necessary class
	 * members are set.
	 * 
	 * @param captureType The capture type of the metric.
	 * @param unit The unit of the metric.
	 * @param scale The measurement level of the metric.
	 */
	private MeasurementMetric(CaptureType captureType, boolean isMonotonic, boolean isStrongMonotonic, Unit<?> unit, String name, String description, Scale scale, Class<?> valueType) {
		super();
		
		if(!isMonotonic && isStrongMonotonic) {
			throw new RuntimeException("A metric cannot be strong monotonic but not not monotonic");
		}
		if(isMonotonic && scale == Scale.NOMINAL) {
			throw new RuntimeException("A metric cannot be monotonic and nominal scale at the same time");
		}
		if(name == null || description == null || unit == null || valueType == null) {
			throw new IllegalArgumentException("No null values allowed in the MeasurementMetric constructor");
		}
		
		this.captureType = captureType;
		this.isMonotonic = isMonotonic;
		this.isStrongMonotonic = isStrongMonotonic;		
		this.unit = unit;
		this.name = name;
		this.description = description;
		this.scale = scale;
		this.valueType = valueType;
	}
	
	/**
	 * Creates a new time metric for a given name and description. Time metrics are
	 * expressed in natural numbers and with seconds as unit. Moreover, they are
	 * ordinal. An example time metric is response time. 
	 * 
	 * @param name The name of the metric, e.g., "response time".
	 * @param description The description of the metric.
	 * @return A new MeasurementMetric for time.
	 */
	public static MeasurementMetric createNewTimeMetric(final String name, final String description) {
		return new MeasurementMetric(
				CaptureType.NATURAL_NUMBER,
				false, false, SI.SECOND,
				name, description, Scale.ORDINAL, Double.class);
	}

	/**
	 * Creates a new natural number metric for a given name and description. Natural number metrics are
	 * expressed in natural numbers and have a dimensionless unit. Moreover, they are
	 * ordinal. An example natural number metric is resource demand.
	 * 
	 * @param name The name of the metric, e.g., "resource demand".
	 * @param description The description of the metric.
	 * @return A new MeasurementMetric for state information.
	 */
	public static MeasurementMetric createNewNaturalNumberMetric(final String name, final String description) {
		return new MeasurementMetric(
				CaptureType.NATURAL_NUMBER,
				false, false, Dimensionless.UNIT,
				name, description, Scale.ORDINAL, Integer.class);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MeasurementMetric [captureType=" + captureType
				+ ", isMonotonic=" + isMonotonic + ", isStrongMonotonic="
				+ isStrongMonotonic + ", unit=" + unit + ", name=" + name
				+ ", description=" + description + ", scale=" + scale
				+ ", valueType=" + valueType + "]";
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((captureType == null) ? 0 : captureType.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + (isMonotonic ? 1231 : 1237);
		result = prime * result + (isStrongMonotonic ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((scale == null) ? 0 : scale.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		result = prime * result
				+ ((valueType == null) ? 0 : valueType.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		MeasurementMetric other = (MeasurementMetric) obj;
		if (captureType != other.captureType) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (isMonotonic != other.isMonotonic) {
			return false;
		}
		if (isStrongMonotonic != other.isStrongMonotonic) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (scale != other.scale) {
			return false;
		}
		if (unit == null) {
			if (other.unit != null) {
				return false;
			}
		} else if (!unit.equals(other.unit)) {
			return false;
		}
		if (valueType == null) {
			if (other.valueType != null) {
				return false;
			}
		} else if (!valueType.equals(other.valueType)) {
			return false;
		}
		return true;
	}

	/**
	 * Returns the capture type.
	 * 
	 * @return The capture type.
	 */
	public CaptureType getCaptureType() {
		return captureType;
	}

	/**
	 * Returns whether the measured values are monotonic.
	 * 
	 * @return True, if the measured values are monotonic, else false.
	 */
	public boolean isMonotonic() {
		return isMonotonic;
	}

	/**
	 * Returns whether the measured values are strong monotonic.
	 * 
	 * @return True, if the measured values are monotonic, else false.
	 */
	public boolean isStrongMonotonic() {
		return isStrongMonotonic;
	}

	/**
	 * Returns the unit of the measured object.
	 * 
	 * @return The Unit of the measured object.
	 */
	public Unit<?> getUnit() {
		return unit;
	}

	/**
	 * Returns the name of the measured object.
	 * 
	 * @return The name of the measured object.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns a textual description of the measured object.
	 * 
	 * @return A textual description of the measured object.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the measurement level of the measured object.
	 * 
	 * @return The measurement level of the measured object.
	 */
	public Scale getScale() {
		return scale;
	}
	
	/**
	 * Returns the value data type of the measured object.
	 * 
	 * @return The value data type of the measured object.
	 */
	public Class<?> getValueType() {
		return valueType;
	}
}
