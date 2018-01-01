package Tas;

import java.util.*;

import prLogicalElements.*;

public class Restrict {

	public static void toRestrict(Operation operation) {
		restriction(operation);
		restrictForward(operation.getInmediateOperations());
	}

	private static void restrictForward(List<Operation> operationsOfRoot) {
		for (Operation operation : operationsOfRoot) {
			restriction(operation);
			restrictForward(operation.getInmediateOperations());
		}
	}

	private static void restriction(Operation operation) {
		Set<Literal> literalsOfRoot = operation.getInmediateLiterals();
		List<Operation> operationsOfRoot = operation.getInmediateOperations();

		restrictWithRoot(literalsOfRoot, operationsOfRoot);
		restrictComponents(operationsOfRoot);
	}

	private static void restrictWithRoot(Set<Literal> literalsOfRoot, List<Operation> operationsOfRoot) {
		for (Literal literals : literalsOfRoot) {
			boolean canRestrict = true;
			for (Operation operations : operationsOfRoot) {
				if (!operations.getInmediateLiterals().contains(literals)) {
					canRestrict = false;
					// probar si el break rompe los dos bucles o solo este (quiero el solo este)
				}
			}
			if (canRestrict) {
				for (Operation operations : operationsOfRoot) {
					operations.type = OperationType.T;
					operations.components.clear();
				}
			}
		}
	}

	private static void restrictComponents(List<Operation> operationsOfRoot) {
		Set<Literal> literalsFirstComponent = operationsOfRoot.get(0).getInmediateLiterals();
		for (Literal literals : literalsFirstComponent) {
			boolean canRestrict = true;
			for (int i = 1; i < operationsOfRoot.size(); i++) {
				if (!operationsOfRoot.get(i).getInmediateLiterals().contains(literals)) {
					canRestrict = false;
					// probar si el break rompe los dos bucles o solo este (quiero el solo este)
				}
			}
			if (canRestrict) {
				for (Operation operations : operationsOfRoot) {
					operations.getInmediateLiterals().remove(literals);
				}

				// miro el tipo de mi operacion y busco una operacion encima de la root que sea del mismo tipo si no la hay la creo

				
				
			}
		}

	}

}
