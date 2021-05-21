package sqlbuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class InsertOrUpdateInto extends AbstractQuery {
	public InsertOrUpdateInto(StringBuilder query, List<Object> params) {
		super(query, params);
	}

	public UsingValues usingValues(Object... values) {
		query.append(" USING (VALUES (")
			.append(Stream.of(values).map(v -> "?").collect(joining(", ")))
			.append(")) AS MRG");

		final List<Object> sanitizedValues = Stream.of(values).map(v -> {
			if (v instanceof LocalDate) {
				v = v.toString();
			}
			return v;
		}).collect(toList());

		params.addAll(sanitizedValues);
		return new UsingValues(query, params);
	}
}
