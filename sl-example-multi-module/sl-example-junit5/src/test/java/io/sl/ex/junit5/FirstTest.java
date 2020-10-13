/*
 * Copyright 2015-2018 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package io.sl.ex.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import io.sl.ex.junit5.Calculator;

@Tag("fast")
class FirstTest {

	@Test
	@DisplayName("My 1st JUnit 5 test! :)")
	void myFirstTest(TestInfo testInfo) {
		Calculator calculator = new Calculator();
		assertEquals(2, calculator.add(1, 1), "1 + 1 should equal 2");
		assertEquals("My 1st JUnit 5 test! :)", testInfo.getDisplayName(), () -> "TestInfo is injected correctly");
	}

}
