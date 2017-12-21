package src.prLogicalElements;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

	public void negateType() {

		switch (this.type) {
		case AND:
			this.type = OperationType.OR;
			this.negateAllComponents();
			break;
		case OR:
			this.type = OperationType.AND;
			this.negateAllComponents();
			break;
		case THEN:
			this.type = OperationType.AND;
			this.components.get(1).negate();
			break;
		case IFF:
			unfoldIffOperation();
			this.negate();
			this.negateType();
			break;
		default:
			throw new RuntimeException("Operation not implemented");
		}

		this.isPositive = true;
	}
	
	private void unfoldIffOperation() {
		this.type = OperationType.AND;
		
		Operation left = new Operation(OperationType.THEN);
		left.addComponents(this.components);
		
		Operation right = new Operation(OperationType.THEN);
		right.addComponents(this.components.get(1).clone());
		right.addComponents(this.components.get(0).clone());
		
		this.components.clear();
		this.addComponents(left, right);
	}

	public void signar() {
		//Check if it is negated or if it is a THEN operation
		
		if (!this.isPositive()) {
			this.negateType();
		} else if (this.type == OperationType.THEN) {
			this.type = OperationType.OR;
			this.components.get(0).negate();
		} else if (this.type == OperationType.IFF) {
			unfoldIffOperation();
		}
		
		//Signar all it's children
		
		for (Element component : this.components) {
			component.signar();
		}
		
		//Merge children of the same type
		
		List<Element> mergedComponents = new ArrayList<>();

		for (Element child : this.components) {
			if (child instanceof Operation) {
				Operation component = (Operation) child;
				if (component.type == this.type) {
					mergedComponents.addAll(component.components);
				} else {
					mergedComponents.add(component);
				}
			} else {
				mergedComponents.add(child);
			}
		}
		this.components = mergedComponents;
	}

	public void negateAllComponents() {
		for (Element component : this.components) {
			component.negate();
		}
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
