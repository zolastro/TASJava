package src;

import src.prLogicalElements.*;

public class Main {
	public static void main(String[] args) {

		Operation opRoot = new Operation(OperationType.THEN);
		Operation op1 = new Operation(OperationType.AND);
		op1.addLiterals("p", "p");
		Operation op2 = new Operation(OperationType.AND);
		op2.addLiterals("q", "q");

		opRoot.addOperations(op1, op2);
		
		System.out.println("Original:");
		System.out.println(opRoot);
		opRoot.signar();
		System.out.println("Signar test");
		System.out.println(opRoot);

	}
}
