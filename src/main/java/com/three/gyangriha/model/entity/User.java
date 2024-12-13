package com.three.gyangriha.model.entity;

import com.three.gyangriha.enums.IdProofType;
import com.three.gyangriha.enums.Role;
import com.three.gyangriha.model.dto.UserRegistrationDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 255)
    private String address;

    @Column(nullable = false, unique = true, length = 15)
    private String mobileNo;

    @Getter
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 50)
    private String idProofNo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private IdProofType idProofType;

    @Column(nullable = false)
    private String password;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role = Role.USER;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public User(String name, String address, String mobileNo, String email, String idProofNo, IdProofType idProofType, String password, Role role) {
        this.name = name;
        this.address = address;
        this.mobileNo = mobileNo;
        this.email = email;
        this.idProofNo = idProofNo;
        this.idProofType = idProofType;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.role = role;
    }

    public User(UserRegistrationDto dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.role = Role.USER;
        this.address = dto.getAddress();
        this.mobileNo = dto.getMobileNo();
        this.idProofNo = dto.getIdProof();
        this.idProofType = dto.getIdProofType();
        //this.password = new BCryptPasswordEncoder().encode(dto.getPassword());
    }

}
