コードジェネレータ
==================

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


License
-------
Copyright (C) 2010 [TRICREO, Inc.](http://tricreo.jp/).

Distributed under the Apache License v2.0.  See the file LICENSE.txt.
