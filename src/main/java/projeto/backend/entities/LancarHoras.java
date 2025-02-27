package projeto.backend.entities;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_horas")
public class LancarHoras {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Se estiver usando MySQL
  @Column(name = "horas_id")
  private Long horasId;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false) // Define a chave estrangeira corretamente
  private Usuario usuario;

  private String conteudo;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private Instant dataCriacao;

  public Long getHorasId() {
    return horasId;
  }

  public void setHorasId(Long horasId) {
    this.horasId = horasId;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public String getConteudo() {
    return conteudo;
  }

  public void setConteudo(String conteudo) {
    this.conteudo = conteudo;
  }

  public Instant getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(Instant dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

}
