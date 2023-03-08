class Parser : IParser {
    override fun parse(tokenList: List<Token>): IAST {
        // Check list is valid
        validateList(tokenList)

        val declaration = AST(NodeToken.DECLARATION, Leaf(tokenList[1] as LeafToken.IDENTIFIER), Leaf(tokenList[2] as LeafToken.TYPE))
        val assignment = if (tokenList[4] is LeafToken.IDENTIFIER) {
            Leaf(tokenList[4] as LeafToken.IDENTIFIER)
        } else {
            parseExpression(tokenList.subList(4, tokenList.size - 1))
        }
        return AST(NodeToken.ASSIGNATION, declaration, assignment)
    }

    private fun parseExpression(subList: List<Token>): IAST {
        return if (subList.size == 1) {
            Leaf(subList.first() as LeafToken.LITERAL)
        } else {
            AST(subList[1] as NodeToken.OPERATOR, Leaf(subList.first() as LeafToken.LITERAL), parseExpression(subList.subList(2, subList.size)))
        }
    }

    private fun validateList(tokenList: List<Token>) {
        // TODO review with no assignation
        if (tokenList.size < 6) throw Exception("Token list should be larger")
        if (tokenList.first() !is UtilToken.LET_KEY_WORD) throw Exception("Token list should start with let")
        if (tokenList.last() !is UtilToken.SEMICOLON) throw Exception("Token list should finish with semicolon")
        if (tokenList[1] !is LeafToken.IDENTIFIER) throw Exception("Token list first item should be an identifier")
        if (tokenList[2] !is LeafToken.TYPE) throw Exception("Token list second item should be a type")
        if (tokenList[3] !is NodeToken.ASSIGNATION) throw Exception("Token list third item should be an assignation symbol (=)")
    }
}
