コードジェネレータ
==================

できること
-----------
設定に基づきJavaBeansのソースコードを自動生成すること。

ビルド方法
-----------
ビルドに必要な環境: JDK 1.5以上, Maven2 

$ mvn clean package

$ mvn dependency:copy-dependencies -DoutputDirectory=bin/lib

$ cp target/code-generator-0.0.1.jar bin/lib


使い方
----------
$ cd bin

ヘルプの表示

$ codegen -h 

コード生成

$ codegen -c config.properties -e F -t template -o export 

-c は、設定ファイルへのパス。設定ファイルの形式はプロパティファイル形式と、JSON形式が使えます。-cを指定しない場合は、このオプションにconfig.propertiesが指定されたものとして動作する。

-e は、テンプレートエンジンの種類。FがFreeMarker, VがVelocity。-eを指定しない場合は、このオプションにFが指定されたものとして動作する。

-t は、コード生成に使うテンプレートファイルを配置しているディレクトリへのパス。テンプレートファイルはFreeMarker用がjava.ftl,Velocity用がjava.vtl。テンプレートをカスタマイズする場合はこのファイルを修正する。-tを指定しない場合は、このオプションにtemplateが指定されたものとして動作する。

-o は、コードを出力する先のパス。-oを指定しない場合は、このオプションに、exportを指定したものとして動作する。


License
-------
Copyright (C) 2010 [TRICREO, Inc.](http://tricreo.jp/).

Distributed under the Apache License v2.0.  See the file LICENSE.txt.
