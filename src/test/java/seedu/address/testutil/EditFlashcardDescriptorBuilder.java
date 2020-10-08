package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Definition;
import seedu.address.model.person.Flashcard;
import seedu.address.model.person.Title;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditFlashcardDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditFlashcardDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditFlashcardDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code flashcard}'s details
     */
    public EditFlashcardDescriptorBuilder(Flashcard flashcard) {
        descriptor = new EditPersonDescriptor();
        descriptor.setTitle(flashcard.getTitle());
        descriptor.setDefinition(flashcard.getDefinition());
        descriptor.setTags(flashcard.getTags());
    }

    /**
     * Sets the {@code Title} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withName(String name) {
        descriptor.setTitle(new Title(name));
        return this;
    }

    /**
     * Sets the {@code Definition} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withDefinition(String definition) {
        descriptor.setDefinition(new Definition(definition));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditFlashcardDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
