package prLogicalElements.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import prLogicalElements.Literal;
import prLogicalElements.Operation;
import prLogicalElements.OperationType;

class OperationTests {
	private Operation operation;
	
	@BeforeEach
	public void setUp() {
		this.operation = new Operation(OperationType.AND);
		this.operation.addComponents(new Literal("p"), new Literal("q"));
		
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
	public void when_operationCloned_then_sameToString() {
		Operation clone = (Operation) this.operation.clone();
		assertEquals(this.operation.toString(), clone.toString());
	}
	
	@Test
	public void when_operationCloned_then_differentInstances() {
		Operation clone = (Operation) this.operation.clone();
		assertFalse(this.operation ==  clone);
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
