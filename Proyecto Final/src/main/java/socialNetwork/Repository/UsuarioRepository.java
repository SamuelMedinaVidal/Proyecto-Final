package socialNetwork.repository;

import org.springframework.data.repository.CrudRepository;
import socialNetwork.Entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Usuario findByUsername(String username);
    Usuario findByNombreAndContrasena(String nombre, String contrasena);
    Usuario findByUsernameAndContrasena(String username, String contrasena);
}