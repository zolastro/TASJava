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
		System.out.println(root);
		// TAS EXECUTION
		//do {
		System.out.println("These are the pure literals: " + PureLiteral.getPureLiterals(root));
		PureLiteral.removePureLiterals(root);
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
}
