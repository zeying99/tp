package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEFINITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Definition;
import seedu.address.model.person.Flashcard;
import seedu.address.model.person.Title;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing flashcard in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the flashcard identified "
            + "by the index number used in the displayed flashcard list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_DEFINITION + "DEFINITION] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Flashcard: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This flashcard already exists in the address book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the flashcard in the filtered flashcard list to edit
     * @param editPersonDescriptor details to edit the flashcard with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Flashcard> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Flashcard flashcardToEdit = lastShownList.get(index.getZeroBased());
        Flashcard editedFlashcard = createEditedPerson(flashcardToEdit, editPersonDescriptor);

        if (!flashcardToEdit.isSameFlashcard(editedFlashcard) && model.hasPerson(editedFlashcard)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(flashcardToEdit, editedFlashcard);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedFlashcard));
    }

    /**
     * Creates and returns a {@code Flashcard} with the details of {@code flashcardToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Flashcard createEditedPerson(Flashcard flashcardToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert flashcardToEdit != null;
        Title updatedTitle = editPersonDescriptor.getTitle().orElse(flashcardToEdit.getTitle());
        Definition updatedDefinition = editPersonDescriptor.getDefinition().orElse(flashcardToEdit.getDefinition());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(flashcardToEdit.getTags());

        return new Flashcard(updatedTitle, updatedDefinition, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the flashcard with. Each non-empty field value will replace the
     * corresponding field value of the flashcard.
     */
    public static class EditPersonDescriptor {
        private Title title;
        private Definition definition;
        private Set<Tag> tags;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setTitle(toCopy.title);
            setDefinition(toCopy.definition);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, definition, tags);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setDefinition(Definition definition) {
            this.definition = definition;
        }

        public Optional<Definition> getDefinition() {
            return Optional.ofNullable(definition);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;
            return getTitle().equals(e.getTitle())
                    && getDefinition().equals(e.getDefinition())
                    && getTags().equals(e.getTags());
        }
    }
}
