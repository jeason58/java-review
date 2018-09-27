package com.jeason.thrift.service;


import com.alibaba.fastjson.JSONObject;
import com.jeason.thrift.message.Response;

@FunctionalInterface
public interface IBusinessService {

  Response handle(String operation, JSONObject parameters);

  default Response defaultResponse() {
    return defaultResponse(400, "invalid parameter of operation", null);
  }

  default Response defaultResponse(int code, String message) {
    return defaultResponse(code, message, null);
  }

  default Response defaultResponse(int code, String message, String data) {
    return new Response(code, message, data);
  }

}
