package com.software.kefa.repository.modelo.modelosdto;

public class UsuarioPerfilDTO {
    private String nombre;
    private String apellido;
    private String cedula;
    private String correoElectronico;
    private String telefono;
    private String genero;
    private String rol;
    private String codigoPostal;
    private String ciudad;
    private String provincia;
    private String direccion;

    public UsuarioPerfilDTO() {

    }

    public UsuarioPerfilDTO(String nombre, String apellido, String cedula, String correoElectronico, String telefono,
            String genero, String rol, String codigoPostal, String ciudad, String provincia, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.genero = genero;
        this.rol = rol;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.direccion = direccion;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    

}
