package com.francescoquarra.gestionale_negozio.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "righe_vendita")
@Getter
@Setter
@NoArgsConstructor
public class RigaVendita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantita;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal prezzoUnitario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendita_id", nullable = false)
    private Vendita vendita;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prodotto_id", nullable = false)
    private Prodotto prodotto;
}