package com.software.kefa.service.modelosto;

import jakarta.persistence.Column;

public class UsuarioRegistroTO {
    private String nickname;
    private String nombre;
    private String apellido;
    private String cedula;
    private String genero;
    private String telefono;
    private String correoElectronico;
    private String constrasenia;
    private String constraseniaRepetir;
    private String preguntaUno;
    private String preguntaDos;
    private String preguntaTres;
    private String rol;
    private String codigoPostal;
    private String ciudad;
    private String provincia;
    private String direccion;

    public UsuarioRegistroTO (){
        
    }

    public UsuarioRegistroTO(String nickname, String nombre, String apellido, String cedula, String genero,
            String telefono, String correoElectronico, String constrasenia, String constraseniaRepetir,
            String preguntaUno, String preguntaDos, String preguntaTres, String rol, String codigoPostal, String ciudad,
            String provincia, String direccion) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.genero = genero;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.constrasenia = constrasenia;
        this.constraseniaRepetir = constraseniaRepetir;
        this.preguntaUno = preguntaUno;
        this.preguntaDos = preguntaDos;
        this.preguntaTres = preguntaTres;
        this.rol = rol;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.direccion = direccion;
    }



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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
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

    public String getConstraseniaRepetir() {
        return constraseniaRepetir;
    }

    public void setConstraseniaRepetir(String constraseniaRepetir) {
        this.constraseniaRepetir = constraseniaRepetir;
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
