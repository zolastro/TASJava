import java.util.List;

class Literal implements Element {

	public boolean notNegated;
	private char symbol;
	public Literal(char symbol, boolean notNegated){
		this.symbol = symbol;
		this.notNegated = notNegated;
	}
	public Literal(char symbol) {
		this(symbol, true);
	}

	public void negate() {
		this.notNegated = !this.notNegated;
	}

	public String toString() {
		return (this.notNegated ? "":"¬") + this.symbol;
	}
}