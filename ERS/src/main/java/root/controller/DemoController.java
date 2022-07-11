package root.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
public class DemoController {

	@RequestMapping("/hello")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String helloEric() {
		return "Hello there!";
	}
}
