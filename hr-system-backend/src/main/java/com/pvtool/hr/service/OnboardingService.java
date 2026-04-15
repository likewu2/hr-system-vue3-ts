package com.pvtool.hr.service;

import com.pvtool.hr.model.Onboarding;

public interface OnboardingService {
    Onboarding save(Onboarding onboarding);
    Onboarding findById(Long id);
}