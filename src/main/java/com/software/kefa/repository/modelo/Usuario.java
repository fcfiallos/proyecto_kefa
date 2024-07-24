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

/**
 * Represents a user in the system.
 * This class contains information about the user, such as their nickname, name, email, password, etc.
 * It also includes relationships with other entities, such as roles, locations, comments, orders, etc.
 */
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

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Ubicacion ubicacion;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Devoluciones> devoluciones;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<PreguntaFrecuente> preguntasFrecuentes;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Orden> ordenes;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private CarritoCompra carritoCompra;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<RegistroSesion> registrosSesion;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private ListaDeseos listaDeseos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Pago> pagos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Factura> facturas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Notificacion> notificaciones;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Envio> envios;

    // Setter and getter methods

    /**
     * Retrieves the nickname of the user.
     *
     * @return the nickname of the user
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Sets the nickname of the user.
     *
     * @param nickname the new nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Returns the nombre of the Usuario.
     *
     * @return the nombre of the Usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the nombre of the Usuario.
     *
     * @param nombre the new nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Returns the apellido of the Usuario.
     *
     * @return the apellido of the Usuario
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Sets the apellido of the Usuario.
     *
     * @param apellido the new apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Returns the cedula of the Usuario.
     *
     * @return the cedula of the Usuario
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Sets the cedula of the Usuario.
     *
     * @param cedula the new cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Returns the genero of the Usuario.
     *
     * @return the genero of the Usuario
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Sets the genero of the Usuario.
     *
     * @param genero the new genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Returns the telefono of the Usuario.
     *
     * @return the telefono of the Usuario
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Sets the telefono of the Usuario.
     *
     * @param telefono the new telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Returns the constrasenia of the Usuario.
     *
     * @return the constrasenia of the Usuario
     */
    public String getConstrasenia() {
        return constrasenia;
    }

    /**
     * Sets the constrasenia of the Usuario.
     *
     * @param constrasenia the new constrasenia to set
     */
    public void setConstrasenia(String constrasenia) {
        this.constrasenia = constrasenia;
    }

    /**
     * Returns the preguntaUno of the Usuario.
     *
     * @return the preguntaUno of the Usuario
     */
    public String getPreguntaUno() {
        return preguntaUno;
    }

    /**
     * Sets the preguntaUno of the Usuario.
     *
     * @param preguntaUno the new preguntaUno to set
     */
    public void setPreguntaUno(String preguntaUno) {
        this.preguntaUno = preguntaUno;
    }

    /**
     * Returns the preguntaDos of the Usuario.
     *
     * @return the preguntaDos of the Usuario
     */
    public String getPreguntaDos() {
        return preguntaDos;
    }

    /**
     * Sets the preguntaDos of the Usuario.
     *
     * @param preguntaDos the new preguntaDos to set
     */
    public void setPreguntaDos(String preguntaDos) {
        this.preguntaDos = preguntaDos;
    }

    /**
     * Returns the preguntaTres of the Usuario.
     *
     * @return the preguntaTres of the Usuario
     */
    public String getPreguntaTres() {
        return preguntaTres;
    }

    /**
     * Sets the preguntaTres of the Usuario.
     *
     * @param preguntaTres the new preguntaTres to set
     */
    public void setPreguntaTres(String preguntaTres) {
        this.preguntaTres = preguntaTres;
    }

    /**
     * Returns the id of the Usuario.
     *
     * @return the id of the Usuario
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id of the Usuario.
     *
     * @param id the new id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the correoElectronico of the Usuario.
     *
     * @return the correoElectronico of the Usuario
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Sets the correoElectronico of the Usuario.
     *
     * @param correoElectronico the new correoElectronico to set
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * Returns the rol of the Usuario.
     *
     * @return the rol of the Usuario
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * Sets the rol of the Usuario.
     *
     * @param rol the new rol to set
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     * Returns the ubicacion of the Usuario.
     *
     * @return the ubicacion of the Usuario
     */
    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    /**
     * Sets the ubicacion of the Usuario.
     *
     * @param ubicacion the new ubicacion to set
     */
    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Returns the comentarios of the Usuario.
     *
     * @return the comentarios of the Usuario
     */
    public List<Comentario> getComentarios() {
        return comentarios;
    }

    /**
     * Sets the comentarios of the Usuario.
     *
     * @param comentarios the new comentarios to set
     */
    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * Returns the devoluciones of the Usuario.
     *
     * @return the devoluciones of the Usuario
     */
    public List<Devoluciones> getDevoluciones() {
        return devoluciones;
    }

    /**
     * Sets the devoluciones of the Usuario.
     *
     * @param devoluciones the new devoluciones to set
     */
    public void setDevoluciones(List<Devoluciones> devoluciones) {
        this.devoluciones = devoluciones;
    }

    /**
     * Returns the preguntasFrecuentes of the Usuario.
     *
     * @return the preguntasFrecuentes of the Usuario
     */
    public List<PreguntaFrecuente> getPreguntasFrecuentes() {
        return preguntasFrecuentes;
    }

    /**
     * Sets the preguntasFrecuentes of the Usuario.
     *
     * @param preguntasFrecuentes the new preguntasFrecuentes to set
     */
    public void setPreguntasFrecuentes(List<PreguntaFrecuente> preguntasFrecuentes) {
        this.preguntasFrecuentes = preguntasFrecuentes;
    }

    /**
     * Returns the ordenes of the Usuario.
     *
     * @return the ordenes of the Usuario
     */
    public List<Orden> getOrdenes() {
        return ordenes;
    }

    /**
     * Sets the ordenes of the Usuario.
     *
     * @param ordenes the new ordenes to set
     */
    public void setOrdenes(List<Orden> ordenes) {
        this.ordenes = ordenes;
    }

    /**
     * Returns the carritoCompra of the Usuario.
     *
     * @return the carritoCompra of the Usuario
     */
    public CarritoCompra getCarritoCompra() {
        return carritoCompra;
    }

    /**
     * Sets the carritoCompra of the Usuario.
     *
     * @param carritoCompra the new carritoCompra to set
     */
    public void setCarritoCompra(CarritoCompra carritoCompra) {
        this.carritoCompra = carritoCompra;
    }

    /**
     * Returns the registrosSesion of the Usuario.
     *
     * @return the registrosSesion of the Usuario
     */
    public List<RegistroSesion> getRegistrosSesion() {
        return registrosSesion;
    }

    /**
     * Sets the registrosSesion of the Usuario.
     *
     * @param registrosSesion the new registrosSesion to set
     */
    public void setRegistrosSesion(List<RegistroSesion> registrosSesion) {
        this.registrosSesion = registrosSesion;
    }

    /**
     * Returns the listaDeseos of the Usuario.
     *
     * @return the listaDeseos of the Usuario
     */
    public ListaDeseos getListaDeseos() {
        return listaDeseos;
    }

    /**
     * Sets the listaDeseos of the Usuario.
     *
     * @param listaDeseos the new listaDeseos to set
     */
    public void setListaDeseos(ListaDeseos listaDeseos) {
        this.listaDeseos = listaDeseos;
    }

    /**
     * Returns the pagos of the Usuario.
     *
     * @return the pagos of the Usuario
     */
    public List<Pago> getPagos() {
        return pagos;
    }

    /**
     * Sets the pagos of the Usuario.
     *
     * @param pagos the new pagos to set
     */
    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    /**
     * Returns the facturas of the Usuario.
     *
     * @return the facturas of the Usuario
     */
    public List<Factura> getFacturas() {
        return facturas;
    }

    /**
     * Sets the facturas of the Usuario.
     *
     * @param facturas the new facturas to set
     */
    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    /**
     * Returns the notificaciones of the Usuario.
     *
     * @return the notificaciones of the Usuario
     */
    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    /**
     * Sets the notificaciones of the Usuario.
     *
     * @param notificaciones the new notificaciones to set
     */
    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

    /**
     * Returns the envios of the Usuario.
     *
     * @return the envios of the Usuario
     */
    public List<Envio> getEnvios() {
        return envios;
    }

    /**
     * Sets the envios of the Usuario.
     *
     * @param envios the new envios to set
     */
    public void setEnvios(List<Envio> envios) {
        this.envios = envios;
    }
}
