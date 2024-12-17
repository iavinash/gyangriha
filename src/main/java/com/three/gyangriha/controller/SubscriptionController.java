package com.three.gyangriha.controller;

import com.three.gyangriha.model.entity.SubscriptionPlan;
import com.three.gyangriha.model.entity.User;
import com.three.gyangriha.model.entity.UserSubscription;
import com.three.gyangriha.service.SubscriptionService;
import com.three.gyangriha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private UserService userService;

    @GetMapping("/plans")
    public ResponseEntity<List<SubscriptionPlan>> getAllPlans() {
        return ResponseEntity.ok(subscriptionService.getAllPlans());
    }

    @PostMapping("/subscribe")
    public ResponseEntity<UserSubscription> subscribe(
            @RequestParam Long userId,
            @RequestParam Long planId,
            @RequestParam(required = false) BigDecimal customPrice) throws ChangeSetPersister.NotFoundException {
        // Fetch user and plan from repositories
        User user = userService.findUserById(userId).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        Optional<User> optionalUser = userService.findUserById(userId);

        SubscriptionPlan plan = subscriptionService.getPlanById(planId).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        return ResponseEntity.ok(subscriptionService.subscribe(user, plan, customPrice));
    }
}

