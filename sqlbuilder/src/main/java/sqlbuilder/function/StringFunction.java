package sqlbuilder.function;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class StringFunction {

	public static Function upper(String... params) {
		StringBuilder sb = new StringBuilder();
		String placeHolders = Arrays.stream(params).map(s -> "?").collect(Collectors.joining(" || "));
		sb.append("UPPER(").append(placeHolders).append(")");
		return new Function(sb.toString(), Arrays.asList(params));
	}

	public static Function upperCol(String... cols) {
		return new Function("UPPER(" + String.join(" || ", cols) + ")", Collections.emptyList());
	}

	public static Function lower(String... params) {
		StringBuilder sb = new StringBuilder();
		String placeHolders = Arrays.stream(params).map(s -> "?").collect(Collectors.joining(" || "));
		sb.append("LOWER(").append(placeHolders).append(")");
		return new Function(sb.toString(), Arrays.asList(params));
	}

	public static Function lowerCol(String... cols) {
		return new Function("LOWER(" + String.join(" || ", cols) + ")", Collections.emptyList());
	}
}
