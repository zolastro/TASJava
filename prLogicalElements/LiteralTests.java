package prLogicalElements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LiteralTests {
	private Literal literal;
	
	@BeforeEach
	public void setUp() {
		this.literal = new Literal('p');
	}
	
	@AfterEach
	public void tearDown() {
		this.literal = null;
	}
	
	@Test
	public void isLiteral_Negated_toggleIsPositive() {
		assertTrue(literal.isPositive());
		literal.negate();
		assertFalse(literal.isPositive);
	}
	
	@Test
	public void toString_LiteralNegated_AddSymbol() {
		assertEquals(literal.toString(), "p");
		literal.negate();
		assertEquals(literal.toString(), "¬p");
	}
}
