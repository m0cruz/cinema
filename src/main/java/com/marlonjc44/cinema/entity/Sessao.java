package com.marlonjc44.cinema.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SESSAO")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "filme", nullable = false)
    private String filme;

    @Column(name = "preco", nullable = false)
    private Float preco;

    @Column(name = "sala_lotada", nullable = false)
    private Boolean salaLotada;
}