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
   * [**`sort`** : Sorting all flashcards.](#sorting-all-flashcards--sort)
   * [**`edit`** : Editing a flashcard.](#editing-a-flashcard--edit)
   * [**`find`** : Locating flashcards by name/tag/priority.](#locating-flashcards-by-nametagpriority-find)
   * [**`delete`** : Deleting a flashcard.](#deleting-a-flashcard--delete)
   * [**`flip`** : Flipping a flashcard.](#flipping-a-flashcard--flip)
   * [**`clear`** : Clearing all flashcards.](#clearing-all-entries--clear)
   * [**`exit`** : Exiting the program.](#exiting-the-program--exit)
   * [Saving the data.](#saving-the-data)
   * [Archiving data files [coming in v2.0].](#archiving-data-files-coming-in-v20)
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

   * **`add`**`n/Insertion Sort d/Worse case: O(n^2)` : Adds a flashcard named `Insertion Sort` to the DSAce folder.
   
   * **`edit`**`1 n/BubbleSort d/Average case: O(n^2)` : Edits the name and definition of the 1st flashcard in
    current list to be `BubbleSort` and `Average case: O(n^2)` respectively.
   
   * **`sort`**`desc` : Sorts all flashcards by priority in descending order.
   
   * **`find`**`n/Trees` : Finds flashcards with names containing the keyword `Trees`

   * **`delete`**`3` : Deletes the 3rd flashcard shown in the current list.
   
   * **`flip`**`2` : Flips the 2nd flashcard shown in the current list.

   * **`clear`** : Deletes all flashcards.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

1. All sample data and flashcards created by the user will be stored in the `DSAce` folder.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user. <br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Sorting`.

* Parameters can be in any order. <br>
  e.g. if the command specifies `n/NAME d/DEFINITION`, `d/DEFINITION n/NAME` is also acceptable.

</div>

### Viewing help : `help`

Shows a message explaining the features of the app, and the format of the command associated with each feature.

![help message](images/helpMessage.png)

Format: `help`


### Adding a flashcard : `add`

Adds a flashcard to the default DSAce folder.

Format: `add n/NAME d/DEFINITION [t/TAGS] [p/PRIORITY]`

Examples:
* `add n/Bellman Ford Search d/runtime: O(VE) p/high`
* `add n/Bubble Sort d/runtime: O(n^2)`

### Listing all flashcards : `list`

Shows a list of all flashcards in the DSAce folder.

Format: `list`

### Sorting all flashcards : `sort`

Sorts all flashcards in the DSAce folder by priority.

Format: `sort [ORDER]`

* Order is specified as either `asc` or `desc`.
* The order is case-insensitive. e.g `ASC` and `AsC` will both sort by ascending.
* If no order is specified, default order is ascending.

Examples:
* `sort ASC` Sorts all flashcards by priority in ascending order.
* `sort desc` Sorts all flashcards by priority in descending order.

### Editing a flashcard : `edit`

Edits an existing flashcard in the DSAce folder.

Format: `edit INDEX [n/NAME] [d/DEFINITION] [t/TAGS] [p/PRIORITY]`

* Edits the flashcard at the specified `INDEX`. 
* The index refers to the index number associated with the edited flashcard, as shown in the displayed flashcard list.
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing t/ without specifying any tags after it.

Examples:
* `list` followed by `edit 1 n/BubbleSort d/Average case: O(n^2)` Edits the name and definition of the 1st flashcard to
 be `BubbleSort
` and `Average case: O(n^2)` respectively.
* `list` followed by `edit 2 n/SelectionSort t/` Edits the name of the 2nd flashcard to be `SelectionSort` and clears
 all existing tags.
* `list` followed by `edit 3 p/high` Edits the priority of the 3rd flashcard to be `high`.

### Locating flashcards by name/tag/priority: `find`

Finds flashcards with names, tags or priorities containing any of the given keywords.

Format: find `[n/KEYWORD​S]​` `[t/KEYWORD​]​` `[p/KEYWORD​]​` 

* The search is case-insensitive. e.g `sort` will match `Sort`
* The order of the keywords does not matter. e.g. `runtime sort` will match `sort runtime`
* Names, tags or priorities will be searched according to input prefixes.
* Only full words will be matched e.g. `sort` will not match `sorting`
* Flashcards matching at least one keyword will be returned (i.e. `OR` search). e.g. `graph algorithm` will return
 `Directed Acyclic Graph` and `Dijkstra’s Algorithm`
 
Examples:

* `find n/Trees` returns `Balanced Trees` and `Range Trees`
* `find n/graph algorithm` returns `Directed Acyclic Graph` and `Dijkstra’s Algorithm`
* `find t/graph sort` returns flashcards with either `graph` or `sort` in tags.
* `find p/high` returns flashcards with `high` priority.

### Deleting a flashcard : `delete`

Deletes the specified flashcard from DSAce folder.

Format: `delete INDEX`

* Deletes the flashcard at the specified `INDEX`. 
* The index refers to the index number associated with the deleted flashcard, as shown in the displayed flashcard list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd flashcard in the folder.

### Flipping a flashcard : `flip`

Flips the specified flashcard from DSAce folder.

Format: `flip INDEX`

* Flips the flashcard at the specified `INDEX`. 
* The index refers to the index number associated with the flipped flashcard, as shown in the displayed flashcard list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `flip 2` flips the 2nd flashcard in the folder.

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
**Add** | `add n/NAME d/DEFINITION` <br> e.g., `add n/Bellman-Ford Search d/runtime: O(VE)`
**Clear** | `clear`
**Sort** | `sort [ORDER]` <br> e.g., `sort ASC`
**Delete** | `delete INDEX` <br> e.g., `delete 3`
**Flip** | `flip INDEX` <br> e.g., `flip 2`
**Edit** | `edit INDEX [n/NAME] [d/DEFINITION] [t/TAGS] [p/PRIORITY]` <br> e.g., `edit 1 n/BubbleSort d/Average case: O(n^2) p/low`
**Find** | `find [n/KEYWORDS] [t/KEYWORD​S] [p/KEYWORD​S]​` <br> e.g., `find n/BellmanFord Search`
**List** | `list`
**Help** | `help`
