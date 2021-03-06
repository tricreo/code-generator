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
package jp.tricreo.codegenerator.model.reader.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import jp.tricreo.codegenerator.model.ClassMetaModel;

import org.junit.Test;

/**
 * {@link ModelReadStrategyInProperties}のためのテスト。
 * 
 * @author j5ik2o
 */
public class ModelReadStrategyInPropertiesTest {
	
	/**
	 * リポジトリからすべてのエンティティが読み込めること
	 * 
	 * @throws IOException リポジトリから読み込めない場合
	 */
	@Test
	public void test01_リポジトリからすべてのエンティティが読み込めること() throws IOException {
		ModelReadStrategyInProperties modelReaderProperty =
				new ModelReadStrategyInProperties(new File("bin/config.properties"));
		Collection<ClassMetaModel> classMetaModels = modelReaderProperty.readAll();
		
		assertThat(classMetaModels, is(notNullValue()));
		assertThat(classMetaModels.size(), is(2));
		for (ClassMetaModel cm : classMetaModels) {
			System.out.println(cm.toString());
		}
	}
	
}
