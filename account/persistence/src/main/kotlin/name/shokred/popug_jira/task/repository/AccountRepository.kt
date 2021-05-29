package name.shokred.popug_jira.task.repository

import name.shokred.popug_jira.task.entity.AccountEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.time.OffsetDateTime

interface AccountRepository : CrudRepository<AccountEntity, Long> {
    // language=sql
    @Query(
        """
        SELECT a
          FROM AccountEntity a 
         WHERE EXISTS (SELECT 1 
                         FROM OperationEntity o
                        WHERE o.operationDate > :operationDate)
         """
    )
    fun findAllWithTodayOperations(@Param("operationDate") operationDate: OffsetDateTime): List<AccountEntity>

    fun findOneByUserId(userId: Long): AccountEntity?
}
