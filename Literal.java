import java.util.List;

class Literal implements Element {

	public boolean isNotNegated;
	private char symbol;
	public Literal(char symbol, boolean isNotNegated){
		this.symbol = symbol;
		this.isNotNegated = isNotNegated;
	}
	public Literal(char symbol) {
		this(symbol, true);
	}

	public boolean isNotNegated() {
		return this.isNotNegated;
	}

	public void negate() {
		this.isNotNegated = !this.isNotNegated;
	}

	public String toString() {
		return (this.isNotNegated ? "":"¬") + this.symbol;
	}
}
