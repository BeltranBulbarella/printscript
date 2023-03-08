sealed class AvailableTypes {
    data class Number(val value: kotlin.Number) : AvailableTypes() {
        override fun equals(other: Any?): Boolean {
            return other is Number && other.value === value
        }
    }

    data class String(val string: kotlin.String) : AvailableTypes() {
        override fun equals(other: Any?): Boolean {
            return other is String && other.string === string
        }
    }
}
