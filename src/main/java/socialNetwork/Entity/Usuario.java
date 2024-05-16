package socialNetwork.Entity;


import jakarta.persistence.*;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id_usuario;
    private String nombre;
    private String apellidos;
    private String correo;
    private Date fecha_de_nacimiento;
    private String nombre_usuario;
    private String contrasena;
    private String foto_de_perfil;
    private boolean es_administrador;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Publicacion> publicaciones;

    public Usuario() {}

    public Usuario(String nombre, String apellidos, String correo, Date fecha_de_nacimiento, String nombre_usuario, String contrasena, String foto_de_perfil, boolean es_administrador) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.fecha_de_nacimiento = fecha_de_nacimiento;
        this.nombre_usuario = nombre_usuario;
        this.contrasena = contrasena;
        this.foto_de_perfil = foto_de_perfil;
        this.es_administrador = es_administrador;;
        this.comentarios = new ArrayList<>();
        this.publicaciones = new ArrayList<>();
    }

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

    public Date getFecha_de_nacimiento() {
        return fecha_de_nacimiento;
    }

    public void setFecha_de_nacimiento(Date fecha_de_nacimiento) {
        this.fecha_de_nacimiento = fecha_de_nacimiento;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
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

    public boolean isEs_administrador() {
        return es_administrador;
    }

    public void setEs_administrador(boolean es_administrador) {
        this.es_administrador = es_administrador;
    }

    @Override
    public String toString() {
        return id_usuario + " - " + nombre + " - " + apellidos + " - " + correo + " - " + fecha_de_nacimiento + " - " + nombre_usuario + " - " + contrasena + " - ";
    }
}
