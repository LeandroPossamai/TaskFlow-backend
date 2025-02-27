package projeto.backend.entities;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;

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

  private Date data_criacao;

  private Date ultimo_login;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "tb_users_perfil", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> perfil;

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

  public Date getData_criacao() {
    return data_criacao;
  }

  public void setData_criacao(Date data_criacao) {
    this.data_criacao = data_criacao;
  }

  public Date getUltimo_login() {
    return ultimo_login;
  }

  public void setUltimo_login(Date ultimo_login) {
    this.ultimo_login = ultimo_login;
  }

  public Set<Role> getPerfil() {
    return perfil;
  }

  public void setPerfil(Set<Role> perfil) {
    this.perfil = perfil;
  }

}
