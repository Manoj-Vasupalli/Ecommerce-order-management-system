package com.ecommerce.notification.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.core.dto.ApiResponse;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
	
	
	
	    @GetMapping("/health-check")
	    public ApiResponse<String> health() {
	        return ApiResponse.success("Notification service is running", "OK");
	    }
	
}
