package app.junsu.issuemanagementspringbootapplication.controller.issue

import app.junsu.issuemanagementspringbootapplication.config.AuthUser
import app.junsu.issuemanagementspringbootapplication.domain.enums.IssueStatus
import app.junsu.issuemanagementspringbootapplication.model.issue.IssueRequest
import app.junsu.issuemanagementspringbootapplication.model.issue.IssueResponse
import app.junsu.issuemanagementspringbootapplication.service.issue.IssueService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
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

    @GetMapping("/{id}")
    fun get(
        authUser: AuthUser,
        @PathVariable(name = "id") id: Long,
    ): IssueResponse {
        return issueService.get(id)
    }

    @PutMapping("/{id}")
    fun edit(
        authUser: AuthUser,
        @PathVariable(name = "id") id: Long,
        @RequestBody request: IssueRequest,
    ): IssueResponse {
        return issueService.edit(
            userId = authUser.userId,
            id = id,
            request = request,
        )
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(
        authUser: AuthUser,
        @PathVariable(name = "id") id: Long,
    ) {
        return issueService.delete(
            id = id,
        )
    }

}