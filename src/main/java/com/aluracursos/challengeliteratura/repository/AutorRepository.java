package com.aluracursos.challengeliteratura.repository;

import com.aluracursos.challengeliteratura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findAll();


   // List<Autor> findByFechaDeNacimientoLessThanOrFechaDeFallecimientoGreaterThanEqual(Integer nacimiento, Integer fallecimiento);


    Optional<Autor> findFirstByNombreContainsIgnoreCase(String autor);

//    List<Autor> findByFechaDeNacimientoLessThanEqualAndFechaDeFallecimientoGreaterThanEqual(
//            Integer nacimiento, Integer fallecimiento);

}
