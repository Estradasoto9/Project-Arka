package com.AdrianAlejandroEstradaLibreria.Libreria.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class LibroDTO {

    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    private Long autorId;

    private AutorDTO autor;
    private EditorialDTO editorial;
    private List<GeneroDTO> generos;

    public LibroDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public AutorDTO getAutor() {
        return autor;
    }

    public void setAutor(AutorDTO autor) {
        this.autor = autor;
    }

    public EditorialDTO getEditorial() {
        return editorial;
    }

    public void setEditorial(EditorialDTO editorial) {
        this.editorial = editorial;
    }

    public List<GeneroDTO> getGeneros() {
        return generos;
    }

    public void setGeneros(List<GeneroDTO> generos) {
        this.generos = generos;
    }

    public LibroDTO(String titulo, String descripcion, Long autorId, EditorialDTO editorial, List<GeneroDTO> generos) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.autorId = autorId;
        this.editorial = editorial;
        this.generos = generos;
    }

}
