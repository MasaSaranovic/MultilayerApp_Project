package rppstart.ctrls;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@Api(tags = {"Hello Rest Controller - Vezba"})
public class HelloRestController {
	
	@ApiOperation(value = "Vraća string Hello World")
	@RequestMapping("/")
	public String helloWorld() {
		return "hello world";
      }
	
	@ApiOperation(value = "Vraća uvek razliciti zbir slučajno generisanih brojeva")
	@RequestMapping("zbir")
	public String zbir() {
		long x = Math.round(Math.random() * 10);
		long y = Math.round(Math.random() * 10);
		
		return x + " + " + y + " = " + (x+y);
	}

}
