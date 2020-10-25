package seedu.address.model.person.exceptions;

/**
 * Signals that an attempt for a particular question is not a valid option.
 */
public class InvalidQuestionAttemptException extends Exception {

    public InvalidQuestionAttemptException(String mesg) {
        super(mesg);
    }
}
