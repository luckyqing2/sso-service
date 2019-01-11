package com.luckyqing.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: notice
 * @description:首页展示类
 * @author: "清歌"
 * @create: 2018-08-21 11:59
 **/
@Controller
public class IndexController {

    // 首页展示

    @RequestMapping("/index")
    public String toIndex(){
        return "/index";
    }

}
