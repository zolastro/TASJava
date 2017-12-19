package src.prLogicalElements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OperationTests {
	private Operation operation;
	
	@BeforeEach
	public void setUp() {
		this.operation = new Operation(OperationType.AND);
		this.operation.addComponent(new Literal("p"));
		this.operation.addComponent(new Literal("q"));
		
	}
	
	@AfterEach
	public void tearDown() {
		this.operation = null;
	}

	@Test
	public void when_useDefaultConstructor_then_operationIsPositive() {
		Operation positiveOperation = new Operation(OperationType.AND);
		assertTrue(positiveOperation.isPositive());
	}
	
	@Test
	public void when_useConstructorWithFalse_then_literalIsNegated() {
		Operation negatedOperation = new Operation(OperationType.AND, false);
		assertFalse(negatedOperation.isPositive());
	}
	
	
	@Test
	void when_negated_then_toggleIsPositive() {
		assertTrue(operation.isPositive());
		operation.negate();
		assertFalse(operation.isPositive());
		operation.negate();
		assertTrue(operation.isPositive());
	}
	
	@Test
	public void when_addComponent_then_correctInsertion() {
		int numberOfComponents = operation.components.size();
		
		Literal literal = new Literal("r");
		operation.addComponent(literal);
		
		assertEquals(numberOfComponents + 1, operation.components.size());
		//Check that both are actually the same instance of Literal.
		assertTrue(literal == operation.components.get(numberOfComponents));
	}
	
	@Test
	public void when_operationNegated_then_toStringAddsSymbol() {
		assertEquals("(p AND q)", operation.toString());
		operation.negate();
		assertEquals("¬(p AND q)", operation.toString());
	}
	
	@Test
	public void when_negateAndOperation_then_typeChangesToOr() {
		Operation andOperation = new Operation(OperationType.AND);

		
		assertEquals(OperationType.AND, andOperation.type);
		andOperation.negateType();
		assertEquals(OperationType.OR, andOperation.type);	
	}
	
	@Test
	public void when_negateOrOperation_then_typeChangesToAnd() {
		Operation orOperation = new Operation(OperationType.OR);
		
		assertEquals(OperationType.OR, orOperation.type);
		orOperation.negateType();
		assertEquals(OperationType.AND, orOperation.type);
	}
	
	@Test
	public void when_negateThenOperation_then_typeChangesToAnd() {
		Operation thenOperation = new Operation(OperationType.THEN);
		thenOperation.addComponent(new Literal("p"));
		thenOperation.addComponent(new Literal("q"));
		
		assertEquals(OperationType.THEN, thenOperation.type);
		thenOperation.negateType();
		assertEquals(OperationType.AND, thenOperation.type);
	}
	
	@Test
	public void when_signarThenOperation_then_typeChangesToOr() {
		Operation thenOperation = new Operation(OperationType.THEN);
		thenOperation.addComponent(new Literal("p"));
		thenOperation.addComponent(new Literal("q"));
		
		assertEquals(OperationType.THEN, thenOperation.type);
		thenOperation.signar();
		assertEquals(OperationType.OR, thenOperation.type);	
	}
	
	@Test
	public void when_signarNegatedOperation_then_operationIsPositive() {
		operation.negate();
		assertFalse(operation.isPositive());
		
		operation.signar();
		assertTrue(operation.isPositive());
	}	
}
