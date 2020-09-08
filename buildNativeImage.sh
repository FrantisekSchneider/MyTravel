#!/usr/bin/env bash

echo $GRAALVM_HOME

/home/fs/my/dev/graalvm-ce-java11-19.3.3/bin/native-image \
    --no-server \
    --no-fallback \
    -H:+ReportUnsupportedElementsAtRuntime \
    -H:Class=de.openvalue.magic.TravelApplication \
    --initialize-at-run-time=org.springframework.data.r2dbc.connectionfactory.ConnectionFactoryUtils \
    --initialize-at-build-time=io.r2dbc.spi.IsolationLevel,io.r2dbc.spi  \
    --initialize-at-build-time=io.r2dbc.spi.ConstantPool,io.r2dbc.spi.Assert,io.r2dbc.spi.ValidationDepth  \
    --initialize-at-build-time=org.springframework.data.r2dbc.connectionfactory \
 --allow-incomplete-classpath \
--report-unsupported-elements-at-runtime \
 -H:+ReportExceptionStackTraces \
  --initialize-at-build-time=org.reactivestreams.Publisher \
   --initialize-at-build-time=com.example.reactive.ReservationRepository \
    --initialize-at-run-time=io.netty.channel.unix.Socket  \
    --initialize-at-run-time=io.netty.channel.unix.IovArray  \
    --initialize-at-run-time=io.netty.channel.epoll.EpollEventLoop \
    --initialize-at-run-time=io.netty.channel.unix.Errors\
    -jar build/libs/MyTravel-v1.0.0.jar