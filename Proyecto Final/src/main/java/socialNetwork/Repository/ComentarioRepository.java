package socialNetwork.repository;

import org.springframework.data.repository.CrudRepository;
import socialNetwork.Entity.Comentario;

import java.util.List;

public interface ComentarioRepository extends CrudRepository<Comentario, Long> {
}