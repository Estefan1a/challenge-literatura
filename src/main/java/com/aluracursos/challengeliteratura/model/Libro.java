package com.aluracursos.challengeliteratura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String titulo;

    // si lo guardas como texto
    private String idiomas;

    private Double numeroDeDescargas;

    @ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro() {}

    public Libro(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        if (datosLibros.autor() != null && !datosLibros.autor().isEmpty()) {
            this.autor = new Autor(datosLibros.autor().get(0)); // Toma el primer autor de la lista
        } else {
            this.autor = null; // o maneja el caso de que no haya autor
        }
        this.idiomas = idiomaModificado(datosLibros.idiomas());
        this.numeroDeDescargas = datosLibros.numeroDeDescargas();

    }

    @Override
    public String toString() {
        return
                ", \ntitulo: '" + titulo + '\'' +
                        (autor != null ? "\n   Autor: " + autor.getNombre() : "\n   Autor: desconocido") +
                ", \nIdiomas: '" + idiomas + '\'' +
                ", \nnumeroDeDescargas: " + numeroDeDescargas;
    }

    private String idiomaModificado(List<String> idiomas) {
        if (idiomas == null || idiomas.isEmpty()) {
            return "Desconocido";
        }
        return idiomas.get(0);
    }


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

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
