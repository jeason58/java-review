package com.jeason.thrift.message;

import com.alibaba.fastjson.JSONObject;
import com.jeason.thrift.service.IBusinessService;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.thrift.TException;

/**
 * @description:
 * @author: jeason·wang
 * @date: 2018-08-03 11:25
 **/
public class ProcessorService implements MessageService.Iface {
  private Map<String, IBusinessService> serviceMap = new HashMap<>();

  public void registerService(String serviceName, IBusinessService service) {
    serviceMap.putIfAbsent(Objects.requireNonNull(serviceName), Objects.requireNonNull(service));
  }

  @Override
  public Response doService(String serviceName, Request request) throws TException {
    String operation = request.getOperation();
    String paramString = request.getParameter();
    JSONObject paramJson = JSONObject.parseObject(paramString);
    IBusinessService businessService = serviceMap.get(serviceName);

    return businessService.handle(operation, paramJson);
  }
}
