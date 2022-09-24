package com.xiaosheng.video.services.service;

import com.xiaosheng.video.dao.mapper.DemoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class DemoService {
    @Resource
    private DemoMapper demoMapper;


    public Map<String, Map<String, Object>> demo() {
        Map<String, Object> stringObjectMap1 = demoMapper.queryallData(1);
        Map<String, Object> stringObjectMap2 = demoMapper.queryallData(2);
        Map<String, Object> stringObjectMap3 = demoMapper.queryallData(3);
        HashMap<String, Map<String, Object>> stringMapHashMap = new HashMap<>();
        stringMapHashMap.put("str", stringObjectMap1);
        stringMapHashMap.put("str2", stringObjectMap2);
        stringMapHashMap.put("str3", stringObjectMap3);
        return stringMapHashMap;
    }
}