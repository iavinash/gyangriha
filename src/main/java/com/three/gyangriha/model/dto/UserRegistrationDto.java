package com.three.gyangriha.model.dto;

import com.three.gyangriha.enums.IdProofType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {
    private String name;
    private String address;
    private String mobileNo;
    private String email;
    private String password;
    private String idProof;
    private IdProofType idProofType;
}
