package seedu.address.logic.commands;

import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class ToggleWindowsCommand extends Command {
    public static final String COMMAND_WORD = "toggle";
    public static final String MESSAGE_USAGE = "Please type 'toggle window' fully";
    public static final String MESSAGE_ENTER_ACKNOWLEDGEMENT = "Showing both flashcards and quizs.";
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_ENTER_ACKNOWLEDGEMENT, true, false, false, false, false);
    }

}
