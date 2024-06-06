package com.software.kefa.repository;

import com.software.kefa.repository.modelo.Proveedor;
 
public interface IProveedorRepository {
    public Proveedor seleccionarPorNombre(String nombre);
    public void insertar(Proveedor proveedor);
    public void actualizar(Proveedor proveedor);
}