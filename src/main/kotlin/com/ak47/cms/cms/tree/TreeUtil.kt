package com.ak47.cms.cms.tree

import com.ak47.cms.cms.vo.TreeVO

object TreeUtil {

    /**
     * 递归遍历树
     * @param node 当前树节点
     * @param visitor 游历函数
     */
    fun visitTree(node: TreeVO, visitor: (TreeVO) -> Unit) {
        // 调用游历函数
        visitor(node)

        node.children.forEach {
            visitTree(it, visitor)
        }
    }


    /**
     * 根据节点列表,递归构建一棵树
     * @param nodes 节点列表
     * @param category 树的类型
     */
    fun buildTree(nodes: List<TreeVO>, category: TreeCategory): TreeVO {
        val treeNodes = mutableListOf<TreeVO>()

        val rootNodes = nodes.filter { it.parentCode == "-1" && it.category == category.name }

        rootNodes.forEach {
            buildChildren(it, nodes)
            treeNodes.add(it)
        }

        val treeVO = TreeVO()
        treeVO.children = treeNodes
        treeVO.category = TreeCategory.COUNTRY.name
        treeVO.name = TreeCategory.COUNTRY.name
        return treeVO
    }

    /**
     * 递归构建当前节点的的孩子列表
     * @param node 当前节点
     * @param nodes 节点列表
     */
    private fun buildChildren(node: TreeVO, nodes: List<TreeVO>) {
        val nodeCode = node.code
        val children = nodes.filter { it.parentCode == nodeCode }
        children.forEach {
            buildChildren(it, nodes)
        }
        node.children = children
    }
}