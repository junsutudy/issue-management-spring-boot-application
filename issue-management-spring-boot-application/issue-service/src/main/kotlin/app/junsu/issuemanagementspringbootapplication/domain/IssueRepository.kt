package app.junsu.issuemanagementspringbootapplication.domain

import app.junsu.issuemanagementspringbootapplication.domain.enums.IssueStatus
import org.springframework.data.jpa.repository.JpaRepository

interface IssueRepository : JpaRepository<Issue, Long> {
    fun findAllByStatusOrderByCreatedAtAsc(status: IssueStatus): List<Issue>?
}
