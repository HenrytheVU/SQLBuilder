package sqlbuilder.function;

import java.util.Collections;

public class NumericFunction {

	public static Function abs(String col) {
		return new Function("ABS(" + col + ")", Collections.emptyList());
	}

	public static Function ceiling(String col) {
		return new Function("CEILING(" + col + ")", Collections.emptyList());
	}

	public static Function count(String col) {
		return new Function("COUNT(" + col + ")", Collections.emptyList());
	}

	public static Function avg(String col) {
		return new Function("AVG(" + col + ")", Collections.emptyList());
	}
}
