## sbt project compiled with Scala 3

### Usage

<h1>実行方法</h1>
> sbt run

<h1>出力ファイル</h1>
antiUnificationOut.txt -> 動詞ごとのanti-unificationの結果
cnadidateRules.txt -> anti-unificationの結果から絞り込んでる
rules.txt -> 自動で生成したパターンマッチのルール一覧

convertOut.txt -> rules.txtのルールから字句解析仕様全体の形式化を行った結果s


extractRule_LOG.txt -> cnadidateRules.txtの結果からrules.txtを生成するまでのログ




This is a normal sbt project. You can compile code with `sbt compile`, run it with `sbt run`, and `sbt console` will start a Scala 3 REPL.

For more information on the sbt-dotty plugin, see the
[scala3-example-project](https://github.com/scala/scala3-example-project/blob/main/README.md).
