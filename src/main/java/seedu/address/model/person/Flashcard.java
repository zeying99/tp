package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Flashcard in the flashcard folder.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Flashcard {

    // Identity fields
    private final Title title;

    // Data fields
    private final Definition definition;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Flashcard(Title title, Definition definition, Set<Tag> tags) {
        requireAllNonNull(title, definition, tags);
        this.title = title;
        this.definition = definition;
        this.tags.addAll(tags);
    }

    public Title getTitle() {
        return title;
    }


    public Definition getDefinition() {
        return definition;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both flashcards of the same title have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameFlashcard(Flashcard otherFlashcard) {
        if (otherFlashcard == this) {
            return true;
        }
        return otherFlashcard != null
                && otherFlashcard.getTitle().equals(getTitle());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Flashcard)) {
            return false;
        }
        Flashcard otherFlashcard = (Flashcard) other;
        return otherFlashcard.getTitle().equals(getTitle())
                && otherFlashcard.getDefinition().equals(getDefinition())
                && otherFlashcard.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, definition, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append(" Address: ")
                .append(getDefinition())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
