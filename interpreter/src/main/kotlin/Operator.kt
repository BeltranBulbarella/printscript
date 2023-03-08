
sealed interface Operator {
    fun operate(a: ExpressionResult, b: ExpressionResult): ExpressionResult
}

object SUM : Operator {
    override fun operate(a: ExpressionResult, b: ExpressionResult): ExpressionResult {
        if(a is ExpressionResult.NumberResult && b is ExpressionResult.NumberResult) {
            return ExpressionResult.NumberResult(a.value.toDouble() + b.value.toDouble())
        } else {
            TODO("Not yet implemented")
        }
    }
}
object SUBSTRACT: Operator {
    override fun operate(a: ExpressionResult, b: ExpressionResult): ExpressionResult {
        TODO("Not yet implemented")
    }
}
object DIVIDE: Operator {
    override fun operate(a: ExpressionResult, b: ExpressionResult): ExpressionResult {
        TODO("Not yet implemented")
    }
}
object MULTIPLY: Operator {
    override fun operate(a: ExpressionResult, b: ExpressionResult): ExpressionResult {
        if(a is ExpressionResult.NumberResult && b is ExpressionResult.NumberResult) {
            return ExpressionResult.NumberResult(a.value.toDouble() * b.value.toDouble())
        } else {
            TODO("Not yet implemented")
        }
    }
}