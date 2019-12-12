package com.yjy.ws.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yjy.ws.config.WebSocketServer;

@Controller
@RequestMapping("/websocket")
public class WebSocketController {

	// 页面请求
	@GetMapping("/socket/{cid}")
	public String socket(@PathVariable String cid, Model model) {
		model.addAttribute("cid", cid); //客户id
		return "/socket";
	}
	
	// 群发推送
	@ResponseBody
	@RequestMapping("/socket/push")
	public String pushToWeb(String message) {
		WebSocketServer.sendMessage(message);
		return "推送成功！推送消息为：" + message;
	}

	// 推送给某客户
	@ResponseBody
	@RequestMapping("/socket/push/{cid}")
	public String pushToWeb(@PathVariable String cid, String message) {
		WebSocketServer.sendMessage(cid, message);
		return "推送成功！推送客户：" + cid + "推送消息为：" + message;
	}
}
