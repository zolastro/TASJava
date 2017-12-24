package prLogicalElements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OperationTests {
	private Operation operation;
	
	@BeforeEach
	public void setUp() {
		this.operation = new Operation(OperationType.AND);
		this.operation.addComponents(new Literal("p"));
		this.operation.addComponents(new Literal("q"));
		
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
	public void when_addComponents_then_correctInsertion() {
		int numberOfComponents = operation.components.size();
		
		Literal literal = new Literal("r");
		operation.addComponents(literal);
		
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
		thenOperation.addComponents(new Literal("p"));
		thenOperation.addComponents(new Literal("q"));
		
		assertEquals(OperationType.THEN, thenOperation.type);
		thenOperation.negateType();
		assertEquals(OperationType.AND, thenOperation.type);
	}
	
	public void when_negateIffOperation_then_typeChangesToAnd() {
		Operation iffOperation = new Operation(OperationType.IFF);
		iffOperation.addComponents(new Literal("p"));
		iffOperation.addComponents(new Literal("q"));
		
		assertEquals(OperationType.IFF, iffOperation.type);
		iffOperation.signar();
		assertEquals(OperationType.AND, iffOperation.type);	
	}
	
	@Test
	public void when_signarThenOperation_then_typeChangesToOr() {
		Operation thenOperation = new Operation(OperationType.THEN);
		thenOperation.addComponents(new Literal("p"));
		thenOperation.addComponents(new Literal("q"));
		
		assertEquals(OperationType.THEN, thenOperation.type);
		thenOperation.signar();
		assertEquals(OperationType.OR, thenOperation.type);	
	}
	
	@Test
	public void when_signarIffOperation_then_typeChangesToAnd() {
		Operation iffOperation = new Operation(OperationType.IFF);
		iffOperation.addComponents(new Literal("p"));
		iffOperation.addComponents(new Literal("q"));
		
		assertEquals(OperationType.IFF, iffOperation.type);
		iffOperation.signar();
		assertEquals(OperationType.AND, iffOperation.type);	
	}
	
	@Test
	public void when_signarNegatedOperation_then_operationIsPositive() {
		operation.negate();
		assertFalse(operation.isPositive());
		
		operation.signar();
		assertTrue(operation.isPositive());
	}
	
	@Test
	public void when_operationCloned_then_sameToString() {
		Operation clone = (Operation) this.operation.clone();
		assertEquals(this.operation.toString(), clone.toString());
	}
	
	@Test
	public void when_operationGetLiterals_then_sameNumberOfLiterals() {
		Operation rootOperation = new Operation(OperationType.AND);
		rootOperation.addComponents(new Literal("p"));
		Operation nestedOperation = new Operation(OperationType.OR);
		nestedOperation.addComponents(new Literal("q"), new Literal("r"));
		rootOperation.addComponents(nestedOperation);
		
		assertEquals(3, rootOperation.getLiterals().size());
	}
	
}
