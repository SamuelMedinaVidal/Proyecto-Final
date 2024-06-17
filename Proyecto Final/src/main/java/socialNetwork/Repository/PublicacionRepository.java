package socialNetwork.repository;

import org.springframework.data.repository.CrudRepository;
import socialNetwork.Entity.Publicacion;
import socialNetwork.Entity.Usuario;

import java.util.List;

public interface PublicacionRepository extends CrudRepository<Publicacion, Long> {
    List<Publicacion> findAll();
    List<Publicacion> findByUsuario(Usuario usuario);
}
