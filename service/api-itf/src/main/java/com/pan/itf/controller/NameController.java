package com.pan.itf.controller;

import com.pan.itf.model.req.UserReq;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-02-22 13:43
 **/
@RestController
@RequestMapping("/name")
public class NameController {
    @GetMapping("/get")
    public String getNameByGet(@RequestParam String name) {
        return "GET: 你的name是" + name;
    }

    @PostMapping("/post")
    public Map<String, Object> getNameByPost(
        @RequestParam String name,
        @RequestBody UserReq user) {
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("name", name);
            put("user", user);
        }};
        return map;
    }
}
