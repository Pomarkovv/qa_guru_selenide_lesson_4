package api.models;

import lombok.Data;

@Data
public class RegistrationRequest {
    String email;
    String password;
}
