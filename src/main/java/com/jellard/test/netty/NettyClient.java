package com.jellard.test.netty;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;

public class NettyClient{
	
	private Bootstrap bootstrap;
	private static String defaultHost = "localhost";
	private static int defaultPort = 9001;
	private static ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
	
	public static void main(String[] args) {
		new NettyClient().connect(defaultHost, defaultPort);
    }
	public void connect(String host,int port) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            bootstrap = new Bootstrap();
            bootstrap.group(workerGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    //ch.pipeline().addLast(new NettyClientHandler());
                    ChannelPipeline p = ch.pipeline();
                    p.addLast(new IdleStateHandler(0, 0, 5));
                    p.addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, -4, 0));
                    p.addLast(new ClientHeartHandler());

                }
            });
            ChannelFuture future = bootstrap.connect(host, port).sync();
            System.err.println("123");
            future.channel().closeFuture().sync();
            System.out.println("456");
        } catch (Exception e) {
            System.err.println(e);
        } finally {
        	workerGroup.shutdownGracefully();
            reConnect();
		}
	}
	int count = 0;
	protected void reConnect() {
		scheduledExecutor.scheduleWithFixedDelay(new Runnable() {
			
			@Override
			public void run() {
				connect(defaultHost, defaultPort);
				count++;
				System.out.println(count);
				
			}
		}, 1, 1, TimeUnit.MINUTES);
    }
	

}
