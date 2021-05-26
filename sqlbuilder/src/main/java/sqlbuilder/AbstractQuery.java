package sqlbuilder;

import sqlbuilder.exception.UnsupportedDataTypeException;
import sqlbuilder.function.Function;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractQuery {

	StringBuilder query;
	final List<Object> params;

	public AbstractQuery(StringBuilder query, List<Object> params) {
		this.query = query;
		this.params = params;
	}

	public List<Object> getParams() {
		return params;
	}

	public Object[] getParamObjects() {
		return params.toArray();
	}

	public String getQuery() {
		if (params.size() == 0) {
			return query.toString();
		}
		AtomicInteger counter = new AtomicInteger();
		return Stream.of(query.toString().split(" ")).map(s -> {
			if (s.contains("?")) {
				s = s.replace("?", toString(this.params.get(counter.getAndIncrement())));
			}
			return s;
		}).collect(Collectors.joining(" "));
	}

	public String getQueryWithPlaceHolders() {
		System.out.printf("INFO: %s%n", getQuery());
		return query.toString();
	}

	private String toString(Object obj) {
		if (obj == null) {
			return "null";
		}
		if (obj instanceof String) {
			return String.format("'%s'", obj.toString().replace("'", "''"));
		}
		if (obj instanceof LocalDate || obj instanceof Timestamp) {
			return String.format("'%s'", obj.toString());
		}
		if (obj instanceof Integer || obj instanceof Long || obj instanceof Short || obj instanceof Function || obj instanceof Boolean) {
			return obj.toString();
		}
		throw new UnsupportedDataTypeException(String.format("Not supported type: %s", obj.getClass()));
	}

	public String toString() {
		return this.query.toString();
	}
}
