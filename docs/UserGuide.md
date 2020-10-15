---
layout: page
title: User Guide
---

DSAce is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, DSAce can get your revision tasks done faster than traditional GUI apps.

## Table of Contents
* [Quick start](#quick-start)
* [Features](#features)
   * [**`help`** : Viewing help.](#viewing-help--help)
   * [**`add`** : Adding a flashcard.](#adding-a-flashcard--add)
   * [**`list`** : Listing all flashcards.](#listing-all-flashcards--list)
   * [**`delete`** : Deleting a flashcard.](#deleting-a-flashcard--delete)
   * [**`clear`** : Clearing all flashcards.](#clearing-all-entries--clear)
   * [**`exit`** : Exiting the program.](#exiting-the-program--exit)
   * [Saving the data.](#saving-the-data)
   * [Archiving data files [coming in v2.0].](#archiving-data-files-coming-in-v20)
   * [**`edit`** : Editing a flashcard [coming in v2.0].](#editing-a-flashcard--edit-coming-in-v20)
   * [**`find`** : Locating flashcard by title/content [coming in v2.0].](#locating-a-flashcard--find-coming-in-v20)
* [FAQ](#faq)
* [Command Summary](#command-summary)


--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `dsace.jar` from [here](https://github.com/AY2021S1-CS2103-T14-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your DSAce.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all flashcards.

   * **`add`**`t/Insertion Sort c/Worse case: O(n^2)` : Adds a flashcard named `Insertion Sort` to the DSAce folder.

   * **`delete`**`3` : Deletes the 3rd flashcard shown in the current list.

   * **`clear`** : Deletes all flashcards.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

1. All sample data and flashcards created by the user will be stored in the `DSAce` folder.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user. <br>
  e.g. in `add t/TITLE`, `TITLE` is a parameter which can be used as `add t/Sorting`.

* Parameters can be in any order. <br>
  e.g. if the command specifies `t/TITLE c/CONTENT`, `c/CONTENT t/TITLE` is also acceptable.

</div>

### Viewing help : `help`

Shows a message explaining the features of the app, and the format of the command associated with each feature.

![help message](images/helpMessage.png)

Format: `help`


### Adding a flashcard : `add`

Adds a flashcard to the default DSAce folder.

Format: `add t/TITLE c/CONTENT`

Examples:
* `add t/Bellman-Ford Search c/runtime: O(VE)`
* `add t/Bubble Sort c/runtime: O(n^2)`

### Listing all flashcards : `list`

Shows a list of all flashcards in the DSAce folder.

Format: `list`

### Deleting a flashcard : `delete`

Deletes the specified flashcard from DSAce folder.
>>>>>>> upstream/master

Format: `delete INDEX`

* Deletes the flashcard at the specified `INDEX`. 
* The index refers to the index number associated with the deleted flashcard, as shown in the displayed flashcard list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd flashcard in the folder.

### Clearing all entries : `clear`

Clears all entries from the flashcards folder.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data
DSAce data is saved in the DSAce folder automatically after any command that changes the data is entered. There is no need to save the data manually.

### FAQ
Q: How do I transfer my data to another Computer? <br>
A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous DSAce home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add t/TITLE c/CONTENT` <br> e.g., `add t/Bellman-Ford Search c/runtime: O(VE)`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** [v 2.0] | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​` <br> e.g., `edit 2 n/James Lee e/jameslee@example.com`
**Find** [v 2.0] | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find Bellman-Ford Search`
**List** | `list`
**Help** | `help`
