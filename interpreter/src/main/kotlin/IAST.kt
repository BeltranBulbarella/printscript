sealed interface IAST {
    fun equalsTree(tree: IAST): Boolean
    fun operate(memory: Memory): ExpressionResult
}
