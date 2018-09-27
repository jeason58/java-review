package com.jeason.thrift.service;

import com.alibaba.fastjson.JSONObject;
import com.jeason.thrift.message.Response;
import com.jeason.thrift.pojo.User;
import java.util.List;

public interface UserService extends IBusinessService {

  Response getById(JSONObject params);

  Response getList(JSONObject params);

  Response updateUser(JSONObject params);

  Response deleteUser(JSONObject params);

}
