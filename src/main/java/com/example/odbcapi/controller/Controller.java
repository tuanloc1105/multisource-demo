package com.example.odbcapi.controller;

import com.example.odbcapi.message.request.ProcessRequest;
import com.example.odbcapi.pattern.source.DataFactory;
import com.example.odbcapi.pattern.source.Source;
import com.example.odbcapi.service.ContactServiceMysql;
import com.example.odbcapi.service.ContactServicePostgres;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/get-data")
    public Object getData(@RequestBody ProcessRequest request,
                          @RequestHeader(name = "Authorization") String token) throws Exception {
        this.validate(token);
        Source source = this.dataFactory.getSource(request.getSourceType());
        switch (request.getBehavior()) {
            case GET:
                return source.getData(request.getParam());
            case INSERT:
                return source.addData(request.getParam());
            default:
                return null;
        }
    }

    @PostMapping("/authen")
    public String auth(@RequestParam(name = "password") String password) {
        return this.genToken(password);
    }

    public void api() {

    }

}
