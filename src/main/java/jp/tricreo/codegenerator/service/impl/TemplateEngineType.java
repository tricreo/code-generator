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
package jp.tricreo.codegenerator.service.impl;

import jp.tricreo.codegenerator.service.CodeGenStrategy;

import org.apache.commons.lang.Validate;

/**
 * テンプレートエンジンの種類を表す列挙型。
 * <p>標準でサポートしているテンプレートエンジンの種類を表す列挙型。</p>
 * 
 * @author j5ik2o
 */
public enum TemplateEngineType {
	
	/** FreeMarker */
	FREE_MAKER(new CodeGenStrategyForFreeMarker(), "FreeMarker"),
	/** Velocity */
	VELOCITY(new CodeGenStrategyForVelocity(), "Velocity");
	
	private final CodeGenStrategy codeGenStrategy;
	
	private final String typeName;
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param codeGenStrategy {@link CodeGenStrategy}
	 * @param typeName テンプレートエンジンの名前
	 */
	TemplateEngineType(CodeGenStrategy codeGenStrategy, String typeName) {
		Validate.notNull(codeGenStrategy);
		Validate.notNull(typeName);
		this.codeGenStrategy = codeGenStrategy;
		this.typeName = typeName;
	}
	
	/**
	 * この列挙型を{@link CodeGenStrategy}に変換する。
	 * 
	 * @return {@link CodeGenStrategy}
	 */
	public CodeGenStrategy toCodeGenStrategy() {
		return codeGenStrategy;
	}
	
	@Override
	public String toString() {
		return typeName;
	}
}
