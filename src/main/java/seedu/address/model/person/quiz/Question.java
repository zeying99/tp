package seedu.address.model.person.quiz;

import static java.util.Objects.requireNonNull;

import seedu.address.model.person.exceptions.InvalidQuestionAttemptException;

/**
 * Represents a question in a quiz.
 */
public abstract class Question {

    protected String prompt;

    /**
     * Every field must be present and not null.
     */
    public Question(String prompt) {
        requireNonNull(prompt);
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }

    /**
     * Returns the question prompt and options, represented in an appropriate String format.
     */
    public abstract String getQuestion();

    /**
     * Checks whether the response is correct.
     * @param response user response
     * @return true if the response is correct and false otherwise
     * @throws InvalidQuestionAttemptException if the response is not valid
     */
    public abstract boolean checkResponse(String response) throws InvalidQuestionAttemptException;
}
