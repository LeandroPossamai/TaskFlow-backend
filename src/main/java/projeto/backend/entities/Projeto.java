package projeto.backend.entities;

import java.util.UUID;
import java.time.Instant;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_projetos")
public class Projeto {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "projeto_id")
  private UUID projetoId;

  @Column(nullable = false)
  private String nome;

  private String descricao;

  @CreationTimestamp
  private Instant dataCriacao;

  @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Atividade> atividades;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private Usuario responsavel;

  public UUID getProjetoId() {
    return projetoId;
  }

  public void setProjetoId(UUID projetoId) {
    this.projetoId = projetoId;
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

  public Instant getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(Instant dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public Set<Atividade> getAtividades() {
    return atividades;
  }

  public void setAtividades(Set<Atividade> atividades) {
    this.atividades = atividades;
  }

}
