package seedu.address.model.person;

/**
 * Represents the priority level of a Flashcard in the flashcard folder.
 */
public enum Priority {
    LOW("low"),
    MEDIUM("medium"),
    HIGH("high");

    private final String priority;

    private Priority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return this.priority;
    }
}
