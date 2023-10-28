package com.javarious.example.javavarious.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// エンドポイントを作成するためのアノテーション
@RestController
public class HelloController {
    // http://localhost:8080/java-various/hello にアクセスすると、"Hello World!" と表示される
    // @RequestMapping(path = "/hello", method = RequestMethod.GET) // 以下のコードと等価
    @GetMapping("/hello2") // pathを変えたらコマンドのhistoryからではなく、Runしなおして新しい実行ファイルを実行する必要がある
    public String hello() {
        return "Hello World!";
    }
}
