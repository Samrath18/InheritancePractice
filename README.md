# Created 2023/04/12

# Sword Creator

**SinghCharacterCreator** is a Java-based GUI application that allows users to create and customize characters with various attributes. This program provides an interactive interface for selecting and customizing different aspects of a character, including its element, metal, hilt, colour, and more.

## Features

- **Element Selection**: Users can choose from three elemental options (Fire, Water, Storm), each with their own set of custom attributes.
- **Dynamic Visibility**: Depending on the element selected, different attributes become visible and editable. For example, selecting "Fire" will reveal fire-specific attributes, while selecting "Water" will show water-specific options.
- **Data Entry**: Users can enter a character's name and select values for attributes like metal, hilt, and colour from dropdown menus.
- **Saving Character Data**: Once all required fields are filled, users can save the character data to a file (`SavedSwords.txt`) for later retrieval.
- **Input Validation**: Ensures that all required fields are selected before saving.
- **Editable Character Data**: Users can edit, view, or delete previously created characters.

## GUI Components

- **JComboBox**: Used for selecting elements (Fire, Water, Storm), metal, hilt, and color.
- **JTextField**: Used for entering the character's name.
- **JLabel**: Displays instructions, error messages, and character data status.
- **JButton**: Allows the user to save, cancel, or perform other actions.

## Functionality

1. **Add Character**: Users can create a new character by selecting all required attributes and saving them to a file.
2. **Edit Character**: Users can modify the details of an existing character.
3. **View Character**: Users can view previously saved characters and their attributes.
4. **Delete Character**: Users can delete a character from the list.

## Technologies Used

- Java
- Swing (for building the graphical user interface)
- File I/O (to save character data to a text file)
