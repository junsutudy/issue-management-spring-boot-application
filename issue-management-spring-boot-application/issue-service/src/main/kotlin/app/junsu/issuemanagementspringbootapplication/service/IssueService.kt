package app.junsu.issuemanagementspringbootapplication.service

import app.junsu.issuemanagementspringbootapplication.domain.Issue
import app.junsu.issuemanagementspringbootapplication.domain.IssueRepository
import app.junsu.issuemanagementspringbootapplication.domain.enums.IssueStatus
import app.junsu.issuemanagementspringbootapplication.exception.ServerException
import app.junsu.issuemanagementspringbootapplication.model.IssueRequest
import app.junsu.issuemanagementspringbootapplication.model.IssueResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class IssueService(
    private val issueRepository: IssueRepository,
) {

    @Transactional
    fun create(
        userId: Long,
        request: IssueRequest,
    ): IssueResponse {
        return IssueResponse(
            issueRepository.save(
                Issue(
                    userId = userId,
                    summary = request.summary,
                    description = request.description,
                    type = request.type,
                    priority = request.priority,
                    status = request.status,
                ),
            )
        )
    }

    @Transactional(readOnly = true)
    fun getAll(
        status: IssueStatus,
    ): List<IssueResponse>? {
        return issueRepository.findAllByStatusOrderByCreatedAtAsc(status)?.map { IssueResponse(it) }
    }

    @Transactional(readOnly = true)
    fun get(
        id: Long,
    ): IssueResponse {
        return IssueResponse(
            issueRepository.findByIdOrNull(id) ?: throw ServerException.NotFoundException("No Issue")
        )
    }

    @Transactional
    fun edit(userId: Long, id: Long, request: IssueRequest): IssueResponse {
        val issue = issueRepository.findByIdOrNull(id) ?: throw ServerException.NotFoundException("No Issue")
        return IssueResponse(
            issueRepository.save(
                issue.apply {
                    summary = request.summary
                    description = request.description
                    this.userId = userId
                    type = request.type
                    priority = request.priority
                    status = request.status
                },
            ),
        )
    }
}
