package Tas;

import java.util.*;

import prLogicalElements.*;

public class Restrict {

	public static void toRestrict(Operation root) {
		restriction(root);
		restrictForward(root);
		reOrganizationOfTree(root);
	}

	private static void restrictForward(Operation root) {
		for (Operation operation : root.getInmediateOperations()) {
			restriction(operation);
			restrictForward(operation);
		}
	}

	private static void restriction(Operation operation) {	

		restrictWithRoot(operation);
		
		if(operation.getInmediateOperations().size() > 1)
			restrictComponents(operation);
	}

	private static void restrictWithRoot(Operation operation) {
		
		for (Literal literals : operation.getInmediateLiterals()) {
			boolean canRestrict = true;
			for (Operation op : operation.getInmediateOperations()) {
				if (!op.getInmediateLiterals().contains(literals)) {
					canRestrict = false;
					break;
				}
			}
			if (canRestrict) {
				for (Operation op : operation.getInmediateOperations()) {
					op.type = OperationType.T;
					op.components.clear();
				}
			}	
		}
	}

	private static void restrictComponents(Operation operation) {
		List<Operation> operationsOfRoot = operation.getInmediateOperations();
		Set<Literal> literalsFirstComponent = operationsOfRoot.get(0).getInmediateLiterals();	
		Set<Literal> literalsToRestrict = new HashSet<>();
		
		for (Literal literal : literalsFirstComponent) {
			boolean canRestrict = true;
			for (int i = 1; i < operationsOfRoot.size(); i++) {
				if (!operationsOfRoot.get(i).getInmediateLiterals().contains(literal)) {
					canRestrict = false;
					break;
				}
			}
			if (canRestrict) {
				literalsToRestrict.add(literal);
			}
		}
		if(literalsToRestrict.size() > 0) {
			for (Operation operations : operationsOfRoot) {
				for(Element e : literalsToRestrict) {
					operations.components.remove(e); 	
				}
				if(operations.components.size() < 2) {
					operation.addComponents(operations.components);
					operation.components.remove(operations);
				}
			}
			
			if(operationsOfRoot.get(0).components.size() < 2) {
				operation.literalsThatMustBeMovedJustOne.addAll(literalsToRestrict);	
			}else {
				operationsOfRoot.get(0).literalsThatMustBeMoved.addAll(literalsToRestrict);
			}	
		}
	}
	
	private static void reOrganizationOfTree(Operation root) {
		for(Operation operation : root.getInmediateOperations()) {
			if(operation.literalsThatMustBeMoved.size() > 0) {
				Operation op1 = new Operation(operation.type);
				op1.addComponents(root.clone());
				op1.addComponents(operation.literalsThatMustBeMoved);
				operation.literalsThatMustBeMoved.clear();
				root.components.clear();
				root.type = op1.type;
				root.addComponents(op1.components);
			}
			if(operation.literalsThatMustBeMovedJustOne.size() > 0) {
				root.addComponents(operation.literalsThatMustBeMovedJustOne);
				operation.literalsThatMustBeMovedJustOne.clear();
			}
			for(Operation grandson : operation.getInmediateOperations()){
				if(grandson.literalsThatMustBeMoved.size() > 0) {
					root.addComponents(grandson.literalsThatMustBeMoved);
					grandson.literalsThatMustBeMoved.clear();
				}
				if(grandson.literalsThatMustBeMovedJustOne.size() > 0) {
					operation.addComponents(grandson.literalsThatMustBeMovedJustOne);
					grandson.literalsThatMustBeMovedJustOne.clear();
				}
			}
		
			reOrganizationOfSubTree(operation);
		}
	}
	
	private static void reOrganizationOfSubTree(Operation root) {

		for(Operation operation : root.getInmediateOperations()) {
			for(Operation grandson : operation.getInmediateOperations()){
				if(grandson.literalsThatMustBeMoved.size() > 0) {
					root.addComponents(grandson.literalsThatMustBeMoved);
					grandson.literalsThatMustBeMoved.clear();
				}
				if(grandson.literalsThatMustBeMovedJustOne.size() > 0) {
					operation.addComponents(grandson.literalsThatMustBeMovedJustOne);
					grandson.literalsThatMustBeMovedJustOne.clear();
				}
			}
			reOrganizationOfSubTree(operation);
		}		
	}

}
