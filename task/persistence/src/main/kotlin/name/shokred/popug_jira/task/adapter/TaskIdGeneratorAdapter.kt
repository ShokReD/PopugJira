package name.shokred.popug_jira.task.adapter

import name.shokred.popug_jira.task.port.TaskIdGenerator
import org.springframework.jdbc.core.JdbcTemplate
import java.util.concurrent.atomic.AtomicLong

class TaskIdGeneratorAdapter(private val jdbcTemplate: JdbcTemplate) : TaskIdGenerator {

    private lateinit var cachedTaskId: AtomicLong

    // language=sql
    private val selectMaxTaskIdQuery: String =
        """
        SELECT MAX(task_id) task_id
          FROM tasks t
        """.trimIndent()

    override fun generate(): Long {
        if (!this::cachedTaskId.isInitialized) {
            synchronized(this) {
                this.cachedTaskId = selectMaxTaskIdQuery
                    .let { jdbcTemplate.queryForObject(it) { rs, _ -> rs.getLong("task_id") } }
                    .let { AtomicLong(it) }
            }
        }

        return this.cachedTaskId.incrementAndGet()
    }
}
