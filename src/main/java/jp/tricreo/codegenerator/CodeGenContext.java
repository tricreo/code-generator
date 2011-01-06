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
package jp.tricreo.codegenerator;

import java.io.File;
import java.util.Collection;

import jp.tricreo.codegenerator.model.ClassMetaModel;

/**
 * コード生成のためのコンテキスト。
 * <p>コード生成のために必要なモデルを集めたクラス。</p>
 * 
 * @author j5ik2o
 */
public interface CodeGenContext {
	
	/**
	 * {@link ClassMetaModel}の集合を取得する。 
	 * 
	 * @return {@link ClassMetaModel}の集合
	 */
	Collection<ClassMetaModel> getClassMetaModels();
	
	/**
	 * 出力先へのパスを取得する。
	 * 
	 * @return 出力先へのパス
	 */
	File getExportDir();
	
	/**
	 * テンプレートを配置しているディレクトリへのパスを取得する。
	 * 
	 * @return テンプレートを配置しているディレクトリへのパス
	 */
	File getTemplateDir();
	
}
