import java.util.List;

class Literal implements Element {

	public boolean isNegated;
	private char symbol;
	public Literal(char symbol) {
		this.symbol = symbol;
	}

	void negate() {
		this.isNegated = !this.isNegated;
	}

	public String toString() {
		return (isNegated? "¬":"") + this.symbol;
	}
}