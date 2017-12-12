import java.util.ArrayList;
import java.util.List;

public abstract class LogicalOperation {
	public List<LogicalOperation> components;
	public Operation operation;
	public static boolean isStateNext = true;
	
	public List<LogicalOperation> negateAll(List<LogicalOperation> components) {

		List<LogicalOperation> aux = new ArrayList<LogicalOperation>();
		for (int i = 0; i < components.size(); i++) {
			aux.add(components.get(i).negate());
		}

		return aux;
	}
	
	public void Next() { // avanza en el arbol, si encuentra un Not hace un negate all sino simplemente avanza
		
		// Si encuentras un no acuerdate de que hay que cambiarle la booleana
		
		
	}

	public LogicalOperation(List<LogicalOperation> components) {
		this.components = components;
	}

	abstract LogicalOperation negate();

}
