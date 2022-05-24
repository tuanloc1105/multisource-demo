package com.example.odbcapi.controller;

import com.example.odbcapi.pattern.DataFactory;
import com.example.odbcapi.service.ContactServiceMysql;
import com.example.odbcapi.service.ContactServicePostgres;
import com.example.odbcapi.value.SourceType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class Controller extends BaseController {

    private final ContactServiceMysql contactServiceMysql;
    private final ContactServicePostgres contactServicePostgres;
    private final DataFactory dataFactory;

    @GetMapping("/get-data/{source}")
    public Object getData(@PathVariable(name = "source") SourceType sourceType,
                          @RequestHeader(name = "Authorization") String token) throws Exception {
        this.validate(token);
//        return dataFactory.getSource(sourceType).getAllData();
        return null;
    }

    @PostMapping("/authen")
    public String auth(@RequestParam(name = "password") String password) {
        return this.genToken(password);
    }

    public void api() {

    }

}
