package prLogicalElements;

public class Literal implements Element {

	private boolean isPositive;
	public String symbol;
	public Literal(String symbol, boolean isPositive){
		this.symbol = symbol;
		this.isPositive = isPositive;
	}
	public Literal(String symbol) {
		this(symbol, true);
	}

	public boolean isPositive() {
		return this.isPositive;
	}

	public void negate() {
		this.isPositive = !this.isPositive;
	}

	public String toString() {
		return (this.isPositive ? "":"¬") + this.symbol;
	}

	
	public boolean isLiteral() {
		return true;
	}
	
	public boolean equals(Object o) {
		boolean isEquals = false;
		if(o instanceof Literal) {
			Literal literal = (Literal)o;
			isEquals = this.symbol.equals(literal.symbol) && (this.isPositive == literal.isPositive);
		}
		return isEquals;
	}
	
	public int hashCode() {
		return this.symbol.hashCode();
	}
	
	public Element clone() {
		return new Literal(this.symbol, this.isPositive);
	}
}