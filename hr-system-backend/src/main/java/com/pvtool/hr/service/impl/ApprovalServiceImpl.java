package com.pvtool.hr.service.impl;

import com.pvtool.hr.service.ApprovalService;
import org.springframework.stereotype.Service;

@Service
public class ApprovalServiceImpl implements ApprovalService {
    @Override
    public void approve(Long id) {
        // 这里应该实现审批逻辑，实际项目中需要使用Repository
        System.out.println("Approving request with id: " + id);
    }
}