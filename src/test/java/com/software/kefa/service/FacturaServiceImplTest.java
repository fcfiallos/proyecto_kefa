package com.software.kefa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Factura;
import com.software.kefa.repository.modelo.Orden;

@SpringBootTest
public class FacturaServiceImplTest {
    @Autowired
    private IFacturaService facturaServiceImpl;
    @Autowired
    private ICarritoCompraService carritoCompraService;
    @Autowired
    private IOrdenService ordenService;

    @Test
    void testEnviarFactura() {
        CarritoCompra carritoCompra = this.carritoCompraService.buscarPorId(2);
        String nickname = "fcfch";
        Orden orden = this.ordenService.buscarPorId(2); 
        Factura factura = this.facturaServiceImpl.enviarFactura(carritoCompra, nickname, orden);
        System.out.println(factura);

    }
}
