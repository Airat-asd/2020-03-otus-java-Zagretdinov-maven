package ru.otus.protobuf.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.36.0)",
    comments = "Source: RemoteNumberService.proto")
public final class RemoteNumberServiceGrpc {

  private RemoteNumberServiceGrpc() {}

  public static final String SERVICE_NAME = "ru.otus.protobuf.generated.RemoteNumberService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<RangeNumberRequest,
      NumberResponse> getGetNumberMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getNumber",
      requestType = RangeNumberRequest.class,
      responseType = NumberResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<RangeNumberRequest,
      NumberResponse> getGetNumberMethod() {
    io.grpc.MethodDescriptor<RangeNumberRequest, NumberResponse> getGetNumberMethod;
    if ((getGetNumberMethod = RemoteNumberServiceGrpc.getGetNumberMethod) == null) {
      synchronized (RemoteNumberServiceGrpc.class) {
        if ((getGetNumberMethod = RemoteNumberServiceGrpc.getGetNumberMethod) == null) {
          RemoteNumberServiceGrpc.getGetNumberMethod = getGetNumberMethod =
              io.grpc.MethodDescriptor.<RangeNumberRequest, NumberResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getNumber"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  RangeNumberRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  NumberResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RemoteNumberServiceMethodDescriptorSupplier("getNumber"))
              .build();
        }
      }
    }
    return getGetNumberMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RemoteNumberServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RemoteNumberServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RemoteNumberServiceStub>() {
        @Override
        public RemoteNumberServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RemoteNumberServiceStub(channel, callOptions);
        }
      };
    return RemoteNumberServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RemoteNumberServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RemoteNumberServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RemoteNumberServiceBlockingStub>() {
        @Override
        public RemoteNumberServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RemoteNumberServiceBlockingStub(channel, callOptions);
        }
      };
    return RemoteNumberServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RemoteNumberServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RemoteNumberServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RemoteNumberServiceFutureStub>() {
        @Override
        public RemoteNumberServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RemoteNumberServiceFutureStub(channel, callOptions);
        }
      };
    return RemoteNumberServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class RemoteNumberServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getNumber(RangeNumberRequest request,
                          io.grpc.stub.StreamObserver<NumberResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetNumberMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetNumberMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                RangeNumberRequest,
                NumberResponse>(
                  this, METHODID_GET_NUMBER)))
          .build();
    }
  }

  /**
   */
  public static final class RemoteNumberServiceStub extends io.grpc.stub.AbstractAsyncStub<RemoteNumberServiceStub> {
    private RemoteNumberServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected RemoteNumberServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RemoteNumberServiceStub(channel, callOptions);
    }

    /**
     */
    public void getNumber(RangeNumberRequest request,
                          io.grpc.stub.StreamObserver<NumberResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getGetNumberMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RemoteNumberServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<RemoteNumberServiceBlockingStub> {
    private RemoteNumberServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected RemoteNumberServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RemoteNumberServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<NumberResponse> getNumber(
        RangeNumberRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getGetNumberMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RemoteNumberServiceFutureStub extends io.grpc.stub.AbstractFutureStub<RemoteNumberServiceFutureStub> {
    private RemoteNumberServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected RemoteNumberServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RemoteNumberServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_GET_NUMBER = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RemoteNumberServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RemoteNumberServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_NUMBER:
          serviceImpl.getNumber((RangeNumberRequest) request,
              (io.grpc.stub.StreamObserver<NumberResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class RemoteNumberServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RemoteNumberServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return RemoteNumberServiceOuterClass.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RemoteNumberService");
    }
  }

  private static final class RemoteNumberServiceFileDescriptorSupplier
      extends RemoteNumberServiceBaseDescriptorSupplier {
    RemoteNumberServiceFileDescriptorSupplier() {}
  }

  private static final class RemoteNumberServiceMethodDescriptorSupplier
      extends RemoteNumberServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RemoteNumberServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RemoteNumberServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RemoteNumberServiceFileDescriptorSupplier())
              .addMethod(getGetNumberMethod())
              .build();
        }
      }
    }
    return result;
  }
}
