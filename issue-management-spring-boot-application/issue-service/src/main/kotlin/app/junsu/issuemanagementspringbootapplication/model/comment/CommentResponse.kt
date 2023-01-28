package app.junsu.issuemanagementspringbootapplication.model.comment

import app.junsu.issuemanagementspringbootapplication.domain.comment.entity.Comment

data class CommentResponse(
    val id: Long,
    val issueId: Long,
    val userId: Long,
    val username: String? = null,
    val body: String,
)

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        id = id!!,
        issueId = issue.id!!,
        userId = userId,
        username = username,
        body = body,
    )
}
