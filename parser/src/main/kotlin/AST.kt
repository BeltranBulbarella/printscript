class AST(private val head: NodeToken, private val left: IAST, private val right: IAST) : IAST {
    override fun equalsTree(tree: IAST): Boolean {
        return if (tree is AST) {
            tree.head === head && left.equalsTree(tree.left) && right.equalsTree(tree.right)
        } else {
            false
        }
    }
}
