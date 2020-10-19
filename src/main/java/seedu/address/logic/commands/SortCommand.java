package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Sorts the flashcards in the flashcard list in order of priority.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredPersonList();
        return new CommandResult("Flashcards sorted.");
    }
}
