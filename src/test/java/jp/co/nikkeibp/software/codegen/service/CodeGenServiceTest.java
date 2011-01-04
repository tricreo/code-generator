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
package jp.co.nikkeibp.software.codegen.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.HashSet;

import jp.co.nikkeibp.software.codegen.CodeGenContext;
import jp.co.nikkeibp.software.codegen.model.ClassMetaModel;
import jp.co.nikkeibp.software.codegen.service.impl.CodeGenException;

import org.junit.Test;

/**
 * {@link CodeGenService}のためのテスト。 
 * 
 * @author j5ik2o
 */
public class CodeGenServiceTest {
	
	@Test
	public void testMerge() throws CodeGenException {
		
		CodeGenStrategy codeGenStrategy = mock(CodeGenStrategy.class);
		CodeGenContext context = mock(CodeGenContext.class);
		
		when(context.getClassMetaModels()).thenReturn(new HashSet<ClassMetaModel>());
		when(context.getTemplateDir()).thenReturn(new File("template"));
		when(context.getExportDir()).thenReturn(new File("src/generated/java"));
		
		CodeGenService codeGenService = new CodeGenService(codeGenStrategy);
		codeGenService.generate(context);
		
		verify(codeGenStrategy).generate(context);
		
	}
}
