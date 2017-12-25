package Tas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Tas.Signar;
import prLogicalElements.Literal;
import prLogicalElements.Operation;
import prLogicalElements.OperationType;

class SignarTests {

	private Operation operation;
	
	@BeforeEach
	public void setUp() {
		operation = new Operation(OperationType.AND);
		operation.addComponents(new Literal("p"), new Literal("q"));
	}
	
	@AfterEach
	public void tearDown() {
		operation = null;
	}

	@Test
	public void when_signarThenOperation_then_typeChangesToOr() {
		operation.type = OperationType.THEN;

		assertEquals(OperationType.THEN, operation.type);
		Signar.signar(operation);
		assertEquals(OperationType.OR, operation.type);
	}

	@Test
	public void when_signarIffOperation_then_typeChangesToAnd() {
		operation.type = OperationType.IFF;

		assertEquals(OperationType.IFF, operation.type);
		Signar.signar(operation);
		assertEquals(OperationType.AND, operation.type);
	}

	@Test
	public void when_signarNegatedOperation_then_operationIsPositive() {
		operation.negate();
		assertFalse(operation.isPositive());

		Signar.signar(operation);
		assertTrue(operation.isPositive());
	}
	
	
	
	@Test
	public void when_signarNegatedAnd_then_typeChangesToOr() {
		operation.type = OperationType.AND;
		
		assertEquals(OperationType.AND, operation.type);
		operation.negate();
		Signar.signar(operation);
		assertEquals(OperationType.OR, operation.type);	
	}
	
	@Test
	public void when_signarNegatedOr_then_typeChangesToAnd() {
		operation.type = OperationType.OR;
		
		assertEquals(OperationType.OR, operation.type);
		operation.negate();
		Signar.signar(operation);
		assertEquals(OperationType.AND, operation.type);	
	}
	
	@Test
	public void when_signarNegatedThen_then_typeChangesToAnd() {
		operation.type = OperationType.THEN;
		
		assertEquals(OperationType.THEN, operation.type);
		operation.negate();
		Signar.signar(operation);
		assertEquals(OperationType.AND, operation.type);	
	}
	
	
	@Test
	public void when_signarNegatedIff_then_typeChangesToOr() {
		operation.type = OperationType.IFF;
		
		assertEquals(OperationType.IFF, operation.type);
		operation.negate();
		Signar.signar(operation);
		assertEquals(OperationType.OR, operation.type);	
	}

}
