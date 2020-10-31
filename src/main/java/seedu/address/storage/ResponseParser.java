package seedu.address.storage;

import java.util.ArrayList;

import seedu.address.model.quiz.Question;
import seedu.address.model.quiz.Response;

public class ResponseParser {

    /**
     * Returns list of responses
     */
    public ArrayList<Response> parseResponses(String stringResponse) {
        String[] stringResponses = stringResponse.split(" \\| ");
        ArrayList<Response> responses = new ArrayList<>();
        for (String s : stringResponses) {
            responses.add(parseResponseFields(s));
        }
        return responses;
    }

    private Response parseResponseFields(String stringResponse) {
        String[] responseFields = stringResponse.split("###");
        String response = responseFields[0];
        boolean isCorrect = responseFields[1].equals("true");
        Question question = new QuestionParser().parseQuestion(responseFields[2]);
        return new Response(response, question, isCorrect);
    }
}
