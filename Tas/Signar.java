package Tas;

import java.util.ArrayList;
import java.util.List;

import prLogicalElements.Element;
import prLogicalElements.Operation;
import prLogicalElements.OperationType;

public class Signar {

	public static void signar(Operation operation) {
		if (!operation.isPositive()) {
			signarNegation(operation);
		} else if (operation.type == OperationType.THEN) {
			operation.type = OperationType.OR;
			operation.components.get(0).negate();
		} else if (operation.type == OperationType.IFF) {
			unfoldIffOperation(operation);
		}

		List<Element> mergedComponents = new ArrayList<>();

		for (Element element : operation.components) {
			if (element.isLiteral()) {
				if(!mergedComponents.contains(element)) {					
					mergedComponents.add(element);
				}
			} else {
				Operation childOperation = (Operation) element;
				signar(childOperation);
				if (operation.type == childOperation.type) {
					mergedComponents.addAll(childOperation.components);
				} else {
					mergedComponents.add(childOperation);
				}
			}
		}

		operation.components = mergedComponents;
	}

	private static void unfoldIffOperation(Operation operation) {
		operation.type = OperationType.AND;

		Operation left = new Operation(OperationType.THEN);
		left.addComponents(operation.components);

		Operation right = new Operation(OperationType.THEN);
		right.addComponents(operation.components.get(1).clone());
		right.addComponents(operation.components.get(0).clone());

		operation.components.clear();
		operation.addComponents(left, right);
	}

	private static void signarNegation(Operation operation) {
		switch (operation.type) {
		case AND:
			operation.type = OperationType.OR;
			operation.negateAllComponents();
			break;
		case OR:
			operation.type = OperationType.AND;
			operation.negateAllComponents();
			break;
		case THEN:
			operation.type = OperationType.AND;
			operation.components.get(1).negate();
			break;
		case IFF:
			unfoldIffOperation(operation);
			operation.negate();
			signarNegation(operation);
			break;
		default:
			throw new RuntimeException("Operation not implemented");
		}

		operation.negate();      //¬¬p = p
	}
}
