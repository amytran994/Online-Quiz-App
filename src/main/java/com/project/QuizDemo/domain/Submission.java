package com.project.QuizDemo.domain;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "submission")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String startTime;

    @Column
    private String endTime;

    @Column
    private int score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="quiz_id")
    private McQuiz quiz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;
}
