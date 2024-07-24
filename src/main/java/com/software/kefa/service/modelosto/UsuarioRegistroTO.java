package com.software.kefa.service.modelosto;

/**
 * The UsuarioRegistroTO class represents a user registration transfer object.
 * It contains various properties related to user registration information.
 */
public class UsuarioRegistroTO {
    // Properties
    private String nickname;
    private String nombreYApellido;
    private String cedula;
    private String genero;
    private String telefono;
    private String correoElectronico;
    private String constrasenia;
    private String constraseniaRepetir;
    private String preguntaUno;
    private String preguntaDos;
    private String preguntaTres;
    private String codigoPostal;
    private String ciudad;
    private String provincia;
    private String direccion;

    /**
     * Default constructor for the UsuarioRegistroTO class.
     */
    public UsuarioRegistroTO (){
        
    }

    /**
     * Parameterized constructor for the UsuarioRegistroTO class.
     * @param nickname The nickname of the user.
     * @param nombreyApellido The name and surname of the user.
     * @param cedula The identification number of the user.
     * @param genero The gender of the user.
     * @param telefono The phone number of the user.
     * @param correoElectronico The email address of the user.
     * @param constrasenia The password of the user.
     * @param constraseniaRepetir The repeated password of the user.
     * @param preguntaUno The first security question of the user.
     * @param preguntaDos The second security question of the user.
     * @param preguntaTres The third security question of the user.
     * @param codigoPostal The postal code of the user.
     * @param ciudad The city of the user.
     * @param provincia The province of the user.
     * @param direccion The address of the user.
     */
    public UsuarioRegistroTO(String nickname, String nombreyApellido, String cedula, String genero,
            String telefono, String correoElectronico, String constrasenia, String constraseniaRepetir,
            String preguntaUno, String preguntaDos, String preguntaTres, String codigoPostal, String ciudad,
            String provincia, String direccion) {
        this.nickname = nickname;
        this.nombreYApellido = nombreyApellido;
        this.cedula = cedula;
        this.genero = genero;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.constrasenia = constrasenia;
        this.constraseniaRepetir = constraseniaRepetir;
        this.preguntaUno = preguntaUno;
        this.preguntaDos = preguntaDos;
        this.preguntaTres = preguntaTres;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.direccion = direccion;
    }

    // Getters and Setters

    /**
     * Get the nickname of the user.
     * @return The nickname of the user.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Set the nickname of the user.
     * @param nickname The nickname of the user.
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Get the name and surname of the user.
     * @return The name and surname of the user.
     */
    public String getNombreYApellido() {
        return nombreYApellido;
    }

    /**
     * Set the name and surname of the user.
     * @param nombre The name and surname of the user.
     */
    public void setNombreYApellido(String nombre) {
        this.nombreYApellido = nombre;
    }

    /**
     * Get the identification number of the user.
     * @return The identification number of the user.
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Set the identification number of the user.
     * @param cedula The identification number of the user.
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Get the gender of the user.
     * @return The gender of the user.
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Set the gender of the user.
     * @param genero The gender of the user.
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Get the phone number of the user.
     * @return The phone number of the user.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Set the phone number of the user.
     * @param telefono The phone number of the user.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Get the email address of the user.
     * @return The email address of the user.
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Set the email address of the user.
     * @param correoElectronico The email address of the user.
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * Get the password of the user.
     * @return The password of the user.
     */
    public String getConstrasenia() {
        return constrasenia;
    }

    /**
     * Set the password of the user.
     * @param constrasenia The password of the user.
     */
    public void setConstrasenia(String constrasenia) {
        this.constrasenia = constrasenia;
    }

    /**
     * Get the first security question of the user.
     * @return The first security question of the user.
     */
    public String getPreguntaUno() {
        return preguntaUno;
    }

    /**
     * Set the first security question of the user.
     * @param preguntaUno The first security question of the user.
     */
    public void setPreguntaUno(String preguntaUno) {
        this.preguntaUno = preguntaUno;
    }

    /**
     * Get the second security question of the user.
     * @return The second security question of the user.
     */
    public String getPreguntaDos() {
        return preguntaDos;
    }

    /**
     * Set the second security question of the user.
     * @param preguntaDos The second security question of the user.
     */
    public void setPreguntaDos(String preguntaDos) {
        this.preguntaDos = preguntaDos;
    }

    /**
     * Get the third security question of the user.
     * @return The third security question of the user.
     */
    public String getPreguntaTres() {
        return preguntaTres;
    }

    /**
     * Set the third security question of the user.
     * @param preguntaTres The third security question of the user.
     */
    public void setPreguntaTres(String preguntaTres) {
        this.preguntaTres = preguntaTres;
    }

    /**
     * Get the repeated password of the user.
     * @return The repeated password of the user.
     */
    public String getConstraseniaRepetir() {
        return constraseniaRepetir;
    }

    /**
     * Set the repeated password of the user.
     * @param constraseniaRepetir The repeated password of the user.
     */
    public void setConstraseniaRepetir(String constraseniaRepetir) {
        this.constraseniaRepetir = constraseniaRepetir;
    }

    /**
     * Get the postal code of the user.
     * @return The postal code of the user.
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Set the postal code of the user.
     * @param codigoPostal The postal code of the user.
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * Get the city of the user.
     * @return The city of the user.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Set the city of the user.
     * @param ciudad The city of the user.
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Get the province of the user.
     * @return The province of the user.
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Set the province of the user.
     * @param provincia The province of the user.
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Get the address of the user.
     * @return The address of the user.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Set the address of the user.
     * @param direccion The address of the user.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
