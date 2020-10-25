package seedu.address.model.quiz;

import static java.util.Objects.requireNonNull;

import seedu.address.model.quiz.exceptions.InvalidQuestionAttemptException;

/**
 * Represents a question in a quiz.
 */
public abstract class Question implements Comparable<Question> {

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

    public abstract int compareTo(Question f2);

    /**
     * Returns true if both questions of the same title have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two questions.
     */
    public boolean isSameQuestion(Question otherQuestion) {
        if (otherQuestion == this) {
            return true;
        }
        return otherQuestion != null
                && otherQuestion.getPrompt().equals(getPrompt());
    }
}
