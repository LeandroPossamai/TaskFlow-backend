package projeto.backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import jakarta.transaction.Transactional;
import projeto.backend.controller.dto.CreateProjetoDto;
import projeto.backend.repository.ProjetoRepository;
import projeto.backend.repository.UserRepository; // Importe o UsuarioRepository
import projeto.backend.entities.Projeto;
import projeto.backend.entities.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

  private final ProjetoRepository projetoRepository;
  private final UserRepository usuarioRepository; // Adicione este campo

  // Injete o UsuarioRepository no construtor
  public ProjetoController(ProjetoRepository projetoRepository, UserRepository usuarioRepository) {
    this.projetoRepository = projetoRepository;
    this.usuarioRepository = usuarioRepository;
  }

  @Transactional
  @PostMapping
  public ResponseEntity<Void> criarProjeto(@RequestBody CreateProjetoDto dto) {

    var nameFromProject = projetoRepository.findByNome(dto.nome());
    if (nameFromProject.isPresent()) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // Buscar o usuário pelo ID
    var usuario = usuarioRepository.findById(dto.usuarioId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado"));

    var projeto = new Projeto();
    projeto.setNome(dto.nome());
    projeto.setDescricao(dto.descricao());
    projeto.setDataCriacao(dto.dataCriacao());
    projeto.setDataFim(dto.dataFim());
    projeto.setStatus(dto.status());
    projeto.setUsuario(usuario); // Associar o usuário ao projeto
    projeto.setAtividades(null);

    projetoRepository.save(projeto);

    return ResponseEntity.ok().build();
  }
}