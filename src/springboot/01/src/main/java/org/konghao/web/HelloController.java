package org.konghao.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by konghao on 2016/11/21.
 */
@RestController
public class HelloController {

    @RequestMapping(value="/hello",method = RequestMethod.GET)
    public String hello() {
        System.out.println("hello");
        return "hello springboot";
    }
}
