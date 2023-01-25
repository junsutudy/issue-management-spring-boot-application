package app.junsu.issuemanagementspringbootapplication.model

import app.junsu.issuemanagementspringbootapplication.domain.Issue
import app.junsu.issuemanagementspringbootapplication.domain.enums.IssuePriority
import app.junsu.issuemanagementspringbootapplication.domain.enums.IssueStatus
import app.junsu.issuemanagementspringbootapplication.domain.enums.IssueType
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class IssueResponse(
    val id: Long,
    val userId: Long,
    val summary: String,
    val description: String,
    val type: IssueType,
    val priority: IssuePriority,
    val status: IssueStatus,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") val createdAt: LocalDateTime?,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") val updatedAt: LocalDateTime?,
) {
    companion object {
        operator fun invoke(issue: Issue): IssueResponse {
            return IssueResponse(
                id = issue.id!!,
                userId = issue.userId,
                summary = issue.summary,
                description = issue.description,
                type = issue.type,
                priority = issue.priority,
                status = issue.status,
                createdAt = issue.createdAt,
                updatedAt = issue.updatedAt,
            )
        }
    }
}
