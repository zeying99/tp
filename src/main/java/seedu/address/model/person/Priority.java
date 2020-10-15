package seedu.address.model.person;

/**
 * Represents the priority level of a Flashcard in the flashcard folder.
 */
public enum Priority {
    LOW("low"),
    MEDIUM("medium"),
    HIGH("high"),
    NULL("");

    public static final String MESSAGE_CONSTRAINTS =
            "Flashcard can only be labelled as high, medium and low priority";
    public final String priority;

    Priority(String priority) {
        this.priority = priority.toLowerCase();
    }

    public static boolean isValid(String priority) {
        return priority.equals("low") || priority.equals("medium") || priority.equals("high");
    }

    public static Priority identifyPriority(String priority) {
        Priority priorityEnum = Priority.NULL;
        if (priority.equals("low")) {
            priorityEnum = Priority.LOW;
        } else if (priority.equals("medium")) {
            priorityEnum = Priority.MEDIUM;
        } else if (priority.equals("high")) {
            priorityEnum = Priority.HIGH;
        }
        return priorityEnum;
    }


    @Override
    public String toString() {
        return this.priority;
    }
}