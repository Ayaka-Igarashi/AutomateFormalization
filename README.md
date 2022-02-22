## sbt project compiled with Scala 3
## 実行方法
> sbt run

## 出力ファイルについて
<h5>rules.txt</h5>
自動で生成したパターンマッチのルール一覧

<h5>convertOut.txt</h5>
rules.txtのルールから字句解析仕様全体の形式化を行った結果

<h5>antiUnificationOut.txt</h5>
動詞ごとのanti-unificationの結果

<h5>candidateRules.txt</h5>
anti-unificationの結果を絞り込んだもの






<h5>extractRule_LOG.txt</h5>
cnadidateRules.txtの結果からrules.txtを生成するまでのログ

