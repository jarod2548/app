package org.app.Account.api;

import jakarta.validation.Valid;
import org.app.Account.domain.User;
import org.app.Account.service.AuthorizationService;
import org.app.Common.CookieService;
import org.app.config.JWTService;
import org.app.config.UserPrincipal;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizationController {
    private final AuthorizationService authorizationService;
    private final JWTService jwtService;
    private final CookieService cookieService;

    public AuthorizationController(AuthorizationService AuthService,
                                   JWTService JWTService,
                                   CookieService CookieService){
        authorizationService = AuthService;
        jwtService = JWTService;
        this.cookieService = CookieService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> Login(@RequestBody @Valid LoginDTO dto) {
        User response = authorizationService.Login(dto.naarUser());
        String token = jwtService.generateToken(response);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,
                        cookieService.createJwtCookie(token)
                                .toString())
                .body(new LoginResponseDTO(response));
    }
    @GetMapping("/me")
    public ResponseEntity<LoginResponseDTO> authorize(@AuthenticationPrincipal UserPrincipal user){
        if(user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        LoginResponseDTO response = new LoginResponseDTO(user);
        return ResponseEntity.ok(response);
    }
}
