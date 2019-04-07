package com.ak47.cms.cms.dao

import com.ak47.cms.cms.entity.TechArticle
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface TechArticleRepository : JpaRepository<TechArticle, Long> {

    @Query("select count(*) from #{#entityName} a where a.url = :url")
    fun countByUrl(@Param("url") url: String): Int

    @Query("select a from #{#entityName} a order by a.id desc")
    fun listTechArticleDto(page: Pageable): Page<TechArticle>

    @Query("select a from #{#entityName} a where a.title like %:searchText% or a.simpleContent  like %:searchText%  order by a.id desc")
    fun searchTechArticleDto(page: Pageable, @Param("searchText") searchText: String): Page<TechArticle>

}