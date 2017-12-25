import Tas.Signar;
import Tas.Tas;
import prLogicalElements.*;

public class Main {
	public static void main(String[] args) {

		Operation andOp = new Operation(OperationType.THEN);
		Operation orOp1 = new Operation(OperationType.THEN, false);
		orOp1.addComponents(new Literal("a"));
		orOp1.addComponents(new Literal("b"));
		andOp.addComponents(orOp1);
		andOp.addComponents(new Literal("c"));
		System.out.println("Original:");
		System.out.println(andOp);
		Signar.signar(andOp);
		System.out.println("Signar test");
		System.out.println(andOp);
		
		
		System.out.println("IFF test");
		Operation root = new Operation(OperationType.IFF, false);
		root.addComponents(new Literal("p"), new Literal("q"));

		Tas.runTAS(root);
	}
}
