package projeto.entities;

import java.util.UUID;
import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_atividades")
public class Atividade {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "atividades_id")
  private UUID atividadeId;

  private String nome;

  private String descricao;

  @ManyToOne
  @JoinColumn(name = "projeto_id", nullable = false)
  private Projeto projeto;

  @CreationTimestamp
  private Instant dataCriacao;

  public UUID getAtividadeId() {
    return atividadeId;
  }

  public void setAtividadeId(UUID atividadeId) {
    this.atividadeId = atividadeId;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Projeto getProjeto() {
    return projeto;
  }

  public void setProjeto(Projeto projeto) {
    this.projeto = projeto;
  }

  public Instant getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(Instant dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

}
