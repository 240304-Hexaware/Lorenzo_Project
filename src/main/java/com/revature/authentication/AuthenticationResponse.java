package com.revature.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private List<String> errors;
    public AuthenticationResponse(String token, String error) {
        this.token = token;
        this.errors = new ArrayList<>();
        this.errors.add(error);
    }

}
