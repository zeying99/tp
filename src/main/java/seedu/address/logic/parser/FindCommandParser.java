package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Priority;
import seedu.address.model.person.PriorityContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;
import seedu.address.model.person.Title;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_TAG, PREFIX_PRIORITY);

        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            Title keyword = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(keyword.fullTitle)));
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            Tag keyword = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get());
            return new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList(keyword.tagName)));
        }
        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            Priority keyword = ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get());
            return new FindCommand(new PriorityContainsKeywordsPredicate(Arrays.asList(keyword.priority)));
        }
        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

}
