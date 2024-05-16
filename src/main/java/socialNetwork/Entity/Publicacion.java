package socialNetwork.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_publicacion;
    private String titulo;
    private int likes;
    private String imagen_api;
    private String imagen;


    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios;

    public Publicacion() {}

    public Publicacion(String titulo, int likes, String imagen_api, String imagen) {
        this.titulo = titulo;
        this.likes = likes;
        this.imagen_api = imagen_api;
        this.imagen = imagen;
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
}
