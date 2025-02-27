package projeto.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_roles")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id")
  private Long roleId;

  private String name;

  // Getters e Setters
  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // Enum dentro da classe Role, com valores predefinidos
  public enum Values {
    USUARIO(2L, "usuario");

    private final long roleId;
    private final String name;

    // Construtor do enum
    Values(long roleId, String name) {
      this.roleId = roleId;
      this.name = name;
    }

    public long getRoleId() {
      return roleId;
    }

    public String getName() {
      return name;
    }

    // Método para retornar a Role correspondente ao roleId
    public static Role getRoleById(long roleId) {
      for (Values value : Values.values()) {
        if (value.getRoleId() == roleId) {
          Role role = new Role();
          role.setRoleId(value.getRoleId());
          role.setName(value.getName());
          return role;
        }
      }
      return null; // Ou lançar uma exceção, se necessário
    }
  }

}
