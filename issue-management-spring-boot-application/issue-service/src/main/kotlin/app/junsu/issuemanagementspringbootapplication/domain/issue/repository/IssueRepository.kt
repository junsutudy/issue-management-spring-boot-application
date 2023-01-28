package app.junsu.issuemanagementspringbootapplication.domain.issue.repository

import app.junsu.issuemanagementspringbootapplication.domain.enums.IssueStatus
import app.junsu.issuemanagementspringbootapplication.domain.issue.entity.Issue
import org.springframework.data.jpa.repository.JpaRepository

interface IssueRepository : JpaRepository<Issue, Long> {
    fun findAllByStatusOrderByCreatedAtAsc(status: IssueStatus): List<Issue>?
}
