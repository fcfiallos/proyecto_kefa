package com.software.kefa.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.IPreguntaFrecuenteRepository;
import com.software.kefa.repository.IUsuarioRepository;
import com.software.kefa.repository.modelo.PreguntaFrecuente;
import com.software.kefa.repository.modelo.Usuario;

import jakarta.transaction.Transactional;

/**
 * This class implements the IPreguntaFrecuenteService interface and provides
 * the implementation for the service methods related to PreguntaFrecuente.
 */
@Service
public class PreguntaFrecuentreServiceImpl implements IPreguntaFrecuenteService {

    @Autowired
    private IPreguntaFrecuenteRepository preguntaFrecuenteRepository;

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    /**
     * Saves a PreguntaFrecuente object along with the associated Usuario.
     * 
     * @param preguntaFrecuente The PreguntaFrecuente object to be saved.
     * @param nickname         The nickname of the associated Usuario.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(PreguntaFrecuente preguntaFrecuente, String nickname) {
        Usuario usuario = this.iUsuarioRepository.seleccionarPorNickname(nickname);
        preguntaFrecuente.setCategoria(preguntaFrecuente.getCategoria());
        preguntaFrecuente.setPregunta(preguntaFrecuente.getPregunta());
        preguntaFrecuente.setRespuesta(preguntaFrecuente.getRespuesta());
        preguntaFrecuente.setFecha_creacion(LocalDateTime.now());
        preguntaFrecuente.setUsuario(usuario);
        this.preguntaFrecuenteRepository.insertar(preguntaFrecuente);
    }

    /**
     * Updates a PreguntaFrecuente object.
     * 
     * @param preguntaFrecuente The PreguntaFrecuente object to be updated.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void actualizar(PreguntaFrecuente preguntaFrecuente) {
        this.preguntaFrecuenteRepository.actualizar(preguntaFrecuente);
    }

    /**
     * Retrieves all PreguntaFrecuente objects.
     * 
     * @return A list of all PreguntaFrecuente objects.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public List<PreguntaFrecuente> buscarTodo() {
        return this.preguntaFrecuenteRepository.seleccionarTodo();
    }

    /**
     * Retrieves a PreguntaFrecuente object by its ID.
     * 
     * @param id The ID of the PreguntaFrecuente object to retrieve.
     * @return The PreguntaFrecuente object with the specified ID, or null if not found.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public PreguntaFrecuente buscarPorId(Integer id) {
        return this.preguntaFrecuenteRepository.seleccionarPorId(id);
    }

}
