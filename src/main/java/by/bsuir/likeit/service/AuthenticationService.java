package by.bsuir.likeit.service;

import by.bsuir.likeit.dto.request.AuthenticationRequest;
import by.bsuir.likeit.dto.request.RegistrationRequest;

public interface AuthenticationService {
    void authenticate(AuthenticationRequest authenticationRequest);
    void register(RegistrationRequest registrationRequest);
}
