package com.jeason.thrift;

import com.jeason.thrift.message.MessageService.Processor;
import com.jeason.thrift.message.ProcessorService;
import com.jeason.thrift.service.UserService;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * @description:
 * @author: jeason·wang
 * @date: 2018-08-03 11:17
 **/
public class ThriftServer {
  private static final int DEFAULT_SERVER_PORT = 8989;
  private static final String DEFAULT_SERVER_NAME = "ThriftServer";
  private static Logger logger = LoggerFactory.getLogger(ThriftServer.class);

  private int port;
  private String serverName;
  private ProcessorService processorService;


  private ThriftServer(ApplicationContext context) {
    this.port = DEFAULT_SERVER_PORT;
    this.serverName = DEFAULT_SERVER_NAME;
    initProcessorService(context);
  }

  // 初始化processorService，并注册service组件
  private void initProcessorService(ApplicationContext context) {
    processorService = new ProcessorService();
    processorService.registerService("userService", (UserService) context.getBean("userService"));
  }

  public static ThriftServer getServer(ApplicationContext context) {
    return new ThriftServer(context);
  }

  public void startServer() {
    try {
      TServerTransport serverTransport = new TServerSocket(port);
      Args args = new Args(serverTransport);
      Processor serviceProcessor = new Processor(processorService);
      TServer server = new TThreadPoolServer(args.processor(serviceProcessor));
      logger.info(serverName + " started on " + port + "...");
      server.serve();
    } catch (TTransportException e) {
      e.printStackTrace();
    }
  }

}
