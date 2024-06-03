package com.software.kefa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.software.kefa.service.IUsuarioService;

@SpringBootTest
@Rollback (true)
public class UsuarioServiceImplTest {
    @Autowired
    private IUsuarioService iUsuarioService;

    @Test
    void test(){
        this.iUsuarioService.registroEficiente(null);
    }
}
