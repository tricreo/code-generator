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
package jp.tricreo.codegenerator.model.reader;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import jp.tricreo.codegenerator.model.ClassMetaModel;
import jp.tricreo.codegenerator.model.reader.impl.ModelReadFormatType;
import jp.tricreo.codegenerator.model.reader.impl.ModelReadStrategyInJSON;
import jp.tricreo.codegenerator.model.reader.impl.ModelReadStrategyInProperties;

import org.apache.commons.lang.Validate;

/**
 * モデルを読み込むためのリーダ。
 * 
 * @author j5ik2o
 */
public class ModelReader {
	
	private ModelReadStrategy modelReadStrategy;
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param modelReadFormatType {@link ModelReadFormatType}
	 * @param configFilePath 設定ファイルを表す{@link File} 
	 */
	public ModelReader(ModelReadFormatType modelReadFormatType, File configFilePath) {
		modelReadStrategy = newModelReadStrategy(modelReadFormatType, configFilePath);
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param modelReadStrategy {@link ModelReadStrategy}
	 */
	public ModelReader(ModelReadStrategy modelReadStrategy) {
		Validate.notNull(modelReadStrategy);
		this.modelReadStrategy = modelReadStrategy;
	}
	
	// ModelReadStrategyを生成するファクトリメソッド。
	private ModelReadStrategy newModelReadStrategy(ModelReadFormatType modelReadFormatType, File configFilePath) {
		Validate.notNull(modelReadFormatType);
		Validate.notNull(configFilePath);
		if (modelReadFormatType == ModelReadFormatType.JSON) {
			return new ModelReadStrategyInJSON(configFilePath);
		} else if (modelReadFormatType == ModelReadFormatType.PROPERTIES) {
			return new ModelReadStrategyInProperties(configFilePath);
		}
		return null;
	}
	
	/**
	 * モデルを読み込む。
	 * <p>{@link ModelReadStrategy}を使ってモデルを読み込む。</p>
	 * 
	 * @return {@link ClassMetaModel}のコレクション
	 * @throws IOException モデルの読み込みに失敗した場合
	 */
	public Collection<ClassMetaModel> readAll() throws IOException {
		return modelReadStrategy.readAll();
	}
	
}
