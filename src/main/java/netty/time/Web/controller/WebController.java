package netty.time.Web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import netty.time.Web.App;
import netty.time.Web.NettyClient.TimeClient;
import netty.time.Web.NettyClient.utils.GatewayService;

@RestController
public class WebController {
	
	@Autowired
	private TimeClient tc;
	
	/**
	 * 查询设备状态
	 * @param id 设备编号
	 * @return
	 */
	@GetMapping(value="/find/{id}")
	public String findOne(@PathVariable("id") String id) {
		return "123456789";
	}
	
	/**
	 * 向设备发出指令
	 * @param instruction
	 * @return
	 */
	@PostMapping(value="/instuct")
	public void postInstruction(String msg) {
		System.out.println("abcdef");
		msg = "post____123456";
		ChannelHandlerContext ctx = (ChannelHandlerContext) GatewayService.getCtx();
		
		ctx.writeAndFlush(Unpooled.copiedBuffer("good!!!".getBytes()));
	}

}
