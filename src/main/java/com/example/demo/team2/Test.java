package com.example.demo.team2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

	// 正常系
    @GetMapping("/team2/hello2")
    public String hello() {
        return "Hello World2!";
    }

    // RuntimeExceptionを発生させる
    @GetMapping("/team2/error/runtime")
    public String runtimeError() {
        throw new RuntimeException("RuntimeExceptionのテストです");
    }

}
