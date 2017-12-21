package src.prLogicalElements;
import java.util.ArrayList;
import java.util.List;

public class Operation implements Element {

	public List<Operation> operations;
	public List<Literal> literals;
	public OperationType type;
	private boolean isPositive;

	public Operation(OperationType type, boolean isPositive) {
		this.type = type;
		this.operations = new ArrayList<>();
		this.literals = new ArrayList<>();
		this.isPositive = isPositive;
	}
	
	public int numberOfComponents() {
		return this.operations.size() + this.literals.size();
	}

	public Operation(OperationType type) {
		this(type, true);
	}

	public void addOperations(Operation ... operations) {
		for(Operation operation : operations) {
			this.operations.add(operation);
		}
	}
	

	
	public void addLiterals(String ... literals) {
		for(String literal : literals) {
			//Remove white spaces
			literal = literal.trim();
			//Starts by ¬?
			boolean positiveLiteral = (literal.charAt(0) != '¬');
			if(!positiveLiteral) {
				//If so, remove the ¬ symbol
				literal = literal.substring(1, literal.length()).trim();
			}
			this.literals.add(new Literal(literal, positiveLiteral));
		}
	}
	
	public void addLiterals(Literal ... literals) {
		for(Literal literal : literals) {
			this.literals.add(literal);
		}
	}
	
	public boolean isPositive() {
		return this.isPositive;
	}

	public void negate() {
		this.isPositive = !this.isPositive;
	}	

	public void negateType() {

		switch (this.type) {
		case AND:
			this.type = OperationType.OR;
			this.negateAllComponents();
			break;
		case OR:
			this.type = OperationType.AND;
			this.negateAllComponents();
			break;
//		Don't know how to implement this...
		case THEN:
			this.type = OperationType.AND;
			this.operations.get(1).negate();		//which goes first if we have an operation and a Literal??
			break;
		default:
			throw new RuntimeException("Operation not implemented");
		}

		this.isPositive = true;
	}

	public void signar() {
		//Check if it is negated or if it is a THEN operation
		
		if (!this.isPositive()) {
			this.negateType();
		} else if (this.type == OperationType.THEN) {
			this.type = OperationType.OR;
			this.operations.get(0).negate();
//			throw new RuntimeException("Operation not implemented");
		}
		
		//Signar all it's children
		
		for (Operation operation : this.operations) {
			operation.signar();
		}
		
		//Merge children of the same type
		
		List<Operation> mergedOperations = new ArrayList<>();

		for (Operation operation : this.operations) {
			if(this.type == operation.type) {
				this.literals.addAll(operation.literals);
				mergedOperations.addAll(operation.operations);
			} else {
				mergedOperations.add(operation);
			}
		}
		
		this.operations = mergedOperations;
	}

	public void negateAllComponents() {
		for (Operation operation : this.operations) {
			operation.negate();
		}
		for (Literal literal : this.literals) {
			literal.negate();
		}
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append((this.isPositive() ? "" : "¬") + "(");
		
		for (int i = 0; i < this.literals.size(); i++) {
			sb.append(this.literals.get(i));
			if (i < this.numberOfComponents() - 1) {
				sb.append(" " + type + " ");
			}
		}
		
		for (int i = 0; i < this.operations.size(); i++) {
			sb.append(this.operations.get(i));
			if (i + this.literals.size() < this.numberOfComponents() - 1) {
				sb.append(" " + type + " ");
			}
		}
		sb.append(")");
		return sb.toString();
	}
}
