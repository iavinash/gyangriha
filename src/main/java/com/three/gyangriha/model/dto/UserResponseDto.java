package com.three.gyangriha.model.dto;

import com.three.gyangriha.enums.IdProofType;
import com.three.gyangriha.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long userId;
    private String name;
    private String email;
    private String mobileNo;
    private IdProofType idProofType;

    public UserResponseDto(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.mobileNo = user.getMobileNo();
        this.idProofType = user.getIdProofType();
    }
}
