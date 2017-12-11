import java.util.List;

class OrOperation extends LogicalOperation {
	public OrOperation(List<LogicalOperation> components) {
		super(components);
		operation = Operation.OR;
	}

	LogicalOperation negate() {
		operation = Operation.AND;
		components = negateAll(components);

		return this;
	}

	public String toString() {
		return operation + components.toString();
	}
}