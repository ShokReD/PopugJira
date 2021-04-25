package name.shokred.popug_jira.task

import java.math.BigDecimal

class Money(val amount: BigDecimal) {
    constructor(amount: Int) : this(BigDecimal(amount))
    constructor(amount: Long) : this(BigDecimal(amount))
}