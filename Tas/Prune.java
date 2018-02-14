package Tas;

import prLogicalElements.*;

public class Prune {

	
	public static void prunning(Operation root) {	// iteras el arbol y si hay un hijo con T como type si es una and la operacion padre 
		
		for (Operation operation : root.getInmediateOperations()) {
			if(operation.type == OperationType.T) {
				if(root.type == OperationType.AND) {
					root.type = OperationType.T;
					root.components.clear();
					break;
				}else {
					root.getInmediateOperations().remove(operation);
				}
			}
			prunning(operation);
		}
		
	}
	
}
