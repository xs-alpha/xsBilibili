package com.xiaosheng.video.api.controller;

import com.xiaosheng.video.services.service.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class DemoController {
    @Resource
    private DemoService demoService;


    @RequestMapping("/test")
    public Map<String, Map<String, Object>> test() {
        Map<String, Map<String, Object>> demo = demoService.demo();
        return demo;
    }
}
