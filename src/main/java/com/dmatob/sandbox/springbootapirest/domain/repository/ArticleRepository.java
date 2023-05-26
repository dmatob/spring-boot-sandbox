package com.dmatob.sandbox.springbootapirest.domain.repository;

import java.util.List;
import java.util.Optional;

import com.dmatob.sandbox.springbootapirest.domain.model.Article;

public interface ArticleRepository {
	
	List<Article> findAll();
	
	Optional<Article> findById(Long id);
	
	Optional<Article> findByCode(String code);
	
	Article save(Article article);
	
	void delete(Article article);
}