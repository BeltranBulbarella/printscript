import visitor.*

class InterpreterVisitor(
    val memory: LocalMemory
) : Visitor {

    override fun visitAssignationAST(ast: AssignationAST): EmptyAST {
        val literalAST = ast.expression.accept(this)
        val declarationAST = ast.declaration.accept(this)
        if(declarationAST is DeclarationAST && literalAST is LiteralAST) {
            if(isSameType(declarationAST.variableType, literalAST.value)) {
                memory.put(declarationAST.variableName, literalAST.value)
            } else {
                throw Error("Invalid type assignation")
            }
        } else {
            throw Error("Invalid tree")
        }
        return EmptyAST()
    }

    private fun isSameType(declarationType: Types, value: Value): Boolean {
        return if(declarationType is NUM && value is NumValue) {
            true
        } else declarationType is STR && value is StrValue
    }

    override fun visitDeclarationAST(ast: DeclarationAST): DeclarationAST {
        return ast
    }

    override fun visitBinaryOperationAST(ast: BinaryOperationAST): LiteralAST {
        val leftResult = ast.left.accept(this)
        val rightResult = ast.right.accept(this)
        if(leftResult is LiteralAST && rightResult is LiteralAST) {
            return when (ast.operation) {
                is ADD -> LiteralAST(sumValues(leftResult.value, rightResult.value))
                is SUB -> LiteralAST(subtractValues(leftResult.value, rightResult.value))
                is DIV -> LiteralAST(divideValues(leftResult.value,rightResult.value))
                is MUL -> LiteralAST(multiplyValues(leftResult.value,rightResult.value))
                else -> throw Error("Invalid operation")
            }
        }
        throw Error("Invalid expression")
    }

    private fun sumValues(left: Value, right: Value): Value {
        if(left is NumValue && right is NumValue) {
            return NumValue(left.value+right.value)
        } else if (left is StrValue && right is NumValue){
            return StrValue(left.value+right.value)
        } else if (left is NumValue && right is StrValue) {
            return StrValue(left.value.toString()+right.value)
        } else if (left is StrValue && right is StrValue) {
            return StrValue(left.value+right.value)
        }
        throw Error("Can not sum values")
    }

    private fun subtractValues(left: Value, right: Value): Value {
        if(left is NumValue && right is NumValue) {
            return NumValue(left.value-right.value)
        }
        throw Error("Can not subtract values")
    }

    private fun divideValues(left: Value, right: Value): Value {
        if(left is NumValue && right is NumValue) {
            return NumValue(left.value/right.value)
        }
        throw Error("Can not divide values")
    }

    private fun multiplyValues(left: Value, right: Value): Value {
        if(left is NumValue && right is NumValue) {
            return NumValue(left.value*right.value)
        }
        throw Error("Can not multiply values")
    }

    override fun visitUnaryOperationAST(ast: UnaryOperationAST): VisitableAST {
        return when(ast.function) {
            is PRINT -> printFunctionImpl(ast.args.accept(this))
        }
    }

    private fun printFunctionImpl(ast: VisitableAST): EmptyAST {
        return when(ast) {
            is LiteralAST -> {
                when(val value = ast.value) {
                    is StrValue -> println(value.value)
                    is NumValue -> println(value.value)
                }
                EmptyAST()
            }
            else -> throw Error("Can not print value")
        }
    }

    override fun visitLiteralAST(ast: LiteralAST): LiteralAST {
        return ast
    }

    override fun visitVariableAST(ast: VariableAST): LiteralAST {
        return LiteralAST(memory.getValue(ast.variableName))
    }

    override fun visitEmptyAST(ast: EmptyAST): EmptyAST {
        return ast
    }

}




