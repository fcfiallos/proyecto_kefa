package com.software.kefa.repository.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1)
    @Column(name = "usua_id")
    private Integer id;

    @Column(name = "usua_nickname", unique = true)
    private String nickname;

    @Column(name = "usua_nombre")
    private String nombre;

    @Column(name = "usua_apellido")
    private String apellido;

    @Column(name = "usua_cedula", unique = true)
    private String cedula;

    @Column(name = "usua_genero")
    private String genero;

    @Column(name = "usua_telefono")
    private String telefono;

    @Column(name = "usua_correo_electronico", unique = true)
    private String correoElectronico;

    @Column(name = "usua_contrasenia")
    private String constrasenia;

    @Column(name = "usua_pregunta_uno")
    private String preguntaUno;

    @Column(name = "usua_pregunta_dos")
    private String preguntaDos;

    @Column(name = "usua_pregunta_tres")
    private String preguntaTres;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "rol_id")
    private Rol rol;

    @OneToOne (mappedBy = "usuario", cascade = CascadeType.ALL)
    private Ubicacion ubicacion;

    @OneToMany (mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Comentario> comentarios;
    
    @OneToMany (mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Devolucion> devoluciones;

    @OneToMany (mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<PreguntaFrecuente> preguntasFrecuentes;

    @OneToMany (mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Orden> ordenes;

    @OneToOne (mappedBy = "usuario", cascade = CascadeType.ALL)
    private CarritoCompra carritoCompra;

    //Setter and getter
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getConstrasenia() {
        return constrasenia;
    }

    public void setConstrasenia(String constrasenia) {
        this.constrasenia = constrasenia;
    }

    public String getPreguntaUno() {
        return preguntaUno;
    }

    public void setPreguntaUno(String preguntaUno) {
        this.preguntaUno = preguntaUno;
    }

    public String getPreguntaDos() {
        return preguntaDos;
    }

    public void setPreguntaDos(String preguntaDos) {
        this.preguntaDos = preguntaDos;
    }

    public String getPreguntaTres() {
        return preguntaTres;
    }

    public void setPreguntaTres(String preguntaTres) {
        this.preguntaTres = preguntaTres;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Devolucion> getDevoluciones() {
        return devoluciones;
    }

    public void setDevoluciones(List<Devolucion> devoluciones) {
        this.devoluciones = devoluciones;
    }

    public List<PreguntaFrecuente> getPreguntasFrecuentes() {
        return preguntasFrecuentes;
    }

    public void setPreguntasFrecuentes(List<PreguntaFrecuente> preguntasFrecuentes) {
        this.preguntasFrecuentes = preguntasFrecuentes;
    }

    public List<Orden> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<Orden> ordenes) {
        this.ordenes = ordenes;
    }
    
}
