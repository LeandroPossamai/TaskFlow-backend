package projeto.backend.controller.dto;

import java.util.List;
import java.util.UUID;

public record CreateProjetoDto(
    String nome,
    String descricao,
    String dataCriacao,
    String dataFim,
    String status,
    UUID usuarioId, // Adicione este campo
    List<AtividadeDto> atividades) {
  public record AtividadeDto(
      String nome,
      String descricao,
      UUID usuarioId) {
  }
}