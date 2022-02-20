## sbt project compiled with Scala 3
## 実行方法
> sbt run

## 出力ファイル
antiUnificationOut.txt -> 動詞ごとのanti-unificationの結果
cnadidateRules.txt -> anti-unificationの結果から絞り込んでる
rules.txt -> 自動で生成したパターンマッチのルール一覧

convertOut.txt -> rules.txtのルールから字句解析仕様全体の形式化を行った結果


extractRule_LOG.txt -> cnadidateRules.txtの結果からrules.txtを生成するまでのログ

