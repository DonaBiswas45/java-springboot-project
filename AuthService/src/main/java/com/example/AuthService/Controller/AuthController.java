package com.example.AuthService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.AuthService.Entity.User;
import com.example.AuthService.service.AuthService;

@RestController          // ← must have this
@RequestMapping("/auth") 
public class AuthController {
	 @Autowired
	    private AuthService authService;

	    @PostMapping("/register")
	    public String register(@RequestBody User user) {
	        return authService.register(user);
	    }

	    @PostMapping("/login")
	    public String login(@RequestParam String email,
	                        @RequestParam String password) {
	        return authService.login(email, password);
	    }
	    
	    @GetMapping("/test")
	    public String test() {
	        return "App is working!";
	    }
}
