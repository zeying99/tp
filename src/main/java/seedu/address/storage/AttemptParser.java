package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import seedu.address.model.quiz.Attempt;
import seedu.address.model.quiz.Response;

public class AttemptParser {

    /**
     * Parses Attempt.
     */
    public Attempt parseAttempt(String attempt) {
        String[] userArguments = attempt.split("####");
        String stringTime = userArguments[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(stringTime, formatter);

        String stringResponses = userArguments[1];
        ArrayList<Response> responses = new ResponseParser().parseResponses(stringResponses);
        return new Attempt(responses, dateTime);
    }
}
