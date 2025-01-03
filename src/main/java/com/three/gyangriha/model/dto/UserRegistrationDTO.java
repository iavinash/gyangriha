package com.three.gyangriha.model.dto;

import com.three.gyangriha.enums.IdProofType;
import com.three.gyangriha.enums.SubscriptionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDTO {
    private Long id;
    private String name;
    private String address;
    private String mobileNo;
    private String email;
    private String password;
    private String idProof;
    private IdProofType idProofType;
    private SubscriptionType subscriptionType;
    private BigDecimal customPrice;

    public UserRegistrationDTO() {
    }

    public UserRegistrationDTO(Long id, String name, String address, String mobileNo, String email, String password, String idProof, IdProofType idProofType) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.mobileNo = mobileNo;
        this.email = email;
        this.password = password;
        this.idProof = idProof;
        this.idProofType = idProofType;
    }

    public UserRegistrationDTO(Long id, String name, String address, String mobileNo, String email, String password, String idProof, IdProofType idProofType, SubscriptionType subscriptionType, BigDecimal customPrice) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.mobileNo = mobileNo;
        this.email = email;
        this.password = password;
        this.idProof = idProof;
        this.idProofType = idProofType;
        this.subscriptionType = subscriptionType;
        this.customPrice = customPrice;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdProof() {
        return idProof;
    }

    public void setIdProof(String idProof) {
        this.idProof = idProof;
    }

    public IdProofType getIdProofType() {
        return idProofType;
    }

    public void setIdProofType(IdProofType idProofType) {
        this.idProofType = idProofType;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public BigDecimal getCustomPrice() {
        return customPrice;
    }

    public void setCustomPrice(BigDecimal customPrice) {
        this.customPrice = customPrice;
    }
}
