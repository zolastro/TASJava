package prLogicalElements.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import prLogicalElements.Literal;

public class LiteralTests {


	@Test
	public void when_useDefaultConstructor_then_literalIsPositive() {
		Literal positiveLiteral = new Literal("p");
		assertTrue(positiveLiteral.isPositive());
	}
	
	@Test
	public void when_useConstructorWithFalse_then_literalIsNegated() {
		Literal negatedLiteral = new Literal("p", false);
		assertFalse(negatedLiteral.isPositive());
	}
	
	@Test
	public void when_literalNegated_then_toggleIsPositive() {
		Literal literal = new Literal("q");
		assertTrue(literal.isPositive());
		literal.negate();
		assertFalse(literal.isPositive());
		literal.negate();
		assertTrue(literal.isPositive());
	}
	
	@Test
	public void when_literalNegated_then_toStringAddsSymbol() {
		Literal literal = new Literal("p");
		assertEquals(literal.toString(), "p");
		literal.negate();
		assertEquals(literal.toString(), "¬p");
	}
	
	@Test
	public void when_sameLiteral_then_areEquals() {
		Literal oneLiteral = new Literal("p");
		Literal anotherLiteral = new Literal("p");
		assertEquals(oneLiteral, anotherLiteral);
	}
	
	@Test
	public void when_sameLiteralButNegated_then_notAreEquals() {
		Literal oneLiteral = new Literal("p");
		Literal anotherLiteral = new Literal("p", false);
		assertNotEquals(oneLiteral, anotherLiteral);
	}
}
