package com.javarious.example.javavarious.app.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javarious.example.javavarious.domain.Hello;

// エンドポイントを作成するためのアノテーション
@RestController
public class HelloController {
    @GetMapping("/hello")
    public Hello hello(@RequestParam("name") Optional<String> name) {

        // ↓引数未指定時は「world!」とする。
        String resName = name.orElse("world!");
        // ↓引数の値または「world!」を
        return new Hello("Hello, " + resName);
    }

    // http://localhost:8080/java-various/hello にアクセスすると、"Hello World!" と表示される
    // @RequestMapping(path = "/hello", method = RequestMethod.GET) // 以下のコードと等価
    @GetMapping("/hello2") // pathを変えたらコマンドのhistoryからではなく、Runしなおして新しい実行ファイルを実行する必要がある
    public String hello() {
        return "Hello World!";
    }
}
