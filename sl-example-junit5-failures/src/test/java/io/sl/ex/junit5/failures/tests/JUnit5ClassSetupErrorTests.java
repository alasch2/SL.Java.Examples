/*
 * Copyright 2015-2018 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package io.sl.ex.junit5.failures.tests;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import io.sl.ex.junit5.failures.CalculatorUse;

@Tag("fast")
class JUnit5ClassSetupErrorTests {
	
	@BeforeAll
	public static void setupClass() throws Exception {
		throw new RuntimeException("Class setup: Fake exception");
	}

	@Test
	@DisplayName("Class setup failure demo test1:)")
	public void test1_JUnit5_shouldFailDueToClassSetupException() {
		System.out.println("----------------'test1_JUnit5_shouldFailDueToClassSetupException' is running .........");
		CalculatorUse calcUse2 = new CalculatorUse();
		assertTrue(calcUse2.calcSub(1, 1) == 0);
	}
	
	@Test
	@DisplayName("Class setup failure demo test2:)")
	public void test2_JUnit5_shouldFailDueToClassSetupException() {
		System.out.println("----------------'test2_JUnit5_shouldFailDueToClassSetupException' is running .........");
		CalculatorUse calcUse2 = new CalculatorUse();
		assertTrue(calcUse2.calcSub(1, 1) == 0);
	}
}
