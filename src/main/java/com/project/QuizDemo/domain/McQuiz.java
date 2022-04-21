package com.project.QuizDemo.domain;

import java.util.List;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "quiz")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class McQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "quiz_question",
            joinColumns = {@JoinColumn(name = "quiz_id")},
            inverseJoinColumns = {@JoinColumn(name = "question_id")})
    private List<McQuestion> questionList;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "quiz_question",
                joinColumns = {@JoinColumn(name = "quiz_id")},
                inverseJoinColumns = {@JoinColumn(name = "selected_option_id")})
    private List<McOption> selectectList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

}
