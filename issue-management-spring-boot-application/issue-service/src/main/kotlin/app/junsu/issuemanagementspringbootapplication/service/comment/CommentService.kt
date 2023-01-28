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

    @Transactional
    fun edit(
        issueId: Long,
        userId: Long,
        request: CommentRequest,
    ): CommentResponse? {
        return commentRepository.findByIdAndUserId(
            issueId = issueId,
            userId = userId,
        )?.run {
            body = request.body
            commentRepository.save(this).toResponse()
        }
    }

    @Transactional
    fun delete(
        issueId: Long,
        commentId: Long,
        userId: Long,
    ) {

        val issue = issueRepository.findByIdOrNull(issueId) ?: throw ServerException.NotFoundException("No Issue")

        commentRepository.findByIdAndUserId(
            issueId = issueId,
            userId = userId,
        )?.let { comment ->
            issue.comments.remove(comment)
        }
    }
}
