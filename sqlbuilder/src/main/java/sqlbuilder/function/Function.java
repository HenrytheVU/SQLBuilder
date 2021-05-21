package sqlbuilder.function;

import java.util.List;
import java.util.Objects;

public class Function {
    private final String function;
    private final List<Object> params;

    public Function(String function, List<Object> params) {
        this.function = function;
        this.params = Objects.requireNonNull(params);
    }

    public List<Object> getParams() {
        return this.params;
    }

    @Override
    public String toString() {
        return function;
    }

}
