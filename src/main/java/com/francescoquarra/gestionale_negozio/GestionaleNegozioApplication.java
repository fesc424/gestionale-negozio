package com.francescoquarra.gestionale_negozio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionaleNegozioApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionaleNegozioApplication.class, args);
	}

}

/*package com.francescoquarra.gestionale_negozio;

import com.francescoquarra.gestionale_negozio.entity.Categoria;
import com.francescoquarra.gestionale_negozio.repository.CategoriaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestionaleNegozioApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionaleNegozioApplication.class, args);
    }

    @Bean
    CommandLineRunner testRepository(CategoriaRepository categoriaRepository) {
        return args -> {
            if (!categoriaRepository.existsByNome("Alimentari")) {
                Categoria categoria = new Categoria();
                categoria.setNome("Alimentari");
                categoriaRepository.save(categoria);
                System.out.println(">>> Categoria 'Alimentari' salvata!");
            } else {
                System.out.println(">>> Categoria 'Alimentari' già presente.");
            }

            categoriaRepository.findByNome("Alimentari")
                    .ifPresent(c -> System.out.println(">>> Trovata categoria con id: " + c.getId()));
        };
    }
}*/