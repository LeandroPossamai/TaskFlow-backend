package projeto.backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.transaction.Transactional;
import java.util.Set;
import java.util.Optional;

import projeto.backend.entities.Role;
import projeto.backend.entities.Usuario; // Correta importação da entidade User
import projeto.backend.repository.RoleRepository;
import projeto.backend.repository.UserRepository;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

  private final RoleRepository roleRepository;
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public AdminUserConfig(RoleRepository roleRepository,
      UserRepository userRepository,
      BCryptPasswordEncoder passwordEncoder) {
    this.roleRepository = roleRepository;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    Role roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());

    Optional<Usuario> userAdmin = userRepository.findByUsername("admin");

    if (userAdmin.isPresent()) {
      System.out.println("Usuário admin já existe");
    } else {
      Usuario user = new Usuario();
      user.setUsername("admin");
      user.setSenha(passwordEncoder.encode("123"));
      user.setPerfil(Set.of(roleAdmin));

      userRepository.save(user);
      System.out.println("Usuário admin criado com sucesso!");
    }
  }
}
