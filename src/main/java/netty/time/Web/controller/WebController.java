package netty.time.Web.controller;

import java.util.Base64;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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
import netty.time.Web.domain.InstructMsg;

@RestController
public class WebController {
	

	/**
	 * 设置加速度输出
	 * @param msg
	 * @param bindingResult
	 * @throws Exception
	 */
	@GetMapping(value="/open/{id}")
	public void openDog(@PathVariable("id") String id) {
		
        byte[] a = id.getBytes();
        
        byte[] b = new byte[8];
        b[0] = (byte) 0xFF;
        b[1] = (byte) 0xAA;
        b[2] = (byte) 0x02;
        b[3] = (byte) 0x02;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        
        byte[] bytes = unitByteArray(a, b);
		
		ChannelHandlerContext ctx = (ChannelHandlerContext) GatewayService.getCtx();
		
		ctx.writeAndFlush(Unpooled.copiedBuffer(bytes));
	}
	
	/**
	 * 设置加速度输出
	 * @param msg
	 * @param bindingResult
	 * @throws Exception
	 */
	@GetMapping(value="/find/{id}")
	public void findDog(@PathVariable("id") String id) {
		
        byte[] a = id.getBytes();
        
        byte[] b = new byte[8];
        b[0] = (byte) 0xFF;
        b[1] = (byte) 0xAA;
        b[2] = (byte) 0x03;
        b[3] = (byte) 0x0C;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        
        byte[] bytes = unitByteArray(a, b);
		
		ChannelHandlerContext ctx = (ChannelHandlerContext) GatewayService.getCtx();
		
		ctx.writeAndFlush(Unpooled.copiedBuffer(bytes));
	}
	
	/**
	 * 向设备发出指令
	 * @param instruction
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value="/instuct")
	public void postInstruction(@Valid InstructMsg msg, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()) {
			throw new Exception(bindingResult.getFieldError().getDefaultMessage());
		}
		
		String id = "ht8888888888";
		
        byte[] a = id.getBytes();
        
		byte[] b = new byte[8];
		b[0] = (byte) 0xCC;
    	b[1] = (byte) 0x33;
    	b[2] = (byte) 0x01;
    	b[3] = (byte) 0x00;
    	b[4] = (byte) 0x00;
    	b[5] = (byte) 0x00;
    	b[6] = (byte) 0xC3;
    	b[7] = (byte) 0x3C;
		
		byte[] bytes = unitByteArray(a, b);
		
				
		ChannelHandlerContext ctx = (ChannelHandlerContext) GatewayService.getCtx();
		
		ctx.writeAndFlush(Unpooled.copiedBuffer(bytes));
	}
	
	/**
     * 合并byte数组
     */
    public byte[] unitByteArray(byte[] byte1,byte[] byte2){
        byte[] unitByte = new byte[byte1.length + byte2.length];
        System.arraycopy(byte1, 0, unitByte, 0, byte1.length);
        System.arraycopy(byte2, 0, unitByte, byte1.length, byte2.length);
        return unitByte;
    }

}
