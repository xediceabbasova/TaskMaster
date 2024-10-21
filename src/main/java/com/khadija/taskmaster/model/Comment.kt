package com.khadija.taskmaster.model

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
data class Comment @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val content: String,

    @CreatedDate
    @Column(nullable = false, updatable = false)
    val createdDate: LocalDateTime? = null,

    @ManyToOne
    @JoinColumn(name = "task_id")
    val task: Task,

    @ManyToOne
    @JoinColumn(name = "author_id")
    val author: User
)