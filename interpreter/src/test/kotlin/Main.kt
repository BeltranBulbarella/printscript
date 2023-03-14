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

        val interpreter = Interpreter(Memory(mutableMapOf()))
        interpreter.run(tree)

        interpreter.getMemory().getValue("name").let {
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
        val interpreter = Interpreter(Memory(mutableMapOf()))
        interpreter.run(tree)

        interpreter.getMemory().getValue("someNumber").let {
            assert(it is AvailableTypes.Number)
            if(it is AvailableTypes.Number) {
                assert(it.value == 7.0)
            }
        }
    }

    @Test
    fun test3() {
        val interpreter = Interpreter(Memory(mutableMapOf()))

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
        interpreter.run(tree)

        // let someNumber = 3*2+1;
        val tree2 = AST(
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
        interpreter.run(tree2)

        interpreter.getMemory().getValue("name").let {
            assert(it is AvailableTypes.String)
            assert(it == AvailableTypes.String("Fede"))
        }

        interpreter.getMemory().getValue("someNumber").let {
            assert(it is AvailableTypes.Number)
            if(it is AvailableTypes.Number) {
                assert(it.value == 7.0)
            }
        }
    }

}