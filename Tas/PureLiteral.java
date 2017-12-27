package Tas;

import java.util.HashSet;
import java.util.Set;

import prLogicalElements.Literal;
import prLogicalElements.Operation;
import prLogicalElements.OperationType;

public class PureLiteral {
	public static void removePureLiterals(Operation operation) {
		Set<Literal> pureLiterals = getPureLiterals(operation);
		removeLiterals(operation, pureLiterals);
	}

	private static void removeLiterals(Operation operation, Set<Literal> literals) {
		switch (operation.type) {
		case OR:
			for (Literal literal : literals) {
				if(operation.components.contains(literal)) {
					operation.type = OperationType.T;
					operation.components.clear();
					break;
				}
			}
			break;
		case AND:
			for (Literal literal : literals) {
				operation.components.remove(literal);			
			}
			if(operation.components.size() == 0) {
				operation.type = OperationType.T;
			}
			break;
		default: 
			throw new RuntimeException("Operation seems not to be in negative normal form: " + operation);
		}
		
		if(operation.type != OperationType.T) {
			for (Operation childOperation : operation.getOperations()) {
				removeLiterals(childOperation, literals);				
			}					
		}
	}
	
	public static Set<Literal> getPureLiterals(Operation operation) {
		Set<Literal> pureLiterals = new HashSet<>();
		Set<Literal> literals = operation.getLiterals();
		for (Literal literal : literals) {
			if (!literals.contains(opositLiteralOf(literal))) {
				pureLiterals.add(literal);
			}
		}
		return pureLiterals;
	}
	
	public static Literal opositLiteralOf(Literal literal) {
		return new Literal(literal.symbol, !literal.isPositive());
	}
}
