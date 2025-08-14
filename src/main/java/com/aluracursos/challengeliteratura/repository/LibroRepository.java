package com.aluracursos.challengeliteratura.repository;

import com.aluracursos.challengeliteratura.model.Autor;
import com.aluracursos.challengeliteratura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {


    boolean existsByTitulo(String titulo);

    Libro findByTituloContainsIgnoreCase(String titulo);

//    @Query("SELECT l FROM Libro l LEFT JOIN FETCH l.autor")
//    List<Libro> findAllConAutor();

    List<Libro> findByIdiomas(String idiomas);



//    @Query("SELECT l FROM Libro l ORDER BY l.cantidadDescargas DESC LIMIT 10")
//    List<Libro> findTop10ByTituloByCantidadDescargas();

//    Optional<Libro> findByTituloIgnoreCase(String titulo);
//    //Optional<Libro> findByIdiomasIgnoreCase(String idioma);
//    List<Libro> findAll();
//    boolean existsByTituloIgnoreCase(String titulo);
    //Optional<Autor> findByNombreIgnoreCase(String titulo);
    //List<Autor> findByFechaDeNacimientoLessThanEqualAndFechaDeFallecimientoGreaterThanEqual(String inicio, String fin);

}
