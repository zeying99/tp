package seedu.address.model.quiz;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Objects;

import seedu.address.model.quiz.exceptions.InvalidQuestionAttemptException;

/**
 * Represents a MCQ question. It is assumed that the options are numerically indexed, starting from 1.
 */
public class Mcq extends Question {

    private int answer;
    private ArrayList<String> options;

    /**
     * Every field must be present and not null.
     */
    public Mcq(String prompt, int answer, ArrayList<String> options) {
        super(prompt);
        requireAllNonNull(answer, options);
        this.answer = answer;
        this.options = options;
        assert(isValidResponse(answer));
    }

    public int getAnswer() {
        return answer;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    /**
     * Returns the question prompt and options, represented in an appropriate String format.
     */
    public String getQuestion() {
        String question = "";
        question += prompt + " Options: ";
        for (int i = 0; i < options.size(); i++) {
            question += Integer.toString(i + 1) + ")";
            question += options.get(i);
            if (i < options.size() - 1) {
                question += ", ";
            }
        }
        return question;
    }

    /**
     * Checks whether the attempt is a valid option.
     */
    public boolean isValidResponse(int attempt) {
        return (attempt >= 1) && (attempt <= options.size());
    }

    /**
     * Checks whether the response is correct.
     * @param response user response
     * @return true if the response is correct and false otherwise
     * @throws InvalidQuestionAttemptException if the response is not valid
     */
    public boolean checkResponse(String response) throws InvalidQuestionAttemptException {
        try {
            int attempt = Integer.parseInt(response);
            if (!isValidResponse(attempt)) {
                throw new InvalidQuestionAttemptException("Response is not a valid option.");
            }
            return attempt == answer;
        } catch (NumberFormatException e) {
            throw new InvalidQuestionAttemptException("Response is not a valid option.");
        }
    }
    @Override
    public int compareTo(Question f2) {
        return prompt.compareTo(f2.prompt);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof Mcq) {
            Mcq mcq = (Mcq) (other);
            boolean isEqual = (mcq.prompt.equals(prompt)) && (mcq.answer == answer);
            for (int i = 0; i < options.size(); i++) {
                if (!options.get(i).equals(mcq.options.get(i))) {
                    isEqual = false;
                }
            }
            return isEqual;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(prompt, answer, options);
    }

    @Override
    public String toString() {
        return getQuestion();
    }
}
