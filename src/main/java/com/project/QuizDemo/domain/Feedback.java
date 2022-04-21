package com.project.QuizDemo.domain;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "feedback")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String content;

    @Column
    private int rating;
}
