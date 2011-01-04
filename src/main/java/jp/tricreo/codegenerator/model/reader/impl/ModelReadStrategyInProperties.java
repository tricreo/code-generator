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
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import jp.tricreo.codegenerator.model.ClassMetaModel;
import jp.tricreo.codegenerator.model.FieldMetaModel;
import jp.tricreo.codegenerator.model.reader.ModelReadStrategy;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.Validate;

/**
 * プロパティファイルからモデルを読み込むための{@link ModelReadStrategy}の実装。
 * 
 * @author j5ik2o
 */
public class ModelReadStrategyInProperties implements ModelReadStrategy {
	
	private final File propertiesFilePath;
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param propertiesFilePath プロパティファイル形式の設定ファイル
	 */
	public ModelReadStrategyInProperties(File propertiesFilePath) {
		Validate.notNull(propertiesFilePath);
		this.propertiesFilePath = propertiesFilePath;
	}
	
	private Map<String, Object> getOrNewPutGetClassMetaModel(Map<String, Map<String, Object>> classModels, String id) {
		Validate.notNull(classModels);
		Validate.notNull(id);
		Map<String, Object> classModel = classModels.get(id);
		if (classModel == null) {
			classModel = new HashMap<String, Object>();
			classModels.put(id, classModel);
		}
		return classModel;
	}
	
	private Map<String, String> getOrNewPutGetFieldMetaModel(Map<String, Object> classModel) {
		Validate.notNull(classModel);
		@SuppressWarnings("unchecked")
		Map<String, String> fieldModels = (Map<String, String>) classModel.get("fields");
		if (fieldModels == null) {
			fieldModels = new HashMap<String, String>();
			classModel.put("fields", fieldModels);
		}
		return fieldModels;
	}
	
	/**
	 * プロパティファイルを読み込み{@link Properties}に変換する。
	 * 
	 * @param filePath プロパティファイル
	 * @return {@link Properties}
	 * @throws IOException プロパティファイルの読み込みに失敗した場合
	 */
	protected Properties loadProperties(File filePath) throws IOException {
		Validate.notNull(filePath);
		FileInputStream fis = null;
		Properties result = new Properties();
		try {
			fis = new FileInputStream(filePath);
			result.load(fis);
		} finally {
			IOUtils.closeQuietly(fis);
		}
		return result;
	}
	
	@Override
	public Collection<ClassMetaModel> readAll() throws IOException {
		Set<ClassMetaModel> result = new HashSet<ClassMetaModel>();
		Map<String, Map<String, Object>> classMetaModels = new HashMap<String, Map<String, Object>>();
		Properties properties = loadProperties(propertiesFilePath);
		for (Entry<Object, Object> propertiesEntry : properties.entrySet()) {
			String key = (String) propertiesEntry.getKey();
			if (key.startsWith("models")) {
				String[] splits = key.split("\\.");
				String id = splits[1];
				Map<String, Object> classMetaModel = getOrNewPutGetClassMetaModel(classMetaModels, id);
				String property = splits[2];
				String value = properties.getProperty(key);
				if (property.equals("fields")) {
					String fieldName = splits[3];
					Map<String, String> fieldModels = getOrNewPutGetFieldMetaModel(classMetaModel);
					fieldModels.put(fieldName, value);
				} else {
					classMetaModel.put(property, value);
				}
			}
		}
		Set<Entry<String, Map<String, Object>>> entrySet = classMetaModels.entrySet();
		for (Entry<String, Map<String, Object>> entry : entrySet) {
			String className = (String) entry.getValue().get("className");
			String packageName = (String) entry.getValue().get("packageName");
			
			@SuppressWarnings("unchecked")
			Map<String, String> fieldModels = (Map<String, String>) entry.getValue().get("fields");
			Set<Entry<String, String>> fieldModelsEntrySet = fieldModels.entrySet();
			
			final List<FieldMetaModel> fieldMetaModels = new ArrayList<FieldMetaModel>();
			for (Entry<String, String> fieldModelsEntry : fieldModelsEntrySet) {
				FieldMetaModel fieldMetaModel =
						new FieldMetaModel(fieldModelsEntry.getKey(), fieldModelsEntry.getValue());
				fieldMetaModels.add(fieldMetaModel);
			}
			
			ClassMetaModel classMetaModel = new ClassMetaModel(className, packageName, fieldMetaModels);
			result.add(classMetaModel);
		}
		
		return result;
	}
}
