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
package jp.co.nikkeibp.software.codegen.service.impl;

import java.io.File;

import jp.co.nikkeibp.software.codegen.CodeGenContext;
import jp.co.nikkeibp.software.codegen.model.ClassMetaModel;
import jp.co.nikkeibp.software.codegen.service.CodeGenStrategy;

import org.apache.commons.lang.Validate;

public abstract class AbstractCodeGenStrategy implements CodeGenStrategy {
	
	protected File getActualExportDir(CodeGenContext context, ClassMetaModel cm) {
		Validate.notNull(context);
		Validate.notNull(cm);
		File actualExportDir = context.getExportDir();
		if (cm.getPackageName() != null) {
			String packageDir = cm.getPackageName().replaceAll("\\.", "/");
			actualExportDir = new File(context.getExportDir(), packageDir);
		}
		return actualExportDir;
	}
	
}
