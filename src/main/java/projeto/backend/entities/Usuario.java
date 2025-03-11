package projeto.backend.entities;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import projeto.backend.controller.dto.LoginRequest;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "user_id")
  private UUID userId;

  @Column(unique = true)
  private String username;

  private String email;

  private String senha;

  @Column(name = "data_criacao")
  private LocalDateTime dataCriacao;

  @Column(name = "ultimo_login")
  private LocalDateTime ultimoLogin;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "tb_users_perfil", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> perfil;

  public Usuario() {
    // Construtor padrão necessário para o JPA
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public LocalDateTime getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(LocalDateTime dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public LocalDateTime getUltimoLogin() {
    return ultimoLogin;
  }

  public void setUltimoLogin(LocalDateTime ultimoLogin) {
    this.ultimoLogin = ultimoLogin;
  }

  public Set<Role> getPerfil() {
    return perfil;
  }

  public void setPerfil(Set<Role> perfil) {
    this.perfil = perfil;
  }

  public boolean isLoginCorrect(LoginRequest loginRequest, PasswordEncoder passwordEncoder) {
    return passwordEncoder.matches(loginRequest.password(), this.senha);
  }

  public void setRoles(Set<Role> of) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setRoles'");
  }
}