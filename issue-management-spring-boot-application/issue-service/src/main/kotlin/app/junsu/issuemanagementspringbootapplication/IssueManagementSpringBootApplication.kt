package app.junsu.issuemanagementspringbootapplication

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class IssueManagementSpringBootApplication

fun main(args: Array<String>) {
    runApplication<IssueManagementSpringBootApplication>(*args)
}
