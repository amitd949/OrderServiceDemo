package com.example.demo.Auth;

import com.example.demo.configutils.OrderConstants;
import org.aspectj.weaver.ast.Var;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtEncoder jwtEncoder;

    public AuthController(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        System.out.println("Inside Login "+ request);
        // Demo only

      String  pwdCheck =   OrderConstants.loginUserConfigs.getOrDefault(request.getUserName(),"");

        User user = OrderConstants.loginUserConfigsNew.get(request.getUserName());

        if (user== null || user.getPassword().isEmpty() ||  !user.getPassword().equals(request.getPassword())) {

            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid credentials"
            );
        }

        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("my-order-service")
                .subject(request.getUserName())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(3600))
                .claim("roles", List.of(user.getRoleType()))
                .build();

     /*   String token = jwtEncoder.encode(
                JwtEncoderParameters.from(claims)
        ).getTokenValue();*/
        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256)
                .build();

        String token = jwtEncoder.encode(
                JwtEncoderParameters.from(header, claims)
        ).getTokenValue();


        return new LoginResponse(token);
    }
}
