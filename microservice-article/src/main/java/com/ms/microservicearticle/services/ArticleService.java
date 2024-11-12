package com.ms.microservicearticle.services;

import com.ms.microservicearticle.dtos.Facture;
import com.ms.microservicearticle.entities.Article;
import com.ms.microservicearticle.feign.FactureClient;
import com.ms.microservicearticle.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private FactureClient factureClient;

    public List<Article> getAllArticles() {

        List<Article> articles = articleRepository.findAll();
        articles.forEach(article -> article.setFacture(factureClient.getFacture(article.getFactureId())));
        return articles;
    }

    public ResponseEntity<?> addArticle(Article article) {
        UUID uniqueID = UUID.randomUUID();
        article.setUid(uniqueID);
        articleRepository.save(article);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Article added successfully");
        return ResponseEntity.accepted().body(response);
    }

    public ResponseEntity<?> updateArticle(Article article, int id) {
        articleRepository.findById(id).ifPresentOrElse((a -> {
            a.setNom(article.getNom());
            a.setPrixu(article.getPrixu());
            a.setQte(article.getQte());
            articleRepository.save(a);
        }), () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found");
        });
        Map<String, String> response = new HashMap<>();
        response.put("message", "Article Updated");
        return ResponseEntity.accepted().body(response);
    }

    public ResponseEntity<?> deleteArticle(int id) {
        articleRepository.findById(id).ifPresentOrElse((a) -> {
            articleRepository.delete(a);
        } , () ->{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found");
        });
        return ResponseEntity.accepted().body("Article deleted successfully");
    }

    public Article getArticle(int id) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article != null) {
            Facture facture = factureClient.getFacture(article.getFactureId());
            article.setFacture(facture);
            return article;

        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found");
        }
    }


}
