package com.software.kefa.service;

import com.software.kefa.service.modelosto.OtrosTO;

public interface IOtrosService {
    public void guardarComentario (OtrosTO otrosTO, String nickname);
    public void guardarDevolucion (OtrosTO otrosTO, String nickname);
}
