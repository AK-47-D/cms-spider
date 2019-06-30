package com.ak47.cms.cms.tree

import com.ak47.cms.cms.tree.Tree
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param

interface TreeRepository : PagingAndSortingRepository<Tree, Long> {
    @Query("SELECT a from #{#entityName} a where a.isDeleted=0 and a.category = :category")
    fun findByCategory(@Param("category") category: String): MutableList<Tree>
}