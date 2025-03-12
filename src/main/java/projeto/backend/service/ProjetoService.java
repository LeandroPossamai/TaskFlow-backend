package projeto.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.backend.entities.Projeto;
import projeto.backend.repository.ProjetoRepository;
import java.util.List;
import java.util.UUID;

@Service
public class ProjetoService {

  @Autowired
  private ProjetoRepository projetoRepository;

  // Criar um projeto
  public Projeto criarProjeto(Projeto projeto) {
    return projetoRepository.save(projeto);
  }

  // Listar todos os projetos
  public List<Projeto> listarProjetos() {
    return projetoRepository.findAll();
  }

  // Buscar um projeto por ID
  public Projeto buscarProjetoPorId(UUID id) {
    return projetoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
  }

  // Editar um projeto
  public Projeto editarProjeto(UUID id, Projeto projetoAtualizado) {
    Projeto projeto = projetoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

    projeto.setNome(projetoAtualizado.getNome());
    projeto.setDescricao(projetoAtualizado.getDescricao());
    projeto.setDataCriacao(projetoAtualizado.getDataCriacao());
    projeto.setDataFim(projetoAtualizado.getDataFim());
    projeto.setStatus(projetoAtualizado.getStatus());

    return projetoRepository.save(projeto);
  }

  // Excluir um projeto
  public void excluirProjeto(UUID id) {
    projetoRepository.deleteById(id);
  }
}