package com.software.kefa.service;

import com.software.kefa.service.modelosto.OtrosTO;

public interface IOtrosService {
    public void guardarComentario (OtrosTO comentario, String nickname);
    public void guardarDevolucion (OtrosTO devolucion, String nickname);
}
