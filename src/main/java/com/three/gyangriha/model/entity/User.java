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

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 50)
    private String idProofNo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private IdProofType idProofType;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role = Role.USER;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public User() {
    }

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

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public User setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getIdProofNo() {
        return idProofNo;
    }

    public User setIdProofNo(String idProofNo) {
        this.idProofNo = idProofNo;
        return this;
    }

    public IdProofType getIdProofType() {
        return idProofType;
    }

    public User setIdProofType(IdProofType idProofType) {
        this.idProofType = idProofType;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public User setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
