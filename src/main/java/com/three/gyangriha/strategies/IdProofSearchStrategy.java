package com.three.gyangriha.strategies;

import com.three.gyangriha.model.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component("idProofNo")
public class IdProofSearchStrategy implements UserSearchStrategy {
    @Override
    public Specification<User> buildSpecification(String value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("idProof"), value);
    }
}
