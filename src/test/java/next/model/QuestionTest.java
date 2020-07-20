package next.model;

import next.CannotDeleteException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static next.model.AnswerTest.newAnswer;
import static next.model.UserTest.newUser;
import static org.junit.Assert.assertTrue;

public class QuestionTest {
    public static Question newQuestion(String writer) {
        return new Question(1L, writer, "title", "contents", new Date(), 0);
    }

    public static Question newQuestion(long questionId, String writer) {
        return new Question(questionId, writer, "title", "contents", new Date(), 0);
    }

    @Test(expected = CannotDeleteException.class)
    public void canDelete_different_writer() throws Exception {
        User user = newUser("doyuni");
        Question question = newQuestion("spring");
        question.canDelete(user, new ArrayList<Answer>());
    }

    @Test
    public void canDelete_same_writer_noAnswer() throws Exception {
        User user = newUser("doyuni");
        Question question = newQuestion("doyuni");
        assertTrue(question.canDelete(user, new ArrayList<Answer>()));
    }

    @Test(expected = CannotDeleteException.class)
    public void canDelete_different_user_answer() throws Exception {
        String userId = "doyuni";
        List<Answer> answers = Arrays.asList(newAnswer(userId), newAnswer("spring"));
        newQuestion(userId).canDelete(newUser(userId), answers);
    }
}