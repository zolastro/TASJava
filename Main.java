import java.util.ArrayList;
import java.util.List;


public class Main {
	public static void main(String[] args) {
		Operation root = new Operation(OperationType.AND);
		root.addComponent(new Literal('p'));
		root.addComponent(new Literal('q'));
		System.out.println(root);

	}
}