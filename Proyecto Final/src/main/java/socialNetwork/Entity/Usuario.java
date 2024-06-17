package socialNetwork.Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // Indica que esta clase es una entidad JPA
public class Usuario {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) // Indica que el campo es una clave primaria autogenerada
    private Long id_usuario;
    private String nombre;
    private String apellidos;
    private String correo;
    private String fecha_de_nacimiento;
    private String username;
    private String contrasena;
    private String foto_de_perfil;
    private Boolean es_administrador;

    // Relación uno a muchos con la entidad Comentario
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios = new ArrayList<>();

    // Relación uno a muchos con la entidad Publicacion
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Publicacion> publicaciones = new ArrayList<>();

    public Usuario() {}

    public Usuario(String nombre, String apellidos, String correo, String fecha_de_nacimiento, String username, String contrasena, String foto_de_perfil, Boolean es_administrador) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.fecha_de_nacimiento = fecha_de_nacimiento;
        this.username = username;
        this.contrasena = contrasena;
        this.foto_de_perfil = foto_de_perfil;
        this.es_administrador = es_administrador;
    }

    // Getters y setters para los atributos de la clase
    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFecha_de_nacimiento() {
        return fecha_de_nacimiento;
    }

    public void setFecha_de_nacimiento(String fecha_de_nacimiento) {
        this.fecha_de_nacimiento = fecha_de_nacimiento;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getFoto_de_perfil() {
        return foto_de_perfil;
    }

    public void setFoto_de_perfil(String foto_de_perfil) {
        this.foto_de_perfil = foto_de_perfil;
    }

    public Boolean getEs_administrador() {
        return es_administrador;
    }

    public void setEs_administrador(Boolean es_administrador) {
        this.es_administrador = es_administrador;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    @Override
    public String toString() {
        return id_usuario + " - " + nombre + " - " + apellidos + " - " + correo + " - " + fecha_de_nacimiento + " - " + username + " - " + contrasena;
    }
}
