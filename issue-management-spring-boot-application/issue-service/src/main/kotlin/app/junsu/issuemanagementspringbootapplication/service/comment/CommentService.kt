package app.junsu.issuemanagementspringbootapplication.service.comment

import app.junsu.issuemanagementspringbootapplication.domain.comment.entity.Comment
import app.junsu.issuemanagementspringbootapplication.domain.comment.repository.CommentRepository
import app.junsu.issuemanagementspringbootapplication.domain.issue.repository.IssueRepository
import app.junsu.issuemanagementspringbootapplication.exception.ServerException
import app.junsu.issuemanagementspringbootapplication.model.comment.CommentRequest
import app.junsu.issuemanagementspringbootapplication.model.comment.CommentResponse
import app.junsu.issuemanagementspringbootapplication.model.comment.toResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val issueRepository: IssueRepository,
) {

    @Transactional
    fun create(
        issueId: Long,
        userId: Long,
        username: String,
        request: CommentRequest,
    ): CommentResponse {

        val issue = issueRepository.findByIdOrNull(issueId) ?: throw ServerException.NotFoundException("No Issue")

        val comment = Comment(
            issue = issue,
            userId = userId,
            username = username,
            body = request.body,
        )

        issue.comments.add(comment)

        return commentRepository.save(comment).toResponse()
    }
}
