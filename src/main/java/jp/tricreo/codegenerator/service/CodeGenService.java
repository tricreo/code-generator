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
package jp.tricreo.codegenerator.service;

import jp.tricreo.codegenerator.CodeGenContext;
import jp.tricreo.codegenerator.service.impl.CodeGenException;
import jp.tricreo.codegenerator.service.impl.TemplateEngineType;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * コード生成のためのサービス。 
 * 
 * @author j5ik2o
 */
public class CodeGenService {
	
	private final CodeGenStrategy codeGenStrategy;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CodeGenService.class);
	

	/**
	 * 
	 * インスタンスを生成する。
	 * 
	 * @param codeGenStrategy {@link CodeGenStrategy}
	 */
	public CodeGenService(CodeGenStrategy codeGenStrategy) {
		Validate.notNull(codeGenStrategy);
		this.codeGenStrategy = codeGenStrategy;
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param templateEngineType {@link TemplateEngineType}
	 */
	public CodeGenService(TemplateEngineType templateEngineType) {
		Validate.notNull(templateEngineType);
		codeGenStrategy = templateEngineType.toCodeGenStrategy();
	}
	
	/**
	 * コードを生成する。
	 * 
	 * @param context {@link CodeGenContext}
	 * @throws CodeGenException コード生成に失敗した場合
	 */
	public void generate(CodeGenContext context) throws CodeGenException {
		Validate.notNull(context);
		LOGGER.info("ソースコード生成を開始しました。");
		codeGenStrategy.generate(context);
		LOGGER.info("ソースコード生成が完了しました。");
	}
}
