package com.three.gyangriha.model.dto;

import com.three.gyangriha.enums.IdProofType;
import com.three.gyangriha.model.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private Long userId;
    private String name;
    private String email;
    private String mobileNo;
    private String idProofNo;
    private IdProofType idProofType;

    public UserResponseDTO() {
    }

    public UserResponseDTO(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.mobileNo = user.getMobileNo();
        this.idProofNo = user.getIdProofNo();
        this.idProofType = user.getIdProofType();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
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
}
