package seedu.address.model.quiz;

import seedu.address.model.quiz.exceptions.InvalidQuestionAnswerException;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a response to a question in a quiz.
 */
public class Response {
    private String response;
    private Question question;
    private boolean isCorrect = false;

    public Response(String response, Question question) {
        requireAllNonNull(response, question);
        this.response = response;
        this.question = question;
    }
    /**
     * Executes the command and returns the result.
     * @return boolean representing whether response to its question is correct.
     * @throws InvalidQuestionAnswerException if the response is not valid.
     */
    public void markResponse() throws InvalidQuestionAnswerException {
        this.isCorrect = question.checkResponse(response);
    }

    public boolean getIsCorrect() {
        return this.isCorrect;
    }

    public Question getQuestion() {
        return this.question;
    }

}
