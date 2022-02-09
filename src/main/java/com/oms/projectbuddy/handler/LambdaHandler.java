package com.oms.projectbuddy.handler;//package com.oms.projectbuddy.handler;
//
//
//import com.amazonaws.serverless.exceptions.ContainerInitializationException;
//import com.amazonaws.serverless.proxy.internal.LambdaContainerHandler;
//import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
//import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
//import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
//import com.amazonaws.serverless.proxy.spring.SpringBootProxyHandlerBuilder;
//import com.amazonaws.services.lambda.runtime.Context;
//import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
//import com.oms.projectbuddy.ProjectbuddyApplication;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.time.Instant;
//
//public class LambdaHandler implements RequestStreamHandler {
//    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
//
//
//    public LambdaHandler() {
//        try {
//            long startTime = Instant.now().toEpochMilli();
//            // by setting this value to 50_000 the framework will wait for an additional 50 seconds
//            // on top of the default 10 initialization time.
//            LambdaContainerHandler.getContainerConfig().setInitializationTimeout(50_000);
//
//            handler = new SpringBootProxyHandlerBuilder()
//                    .defaultProxy()
//                    .asyncInit(startTime)
//                    .springBootApplication(ProjectbuddyApplication.class)
//                    .buildAndInitialize();
//        } catch (ContainerInitializationException e) {
//            e.printStackTrace();
//        }
//    }
//
////    static {
////        try {
////            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(ProjectbuddyApplication.class);
////            // For applications that take longer than 10 seconds to start, use the async builder:
////            long startTime = Instant.now().toEpochMilli();
////            handler = new SpringBootProxyHandlerBuilder()
////                    .defaultProxy()
////                    .asyncInit(startTime)
////                    .springBootApplication(ProjectbuddyApplication.class)
////                    .buildAndInitialize();
////        } catch (ContainerInitializationException e) {
////            // if we fail here. We re-throw the exception to force another cold start
////            e.printStackTrace();
////            throw new RuntimeException("Could not initialize Spring Boot application", e);
////        }
////    }
//    @Override
//    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context)
//            throws IOException {
//
//        handler.proxyStream(inputStream, outputStream, context);
//    }
//}
