package de.uka.ipd.sdq.probespec.framework;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;
import javax.measure.unit.Unit;

import org.jscience.mathematics.number.Number;
import org.jscience.mathematics.structure.GroupAdditive;

public class NumberMeasure<T extends Number<T>, Q extends Quantity> extends
		Measure<T, Q> implements GroupAdditive<NumberMeasure<T, Q>> {

	private static final long serialVersionUID = 1L;

	private final T value;

	private final Unit<Q> unit;

	public NumberMeasure(T value, Unit<Q> unit) {
		this.value = value;
		this.unit = unit;
	}

	@Override
	public double doubleValue(Unit<Q> unit) {
		// TODO Test this implementation!
        if ((unit == this.unit) || (unit.equals(this.unit)))
            return value.doubleValue();
        return this.unit.getConverterTo(unit).convert(value.doubleValue());
	}

	@Override
	public Unit<Q> getUnit() {
		return unit;
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public Measure<T, Q> to(Unit<Q> unit) {
		throw new RuntimeException("Not yet implemented.");
	}

	@Override
	public NumberMeasure<T,Q> opposite() {
		return new NumberMeasure<T,Q>(value.opposite(), unit);
	}

	@Override
	public NumberMeasure<T,Q> plus(NumberMeasure<T,Q> that) {
		if ((unit == that.getUnit()) || (unit.equals(that.unit)))
            return new NumberMeasure<T,Q>(value.plus(that.value), unit);
		throw new RuntimeException("Unit conversion is not yet implemented.");
		
		// TODO When using the abstract UnitConverter, the accuracy is lost
		// because the convert method works only for double values!
//        return unit.getConverterTo(unit).convert(value.doubleValue());
	}
	
	public NumberMeasure<T,Q> minus(NumberMeasure<T,Q> that) {
		if ((unit == that.getUnit()) || (unit.equals(that.unit)))
            return new NumberMeasure<T,Q>(value.minus(that.value), unit);
		throw new RuntimeException("Unit conversion is not yet implemented.");
	}

	@Override
	public Object copy() {
		throw new RuntimeException("Not yet implemented");
	}

}
