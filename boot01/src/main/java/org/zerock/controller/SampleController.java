package org.zerock.controller;

import org.zerock.domain.SampleVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
	
	@GetMapping("/Hello")
	public String sayHello() {
		return "Hello, World";
	}

	@GetMapping("/sample")
	public SampleVO makeSample() {
		
		SampleVO vo = new SampleVO();
		vo.setVal1("val1");
		vo.setVal2("val2");
		vo.setVal3("val3");
		
		System.out.println(vo);
		
		return vo;
	}
}
