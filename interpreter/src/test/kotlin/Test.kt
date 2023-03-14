import org.junit.jupiter.api.Test
import visitor.*
import visitor.MULTIPLY
import visitor.SUM

class Test {

    @Test
    fun test1() {
        // let someNumber: Number = 3*2+1;
        val tree = AssignationAST(
            DeclarationAST(
                "someNumber",
                NUM
            ),
            BinaryOperationAST(
                BinaryOperationAST(
                    LiteralAST(NumValue(3.0)),
                    LiteralAST(NumValue(2.0)),
                    MULTIPLY
                ),
                LiteralAST(NumValue(1.0)),
                SUM
            )
        )
        val interpreter = Interpreter.Factory.create()
        interpreter.interpret(tree)
        assert((interpreter.getMemory().getValue("someNumber") as NumValue).value == 7.0)
    }

    @Test
    fun test2() {
        // let someString: String = "some" + "1";
        val tree = AssignationAST(
            DeclarationAST(
                "someString",
                STR
            ),
            BinaryOperationAST(
                LiteralAST(StrValue("some")),
                LiteralAST(NumValue(1.0)),
                SUM
            )
        )
        val interpreter = Interpreter.Factory.create()
        interpreter.interpret(tree)
        assert((interpreter.getMemory().getValue("someString") as StrValue).value == "some1.0")
    }

    @Test
    fun test3() {
        // let someString: String = "some" + "1";
        // let newSomeString: String = "new" + someString;

        val tree1 = AssignationAST(
            DeclarationAST(
                "someString",
                STR
            ),
            BinaryOperationAST(
                LiteralAST(StrValue("some")),
                LiteralAST(NumValue(1.0)),
                SUM
            )
        )
        val tree2 = AssignationAST(
            DeclarationAST(
                "newSomeString",
                STR
            ),
            BinaryOperationAST(
                LiteralAST(StrValue("new ")),
                VariableAST("someString"),
                SUM
            )
        )
        val interpreter = Interpreter.Factory.create()
        interpreter.interpret(tree1)
        interpreter.interpret(tree2)
        assert((interpreter.getMemory().getValue("someString") as StrValue).value == "some1.0")
        assert((interpreter.getMemory().getValue("newSomeString") as StrValue).value == "new some1.0")
    }

    @Test
    fun test4() {
        // let someString: String = "some" + "1";
        val tree1 = AssignationAST(
            DeclarationAST(
                "someString",
                STR
            ),
            BinaryOperationAST(
                LiteralAST(StrValue("some")),
                LiteralAST(NumValue(1.0)),
                SUM
            )
        )
        //print(someString);
        val tree2 = UnaryOperationAST(
            PRINT,
            VariableAST("someString")
        )
        val interpreter = Interpreter.Factory.create()
        interpreter.interpret(tree1)
        interpreter.interpret(tree2)
    }

}