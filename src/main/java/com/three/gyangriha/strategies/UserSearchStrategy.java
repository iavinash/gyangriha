package com.three.gyangriha.strategies;

import com.three.gyangriha.model.entity.User;
import org.springframework.data.jpa.domain.Specification;

public interface UserSearchStrategy {
    Specification<User> buildSpecification(String value);
}
