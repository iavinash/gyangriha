package com.three.gyangriha.service;

import com.three.gyangriha.enums.SubscriptionStatus;
import com.three.gyangriha.model.entity.SubscriptionPlan;
import com.three.gyangriha.model.entity.User;
import com.three.gyangriha.model.entity.UserSubscription;
import com.three.gyangriha.repo.SubscriptionPlanRepository;
import com.three.gyangriha.repo.UserSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionPlanRepository planRepository;

    @Autowired
    private UserSubscriptionRepository subscriptionRepository;

    public SubscriptionPlan addPlan(SubscriptionPlan plan) {
        return planRepository.save(plan);
    }

    public List<SubscriptionPlan> getAllPlans() {
        return planRepository.findAll();
    }

    public UserSubscription subscribe(User user, SubscriptionPlan plan, BigDecimal customPrice) {
        UserSubscription subscription = new UserSubscription();
        subscription.setUser(user);
        subscription.setPlan(plan);
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusMonths(plan.getDurationMonths()));
        subscription.setCustomPrice(customPrice != null ? customPrice : plan.getBasePrice());
        subscription.setStatus(SubscriptionStatus.ACTIVE);
        return subscriptionRepository.save(subscription);
    }

    public List<UserSubscription> getSubscriptionsExpiringSoon() {
        LocalDate soon = LocalDate.now().plusDays(30);
        return subscriptionRepository.findByStatus(SubscriptionStatus.ACTIVE).stream()
                .filter(sub -> sub.getEndDate().isBefore(soon))
                .collect(Collectors.toList());
    }

    public Optional<SubscriptionPlan> getPlanById(Long planId){
        return subscriptionRepository.getPlanById(planId);
    }
}