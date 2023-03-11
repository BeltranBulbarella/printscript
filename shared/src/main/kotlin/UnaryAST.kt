class UnaryAST(private val head: NodeToken, private val branch: IAST) : IAST {
    override fun equalsTree(tree: IAST): Boolean {
        return if (tree is UnaryAST) {
            tree.head === head && branch.equalsTree(tree.branch)
        } else {
            false
        }
    }
}
