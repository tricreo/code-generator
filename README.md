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

$ codegen -c config.properties -t template -o export -e F

-c は、設定ファイルへのパス。設定ファイルの形式はプロパティファイル形式と、JSON形式が使えます。
-t は、コード生成に使うテンプレートファイルを配置しているディレクトリへのパス。
-o は、コードを出力する先のパス。
-e は、テンプレートエンジンの種類。FがFreeMarker, VがVelocity。


License
-------
Copyright (C) 2010 [TRICREO, Inc.](http://tricreo.jp/).

Distributed under the Apache License v2.0.  See the file LICENSE.txt.
