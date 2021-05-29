package name.shokred.popug_jira.account

class Account(
    val id: Long,
    val userId: Long,
    val operations: MutableCollection<Operation>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Account) return false

        if (id != other.id) return false
        if (userId != other.userId) return false
        if (operations != other.operations) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + userId.hashCode()
        result = 31 * result + operations.hashCode()
        return result
    }

    override fun toString(): String {
        return "Account(id=$id, userId=$userId, operations=$operations)"
    }
}
