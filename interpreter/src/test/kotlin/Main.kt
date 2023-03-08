import org.junit.jupiter.api.Test

class MainTest {

    @Test
    fun test1() {
        // let name = "Fede";
        val tree = AST(
            NodeToken.ASSIGNATION,
            AST(
                NodeToken.DECLARATION,
                Leaf(LeafToken.IDENTIFIER("name")),
                Leaf(LeafToken.TYPE(Type.StringType)),
            ),
            Leaf(
                LeafToken.LITERAL(AvailableTypes.String("Fede")),
            ),
        )

        val interpreter = Interpreter(mutableMapOf())
        interpreter.run(tree)

        interpreter.getMemory()["name"]?.let {
            assert(it is AvailableTypes.String)
            assert(it == AvailableTypes.String("Fede"))
        }
    }

    @Test
    fun test2() {
        // let someNumber = 3*2+1;
        val tree = AST(
            NodeToken.ASSIGNATION,
            AST(
                NodeToken.DECLARATION,
                Leaf(LeafToken.IDENTIFIER("someNumber")),
                Leaf(LeafToken.TYPE(Type.NumberType)),
            ),
            AST(
                NodeToken.OPERATOR(SUM),
                AST(
                    NodeToken.OPERATOR(MULTIPLY),
                    Leaf(LeafToken.LITERAL(AvailableTypes.Number(3))),
                    Leaf(LeafToken.LITERAL(AvailableTypes.Number(2)))
                ),
                Leaf(LeafToken.LITERAL(AvailableTypes.Number(1)))
            ),
        )
        val interpreter = Interpreter(mutableMapOf())
        interpreter.run(tree)

        interpreter.getMemory()["someNumber"]?.let {
            assert(it is AvailableTypes.Number)
            if(it is AvailableTypes.Number) {
                assert(it.value == 7.0)
            }
        }
    }

}