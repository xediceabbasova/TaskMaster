package com.khadija.taskmaster.model

import jakarta.persistence.*

@Entity
@Table(name = "usr")
data class User @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true)
    val username: String,

    @Column(unique = true)
    val email: String,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    val tasks: Set<Task> = emptySet(),

    @OneToMany(mappedBy = "author", cascade = [CascadeType.ALL])
    val comments: Set<Comment> = emptySet()
)
