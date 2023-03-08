interface IParser {
    fun parse(tokenList: List<Token>): IAST
}