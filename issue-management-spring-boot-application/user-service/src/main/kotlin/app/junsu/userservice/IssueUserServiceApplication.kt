package app.junsu.userservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class IssueUserServiceApplication

fun main(args: Array<String>) {
    runApplication<IssueUserServiceApplication>(*args)
}


