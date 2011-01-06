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
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashSet;

import jp.tricreo.codegenerator.model.ClassMetaModel;
import jp.tricreo.codegenerator.model.reader.ModelReader;
import jp.tricreo.codegenerator.model.reader.impl.ModelReadFormatType;
import jp.tricreo.codegenerator.service.CodeGenService;
import jp.tricreo.codegenerator.service.impl.TemplateEngineType;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * アプリケーションクラス。
 * 
 * @author j5ik2o
 */
public class Application implements CodeGenContext {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
	

	/**
	 * プログラムのエントリポイント。
	 * 
	 * @param args プログラム引数
	 */
	public static void main(String[] args) {
		System.exit(new Application().run(args));
	}
	

	private Collection<ClassMetaModel> classMetaModels = new HashSet<ClassMetaModel>();
	
	private File configFile = new File("config.properties");
	
	private File templateDir = new File("template");
	
	private File exportDir = new File("export");
	
	private TemplateEngineType templateEngineType = TemplateEngineType.FREE_MAKER;
	
	private BasicParser parser = new BasicParser();
	

	private CommandLine createCommandLine(Options options, String[] args) throws ParseException {
		CommandLine cl = parser.parse(options, args);
		return cl;
	}
	
	@Override
	public Collection<ClassMetaModel> getClassMetaModels() {
		return new HashSet<ClassMetaModel>(classMetaModels);
	}
	
	@Override
	public File getExportDir() {
		return exportDir;
	}
	
	private ModelReadFormatType getModelReadFormatType(File configFile) {
		Validate.notNull(configFile);
		String configFilePath = configFile.getPath();
		if (configFilePath.endsWith(".properties")) {
			return ModelReadFormatType.PROPERTIES;
		} else if (configFilePath.endsWith(".json")) {
			return ModelReadFormatType.JSON;
		}
		return null;
	}
	
	@Override
	public File getTemplateDir() {
		return templateDir;
	}
	
	// プログラム引数を解析する
	private boolean parseCommandLine(String[] args) throws ParseException, FileNotFoundException {
		Options options = new Options();
		options.addOption("h", "help", false, "help");
		options.addOption("c", "config", true, "config.properties {.properties, .json}");
		options.addOption("o", "output-dir", true, "output dir");
		options.addOption("t", "template-dir", true, "template file dir");
		options.addOption("e", "template-engine", true, "template engine {F = freemarker, V = velocity}");
		
		CommandLine cl = createCommandLine(options, args);
		if (cl.hasOption('h')) {
			new HelpFormatter().printHelp("help tips", options);
			return false;
		}
		if (cl.hasOption("c")) {
			configFile = new File(cl.getOptionValue("c"));
		}
		if (cl.hasOption("o")) {
			exportDir = new File(cl.getOptionValue("o"));
		}
		if (cl.hasOption("t")) {
			templateDir = new File(cl.getOptionValue("t"));
		}
		if (cl.hasOption("e")) {
			if (cl.getOptionValue("e").equals("F")) {
				templateEngineType = TemplateEngineType.FREE_MAKER;
			} else if (cl.getOptionValue("e").equals("V")) {
				templateEngineType = TemplateEngineType.VELOCITY;
			}
		}
		if (!configFile.exists()) {
			throw new FileNotFoundException(configFile + "is not found.");
		}
		
		LOGGER.info("設定ファイル = {}", configFile);
		LOGGER.info("テンプレートパス : {}", templateDir);
		LOGGER.info("出力パス : {}", exportDir);
		LOGGER.info("テンプレートエンジンの種類 : {}", templateEngineType);
		
		return true;
	}
	
	/**
	 * プログラムを実行する。
	 * 
	 * @param args プログラム引数
	 * @return プログラムが正常に終了した場合は0,異常終了の場合は1
	 */
	public int run(String[] args) {
		try {
			if (parseCommandLine(args)) {
				ModelReader modelReader = new ModelReader(getModelReadFormatType(configFile), configFile);
				classMetaModels.addAll(modelReader.readAll());
				new CodeGenService(templateEngineType).generate(this);
			}
			return 0;
		} catch (Exception e) {
			LOGGER.error("例外が発生しました。", e);
		}
		return 1;
	}
}
