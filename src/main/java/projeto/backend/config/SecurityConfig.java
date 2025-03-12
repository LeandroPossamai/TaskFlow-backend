package projeto.backend.config;

import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.converter.RsaKeyConverters;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

  @Bean
  public RSAPublicKey publicKey() throws IOException {
    // Caminho correto: "app.pub" (relativo ao classpath)
    ClassPathResource resource = new ClassPathResource("app.pub");
    return RsaKeyConverters.x509().convert(resource.getInputStream());
  }

  @Bean
  public RSAPrivateKey privateKey() throws IOException {
    // Caminho correto: "app.key" (relativo ao classpath)
    ClassPathResource resource = new ClassPathResource("app.key");
    return RsaKeyConverters.pkcs8().convert(resource.getInputStream());
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    System.out.println("Configurando SecurityFilterChain...");
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> {
          System.out.println("Configurando autorizações...");
          auth.requestMatchers("/login").permitAll();
          auth.requestMatchers("/users").permitAll();
          auth.requestMatchers("/projetos").permitAll();
          auth.anyRequest().authenticated();
        });

    return http.build();
  }

  @Bean
  public JwtDecoder jwtDecoder(RSAPublicKey publicKey) {
    return NimbusJwtDecoder.withPublicKey(publicKey).build();
  }

  @Bean
  public JwtEncoder jwtEncoder(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
    JWK jwk = new RSAKey.Builder(publicKey).privateKey(privateKey).build();
    var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
    return new NimbusJwtEncoder(jwks);
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}