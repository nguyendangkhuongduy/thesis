package com.duy.thesisManagement.thesis.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
		@GetMapping("/all")
		public String allAccess() {
				return "Public Content.";
		}

		@GetMapping("/user")
		@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ASSOCIATE')")
		public String userAccess() {
				return "User Content.";
		}
		@GetMapping("/manager")
		@PreAuthorize("hasRole('ROLE_MANAGER')")
		public String moderatorAccess() {
				return "Moderator Board.";
		}
		@GetMapping("/admin")
		@PreAuthorize("hasRole('ROLE_ADMIN')")
		public String adminAccess() {
				return "Admin Board.";
		}
}
