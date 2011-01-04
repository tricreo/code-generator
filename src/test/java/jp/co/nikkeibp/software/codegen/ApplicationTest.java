/*
 * Copyright 2010 TRICREO, Inc. (http://tricreo.jp/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.nikkeibp.software.codegen;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * {@link Application}のためのテスト
 */
public class ApplicationTest {
	
	@Test
	public void testRunByByPropertiesAndFreeMarker() {
		int result = new Application().run(new String[] {
			"-c",
			"bin/config.properties",
			"-o",
			"src/generated-properties-freemarker/java",
			"-e",
			"F"
		});
		assertThat(result, is(0));
	}
	
	@Test
	public void testRunByJsonAndFreeMarker() {
		int result = new Application().run(new String[] {
			"-c",
			"bin/config.json",
			"-o",
			"src/generated-json-freemarker/java",
			"-e",
			"F"
		});
		assertThat(result, is(0));
	}
	
	@Test
	public void testRunByJsonAndVelocity() {
		int result = new Application().run(new String[] {
			"-c",
			"bin/config.json",
			"-o",
			"src/generated-json-velocity/java",
			"-e",
			"V"
		});
		assertThat(result, is(0));
	}
	
	@Test
	public void testRunByPropertiesAndVelocity() {
		int result = new Application().run(new String[] {
			"-c",
			"bin/config.properties",
			"-o",
			"src/generated-properties-velocity/java",
			"-e",
			"V"
		});
		assertThat(result, is(0));
	}
	
}
