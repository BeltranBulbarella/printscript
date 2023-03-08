class Interpreter (
    private val memory: MutableMap<String, AvailableTypes>
) {

    fun run(tree: IAST) {
        when(val result = tree.operate()) {
          is ExpressionResult.VariableAssignation -> {
              memory.put(result.name, result.value)
          }
          else -> {}
        }
        println("")
    }

    fun getMemory(): Map<String, AvailableTypes> = memory

}