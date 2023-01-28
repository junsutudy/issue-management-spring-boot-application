package app.junsu.issuemanagementspringbootapplication.domain.comment.entity

import app.junsu.issuemanagementspringbootapplication.domain.BaseTimeEntity
import app.junsu.issuemanagementspringbootapplication.domain.issue.entity.Issue
import jakarta.persistence.*

@Entity
@Table(name = "comment")
data class Comment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "issue_id") val issue: Issue,
    @Column(name = "user_id") val userId: Long,
    @Column(name = "username") val username: String,
    @Column(name = "body") var body: String,
) : BaseTimeEntity()
