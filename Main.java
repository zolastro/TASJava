import Tas.Signar;
import Tas.Tas;
import prLogicalElements.*;

public class Main {
	public static void main(String[] args) {	
		System.out.println("Test 1");
		Operation root = new Operation(OperationType.OR);
		root.addComponents(new Literal("p"));
		Operation child1 = new Operation(OperationType.AND);
		child1.addComponents(new Literal("q"), new Literal("r"), new Literal("p", false));
		root.addComponents(child1);
		Tas.runTAS(root);
		System.out.println();
		
		System.out.println("Test 2");
		root = new Operation(OperationType.OR);
		child1 = new Operation(OperationType.AND);
		Operation child2 = new Operation(OperationType.AND);
		child1.addComponents(new Literal("p"), new Literal("q"));
		child2.addComponents(new Literal("p", false), new Literal("q", false));
		root.addComponents(child1, child2);
		Tas.runTAS(root);
		System.out.println();
	
		System.out.println("Test 3");				// comprueba que a 1 de profundidad restringa y genere un nuevo nodo encima de todo
		root = new Operation(OperationType.AND);
		child1 = new Operation(OperationType.OR);
		child2 = new Operation(OperationType.OR);
		child1.addComponents(new Literal("p"), new Literal("q"),new Literal("z"));
		child2.addComponents(new Literal("q"), new Literal("t"),new Literal("r"));
		root.addComponents(child1, child2);
		Tas.runTAS(root);
		System.out.println();
		
		System.out.println("Test 4");				// comprueba restrictWithRoot()
		root = new Operation(OperationType.AND);
		root.addComponents(new Literal("q"));
		child1 = new Operation(OperationType.OR);
		child2 = new Operation(OperationType.OR);
		Operation child3 = new Operation(OperationType.AND);
		child3.addComponents(new Literal("n"), new Literal("m"));
		child1.addComponents(new Literal("p"), new Literal("q"),new Literal("z"));
		child2.addComponents(new Literal("q"), new Literal("t"),new Literal("r"),child3);
		root.addComponents(child1, child2);
		Tas.runTAS(root);
		System.out.println();
		
		System.out.println("Test 5");				// comprueba a 2 de profundidad restringir y que solo uno de los componentes se suba 
		root = new Operation(OperationType.AND);
		child1 = new Operation(OperationType.OR);
		child2 = new Operation(OperationType.OR);
		child3 = new Operation(OperationType.AND);
		Operation child4 = new Operation(OperationType.AND);
		child4.addComponents(new Literal("v"), new Literal("c"),new Literal("x"));
		child3.addComponents(new Literal("v"), new Literal("m"));
		child1.addComponents(new Literal("p"), new Literal("d"),new Literal("z"));
		child2.addComponents(new Literal("q"), new Literal("t"),new Literal("r"),child3,child4);
		root.addComponents(child1, child2);
		Tas.runTAS(root);
		System.out.println();
		
		System.out.println("Test 6");				// a nivel 2, doble restriccion con doble subida 
		root = new Operation(OperationType.AND);
		child1 = new Operation(OperationType.OR);
		child2 = new Operation(OperationType.OR);
		child3 = new Operation(OperationType.AND);
		child4 = new Operation(OperationType.AND);
		child4.addComponents(new Literal("v"), new Literal("c"),new Literal("x"));
		child3.addComponents(new Literal("v"), new Literal("m"),new Literal("x"));
		child1.addComponents(new Literal("p"), new Literal("d"),new Literal("z"));
		child2.addComponents(new Literal("q"), new Literal("t"),new Literal("r"),child3,child4);
		root.addComponents(child1, child2);
		Tas.runTAS(root);
		System.out.println();
		
		System.out.println("Test 7");				// a nivel 3 una restriccion con 2 restricciones a nivel 2 y mil subidas
		root = new Operation(OperationType.AND);
		child1 = new Operation(OperationType.OR);
		child2 = new Operation(OperationType.OR);
		child3 = new Operation(OperationType.AND);
		child4 = new Operation(OperationType.AND);
		Operation child5 = new Operation(OperationType.OR);
		Operation child6 = new Operation(OperationType.OR);
		child6.addComponents(new Literal("w"), new Literal("u"));
		child5.addComponents(new Literal("u"), new Literal("l"));
		child4.addComponents(new Literal("v"), new Literal("c"),new Literal("x"),child5,child6);
		child3.addComponents(new Literal("v"), new Literal("m"),new Literal("x"));
		child1.addComponents(new Literal("p"), new Literal("d"),new Literal("z"));
		child2.addComponents(new Literal("q"), new Literal("t"),new Literal("r"),child3,child4);
		root.addComponents(child1, child2);
		Tas.runTAS(root);
		System.out.println();
	}
}
