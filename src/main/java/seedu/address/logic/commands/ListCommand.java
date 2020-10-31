package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_FLASHCARD;

import seedu.address.model.Model;

import java.io.IOException;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all flashcards";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        try {
            model.tester();
        } catch (IOException e) {
            System.out.println("Uh oh.");
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_FLASHCARD);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
