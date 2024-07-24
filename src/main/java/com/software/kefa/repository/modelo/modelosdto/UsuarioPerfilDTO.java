package com.software.kefa.repository.modelo.modelosdto;

/**
 * Represents a user profile data transfer object (DTO).
 * This class contains the user profile information such as name, email, address, etc.
 */
public class UsuarioPerfilDTO {
    // class fields

    /**
     * The user's first name.
     */
    private String nombre;

    /**
     * The user's last name.
     */
    private String apellido;

    /**
     * The user's identification number.
     */
    private String cedula;

    /**
     * The user's email address.
     */
    private String correoElectronico;

    /**
     * The user's phone number.
     */
    private String telefono;

    /**
     * The user's gender.
     */
    private String genero;

    /**
     * The user's postal code.
     */
    private String codigoPostal;

    /**
     * The user's city.
     */
    private String ciudad;

    /**
     * The user's province.
     */
    private String provincia;

    /**
     * The user's address.
     */
    private String direccion;

    /**
     * The user's nickname.
     */
    private String nickname;

    // constructors

    /**
     * Default constructor for the UsuarioPerfilDTO class.
     */
    public UsuarioPerfilDTO() {

    }

    /**
     * Constructor for the UsuarioPerfilDTO class with all the user profile information.
     * @param nombre The user's first name.
     * @param apellido The user's last name.
     * @param cedula The user's identification number.
     * @param correoElectronico The user's email address.
     * @param telefono The user's phone number.
     * @param genero The user's gender.
     * @param codigoPostal The user's postal code.
     * @param ciudad The user's city.
     * @param provincia The user's province.
     * @param direccion The user's address.
     * @param nickname The user's nickname.
     */
    public UsuarioPerfilDTO(String nombre, String apellido, String cedula, String correoElectronico, String telefono,
            String genero, String codigoPostal, String ciudad, String provincia, String direccion, String nickname) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.genero = genero;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.direccion = direccion;
        this.nickname = nickname;
    }

    // getters and setters

    /**
     * Get the user's first name.
     * @return The user's first name.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the user's first name.
     * @param nombre The user's first name.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Get the user's last name.
     * @return The user's last name.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Set the user's last name.
     * @param apellido The user's last name.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Get the user's identification number.
     * @return The user's identification number.
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Set the user's identification number.
     * @param cedula The user's identification number.
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Get the user's email address.
     * @return The user's email address.
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Set the user's email address.
     * @param correoElectronico The user's email address.
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * Get the user's phone number.
     * @return The user's phone number.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Set the user's phone number.
     * @param telefono The user's phone number.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Get the user's gender.
     * @return The user's gender.
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Set the user's gender.
     * @param genero The user's gender.
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Get the user's postal code.
     * @return The user's postal code.
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Set the user's postal code.
     * @param codigoPostal The user's postal code.
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * Get the user's city.
     * @return The user's city.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Set the user's city.
     * @param ciudad The user's city.
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Get the user's province.
     * @return The user's province.
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Set the user's province.
     * @param provincia The user's province.
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Get the user's address.
     * @return The user's address.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Set the user's address.
     * @param direccion The user's address.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Get the user's nickname.
     * @return The user's nickname.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Set the user's nickname.
     * @param nickname The user's nickname.
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
