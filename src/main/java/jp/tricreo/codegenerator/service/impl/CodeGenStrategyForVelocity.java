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
import java.io.FileWriter;
import java.util.Properties;

import jp.tricreo.codegenerator.CodeGenContext;
import jp.tricreo.codegenerator.model.ClassMetaModel;
import jp.tricreo.codegenerator.service.CodeGenStrategy;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.Validate;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.tools.generic.DisplayTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Velocityのための{@link CodeGenStrategy}の実装。
 * 
 * @author j5ik2o
 */
public class CodeGenStrategyForVelocity extends AbstractCodeGenStrategy {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CodeGenStrategyForVelocity.class);
	
	private static final String TEMPLATE_FILENAME = "java.vm";
	
	private static final String TEMPLATE_FILE_ENCODING = "UTF-8";
	
	private static final String GENERATE_FILE_EXT = "java";
	

	@Override
	public void generate(CodeGenContext context) throws CodeGenException {
		Validate.notNull(context);
		File templateDirFile = context.getTemplateDir();
		Properties properties = new Properties();
		properties.setProperty("file.resource.loader.path", templateDirFile.getPath());
		try {
			Velocity.init(properties);
			Template template = Velocity.getTemplate(TEMPLATE_FILENAME, TEMPLATE_FILE_ENCODING);
			DisplayTool displayTool = new DisplayTool();
			for (ClassMetaModel classMetaModel : context.getClassMetaModels()) {
				FileWriter fw = null;
				try {
					VelocityContext velocityContext = new VelocityContext();
					velocityContext.put("displayTool", displayTool);
					velocityContext.put("classMetaModel", classMetaModel);
					
					File exportClassDir = getExportClassDir(context, classMetaModel);
					exportClassDir.mkdirs();
					
					fw =
							new FileWriter(new File(exportClassDir, classMetaModel.getClassName() + "."
									+ GENERATE_FILE_EXT));
					template.merge(velocityContext, fw);
					fw.flush();
					LOGGER.info("ソースコードを生成しました。 : {}", classMetaModel.getClassName());
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
