import java.util.List;

class Literal extends LogicalOperation {
	public boolean isNegated;

	public Literal(List<LogicalOperation> components) {
		super(components);
		this.isNegated = false;
		operation = Operation.LITERAL;
	}

	public Literal(List<LogicalOperation> components, boolean isNegated) {
		super(components);
		this.isNegated = isNegated;
		operation = Operation.LITERAL;
	}

	LogicalOperation negate() {

		isNegated = !isNegated;
		return this;
	}

	public String toString() {
		String s = "";

		if (isNegated) {
			s = "Negated";
		}
		return operation + s;
	}
}