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

import org.junit.BeforeClass;
import org.junit.Test;

public class JUnit4_ClassSetupErrorTests {

	@BeforeClass
	public static void setupClass() throws Exception {
		throw new RuntimeException("Class setup: Fake exception");
	}
	@Test
	public void test1_JUnit4_shouldFailDueToClassSetupException() {
		System.out.println("----------------'test1_JUnit4_shouldFailDueToClassSetupException' is running .........");
	}
	
	@Test
	public void test2_JUnit4_shouldFailDueToClassSetupException() {
		System.out.println("----------------'test2_JUnit4_shouldFailDueToClassSetupException' is running .........");
	}
}
