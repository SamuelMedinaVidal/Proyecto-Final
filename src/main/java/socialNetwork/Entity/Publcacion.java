package socialNetwork.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Blob;

@Entity
public class Publcacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_publicacion;
    private String titulo;
    private int likes;
    private String imagen_api;
    private String imagen;
    private int id_usuario;

    public Publcacion() {}

    public Publcacion(String titulo, int likes, String imagen_api, String imagen, int id_usuario) {
        this.titulo = titulo;
        this.likes = likes;
        this.imagen_api = imagen_api;
        this.imagen = imagen;
        this.id_usuario = id_usuario;
    }

    public Long getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(Long id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getImagen_api() {
        return imagen_api;
    }

    public void setImagen_api(String imagen_api) {
        this.imagen_api = imagen_api;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}
