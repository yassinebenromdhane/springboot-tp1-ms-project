package com.ms.microservicearticle.dtos;


import lombok.Data;

@Data
public class Facture {
    private int id ;
    private Double somme ;
    private String entreprise ;
}
