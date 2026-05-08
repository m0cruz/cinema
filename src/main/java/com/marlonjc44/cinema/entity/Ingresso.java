package com.marlonjc44.cinema.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "INGRESSO")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // JPA/Hibernate associa automaticamente a FK na configuração do DB
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // JPA/Hibernate associa automaticamente a FK na configuração do DB
    @ManyToOne
    @JoinColumn(name = "sessao_id", nullable = false)
    private Sessao sessao;

    @Column(name = "assentos_comprados", nullable = false)
    private int assentosComprados;
}