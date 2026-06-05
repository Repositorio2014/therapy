package br.com.therapy.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    private String username;
    private String password;
}



