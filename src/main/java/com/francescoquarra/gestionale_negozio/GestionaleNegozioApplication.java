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
import com.francescoquarra.gestionale_negozio.entity.Prodotto;
import com.francescoquarra.gestionale_negozio.repository.CategoriaRepository;
import com.francescoquarra.gestionale_negozio.repository.ProdottoRepository;
import com.francescoquarra.gestionale_negozio.service.RigaVenditaRequest;
import com.francescoquarra.gestionale_negozio.service.VenditaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class GestionaleNegozioApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionaleNegozioApplication.class, args);
    }

    @Bean
    CommandLineRunner testVenditaService(CategoriaRepository categoriaRepository,
                                          ProdottoRepository prodottoRepository,
                                          VenditaService venditaService) {
        return args -> {

            // 1. Prepariamo una categoria e un prodotto di prova, se non esistono già
            Categoria categoria = categoriaRepository.findByNome("Alimentari")
                    .orElseGet(() -> {
                        Categoria c = new Categoria();
                        c.setNome("Alimentari");
                        return categoriaRepository.save(c);
                    });

            Prodotto prodotto = prodottoRepository.findByNomeContainingIgnoreCase("Pasta").stream()
                    .findFirst()
                    .orElseGet(() -> {
                        Prodotto p = new Prodotto();
                        p.setNome("Pasta 500g");
                        p.setPrezzo(new BigDecimal("1.20"));
                        p.setQuantitaInStock(50);
                        p.setCategoria(categoria);
                        return prodottoRepository.save(p);
                    });

            System.out.println(">>> Stock prima della vendita: " + prodotto.getQuantitaInStock());

            // 2. Registriamo una vendita di 3 unità di quel prodotto, senza cliente associato
            RigaVenditaRequest richiesta = new RigaVenditaRequest(prodotto.getId(), 3);

            var vendita = venditaService.registraVendita(null, List.of(richiesta));

            System.out.println(">>> Vendita registrata con id: " + vendita.getId());
            System.out.println(">>> Totale vendita: " + vendita.getTotale());

            Prodotto prodottoAggiornato = prodottoRepository.findById(prodotto.getId()).get();
            System.out.println(">>> Stock dopo la vendita: " + prodottoAggiornato.getQuantitaInStock());
        };
    }
}*/

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