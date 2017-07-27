package com.learn.demo.unit.controller;

import com.learn.demo.unit.domain.Language;
import com.learn.demo.unit.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 类说明:
 *
 * @author 杨磊
 * @since 2017-07-25 下午2:36
 */
@RestController
public class DemoController {
    @Autowired
    private DemoService service;

    @GetMapping("helloworld")
    public ResponseEntity helloWorld() {
        return ResponseEntity.ok("hello world");
    }

    @GetMapping(path = "language/{id}")
    public ResponseEntity getLanguage(@PathVariable Integer id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping(path = "language/{pageIndex}/{pageSize}")
    public ResponseEntity getLanguagePage(@PathVariable Integer pageIndex, @PathVariable Integer pageSize) {
        return ResponseEntity.ok(service.list(pageIndex, pageSize));
    }

    @PostMapping(path = "language")
    public ResponseEntity saveLanguage(@RequestBody Language language) {
        return ResponseEntity.ok(service.save(language));
    }

    @PutMapping(path = "language")
    public ResponseEntity updateLanguage(@RequestBody Language language) {
        return ResponseEntity.ok(service.update(language));
    }
}
