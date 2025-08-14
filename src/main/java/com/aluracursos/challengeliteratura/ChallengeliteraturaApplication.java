package com.aluracursos.challengeliteratura;

import com.aluracursos.challengeliteratura.principal.Principal;
import com.aluracursos.challengeliteratura.repository.AutorRepository;
import com.aluracursos.challengeliteratura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeliteraturaApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ChallengeliteraturaApplication.class, args);
	}

	@Autowired
	private LibroRepository libroRepository;
	@Autowired
	private AutorRepository autorRepository;


//	@Bean
//	CommandLineRunner run() {
//		return args -> new Principal().muestraElMenu();
//	}
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepository, autorRepository);
		principal.muestraElMenu();
	}

}
