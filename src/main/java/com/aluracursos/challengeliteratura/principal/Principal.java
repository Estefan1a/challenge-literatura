package com.aluracursos.challengeliteratura.principal;

import com.aluracursos.challengeliteratura.model.*;
import com.aluracursos.challengeliteratura.repository.AutorRepository;
import com.aluracursos.challengeliteratura.repository.LibroRepository;
import com.aluracursos.challengeliteratura.service.ConsumoAPI;
import com.aluracursos.challengeliteratura.service.ConvierteDatos;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();
    private final Scanner teclado = new Scanner(System.in);
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private Optional<Libro> libroBuscado;

    public Principal(LibroRepository libroRepository,AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;

    }


    public void muestraElMenu(){

        var opcion = -1;

        while(opcion != -0){
            System.out.println("\nIntroduzca el numero de la opcion que desea realizar");

            var menu = """
                    1.- Agregar libro en la base de datos 
                    2.- Listar todos los libros de la base
                    3.- Buscar un libro por el titulo
                    4.- Listar todos los autores registrados
                    5.- Buscar autores vivos a partir de un año dado
                    6.- Listar libros por idioma
               
                    
                    0.- Salir
                    """;
            System.out.println(menu);
            String linea = teclado.nextLine();
            try {
                opcion = Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Ingresa un número.");
                continue;
            }

            switch (opcion){
                case 1:
                    buscarLibroEnLaWeb();
                    break;
                case 2:
                    librosBuscados();
                    break;
                case 3:
                    buscarLibroPorNombre();
                    break;
                case 4:
                    BuscarAutores();
                    break;
                case 5:
                    buscarAutoresPorAnio();
                    break;
                case 6:
                    buscarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicacion...");
                    break;
                default:
                    System.out.println("Opcion inválida");
                    break;
            }
        }
    }

    private Libro getDatosLibro(){
        System.out.println("Ingrese el nombre del libro: ");
        var tituloLibro = teclado.nextLine().trim().toLowerCase();
        var url = URL_BASE + "?search=" + tituloLibro.replace(" ", "+");
        var json = consumoAPI.obtenerDatos(url);

        if (json == null || json.isBlank()) {
            System.out.println("La API no devolvió datos. URL usada: " + url);
            return null;
        }

        Datos datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        if (datosBusqueda == null || datosBusqueda.resultados() == null || datosBusqueda.resultados().isEmpty()) {
            System.out.println("No se encontraron resultados para: " + tituloLibro);
            return null;
        }

        // obtenemos el primero
        DatosLibros primerLibro = datosBusqueda.resultados().get(0);
        return new Libro(primerLibro);
    }

    private void buscarLibroEnLaWeb() {
        Libro libro = getDatosLibro();

        if (libro == null){
            System.out.println("Libro no encontrado. el valor es null");
            return;
        }

        try{
            boolean libroExists = libroRepository.existsByTitulo(libro.getTitulo());
            if (libroExists){
                System.out.println("El libro ya existe en la base de datos!");
            }else {
                libroRepository.save(libro);
                System.out.println(libro);
            }
        }catch (InvalidDataAccessApiUsageException e){
            System.out.println("No se puede persisitir el libro buscado!");
        }
    }

    @Transactional(readOnly = true)
    private void librosBuscados(){
        //datosLibro.forEach(System.out::println);
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en la base de datos.");
        } else {
            System.out.println("Libros encontrados en la base de datos:");
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        }
    }

    @Transactional(readOnly = true)
    private void buscarLibroPorNombre() {
        System.out.println("Ingrese Titulo del libro que quiere buscar: ");
        var titulo = teclado.nextLine();
        Libro libroBuscado = libroRepository.findByTituloContainsIgnoreCase(titulo);
        if (libroBuscado != null) {
            System.out.println("El libro buscado fue: " + libroBuscado);
        } else {
            System.out.println("El libro: " + titulo + " no se encontrado.");
        }
    }

    private  void BuscarAutores(){
        //LISTAR AUTORES DE LIBROS BUSCADOS
        List<Autor> autores = autorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("No se encontraron libros en la base de datos. \n");
        } else {
            System.out.println("Libros encontrados en la base de datos: \n");
            Set<String> autoresUnicos = new HashSet<>();
            for (Autor autor : autores) {
                // add() retorna true si el nombre no estaba presente y se añade correctamente
                if (autoresUnicos.add(autor.getNombre())){
                    System.out.println(autor.getNombre()+'\n');
                }
            }
        }
    }
    private void buscarAutoresPorAnio() {
//        //BUSCAR AUTORES POR AÑO
        System.out.println("Indica el año para consultar qué autores estaban vivos: ");
        int anioBuscado = teclado.nextInt();
        teclado.nextLine();

        List<Autor> todosAutores = autorRepository.findAll();
        Set<String> autoresUnicos = new HashSet<>();

        for (Autor autor : todosAutores) {
            try {
                Integer nacimiento = (autor.getFechaDeNacimiento() != null && !autor.getFechaDeNacimiento().isBlank())
                        ? Integer.parseInt(autor.getFechaDeNacimiento())
                        : null;

                Integer fallecimiento = (autor.getFechaDeFallecimiento() != null && !autor.getFechaDeFallecimiento().isBlank())
                        ? Integer.parseInt(autor.getFechaDeFallecimiento())
                        : null;

                if (nacimiento != null && nacimiento <= anioBuscado &&
                        (fallecimiento == null || fallecimiento >= anioBuscado)) {

                    if (autoresUnicos.add(autor.getNombre())) {
                        System.out.println("Autor: " + autor.getNombre());
                    }
                }
            } catch (NumberFormatException e) {
                // Ignorar autores con formato de fecha inválido
            }
        }
    }

    private void  buscarLibrosPorIdioma(){
        System.out.println("Ingrese Idioma en el que quiere buscar: \n");
        System.out.println("por ejempo:  es: español | en: inglés");

        var idioma = teclado.nextLine();
        List<Libro> librosPorIdioma = libroRepository.findByIdiomas(idioma);

        if (librosPorIdioma.isEmpty()) {
            System.out.println("No se encontraron libros de ese idioma en la base de datos.");
        } else {
            System.out.println("Libros en tu idioma encontrados en la base de datos:");
            for (Libro libro : librosPorIdioma) {
                System.out.println(libro.toString());
            }
        }

    }






}
