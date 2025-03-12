package projeto.backend.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

  @Column(nullable = false)
  private String dataCriacao;

  private String dataFim;

  @Column(nullable = false)
  private String status;

  @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Atividade> atividades;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false) // Adicione esta linha
  private Usuario usuario; // Relacionamento com a entidade Usuario

  // Getters e setters
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

  public String getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(String dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public String getDataFim() {
    return dataFim;
  }

  public void setDataFim(String dataFim) {
    this.dataFim = dataFim;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<Atividade> getAtividades() {
    return atividades;
  }

  public void setAtividades(List<Atividade> atividades) {
    this.atividades = atividades;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
}