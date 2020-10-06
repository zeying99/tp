package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Definition;
import seedu.address.model.person.Flashcard;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Flashcard objects.
 */
public class FlashcardBuilder {

    public static final String DEFAULT_NAME = "Alicia Pauline";
    public static final String DEFAULT_DEFINITION = "<DEFAULT DEFINITION> placeholder";

    private Name name;
    private Definition definition;
    private Set<Tag> tags;

    /**
     * Creates a {@code FlashcardBuilder} with the default details.
     */
    public FlashcardBuilder() {
        name = new Name(DEFAULT_NAME);
        definition = new Definition(DEFAULT_DEFINITION);
        tags = new HashSet<>();
    }

    /**
     * Initializes the FlashcardBuilder with the data of {@code flashcardToCopy}.
     */
    public FlashcardBuilder(Flashcard flashcardToCopy) {
        name = flashcardToCopy.getName();
        definition = flashcardToCopy.getDefinition();
        tags = new HashSet<>(flashcardToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withDefinition(String definition) {
        this.definition = new Definition(definition);
        return this;
    }

    public Flashcard build() {
        return new Flashcard(name, definition, tags);
    }

}
