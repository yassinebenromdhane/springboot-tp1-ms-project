package com.ms.microserviceinventaire.services;


import com.ms.microserviceinventaire.entities.Facture;
import com.ms.microserviceinventaire.repositories.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FactureService {
    @Autowired
    FactureRepository factureRepository;

    public Facture getFacture(int id) {
        return factureRepository.findById(id).orElse(null);
    }
    public List<Facture> getFactures() {
        return factureRepository.findAll();
    }

    public ResponseEntity<?> deleteArticle(int id) {
        factureRepository.findById(id).ifPresentOrElse((f) -> {
            factureRepository.delete(f);
        } , () ->{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Facture not found");
        });
        return ResponseEntity.accepted().body("Facture deleted successfully");
    }

    public ResponseEntity<?> updateFacture(Facture facture, int id) {
        factureRepository.findById(id).ifPresentOrElse((f -> {
            f.setSomme(facture.getSomme());
            f.setEntreprise(facture.getEntreprise());
            factureRepository.save(f);
        }), () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Facture not found");
        });
        Map<String, String> response = new HashMap<>();
        response.put("message", "Facture Updated");
        return ResponseEntity.accepted().body(response);
    }

    public ResponseEntity<?> addFacture(Facture fact) {
        factureRepository.save(fact);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Facture added successfully");
        return ResponseEntity.accepted().body(response);
    }

}
