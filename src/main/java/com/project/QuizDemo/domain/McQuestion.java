package com.project.QuizDemo.domain;
import lombok.*;
import com.project.QuizDemo.domain.Category;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class McQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "q_content")
    private String content;

    @OneToMany (mappedBy = "question",fetch = FetchType.LAZY)
    private List<McOption> options = new ArrayList<>();

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name="category_id" )
    private Category category;

    @Column(name = "disable")
    private boolean disable;

    @Transient
    private int categoryId;

    @Transient
    private int correct;

}
