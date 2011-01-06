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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jp.tricreo.codegenerator.CodeGenContext;
import jp.tricreo.codegenerator.model.ClassMetaModel;
import jp.tricreo.codegenerator.service.CodeGenStrategy;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FreeMarkerのための{@link CodeGenStrategy}の実装。
 * 
 * @author j5ik2o
 */
public class CodeGenStrategyForFreeMarker extends AbstractCodeGenStrategy {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CodeGenStrategyForFreeMarker.class);
	

	@Override
	public void generate(CodeGenContext context) throws CodeGenException {
		Validate.notNull(context);
		File templateDirFile = context.getTemplateDir();
		Configuration cfg = new Configuration();
		try {
			cfg.setDirectoryForTemplateLoading(templateDirFile);
			Template template = cfg.getTemplate("java.ftl");
			
			for (ClassMetaModel cm : context.getClassMetaModels()) {
				FileWriter fw = null;
				try {
					Map<String, Object> rootMap = new HashMap<String, Object>();
					rootMap.put("classMetaModel", cm);
					
					File exportClassDir = getExportClassDir(context, cm);
					exportClassDir.mkdirs();
					
					fw = new FileWriter(new File(exportClassDir, cm.getClassName() + ".java"));
					template.process(rootMap, fw);
					fw.flush();
					LOGGER.info("ソースコードを生成しました。 : {}", cm.getClassName());
				} catch (TemplateException ex) {
					throw new CodeGenException(ex);
				} finally {
					IOUtils.closeQuietly(fw);
				}
			}
		} catch (IOException ex) {
			throw new CodeGenException(ex);
		}
	}
}
