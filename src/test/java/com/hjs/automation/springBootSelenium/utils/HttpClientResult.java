package com.hjs.automation.springBootSelenium.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpClientResult {

//    private static final long serialVersionUID = 2168152194164783950L;

    private int code;

    private String content;

    private JSONObject responseJson;

    @Override
    public String toString() {
        return "HttpClientResult {status=" + code + ", content=\'" + content + '\'' + "}";
    }
}
