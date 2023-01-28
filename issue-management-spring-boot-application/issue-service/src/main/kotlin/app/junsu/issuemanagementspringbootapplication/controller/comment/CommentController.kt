package app.junsu.issuemanagementspringbootapplication.controller.comment

import app.junsu.issuemanagementspringbootapplication.config.AuthUser
import app.junsu.issuemanagementspringbootapplication.model.comment.CommentRequest
import app.junsu.issuemanagementspringbootapplication.model.comment.CommentResponse
import app.junsu.issuemanagementspringbootapplication.service.comment.CommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/issue/{issue-id}/comment")
class CommentController(
    @Autowired private val commentService: CommentService,
) {

    @PostMapping
    fun create(
        authUser: AuthUser,
        @PathVariable("issue-id") issueId: Long,
        @RequestBody request: CommentRequest,
    ): CommentResponse {
        return commentService.create(
            issueId = issueId,
            userId = authUser.userId,
            username = authUser.username,
            request = request,
        )
    }

    @PutMapping("/{comment-id}")
    fun edit(
        authUser: AuthUser,
        @PathVariable("issue-id") issueId: Long,
        @PathVariable("comment-id") commentId: Long,
        @RequestBody request: CommentRequest,
    ): CommentResponse? {
        return commentService.edit(
            issueId = issueId,
            userId = authUser.userId,
            request = request,
        )
    }

    @DeleteMapping("/{comment-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(
        authUser: AuthUser,
        @PathVariable("issue-id") issueId: Long,
        @PathVariable("comment-id") commentId: Long,
    ) {
        return commentService.delete(
            issueId = issueId,
            commentId = commentId,
            userId = authUser.userId,
        )
    }
}
