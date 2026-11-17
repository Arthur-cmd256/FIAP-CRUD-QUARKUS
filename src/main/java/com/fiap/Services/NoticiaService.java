package com.fiap.Services;

import java.util.List;
import java.util.Optional;

import com.fiap.Models.Noticia;
import com.fiap.Repositories.NoticiaRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped 
public class NoticiaService {
    
    @Inject 
    NoticiaRepository noticiaRepository;

    public List<Noticia> getAllNoticias() {
        return noticiaRepository.listAll();
    }

    public Optional<Noticia> getNoticiaById(Long id) {
        return noticiaRepository.findByIdOptional(id);
    }

    @Transactional
    public Noticia createNoticia(Noticia noticia) {
        noticiaRepository.persist(noticia);
        return noticia;
    }

    @Transactional
    public void updateNoticia(Long id, Noticia noticia) {
        Noticia existingNoticia = noticiaRepository.findById(id);
        if (existingNoticia != null) {
            existingNoticia.setTitulo(noticia.getTitulo());
            existingNoticia.setConteudo(noticia.getConteudo());
            existingNoticia.setDataPublicacao();
            noticiaRepository.persist(existingNoticia);
        }
    }

    @Transactional
    public void deleteNoticia(Long id) {
        Noticia existingNoticia = noticiaRepository.findById(id);
        if (existingNoticia != null) {
            noticiaRepository.delete(existingNoticia);
        }
    }
}