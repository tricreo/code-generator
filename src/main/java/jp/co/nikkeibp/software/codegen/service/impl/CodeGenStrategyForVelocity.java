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
import java.io.FileWriter;
import java.util.Properties;

import jp.co.nikkeibp.software.codegen.CodeGenContext;
import jp.co.nikkeibp.software.codegen.model.ClassMetaModel;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.Validate;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.tools.generic.DisplayTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodeGenStrategyForVelocity extends AbstractCodeGenStrategy {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CodeGenStrategyForVelocity.class);
	

	@Override
	public void generate(CodeGenContext context) throws CodeGenException {
		Validate.notNull(context);
		File templateDirFile = context.getTemplateDir();
		Properties properties = new Properties();
		properties.setProperty("file.resource.loader.path", templateDirFile.getPath());
		try {
			Velocity.init(properties);
			Template template = Velocity.getTemplate("java.vm", "UTF-8");
			DisplayTool displayTool = new DisplayTool();
			for (ClassMetaModel cm : context.getClassMetaModels()) {
				FileWriter fw = null;
				try {
					VelocityContext velocityContext = new VelocityContext();
					velocityContext.put("displayTool", displayTool);
					velocityContext.put("classMetaModel", cm);
					
					File actualExportDir = getActualExportDir(context, cm);
					actualExportDir.mkdirs();
					
					fw = new FileWriter(new File(actualExportDir, cm.getClassName() + ".java"));
					template.merge(velocityContext, fw);
					fw.flush();
					LOGGER.info("ソースコードを生成しました。 : {}", cm.getClassName());
				} catch (Exception e) {
					throw new CodeGenException(e);
				} finally {
					IOUtils.closeQuietly(fw);
				}
			}
		} catch (Exception ex) {
			throw new CodeGenException(ex);
		}
	}
}
