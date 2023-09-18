package com.dmatob.sandbox.springbootapirest.infrastructure.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.dmatob.sandbox.springbootapirest.domain.model.Article;
import com.dmatob.sandbox.springbootapirest.infrastructure.api.dto.ArticleDTO;
import com.dmatob.sandbox.springbootapirest.infrastructure.api.dto.ArticleModificationRequestDTO;

@Mapper(uses = ArticleTypeDTOMapper.class)
public interface ArticleDTOMapper {

	public ArticleDTO toArticleDTO (Article article);
	public List<ArticleDTO> map (List<Article> lstOfArticles);
	public Article fromArticleDTO (ArticleDTO articleDTO);
	public List<Article> fromListOfArticleDTOs (List<ArticleDTO> listOfArticleDTOs);

	@Mapping(source = "typeId", target = "type.id")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "lastModificationDate", ignore = true)
	public Article fromArticleModificationRequestDTO (ArticleModificationRequestDTO articleDTO);

	@Mapping(source = "type.id", target = "typeId")
	public ArticleModificationRequestDTO toArticleModificationRequestDTO (Article article);
	
}
