package name.shokred.popug_jira.task.configuration

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableJpaRepositories(basePackages = ["name.shokred.popug_jira.task.repository"])
@EnableTransactionManagement
@EntityScan(basePackages = ["name.shokred.popug_jira.task.entity"])
class JpaConfiguration
