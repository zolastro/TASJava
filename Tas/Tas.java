package Tas;

import java.util.HashSet;
import java.util.Set;

import prLogicalElements.*;

public class Tas {

	public static void runTAS(Operation root) {
		System.out.println("Original");
		System.out.println(root);
		Signar.signar(root);
		System.out.println("After signar");
		// TAS EXECUTION
		//do {
		System.out.println(root);
		System.out.println("These are the pure literals: " + getPureLiterals(root));
		removePureLiterals(root, getPureLiterals(root));
		System.out.println("Removing pure literals...");
		System.out.println(root);
		//System.out.println("Prunning...");
		//} while (pruneOperation(root));
		//System.out.println("Done!");
		//System.out.println(root);

		/*
		 * Restringir Red Completa Puro Sub Quine
		 * 
		 * A continuacion idea basica que en parte no me convenze por el hecho de que al
		 * comprobar restringir por ejemplo lo hace desde la root y no solo de las
		 * componentes modificadas
		 */

		// while(!isSolution(root)) {
		//
		// restringe(root);
		// if(!isModified) { // opto porque esto sea una booleana en realidad que se
		// pone a true desde cada metodo(res,red,pur,etc)
		// // si hace cambios(asi no hay que comprobar nada)
		// RedCompleta(root);
		// if(!isModified){
		// LiteralPuro(root);
		// if(!isModified){
		// SubReduccion(root);
		// if(!isModified){
		// Quine(root);
		// }
		// }
		// }
		// }
		//
		// }
	}


	private static void removePureLiterals(Operation operation, Set<Literal> pureLiterals) {
		boolean hasToremoveFromChildren = true;
		switch (operation.type) {
		case OR:
			for (Literal literal : pureLiterals) {
				if(operation.components.contains(literal)) {
					operation.type = OperationType.T;
					hasToremoveFromChildren = false;
					break;
				}
			}
			break;
		case AND:
			for (Literal literal : pureLiterals) {
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
				removePureLiterals(childOperation, pureLiterals);				
			}					
		}
	}

	private static Set<Literal> getPureLiterals(Operation operation) {
		Set<Literal> pureLiterals = new HashSet<>();
		Set<Literal> literals = operation.getLiterals();
		for (Literal literal : literals) {
			if (!literals.contains(opositLiteralOf(literal))) {
				pureLiterals.add(literal);
			}
		}
		return pureLiterals;
	}
	
	private static Literal opositLiteralOf(Literal literal) {
		return new Literal(literal.symbol, !literal.isPositive());
	}

	
}
