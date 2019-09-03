package com.jellard.test.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;

public class NettyServer {
	
	public void bind(int port) throws InterruptedException {
		EventLoopGroup loopGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(loopGroup, workGroup);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
			bootstrap.childHandler(new ChildChannelHandler());
			ChannelFuture future = bootstrap.bind(port).sync();
			System.out.println("789");
			future.channel().closeFuture().sync();
			System.out.println("78910");
		} finally {
			loopGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}
	private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
           // ch.pipeline().addLast(new NettyServerHandle());
        	ChannelPipeline p = ch.pipeline();
            p.addLast(new IdleStateHandler(10, 0, 0));
            p.addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, -4, 0));
            p.addLast(new ServerHeartHandler());

        }

    }

    public static void main(String[] args) throws InterruptedException {
        int port = 9001;
        if (args != null && args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        new NettyServer().bind(port);

    }

}
