
sealed interface Operator {
    fun operate(memory: Memory, a: ExpressionResult, b: ExpressionResult): ExpressionResult
}

object SUM : Operator {
    override fun operate(memory: Memory, a: ExpressionResult, b: ExpressionResult): ExpressionResult {
//        val valueA = getValue(memory, a)
        if(a is ExpressionResult.NumberResult && b is ExpressionResult.NumberResult) {
            return ExpressionResult.NumberResult(a.value.toDouble() + b.value.toDouble())
        } else {
            throw Error()
//            return ExpressionResult.StringResult(a.value +)
        }
    }
//
//    fun getValue(memory: Memory, a: ExpressionResult): Operable {
//        if(a is ExpressionResult.VariableDeclaration) {
//            return memory.getValue(a.name)
//        } else if (a is ExpressionResult.NumberResult) {
//            return a.value
//        } else if (a is ExpressionResult.StringResult) {
//            return a.string
//        }
//    }
}
object SUBSTRACT: Operator {
    override fun operate(memory: Memory, a: ExpressionResult, b: ExpressionResult): ExpressionResult {
        TODO("Not yet implemented")
    }
}
object DIVIDE: Operator {
    override fun operate(memory: Memory, a: ExpressionResult, b: ExpressionResult): ExpressionResult {
        TODO("Not yet implemented")
    }
}
object MULTIPLY: Operator {
    override fun operate(memory: Memory, a: ExpressionResult, b: ExpressionResult): ExpressionResult {
        if(a is ExpressionResult.NumberResult && b is ExpressionResult.NumberResult) {
            return ExpressionResult.NumberResult(a.value.toDouble() * b.value.toDouble())
        } else {
            TODO("Not yet implemented")
        }
    }
}

sealed interface Operable
sealed class OperableNumber(value: Number) : Operable
sealed class OperableString(value: String) : Operable