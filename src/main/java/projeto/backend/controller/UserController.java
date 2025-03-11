package projeto.backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;
import projeto.backend.controller.dto.CreateUserDto;
import projeto.backend.repository.RoleRepository;
import projeto.backend.repository.UserRepository;

import projeto.backend.entities.Role;
import projeto.backend.entities.Usuario;

import java.util.Set;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UserController {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  private final PasswordEncoder passwordEncoder;

  public UserController(UserRepository userRepository,
      RoleRepository roleRepository,
      PasswordEncoder passwordEncoder,
      BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.passwordEncoder = passwordEncoder;
    this.roleRepository = roleRepository;
    this.userRepository = userRepository;
  }

  @Transactional
  @PostMapping("/users")
  public ResponseEntity<Void> newUser(@RequestBody CreateUserDto dto) {

    Role basicRole = roleRepository.findByName(Role.Values.ADMIN.name());

    Optional<Usuario> userFromDb = userRepository.findByUsername(dto.username());

    if (userFromDb.isPresent()) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    Usuario user = new Usuario();
    user.setUsername(dto.username());
    user.setSenha(passwordEncoder.encode(dto.password()));
    user.setEmail(dto.email());
    user.setDataCriacao(LocalDateTime.now());
    user.setPerfil(Set.of(basicRole));

    userRepository.save(user);

    return ResponseEntity.ok().build();
  }

  @GetMapping("/users")
  @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
  public ResponseEntity<List<Usuario>> listUsers() {
    List<Usuario> users = userRepository.findAll();
    return ResponseEntity.ok(users);
  }

}