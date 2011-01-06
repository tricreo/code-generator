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

import java.io.File;

import jp.tricreo.codegenerator.CodeGenContext;
import jp.tricreo.codegenerator.model.ClassMetaModel;
import jp.tricreo.codegenerator.service.CodeGenStrategy;

import org.apache.commons.lang.Validate;

/**
 * {@link CodeGenStrategy}の抽象クラス。
 * 
 * @author j5ik2o
 */
public abstract class AbstractCodeGenStrategy implements CodeGenStrategy {
	
	/**
	 * 指定した{@link ClassMetaModel}から実際の出力ディレクトリを取得する。 
	 * 
	 * @param codeGenContext {@link CodeGenContext} 
	 * @param classMetaModel {@link ClassMetaModel}
	 * @return 出力ディレクトリ。nullを返してはならない。
	 */
	protected File getExportClassDir(CodeGenContext codeGenContext, ClassMetaModel classMetaModel) {
		Validate.notNull(codeGenContext);
		Validate.notNull(classMetaModel);
		File exportDir = codeGenContext.getExportDir();
		if (classMetaModel.getPackageName() != null) {
			String packageDir = classMetaModel.getPackageName().replaceAll("\\.", "/");
			exportDir = new File(codeGenContext.getExportDir(), packageDir);
		}
		return exportDir;
	}
	
}
