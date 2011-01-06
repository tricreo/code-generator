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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * クラスの情報を扱うモデル。
 * 
 * @author j5ik2o
 */
public class ClassMetaModel {
	
	private final String packageName;
	
	private final String className;
	
	private final List<FieldMetaModel> fieldMetaModels;
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param className クラス名
	 */
	public ClassMetaModel(String className) {
		this(className, null);
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param className クラス名
	 * @param packageName パッケージ名
	 */
	public ClassMetaModel(String className, String packageName) {
		this(className, packageName, new ArrayList<FieldMetaModel>());
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param className クラス名
	 * @param packageName パッケージ名。nullの場合がある。
	 * @param fieldMetaModels {@link FieldMetaModel}のリスト
	 */
	public ClassMetaModel(String className, String packageName, List<FieldMetaModel> fieldMetaModels) {
		Validate.notNull(className);
		Validate.notNull(fieldMetaModels);
		this.className = className;
		this.packageName = packageName;
		this.fieldMetaModels = new ArrayList<FieldMetaModel>(fieldMetaModels);
	}
	
	/**
	 * クラス名を取得する。
	 * 
	 * @return クラス名
	 */
	public String getClassName() {
		return className;
	}
	
	/**
	 * {@link FieldMetaModel}のリストを取得する。
	 * <p>返されるリストは複製であり、そのリストの状態を更新しても
	 * {@link ClassMetaModel}は影響を受けない。</p>
	 * 
	 * @return {@link FieldMetaModel}のリスト
	 */
	public List<FieldMetaModel> getFieldMetaModels() {
		return new ArrayList<FieldMetaModel>(fieldMetaModels);
	}
	
	/**
	 * パッケージ名を返す。
	 * 
	 * @return パッケージ名。nullが帰る場合がある。
	 */
	public String getPackageName() {
		return packageName;
	}
	
	// デバッグやログ出力を助けるためにtoStringを実装しておく。
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
