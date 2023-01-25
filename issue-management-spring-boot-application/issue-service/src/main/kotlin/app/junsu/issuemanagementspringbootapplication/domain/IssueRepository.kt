package app.junsu.issuemanagementspringbootapplication.domain

import org.springframework.data.jpa.repository.JpaRepository

interface IssueRepository: JpaRepository<Issue, Long>
