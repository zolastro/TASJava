package prLogicalElements;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Operation implements Element {

	public List<Element> components;
	public OperationType type;
	private boolean isPositive;

	public Operation(OperationType type, boolean isPositive) {
		this.type = type;
		this.components = new ArrayList<>();
		this.isPositive = isPositive;
	}

	public Operation(OperationType type) {
		this(type, true);
	}

	public void addComponents(Element ... elements) {
		for(Element element : elements) {
			this.components.add(element);
		}
	}
	
	public void addComponents(Collection<Element> elements) {
		this.components.addAll(elements);
	}

	public boolean isPositive() {
		return this.isPositive;
	}

	public void negate() {
		this.isPositive = !this.isPositive;
	}	

	public void negateAllComponents() {
		for (Element component : this.components) {
			component.negate();
		}
	}
	
	public boolean isLiteral() {
		return false;
	}
	
	public Set<Literal> getLiterals() {
		Set<Literal> literals = new HashSet<>();
		for(Element element: this.components) {
			if (element.isLiteral()) {
				literals.add((Literal) element);
			} else {
				literals.addAll(((Operation)element).getLiterals());
			}
		}
		return literals;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append((this.isPositive() ? "" : "¬") + "(");
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
	
	public Element clone() {
		Operation clone = new Operation(this.type);
		for (Element component: this.components) {
			clone.addComponents(component.clone());
		}
		return clone;
	}
}
