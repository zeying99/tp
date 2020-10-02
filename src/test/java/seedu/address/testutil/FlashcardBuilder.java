package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.*;
import seedu.address.model.person.Flashcard;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Flashcard objects.
 */
public class FlashcardBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Email email;
    private Address address;
    private Set<Tag> tags;

    /**
     * Creates a {@code FlashcardBuilder} with the default details.
     */
    public FlashcardBuilder() {
        name = new Name(DEFAULT_NAME);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the FlashcardBuilder with the data of {@code flashcardToCopy}.
     */
    public FlashcardBuilder(Flashcard flashcardToCopy) {
        name = flashcardToCopy.getName();
        email = flashcardToCopy.getEmail();
        address = flashcardToCopy.getAddress();
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
    public FlashcardBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Flashcard build() {
        return new Flashcard(name, email, address, tags);
    }

}
