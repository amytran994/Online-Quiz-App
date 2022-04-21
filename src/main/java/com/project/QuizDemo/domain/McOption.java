package com.project.QuizDemo.domain;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "options")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class McOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "op_content")
    private String opContent;

    @Column
    private boolean isCorrect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private McQuestion question;

    @Transient
    private int questionId;
}
