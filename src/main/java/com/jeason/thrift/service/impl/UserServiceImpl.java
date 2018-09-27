package com.jeason.thrift.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jeason.thrift.message.Response;
import com.jeason.thrift.pojo.User;
import com.jeason.thrift.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: jeason·wang
 * @date: 2018-08-03 09:54
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

  @Override
  public Response handle(String operation, JSONObject paramJson) {
    if (operation != null) {
      switch (operation) {
        case "getById":
          return getById(paramJson);
        case "getList":
          return getList(paramJson);
        case "updateUser":
          return updateUser(paramJson);
        case "deleteUser":
          return deleteUser(paramJson);
        default: return defaultResponse();
      }
    }
    return defaultResponse(400, "operation cannot be null");
  }

  @Override
  public Response getById(JSONObject params) {
    Integer id = params.getInteger("id");
    User user = new User(id, "jeasonwang", "male", 24);
    Response response = new Response(200, "success", JSONObject.toJSONString(user));
    return response;
  }

  @Override
  public Response getList(JSONObject params) {
    return defaultResponse();
  }

  @Override
  public Response updateUser(JSONObject params) {
    return defaultResponse();
  }

  @Override
  public Response deleteUser(JSONObject params) {
    return defaultResponse();
  }
}
