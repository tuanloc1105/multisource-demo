package com.example.odbcapi.pattern.source;

import com.example.odbcapi.value.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataFactory {

    @Autowired
    private MysqlData mysqlData;

    @Autowired
    private PostgresData postgresData;

    public Source getSource(SourceType type) throws Exception {
        switch (type) {
            case POSTGRES:
                return postgresData;
            case MYSQL:
                return mysqlData;
            default:
                throw new Exception("Unknown source");
        }
    }
}
