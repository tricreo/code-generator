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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.tricreo.codegenerator.model.ClassMetaModel;
import jp.tricreo.codegenerator.model.FieldMetaModel;
import jp.tricreo.codegenerator.model.reader.ModelReadStrategy;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

import org.apache.commons.lang.Validate;

/**
 * JSON形式のファイルからモデルを読み込むための{@link ModelReadStrategy}の実装。
 * 
 * @author j5ik2o
 */
public class ModelReadStrategyInJSON implements ModelReadStrategy {
	
	private final File jsonFilePath;
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param jsonFilePath JSON形式の設定ファイル
	 */
	public ModelReadStrategyInJSON(File jsonFilePath) {
		Validate.notNull(jsonFilePath);
		this.jsonFilePath = jsonFilePath;
	}
	
	@SuppressWarnings("unchecked")
	List<Map<String, Object>> loadClassMetaModeMaps(File file) throws JSONException, FileNotFoundException, IOException {
		return (List<Map<String, Object>>) JSON.decode(new FileReader(file));
	}
	
	@Override
	public Collection<ClassMetaModel> readAll() throws IOException {
		Set<ClassMetaModel> result = new HashSet<ClassMetaModel>();
		List<Map<String, Object>> classMetaModelMaps = loadClassMetaModeMaps(jsonFilePath);
		for (Map<String, Object> classMetaModelMap : classMetaModelMaps) {
			String className = (String) classMetaModelMap.get("className");
			String packageName = (String) classMetaModelMap.get("packageName");
			@SuppressWarnings("unchecked")
			List<Map<String, String>> fieldMetaModelMaps = (List<Map<String, String>>) classMetaModelMap.get("fields");
			List<FieldMetaModel> fieldMetaModels = new ArrayList<FieldMetaModel>();
			for (Map<String, String> fieldMetaModelMap : fieldMetaModelMaps) {
				String fieldName = fieldMetaModelMap.get("fieldName");
				String typeName = fieldMetaModelMap.get("typeName");
				fieldMetaModels.add(new FieldMetaModel(fieldName, typeName));
			}
			ClassMetaModel classMetaModel = new ClassMetaModel(className, packageName, fieldMetaModels);
			result.add(classMetaModel);
		}
		return result;
	}
}
