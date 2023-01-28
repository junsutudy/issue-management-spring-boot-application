package app.junsu.issuemanagementspringbootapplication.model.issue

import app.junsu.issuemanagementspringbootapplication.domain.enums.IssuePriority
import app.junsu.issuemanagementspringbootapplication.domain.enums.IssueStatus
import app.junsu.issuemanagementspringbootapplication.domain.enums.IssueType

data class IssueRequest(
    val summary: String,
    val description: String,
    val type: IssueType,
    val priority: IssuePriority,
    val status: IssueStatus,
)
