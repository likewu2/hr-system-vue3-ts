package com.pvtool.hr.controller;

import com.pvtool.hr.dto.ApiResponse;
import com.pvtool.hr.model.Employee;
import com.pvtool.hr.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userService.authenticate(request.getUsername(), request.getPassword());
        if (user != null) {
            String token = jwtUtil.generateToken(user);
            return ResponseEntity.ok(new LoginResponse(user.getName(), user.getRole(), token));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/onboarding")
    public ResponseEntity<Onboarding> createOnboarding(@RequestBody Onboarding onboarding) {
        onboarding.setStatus("待入职");
        return ResponseEntity.ok(onboardingService.save(onboarding));
    }

    @PostMapping("/contracts/{id}/sign")
    public ResponseEntity<?> signContract(@PathVariable Long id, @AuthenticationPrincipal User user) {
        Contract contract = contractService.findById(id);
        contract.setSignedBy(user.getId());
        contract.setSignedAt(new Date());
        contract.setStatus("已签署");
        contractService.save(contract);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/approvals/{id}/approve")
    public ResponseEntity<?> approve(@PathVariable Long id) {
        approvalService.approve(id);
        return ResponseEntity.ok().build();
    }
}