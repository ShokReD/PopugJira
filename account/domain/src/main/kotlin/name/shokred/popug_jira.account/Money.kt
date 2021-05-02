package name.shokred.popug_jira.account

import java.math.BigDecimal

class Money(val amount: BigDecimal) {
    constructor(amount: Int) : this(BigDecimal(amount))
    constructor(amount: Long) : this(BigDecimal(amount))

    operator fun unaryMinus(): Money {
        return Money(-amount)
    }

    operator fun plus(other: Money): Money {
        return Money(this.amount + other.amount)
    }
}
