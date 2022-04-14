package io.springboot.exe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wxm
 * @version 1.0
 * @since 2022/4/14 14:26
 */
@RestController
public class IndexController {

    @GetMapping("/index")
    public String index(){

        return "Hello world!";
    }

}
