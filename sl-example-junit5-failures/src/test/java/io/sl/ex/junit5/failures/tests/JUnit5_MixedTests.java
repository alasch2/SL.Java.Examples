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

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class JUnit5_MixedTests {

	@Test
	void test1_JUnit5_OK() {
		System.out.println("----------------'test1_JUnit5_OK' is running .........");
	}

	@Test
	void test2_JUnit5_OK() {
		System.out.println("----------------'test2_JUnit5_OK' is running .........");
	}

	@Test
	void test3_JUnit5_shouldFail() {
		System.out.println("----------------'test3_JUnit5_shouldFail' is running .........");
		assertEquals(3, 2 + 2);
	}
}
