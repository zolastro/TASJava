import java.util.ArrayList;
import java.util.List;


public class Main {
	public static void main(String[] args) {
		System.out.println("Test 1");
		Operation opA = new Operation(OperationType.AND);
		opA.addComponent(new Literal('p', false));
		opA.addComponent(new Literal('q'));
		System.out.println(opA);
		System.out.println("Negate...");
		opA.negate();
		System.out.println(opA);
		System.out.println("Test 2");
		Operation opB = new Operation(OperationType.AND);
		opB.addComponent(new Literal('p'));
		opB.addComponent(new Literal('q', false));
		Operation opC = new Operation(OperationType.THEN);
		opC.addComponent(opA);
		opC.addComponent(opB);
		System.out.println(opC);
		System.out.println("Negate...");
		opA.negate();
		System.out.println(opC);
	}
}