package com.machine.socket.netty.marshalling;
import java.io.File;
import java.io.FileOutputStream;

import com.machine.utils.io.zip.GzipUtils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter{

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		Req req = (Req)msg;
		System.out.println("Server : " + req.getId() + ", " + req.getName() + ", " + req.getRequestMessage());
		/*byte[] attachment = GzipUtils.ungzip(req.getAttachment());
		
		String path = System.getProperty("user.dir") + File.separatorChar + "receive" +  File.separatorChar + "001.jpg";
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(attachment);
        fos.close();*/
		
		Resp resp = new Resp();
		resp.setId(req.getId());
		resp.setName("resp" + req.getId());
		resp.setResponseMessage("响应内容" + req.getId());
		ctx.writeAndFlush(resp);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}

	
	
}
