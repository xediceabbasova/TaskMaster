package com.khadija.taskmaster.model

import com.khadija.taskmaster.model.TaskStatus.*
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
data class Task @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val title: String,
    val description: String,

    @Enumerated(EnumType.STRING)
    val taskStatus: TaskStatus = NEW,
    val createdDate: LocalDateTime = LocalDateTime.now(),
    val dueDate: LocalDate?,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @OneToMany(mappedBy = "task", cascade = [CascadeType.ALL])
    val comments: Set<Comment> = emptySet()
)
