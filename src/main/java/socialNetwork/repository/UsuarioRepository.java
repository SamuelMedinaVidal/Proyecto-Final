package socialNetwork.repository;

import org.springframework.data.repository.CrudRepository;
import socialNetwork.Entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Usuario findByNombreAndContrasena(String nombre, String contrasena);
    Usuario findByUsernameAndContrasena(String username, String contrasena);
}
