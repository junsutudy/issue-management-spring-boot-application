package app.junsu.issuemanagementspringbootapplication.controller

import app.junsu.issuemanagementspringbootapplication.config.AuthUser
import app.junsu.issuemanagementspringbootapplication.model.IssueRequest
import app.junsu.issuemanagementspringbootapplication.model.IssueResponse
import app.junsu.issuemanagementspringbootapplication.service.IssueService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/issue")
class IssueController(
    @Autowired private val issueService: IssueService,
) {

    @PostMapping
    fun create(
        authUser: AuthUser,
        @RequestBody request: IssueRequest,
    ): IssueResponse {
        return issueService.create(
            userId = authUser.userId,
            request = request,
        )
    }
}