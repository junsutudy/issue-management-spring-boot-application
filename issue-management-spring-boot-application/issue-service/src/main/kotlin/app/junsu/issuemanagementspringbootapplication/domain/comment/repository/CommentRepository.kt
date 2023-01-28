package app.junsu.issuemanagementspringbootapplication.domain.comment.repository

import app.junsu.issuemanagementspringbootapplication.domain.comment.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByIdAndUserId(issueId: Long, userId: Long): Comment?
}