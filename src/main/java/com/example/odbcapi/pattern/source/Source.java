package com.example.odbcapi.pattern.source;

import com.example.odbcapi.message.Param;

public interface Source {

    Object getData(Param param);

    Object addData(Param param);

}
