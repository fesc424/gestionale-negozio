package com.francescoquarra.gestionale_negozio.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vendite")
@Getter
@Setter
@NoArgsConstructor
public class Vendita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime data = LocalDateTime.now();

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totale = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Cliente cliente;

    @OneToMany(mappedBy = "vendita", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RigaVendita> righe = new ArrayList<>();
}