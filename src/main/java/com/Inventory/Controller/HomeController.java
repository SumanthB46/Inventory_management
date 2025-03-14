package com.Inventory.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";  
    }
	@GetMapping("/Login")
	public String openLoginPage() {
		return "Login";
	}
	 @GetMapping("/home")
	    public String returnhome() {
	        return "index";  
	    }

	
}
