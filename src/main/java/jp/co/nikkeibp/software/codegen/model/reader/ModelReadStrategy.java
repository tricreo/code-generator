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
package jp.co.nikkeibp.software.codegen.model.reader;

import java.io.IOException;
import java.util.Collection;

import jp.co.nikkeibp.software.codegen.model.ClassMetaModel;

/**
 * モデルを読み込むためのストラテジ(戦略)。
 * 
 * @author j5ik2o
 */
public interface ModelReadStrategy {
	
	/**
	 * モデルを読み込む。
	 * 
	 * @return {@link ClassMetaModel}のコレクション
	 * @throws IOException モデルの読み込みに失敗した場合
	 */
	Collection<ClassMetaModel> readAll() throws IOException;
	
}
