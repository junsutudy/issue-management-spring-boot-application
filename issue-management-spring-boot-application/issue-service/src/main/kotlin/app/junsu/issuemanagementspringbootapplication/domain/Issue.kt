package app.junsu.issuemanagementspringbootapplication.domain

import app.junsu.issuemanagementspringbootapplication.domain.enums.IssuePriority
import app.junsu.issuemanagementspringbootapplication.domain.enums.IssueStatus
import app.junsu.issuemanagementspringbootapplication.domain.enums.IssueType
import jakarta.persistence.*

@Entity
@Table(name = "issue")
class Issue(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @Column var userId: Long,
    @Column var summary: String,
    @Column var description: String,
    @Column @Enumerated(EnumType.STRING) var type: IssueType,
    @Column @Enumerated(EnumType.STRING) var priority: IssuePriority,
    @Column @Enumerated(EnumType.STRING) var status: IssueStatus,
) : BaseTimeEntity()
