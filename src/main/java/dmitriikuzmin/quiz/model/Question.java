package dmitriikuzmin.quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "difficulty",
        "category",
        "question",
        "correct_answer",
        "incorrect_answers"
})

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty("type")
    @NonNull
    private String type;

    @JsonProperty("difficulty")
    @NonNull
    private String difficulty;

    @JsonProperty("category")
    @NonNull
    private String category;

    @JsonProperty("question")
    @NonNull
    private String question;

    @JsonProperty("correct_answer")
    @NonNull
    private String correctAnswer;

    @JsonProperty("incorrect_answers")
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "incorrectAnswers", joinColumns = @JoinColumn(name = "question_id"))
    private List<String> incorrectAnswers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Quiz quiz;

    private QuestionResult result = QuestionResult.NO_ANSWER;
}
