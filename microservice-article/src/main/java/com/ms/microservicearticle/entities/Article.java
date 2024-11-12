package com.ms.microservicearticle.entities;

import com.ms.microservicearticle.dtos.Facture;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Entity
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false , unique = true )
    private UUID uid;
    private String nom;
    private Double prixu;
    private int qte;
    private int factureId;
    @Transient
    private Facture facture;



}
