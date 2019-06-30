package com.ak47.cms.cms.tree

import com.ak47.cms.cms.dao.TreeRepository
import com.ak47.cms.cms.vo.TreeVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TreeCountryController {
    @Autowired
    lateinit var treeRepository: TreeRepository


    @GetMapping("/tree/country.json")
    fun country(): TreeVO {
        val trees = treeRepository.findByCategory(TreeCategory.COUNTRY.name)

        val treeVOList = mutableListOf<TreeVO>()
        trees.forEach {
            val vo = TreeVO()
            vo.category = it.category
            vo.code = it.code
            vo.name = it.name
            vo.parentCode = it.parentCode
            treeVOList.add(vo)
        }

        return TreeUtil.buildTree(treeVOList, TreeCategory.COUNTRY)

    }
}

