package com.khadija.taskmaster.model

import com.khadija.taskmaster.model.TaskStatus.*
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
data class Task @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val title: String,
    val description: String,

    @Enumerated(EnumType.STRING)
    val taskStatus: TaskStatus = NEW,

    @CreatedDate
    @Column(nullable = false, updatable = false)
    val createdDate: LocalDateTime? = null,
    val dueDate: LocalDateTime?,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @OneToMany(mappedBy = "task", cascade = [CascadeType.ALL])
    val comments: Set<Comment> = emptySet()
)
