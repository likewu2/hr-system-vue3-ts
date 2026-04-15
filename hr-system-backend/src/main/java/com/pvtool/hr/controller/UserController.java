package com.pvtool.hr.controller;

import com.pvtool.hr.dto.LoginRequest;
import com.pvtool.hr.dto.LoginResponse;
import com.pvtool.hr.model.Employee;
import com.pvtool.hr.model.Onboarding;
import com.pvtool.hr.model.Contract;
import com.pvtool.hr.service.EmployeeService;
import com.pvtool.hr.service.OnboardingService;
import com.pvtool.hr.service.ContractService;
import com.pvtool.hr.service.ApprovalService;
import com.pvtool.hr.util.JwtUtil;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private OnboardingService onboardingService;
    
    @Autowired
    private ContractService contractService;
    
    @Autowired
    private ApprovalService approvalService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllUsers() {
        List<Employee> users = employeeService.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        Employee user = employeeService.authenticate(request.getUsername(), request.getPassword());
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
    public ResponseEntity<?> signContract(@PathVariable Long id, @AuthenticationPrincipal Employee user) {
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