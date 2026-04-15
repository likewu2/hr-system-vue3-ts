package com.pvtool.hr.service.impl;

import com.pvtool.hr.model.Onboarding;
import com.pvtool.hr.service.OnboardingService;
import org.springframework.stereotype.Service;

@Service
public class OnboardingServiceImpl implements OnboardingService {
    @Override
    public Onboarding save(Onboarding onboarding) {
        // 这里应该实现保存逻辑，实际项目中需要使用Repository
        return onboarding;
    }
    
    @Override
    public Onboarding findById(Long id) {
        // 这里应该实现查询逻辑，实际项目中需要使用Repository
        return new Onboarding();
    }
}