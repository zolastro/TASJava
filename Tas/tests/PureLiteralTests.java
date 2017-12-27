package Tas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Tas.PureLiteral;
import prLogicalElements.Literal;
import prLogicalElements.Operation;
import prLogicalElements.OperationType;

class PureLiteralTests {
	Operation operation;
	
	@BeforeEach
	public void setUp() {
		operation = new Operation(OperationType.AND);
		operation.addComponents(new Literal("p"), new Literal("q", false));
		Operation child1 = new Operation(OperationType.OR);
		child1.addComponents(new Literal("q"), new Literal("r"), new Literal("p", false));
		operation.addComponents(child1);
	}
	
	@AfterEach
	public void tearDown() {
		operation = null;
	}
	
	@Test
	public void when_oppositLiteralOfPostive_then_getSameLiteralButNegated() {
		Literal literal = new Literal("p");
		Literal opposit = PureLiteral.opositLiteralOf(literal);
		literal.negate();
		assertEquals(literal, opposit);
	}
	
	@Test
	public void when_oppositLiteralOfNegated_then_getSameLiteralButPositive() {
		Literal literal = new Literal("p", false);
		Literal opposit = PureLiteral.opositLiteralOf(literal);
		literal.negate();
		assertEquals(literal, opposit);
	}
	
	@Test
	public void when_removePureLiterals_then_operationNotHasThoseLiterals() {
		Set<Literal> pureLiterals = PureLiteral.getPureLiterals(operation);		
		
		PureLiteral.removePureLiterals(operation);
		Set<Literal> literals = operation.getLiterals();
		for (Literal pureLiteral: pureLiterals) {			
			assertFalse(literals.contains(pureLiteral));
		}
	}
	
	@Test
	public void when_getPureLiteralsOnOperation_then_getLiteralR() {
		Set<Literal> expected = new HashSet<>();
		expected.add(new Literal("r"));
		
		Set<Literal> pureLiterals = PureLiteral.getPureLiterals(operation);
		assertEquals(expected, pureLiterals);
	}

}
