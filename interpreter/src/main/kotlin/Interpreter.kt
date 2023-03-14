class Interpreter (
    private val memory: Memory
) {

    fun run(tree: IAST) {
        when(val result = tree.operate(memory)) {
          is ExpressionResult.VariableAssignation -> {
              memory.put(result.name, result.value)
          }
          else -> {
              throw Error("invalid result")
          }
        }
    }

    fun getMemory(): Memory = memory

}