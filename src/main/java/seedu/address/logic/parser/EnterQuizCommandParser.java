package seedu.address.logic.parser;

import seedu.address.logic.commands.EnterQuizCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class EnterQuizCommandParser implements Parser<EnterQuizCommand> {

    @Override
    public EnterQuizCommand parse(String userInput) throws ParseException {
        return new EnterQuizCommand();
    }
}
