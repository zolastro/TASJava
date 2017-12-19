package src.prLogicalElements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LiteralTests {
	private Literal literal;
	
	@BeforeEach
	public void setUp() {		
		this.literal = new Literal("p");
	}
	
	@AfterEach
	public void tearDown() {
		this.literal = null;
	}

	@Test
	public void when_useDefaultConstructor_then_literalIsPositive() {
		Literal positiveLiteral = new Literal("q");
		assertTrue(literal.isPositive());
	}
	
	@Test
	public void when_useConstructorWithFalse_then_literalIsNegated() {
		Literal negatedLiteral = new Literal("q", false);
		assertFalse(negatedLiteral.isPositive());
	}
	
	@Test
	public void when_literalNegated_then_toggleIsPositive() {
		assertTrue(literal.isPositive());
		literal.negate();
		assertFalse(literal.isPositive());
		literal.negate();
		assertTrue(literal.isPositive());
	}
	
	@Test
	public void when_literalNegated_then_toStringAddsSymbol() {
		assertEquals(literal.toString(), "p");
		literal.negate();
		assertEquals(literal.toString(), "¬p");
	}
}
