package src.prSemanticTableau;

import java.util.List;
import java.util.Set;

import src.prLogicalElements.*;

public class SemanticTableau {
	
	private static enum Type { ALPHA, BETA };
	
	public static boolean checkSatisfacibility(Element logicalElement) {
		logicalElement.signar();
		//Do some magic
		
		
		return false;
	}
	
	private static Type checkOperationType(Operation operation) {
		return (operation.type == OperationType.AND)? Type.ALPHA : Type.BETA; 
	}
	
	private boolean isLiteral(Element element) {
		return element instanceof Literal;
	}
}


class TableauTree {
	
	Node root;
	
	class Node {
		Set<Literal> literals;
		List<Operation> operations;
		public Node left;
		public Node right;
		
		
		public void addOperation(Operation op) {
			this.operations.add(op);
		}
		
		public void removeOperation(int index) {
			this.operations.remove(index);
		}
		
		public void addLiteral(Literal l) {
			this.literals.add(l);
		}
		
		public boolean checkIfNodeContainsNegatedOf(Literal literal) {
			return literals.contains(new Literal(literal.symbol, !literal.isPositive()));
		}
	}
}