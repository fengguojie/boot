package com.jellard.test.netty;


import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class NettyServerHandle extends ChannelHandlerAdapter {
	
	    private int Count = 0;

	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
	            throws Exception {
	        cause.printStackTrace();
	        ctx.close();
	    }

	    public void channelRead(ChannelHandlerContext ctx, Object msg)
	            throws Exception {
	    	Count++;
	        ByteBuf buf = (ByteBuf) msg;
	        byte[] req = new byte[buf.readableBytes()];// ��û������ɶ����ֽ���
	        buf.readBytes(req);
	        String body = new String(req, "UTF-8");
	        System.err.println("the netty server receive order : "+Count + body);
	        String currentTime = "query time order".equalsIgnoreCase(body) ? new Date(
	                System.currentTimeMillis()).toString() : "bad order";
	        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
	        ctx.write(resp);//性能考虑，仅将待发送的消息发送到缓冲数组中，再通过调用flush方法，写入channel中
	    }

	    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
	        ctx.flush();//将消息发送队列中的消息写入到SocketChannel中发送给对方。
	    }

}