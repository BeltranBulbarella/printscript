sealed interface IAST {
    fun equalsTree(tree: IAST): Boolean
    fun operate(): ExpressionResult
}
