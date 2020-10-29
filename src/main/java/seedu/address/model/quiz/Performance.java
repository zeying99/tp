package seedu.address.model.quiz;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a history of past attempts.
 */
public class Performance {
    private ArrayList<Attempt> attempts;

    /**
     * Constructor of Performance called when no past attempts exists
     */
    public Performance() {
        this.attempts = new ArrayList<>();
    }
    /**
     * Constructor of Performance called when local cache of attempts exists
     */
    public Performance(ArrayList<Attempt> attempts) {
        requireNonNull(attempts);
        this.attempts = attempts;
    }


    /**
     * Calculates statistics of all past attempts.
     * @return string representing statistics of all attempts
     */
    public String getStatistics() {
        String res = "";
        Iterator<Attempt> itr = attempts.iterator();
        while (itr.hasNext()) {
            Attempt attempt = itr.next();
            res += String.format("Attempt recorded at: %s\n", getFormattedTimestamp(attempt.getTimestamp()));
            int score = attempt.calculateScore();
            int numOfResp = attempt.getNumOfResponses();
            res += String.format("%d/%d\n", score, numOfResp);
        }
        return res;
    }

    /**
     * Converts timestamp to formatted string.
     * @param timestamp LocalDateTime object
     * @return string representing formatted timestamp
     */
    private String getFormattedTimestamp(LocalDateTime timestamp) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return dtf.format(timestamp);
    }
}
