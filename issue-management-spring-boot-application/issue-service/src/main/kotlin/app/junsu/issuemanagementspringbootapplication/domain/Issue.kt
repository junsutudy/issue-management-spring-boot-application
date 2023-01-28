package app.junsu.issuemanagementspringbootapplication.domain

import app.junsu.issuemanagementspringbootapplication.domain.enums.IssuePriority
import app.junsu.issuemanagementspringbootapplication.domain.enums.IssueStatus
import app.junsu.issuemanagementspringbootapplication.domain.enums.IssueType
import jakarta.persistence.*

@Entity
@Table(name = "issue")
class Issue(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @Column(name = "user_id") var userId: Long,
    @Column(name = "summary") var summary: String,
    @Column(name = "description") var description: String,
    @Column(name = "type") @Enumerated(EnumType.STRING) var type: IssueType,
    @Column(name = "priority") @Enumerated(EnumType.STRING) var priority: IssuePriority,
    @Column(name = "status") @Enumerated(EnumType.STRING) var status: IssueStatus,
) : BaseTimeEntity()
