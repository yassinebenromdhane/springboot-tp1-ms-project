package com.ms.microserviceinventaire.controllers;


import com.ms.microserviceinventaire.entities.Facture;
import com.ms.microserviceinventaire.repositories.FactureRepository;
import com.ms.microserviceinventaire.services.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/facture")
public class FactureController {
    @Autowired
    private FactureService factureService;
    @GetMapping("/")
    public List<Facture> getAllFactures() {
        return factureService.getFactures();
    }
    @GetMapping("/{id}")
    public Facture getFacture(@PathVariable int id) {
        return factureService.getFacture(id);
    }
    @PostMapping("/")
    public ResponseEntity<?> addFacture(@RequestBody Facture facture) {
        return factureService.addFacture(facture);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFacture(@PathVariable int id, @RequestBody Facture facture) {
        return factureService.updateFacture(facture , id);
    }
}
