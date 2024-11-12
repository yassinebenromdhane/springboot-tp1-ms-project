package com.ms.microservicearticle.controllers;

import com.ms.microservicearticle.entities.Article;
import com.ms.microservicearticle.services.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
@AllArgsConstructor()
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("{id}")
    public Article getArticleById(@PathVariable int id) {
        return articleService.getArticle(id);
    }

    @PostMapping("/")
    public ResponseEntity<?> addArticle(@RequestBody Article article) {
        return articleService.addArticle(article);
    }
}
