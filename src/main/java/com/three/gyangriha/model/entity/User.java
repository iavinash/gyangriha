package com.three.gyangriha.model.entity;

import com.three.gyangriha.enums.IdProofType;
import com.three.gyangriha.enums.Role;
import com.three.gyangriha.model.dto.UserRegistrationDTO;
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

    @Column(length = 4000)
    private String address;

    @Column(nullable = false, unique = true, length = 10)
    private String mobileNo;

    @Getter
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, unique = true, length = 20)
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

    public User(UserRegistrationDTO dto) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdProofNo() {
        return idProofNo;
    }

    public void setIdProofNo(String idProofNo) {
        this.idProofNo = idProofNo;
    }

    public IdProofType getIdProofType() {
        return idProofType;
    }

    public void setIdProofType(IdProofType idProofType) {
        this.idProofType = idProofType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
