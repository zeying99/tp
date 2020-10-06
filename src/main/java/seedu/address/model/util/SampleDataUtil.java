package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Definition;
import seedu.address.model.person.Flashcard;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Flashcard[] getSamplePersons() {
        return new Flashcard[] {
            new Flashcard(new Name("Alex Yeoh"),
                new Definition("<DEFINITION_1> placeholder"),
                getTagSet("friends")),
            new Flashcard(new Name("Bernice Yu"),
                new Definition("<DEFINITION_2> placeholder"),
                getTagSet("colleagues", "friends")),
            new Flashcard(new Name("Charlotte Oliveiro"),
                new Definition("<DEFINITION_3> placeholder"),
                getTagSet("neighbours")),
            new Flashcard(new Name("David Li"),
                new Definition("<DEFINITION_4> placeholder"),
                getTagSet("family")),
            new Flashcard(new Name("Irfan Ibrahim"),
                new Definition("<DEFINITION_5> placeholder"),
                getTagSet("classmates")),
            new Flashcard(new Name("Roy Balakrishnan"),
                new Definition("<DEFINITION_6> placeholder"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Flashcard sampleFlashcard : getSamplePersons()) {
            sampleAb.addPerson(sampleFlashcard);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
