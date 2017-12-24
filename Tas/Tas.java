package Tas;

import prLogicalElements.*;

public class Tas {

	public static void execute(Operation root) {
		System.out.println(root);
		root.signar();
		System.out.println(root);
		//TAS EXECUTION
		
		/*
		 * Restringir
		 * Red Completa
		 * Puro
		 * Sub
		 * Quine
		 * 
		 * A continuacion idea basica que en parte no me convenze por el hecho de que al comprobar restringir por ejemplo lo hace 
		 * desde la root y no solo de las componentes modificadas
		 */
		
//		while(!isSolution(root)) {
//			
//			restringe(root);
//			if(!isModified) { // opto porque esto sea una booleana en realidad que se pone a true desde cada metodo(res,red,pur,etc)
//							  // si hace cambios(asi no hay que comprobar nada)
//				RedCompleta(root);
//				if(!isModified){
//					LiteralPuro(root);
//					if(!isModified){
//						SubReduccion(root);
//						if(!isModified){
//							Quine(root);	
//						}
//					}
//				}
//			}
//			
//		}	
		
		
		
	}
}
