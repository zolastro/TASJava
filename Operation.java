import java.util.ArrayList;
import java.util.List;

public class Operation implements Element {

	public List<Element> components;
	public OperationType type;

	public Operation(OperationType type) {
		this.type = type;
		this.components = new ArrayList<>();
	}

	public void addComponent(Element component) {
		this.components.add(component);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		int lenComponents = this.components.size();
		for (int i = 0; i < lenComponents; i++) {
			sb.append(this.components.get(i));
			if (i < lenComponents - 1) {
				sb.append(" " + type + " ");
			}
		}
		sb.append(")");
		return sb.toString();
	}
}
