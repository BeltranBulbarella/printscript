import java.lang.Error

class Memory(
    val memory: MutableMap<String, AvailableTypes>
) {
    fun getValue(key: String): AvailableTypes {
        return memory[key] ?: throw Error("variable " + key + "not found")
    }
    fun put(key: String, value: AvailableTypes) = run { memory[key]=value }
}