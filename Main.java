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
		
		System.out.println("Test 2");
		root = new Operation(OperationType.OR);
		child1 = new Operation(OperationType.AND);
		Operation child2 = new Operation(OperationType.AND);
		child1.addComponents(new Literal("p"), new Literal("q"));
		child2.addComponents(new Literal("p", false), new Literal("q", false));
		root.addComponents(child1, child2);
		Tas.runTAS(root);
	}
}
