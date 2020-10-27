package seedu.address.logic.parser;

import seedu.address.logic.commands.ExitQuizCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ExitQuizCommandParser implements Parser<ExitQuizCommand> {

    @Override
    public ExitQuizCommand parse(String userInput) throws ParseException {
        return new ExitQuizCommand();
    }
}
