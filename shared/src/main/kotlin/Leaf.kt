class Leaf(private val value: LeafToken) : IAST {
    override fun equalsTree(tree: IAST): Boolean {
        return if (tree is Leaf) {
            when (value) {
                is LeafToken.IDENTIFIER -> tree.value is LeafToken.IDENTIFIER && tree.value.value === value.value
                is LeafToken.TYPE -> tree.value is LeafToken.TYPE && tree.value.type === value.type
                is LeafToken.LITERAL -> tree.value is LeafToken.LITERAL && value == tree.value
            }
        } else {
            false
        }
    }
}
