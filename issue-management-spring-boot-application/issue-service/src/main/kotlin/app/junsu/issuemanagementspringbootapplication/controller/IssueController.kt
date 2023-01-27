package app.junsu.issuemanagementspringbootapplication.controller

import app.junsu.issuemanagementspringbootapplication.config.AuthUser
import app.junsu.issuemanagementspringbootapplication.domain.enums.IssueStatus
import app.junsu.issuemanagementspringbootapplication.model.IssueRequest
import app.junsu.issuemanagementspringbootapplication.model.IssueResponse
import app.junsu.issuemanagementspringbootapplication.service.IssueService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

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

    @GetMapping
    fun getAll(
        authUser: AuthUser,
        @RequestParam(
            required = false, defaultValue = "TODO",
        ) status: IssueStatus,
    ): List<IssueResponse>? {
        return issueService.getAll(status)
    }
}