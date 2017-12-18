package src;

import src.prLogicalElements.*;

public class Main {
	public static void main(String[] args) {

		Operation andOp = new Operation(OperationType.THEN);
		Operation orOp1 = new Operation(OperationType.THEN, false);
		orOp1.addComponent(new Literal('a'));
		orOp1.addComponent(new Literal('b'));
		andOp.addComponent(orOp1);
		andOp.addComponent(new Literal('c'));
		System.out.println("Original:");
		System.out.println(andOp);
		andOp.propagateToChildren();
		System.out.println("Signar test");
		System.out.println(andOp);

	}
}
