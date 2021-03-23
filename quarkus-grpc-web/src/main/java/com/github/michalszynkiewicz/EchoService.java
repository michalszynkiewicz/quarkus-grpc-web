package com.github.michalszynkiewicz;

import grpc.gateway.testing.Echo;
import grpc.gateway.testing.EchoServiceGrpc;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;
import io.smallrye.common.annotation.Blocking;
import io.vertx.core.Vertx;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CopyOnWriteArrayList;

@Singleton
public class EchoService extends EchoServiceGrpc.EchoServiceImplBase {

    @Inject
    Vertx vertx;

    @Override
    public void echo(Echo.EchoRequest request, StreamObserver<Echo.EchoResponse> responseObserver) {
        String messageToEcho = request.getMessage();
        responseObserver.onNext(Echo.EchoResponse.newBuilder().setMessage(messageToEcho).build());

        responseObserver.onCompleted();
    }

    @Override
    public void echoAbort(Echo.EchoRequest request, StreamObserver<Echo.EchoResponse> responseObserver) {
        responseObserver.onError(new StatusException(Status.ABORTED));
    }

    @Override
    @Blocking
    public void serverStreamingEcho(Echo.ServerStreamingEchoRequest request, StreamObserver<Echo.ServerStreamingEchoResponse> responseObserver) {
        String message = request.getMessage();

        int messageCount = request.getMessageCount();
        int messageInterval = request.getMessageInterval();
        sendWithDelay(messageCount == 0 ? 10 : messageCount, messageInterval == 0 ? 100 : messageInterval,
                Echo.ServerStreamingEchoResponse.newBuilder().setMessage(message + " streamed").build(), responseObserver);
    }

    <T> void sendWithDelay(int times, int sleep, T response, StreamObserver<T> responseObserver) {
        if (times > 0) {
            responseObserver.onNext(response);
            vertx.setTimer(sleep, ignored -> sendWithDelay(times - 1, sleep, response, responseObserver));
        } else {
            responseObserver.onCompleted();
        }
    }

    @Override
    public void serverStreamingEchoAbort(Echo.ServerStreamingEchoRequest request, StreamObserver<Echo.ServerStreamingEchoResponse> responseObserver) {
        super.serverStreamingEchoAbort(request, responseObserver);
    }

    @Override
    public StreamObserver<Echo.ClientStreamingEchoRequest> clientStreamingEcho(StreamObserver<Echo.ClientStreamingEchoResponse> responseObserver) {
        return super.clientStreamingEcho(responseObserver);
    }

    @Override
    public StreamObserver<Echo.EchoRequest> fullDuplexEcho(StreamObserver<Echo.EchoResponse> responseObserver) {
        return super.fullDuplexEcho(responseObserver);
    }

    @Override
    public StreamObserver<Echo.EchoRequest> halfDuplexEcho(StreamObserver<Echo.EchoResponse> responseObserver) {
        return super.halfDuplexEcho(responseObserver);
    }
}
