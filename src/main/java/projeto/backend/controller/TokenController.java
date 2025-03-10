package projeto.backend.controller;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.RestController;
import projeto.backend.controller.dto.LoginRequest;
import projeto.backend.controller.dto.LoginResponse;
import projeto.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class TokenController {

  private final JwtEncoder jwtEncoder;
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public TokenController(JwtEncoder jwtEncoder, UserRepository userRepository,
      BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.jwtEncoder = jwtEncoder;
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    try {
      System.out.println("Login request: " + loginRequest);
      System.out.println("Username: " + loginRequest.username());
      System.out.println("Password: " + loginRequest.password());

      // Verifica se o loginRequest, username ou password são nulos
      if (loginRequest == null || loginRequest.username() == null || loginRequest.password() == null) {
        return ResponseEntity.badRequest().body("Username e senha são obrigatórios");
      }

      var user = userRepository.findByUsername(loginRequest.username())
          .orElseThrow(() -> new BadCredentialsException("Usuário ou senha inválidos"));

      System.out.println("Usuário encontrado: " + user.getUsername());
      System.out.println("Senha fornecida: " + loginRequest.password());
      System.out.println("Senha no banco: " + user.getSenha());

      if (!bCryptPasswordEncoder.matches(loginRequest.password(), user.getSenha())) {
        System.out.println("Senha incorreta");
        throw new BadCredentialsException("Usuário ou senha inválidos");
      }

      var now = Instant.now();
      var expiresIn = 300L;

      var claims = JwtClaimsSet.builder()
          .issuer("mybackend")
          .subject(user.getUserId().toString())
          .issuedAt(now)
          .expiresAt(now.plusSeconds(expiresIn))
          .build();

      var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

      return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));
    } catch (BadCredentialsException e) {
      System.err.println("Erro durante o login: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
    } catch (Exception e) {
      System.err.println("Erro inesperado durante o login: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
    }
  }
}