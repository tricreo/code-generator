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
package jp.tricreo.codegenerator.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * クラスのフィールドを表すモデル。
 * 
 * @author j5ik2o
 */
public class FieldMetaModel {
	
	private final String fieldName;
	
	private final String typeName;
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param fieldName フィールド名
	 * @param typeName フィールドの型名
	 */
	public FieldMetaModel(String fieldName, String typeName) {
		this.fieldName = fieldName;
		this.typeName = typeName;
	}
	
	/**
	 * フィールド名を取得する。
	 * 
	 * @return フィールド名
	 */
	public String getFieldName() {
		return fieldName;
	}
	
	/**
	 * フィールドの型名を取得する。 
	 * 
	 * @return フィールドの型名
	 */
	public String getTypeName() {
		return typeName;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
