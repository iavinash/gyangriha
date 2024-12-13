package com.three.gyangriha.controller;

import com.three.gyangriha.model.entity.Attendance;
import com.three.gyangriha.model.entity.User;
import com.three.gyangriha.model.entity.UserSubscription;
import com.three.gyangriha.service.AttendanceService;
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

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/subscriptions-expiring")
    public ResponseEntity<List<UserSubscription>> getExpiringSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getSubscriptionsExpiringSoon());
    }

    @PostMapping("/mark-attendance")
    public ResponseEntity<Attendance> markAttendance(@RequestParam Long userId) throws ChangeSetPersister.NotFoundException {
        User user = userService.findUserById(userId).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        return ResponseEntity.ok(attendanceService.markAttendance(user));
    }
}

