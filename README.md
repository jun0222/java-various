# 目次

<!-- TOC -->

- [目次](#目次)
- [概要](#概要)
- [コマンド](#コマンド)
  - [gradle のビルド](#gradle-のビルド)
  - [起動](#起動)
  - [デバッグ](#デバッグ)
- [ファイル概要](#ファイル概要)
- [実務利用のためのナレッジ](#実務利用のためのナレッジ)
  - [動作確認](#動作確認)
  - [postman での動作確認を prj に合わせて curl で](#postman-での動作確認を-prj-に合わせて-curl-で)
    - [get](#get)
    - [post](#post)
  - [教材に沿って、キリの良いタイミングでまとめて色々必要そうなことを書く](#教材に沿ってキリの良いタイミングでまとめて色々必要そうなことを書く)
- [エラー](#エラー)
  - [Error: Main method not found in the file~](#error-main-method-not-found-in-the-file)
  - [Incompatible because this component declares~](#incompatible-because-this-component-declares)
  - [@SpringBootTest が import できない](#springboottest-が-import-できない)
  - [DBUnit が import できない](#dbunit-が-import-できない)
  - [javax.sql.DataSource が使えない](#javaxsqldatasource-が使えない)
  - [200 になるはずのテストが 404](#200-になるはずのテストが-404)
- [関連情報](#関連情報)

<!-- /TOC -->

# 概要

- 目的
  - Java のキャッチアップ、検証環境、個人用ツール、なんでも入れる。
- 参考教材
  - [実践 SpringBoot \~SpringBoot Advanced Tutorial\~](https://www.techpit.jp/courses/232)
- 使用技術
  - Java
  - SpringBoot
  - MySQL
  - Gradle

# コマンド

## gradle のビルド

```bash
./gradlew build
```

## 起動

メイン関数近くの Run をクリック

## デバッグ

メイン関数近くの Debug をクリック

# ファイル概要

- HelloWorld
  - src/main/java/com/javarious/example/javavarious/app/controller/HelloController.java
- HelloWorld のテストコード
  - src/test/java/com/javarious/example/javavarious/api/HelloApiTest.java

# 実務利用のためのナレッジ

## 動作確認

ビルドすると最低限動作確認ができるので、何か実装したらビルドはする。

## postman での動作確認を prj に合わせて curl で

### get

`postman`

```
- HTTPメソッドをGETにします。
- URLにhttp://localhost:8080/chat-backend/channelsを入力します。
- Bodyタブを開きます。
- Bodyタイプをnoneにします。
- Sendをクリックします。
```

`curl`

```bash
curl -X GET http://localhost:8080/java-various/channels
```

### post

`postman`

```
- HTTPメソッドをPOSTにします。
- URLにhttp://localhost:8080/chat-backend/channelsを入力します。
- Bodyタブを開きます。
- Bodyタイプをrawにします。
- rawのテキストタイプをJSONにします。
- Body本文に{"name": "hoge"}を入力します。
- Sendをクリックします。
```

`curl`

```bash
curl -X POST http://localhost:8080/java-various/channels \
     -H "Content-Type: application/json" \
     -d '{"name": "hoge"}'
```

## 教材に沿って、キリの良いタイミングでまとめて色々必要そうなことを書く

# エラー

## Error: Main method not found in the file~

- エラー詳細
  - Error: Main method not found in the file, please define the main method as: public static void main(String[] args)
  - Run が main 関数のところに出なかったので、右クリックして Run Java というやつをクリックした
- 解決時やったこと

  - すでに動作している別リポジトリ javaCrud を見て Run する方法を確認
  - VSCode の Reload Window を実行
  - そもそも main 関数に表示されていなかった Run が表示されて解決
  - ↓ みたいな感じで結構見えにくい。
    ![picture 0](images/07414e89fcee27c1b2a9d06d71b44a72f8ea467975a059dda240057eeaf012a0.png)

- 解決しない時に見た記事
  - [workspaceStorage フォルダを消す](https://teratail.com/questions/t2833q7nv8wior)
  - [シンタックスの見直し](https://ameblo.jp/taktak0/entry-12372913263.html)

## Incompatible because this component declares~

- エラー詳細
  - Incompatible because this component declares a component, compatible with Java 17 and the consumer needed a component, compatible with Java 13
  - ローカル環境が Java 13 で、プロジェクトが Java 17 だった。
  - `./gradlew build`を実行した時に出た。
- 解決時やったこと
  - ローカル環境の Java をアップデート
  - 21 になったが動いている
  - [大まかな流れの参考記事 ※記事ないは zsh なので、bash 環境の場合注意](https://zenn.dev/hayato94087/articles/c0345e6c2c53e7)
  - 以下を bash_profile に書いて、`source ~/.bash_profile`を実行
  - `java -version`、`./gradlew build`を問題なく実行できた。

## @SpringBootTest が import できない

- 解決時にやったこと
  - tree 出力して GPT 確認
  - ディレクトリ構成が間違っていることを確認
  - main ではなく test 配下に移動して解決
  - .bash_profile に以下を追加して、`source ~/.bash_profile`を実行

```bash
# ~/.bash_profile
export PATH="/usr/local/opt/openjdk/bin:$PATH"
export JAVA_HOME="/usr/local/opt/openjdk"
```

## DBUnit が import できない

- 解決時にやったこと
  - build.gradle に`testImplementation "org.dbunit:dbunit:2.7.3"`を追加
  - `./gradlew build`を実行
  - VSCode で`Reload Window`と`Java: Reload project`を実行

## javax.sql.DataSource が使えない

- エラー詳細
  - `@Autowired`をつけて`private DataSource dataSource;`と宣言しているのに、`org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'com.javarious.example.javavarious.api.HelloApiTest': Unsatisfied dependency expressed through field 'dataSource': No qualifying bean of type 'javax.sql.DataSource' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}`と出る。
- 解決方法
  - Google 検索して`@Autowired`など、アノテーションの問題という仮説を置く
  - 教材の実装サンプルと違い、`@WebMvcTest(HelloController.class)`にしていたため、`@SpringBootTest`に変更、解決。

## 200 になるはずのテストが 404

- エラー詳細
  - `java.lang.AssertionError: Status expected:[200] but was:[404]`となる
  - src/test/java/com/javarious/example/javavarious/api/HelloApiTest.java の`testHello()`が失敗する
- 解決時にやったこと
  - エラーが発生している箇所は api リクエストの箇所と確認
  - queryStrings という値を引数から取っているので内容を確認
  - `test-user-name`としていた。本来は`?name=test-user-name`となるべき。間違った内容だと path に文字列を追加してしまうので、404 になる。
  - 単にエラーを辿って順当に解決する。原因不明な場合は小さくプロトタイプを動かす。動作するものと比較する。

# 関連情報

- [javaCrud（簡易サンプルリポジトリ）](https://github.com/jun0222/javaCrud)
