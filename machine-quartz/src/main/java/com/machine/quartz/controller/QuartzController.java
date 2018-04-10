package com.machine.quartz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.machine.quartz.service.QuartzService;

@Controller
@RequestMapping("/")
public class QuartzController {
	
	@Autowired
	QuartzService quartzService;

	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "hello world";
	}
	
	
	@RequestMapping("/pause")
	@ResponseBody
	public String pause() {
		quartzService.pause();
		return "pause";
	}
	
	@RequestMapping("/resume")
	@ResponseBody
	public String resume() {
		quartzService.resume();
		return "resume";
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update() {
		quartzService.update();
		return "update";
	}
	
	@RequestMapping("/remove")
	@ResponseBody
	public String remove() {
		quartzService.remove();
		return "remove";
	}
	
}
