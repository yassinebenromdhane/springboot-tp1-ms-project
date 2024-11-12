package com.ms.microservicearticle.feign;

import com.ms.microservicearticle.dtos.Facture;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-inventaire" , url = "http://localhost:9091/facture")
public interface FactureClient {

    @GetMapping("/{id}")
    Facture getFacture(@PathVariable() int id);
}
