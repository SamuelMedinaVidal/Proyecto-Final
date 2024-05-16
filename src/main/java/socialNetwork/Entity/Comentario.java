package socialNetwork.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id_comentario;
    private String comentario;
    private int id_uuario;
    private int id_publicacion;

    public Comentario() {}

    public Comentario(String comentario, int id_uuario, int id_publicacion) {
        this.comentario = comentario;
        this.id_uuario = id_uuario;
        this.id_publicacion = id_publicacion;
    }

    public Long getId_comentario() {
        return id_comentario;
    }

    public void setId_comentario(Long id_comentario) {
        this.id_comentario = id_comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getId_uuario() {
        return id_uuario;
    }

    public void setId_uuario(int id_uuario) {
        this.id_uuario = id_uuario;
    }

    public int getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(int id_publicacion) {
        this.id_publicacion = id_publicacion;
    }
}
