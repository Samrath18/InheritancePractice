/*
 * Samrath Singh
 * ICS4UO-B
 * Inheritance
 * 2023/04/12
 * This program utilizes inheritance to create different objects.
 * This program allows the user add a new sword, edit existing swords, views existing swords, and delete existing swords.
 * The program utilizes comboBoxes to avoid typo errors and includes many different comboBoxes for the different selection choices.
 * Add: Line 486, 846, 916
 * Edit: Line 500, 524
 * Delete: Line 674, 706
 * View: Line 757
 * text file: SavedSwords.txt
 * inheritance: Line 45
 * array of records: Line 67, 250, 236, 350, 388, 432
 * creation and usage of objects: Lines 241, 268, 280, 293, 351, 357, 363, 373, 392, 402, 413
 */

// Import statements
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class SinghCharacterCreator extends JFrame {//Start of SinghCharacterCreator class


	// Declare variables
	private static JPanel contentPane, panel;
	private static JButton bAdd, bEdit, bDelete, bView, bSave, bCancel, bConfirmDelete, bConfirmEdit;
	private static JComboBox elementBox, metalBox, hiltBox, colourBox, fireBox, waterBox, stormBox, selectBox;
	private static JLabel lblElement, lblMetal, lblHilt, lblColour, lblFire, lblWater, lblStorm, lblName, lblResult, lblSelect;
	private static JTextField nameField;

	private static String elementList[] = { "Fire", "Water", "Storm" };
	private static String colourList[] = { "Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Black", "White" }; // this will instantiate the array and datafill at the same time
	private static String hiltList[] = { "Rapier Hilt", "Cross Hilt", "L-Shaped Hilt", "Ornate Hilt", "Piston Style Hilt", "Basket Hilt" }; // this will instantiate the array and datafill at the same time
	private static String metalList[] = { "Carbon Steel", "Stainless Steel", "Alloy Steel", "Titanium Alloys", "Obsidian" }; // this will instantiate the array and datafill at the same time
	private static String fireList[] = { "Sun Slash", "Lava Strike", "Heat Storm" }; // this will instantiate the array and datafill at the same time
	private static String waterList[] = { "Tidal Blast", "Projectile Water", "Hurricane Slash" }; // this will instantiate the array and datafill at the same time
	private static String stormList[] = { "Lightning Strike", "Rising Storm", "Electric Touch" }; // this will instantiate the array and datafill at the same time
	private static String selectList[];
	static String line;
	static String[] lineData;

	// Declare arraylist
	private static ArrayList<SinghBaseSword> charactersList;

	/**
	 * Main class.
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SinghCharacterCreator frame = new SinghCharacterCreator();
			frame.setVisible(true);
			initialize(); // Method call
			actionListeners(); // Method call
			ReadData(); // Method call

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public SinghCharacterCreator() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 363);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(241, 21, 1, 1);
		contentPane.add(panel);
		panel.setLayout(null);

		// JButtons
		bAdd = new JButton("Add");
		bAdd.setToolTipText("Create a new sword");
		bAdd.setBounds(10, 11, 131, 23);
		contentPane.add(bAdd);

		bEdit = new JButton("Edit");
		bEdit.setToolTipText("Edit an existing sword");
		bEdit.setBounds(151, 11, 119, 23);
		contentPane.add(bEdit);

		bDelete = new JButton("Delete");
		bDelete.setToolTipText("Delete an existing sword");
		bDelete.setBounds(280, 11, 128, 23);
		contentPane.add(bDelete);

		bView = new JButton("View");
		bView.setToolTipText("View existing swords");
		bView.setBounds(418, 11, 119, 23);
		contentPane.add(bView);

		bSave = new JButton("Save");
		bSave.setToolTipText("Select an answer for all options first.");
		bSave.setBounds(440, 227, 97, 23);
		contentPane.add(bSave);

		bCancel = new JButton("Cancel");
		bCancel.setToolTipText("Return to main menu");
		bCancel.setBounds(333, 227, 97, 23);
		contentPane.add(bCancel);

		bConfirmDelete = new JButton("Confirm Delete");
		bConfirmDelete.setBounds(333, 279, 119, 23);
		contentPane.add(bConfirmDelete);

		bConfirmEdit = new JButton("Confirm Edits");
		bConfirmEdit.setBounds(418, 279, 119, 23);
		contentPane.add(bConfirmEdit);

		// JComboBoxes
		elementBox = new JComboBox(elementList);
		elementBox.setBounds(10, 73, 131, 23);
		elementBox.setSelectedIndex(-1);
		contentPane.add(elementBox);

		colourBox = new JComboBox(colourList);
		colourBox.setBounds(418, 73, 119, 23);
		colourBox.setSelectedIndex(-1);
		contentPane.add(colourBox);

		metalBox = new JComboBox(metalList);
		metalBox.setBounds(151, 73, 119, 23);
		metalBox.setSelectedIndex(-1);
		contentPane.add(metalBox);

		hiltBox = new JComboBox(hiltList);
		hiltBox.setBounds(280, 73, 128, 23);
		hiltBox.setSelectedIndex(-1);
		contentPane.add(hiltBox);

		fireBox = new JComboBox(fireList);
		fireBox.setBounds(10, 158, 129, 23);
		contentPane.add(fireBox);

		waterBox = new JComboBox(waterList);
		waterBox.setBounds(151, 158, 119, 23);
		contentPane.add(waterBox);

		stormBox = new JComboBox(stormList);
		stormBox.setBounds(280, 158, 128, 23);
		contentPane.add(stormBox);

		selectBox = new JComboBox();
		selectBox.setBounds(137, 279, 165, 23);
		contentPane.add(selectBox);

		// JLabels
		lblElement = new JLabel("Select Element:");
		lblElement.setBackground(new Color(255, 255, 255));
		lblElement.setHorizontalAlignment(SwingConstants.CENTER);
		lblElement.setBounds(10, 52, 131, 23);
		contentPane.add(lblElement);

		lblMetal = new JLabel("Select Metal:");
		lblMetal.setHorizontalAlignment(SwingConstants.CENTER);
		lblMetal.setBounds(151, 52, 119, 23);
		contentPane.add(lblMetal);

		lblHilt = new JLabel("Select Hilt:");
		lblHilt.setHorizontalAlignment(SwingConstants.CENTER);
		lblHilt.setBounds(280, 52, 126, 23);
		contentPane.add(lblHilt);

		lblColour = new JLabel("Select Colour:");
		lblColour.setHorizontalAlignment(SwingConstants.CENTER);
		lblColour.setBounds(418, 52, 119, 23);
		contentPane.add(lblColour);

		lblFire = new JLabel("Fire Attack:");
		lblFire.setHorizontalAlignment(SwingConstants.CENTER);
		lblFire.setBackground(Color.WHITE);
		lblFire.setBounds(10, 137, 131, 23);
		contentPane.add(lblFire);

		lblWater = new JLabel("Water Attack:");
		lblWater.setHorizontalAlignment(SwingConstants.CENTER);
		lblWater.setBounds(151, 137, 119, 23);
		contentPane.add(lblWater);

		lblStorm = new JLabel("Storm Attack:");
		lblStorm.setHorizontalAlignment(SwingConstants.CENTER);
		lblStorm.setBackground(Color.WHITE);
		lblStorm.setBounds(280, 137, 128, 23);
		contentPane.add(lblStorm);

		lblResult = new JLabel("Select an option for all choices!");
		lblResult.setForeground(new Color(255, 0, 0));
		lblResult.setBounds(10, 227, 200, 23);
		contentPane.add(lblResult);

		lblName = new JLabel("Sword Name:");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(418, 137, 119, 23);
		contentPane.add(lblName);

		lblSelect = new JLabel("Select a sword to view:");
		lblSelect.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelect.setBounds(149, 261, 139, 23);
		contentPane.add(lblSelect);

		// JTextField
		nameField = new JTextField();
		nameField.setBounds(418, 158, 119, 22);
		contentPane.add(nameField);
		nameField.setColumns(10);
	}

	/**
	 * Read data from textfile and add to arraylist
	 */
	private static void ReadData() {

		try {
			FileReader reader = new FileReader("SavedSwords.txt"); //Declare FileReader
			BufferedReader bufferedReader = new BufferedReader(reader); //Declare BufferedReader

			charactersList = new ArrayList<SinghBaseSword>(); //Create arraylist
			while ((line = bufferedReader.readLine()) != null) { //Loop till condition is not satisfied


				lineData = line.split(":"); //Split on colon

				//Declare local variables
				String name = lineData[0];
				String element = lineData[1];
				String metal = lineData[2];
				String hilt = lineData[3];
				String color = lineData[4];
				String fireAttack = "";
				String waterAttack = "";
				String stormAttack = "";

				if (element.equalsIgnoreCase("Fire")) { //If element is fire
					fireAttack = lineData[5]; //Set variable
					SinghFireSword fs = new SinghFireSword(); //Create object

					//Setting the fields of the object
					fs.setFireAttack(fireAttack);
					fs.setName(name);
					fs.setElement(element);
					fs.setMetal(metal);
					fs.setHilt(hilt);
					fs.setColour(color);
					charactersList.add(fs); //Add to arraylist
				} else if (element.equalsIgnoreCase("Water")) { //Else if element is water
					waterAttack = lineData[5]; //Set variable
					SinghWaterSword ws = new SinghWaterSword(); //Create object

					//Setting the fields of the object
					ws.setWaterAttack(waterAttack);
					ws.setName(name);
					ws.setElement(element);
					ws.setMetal(metal);
					ws.setHilt(hilt);
					ws.setColour(color);
					charactersList.add(ws); //Add to arraylist
				} else if (element.equalsIgnoreCase("Storm")) { //Else if element is storm
					waterAttack = lineData[5]; //Set variable
					stormAttack = lineData[6]; //Set variable
					SinghStormSword ss = new SinghStormSword(); //Create object

					//Setting the fields of the object
					ss.setStormAttack(stormAttack);
					ss.setWaterAttack(waterAttack);
					ss.setName(name);
					ss.setElement(element);
					ss.setMetal(metal);
					ss.setHilt(hilt);
					ss.setColour(color);
					charactersList.add(ss); //Add to arraylist
				}
			}
			reader.close(); //Close reader
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete function
	 */
	private static void deleteSelected() {
		if (selectBox.getSelectedItem() == null) { //If no item selected in the comboBox
			lblResult.setText("Please select a sword!"); //Set text
			lblResult.setForeground(new Color(255, 0, 0)); //Set foreground colour
			lblResult.setVisible(true); //Set visible
		}
		else { //Otherwise
			String chosenSword = (String) selectBox.getSelectedItem(); //Declare local variable

			if (chosenSword != null) { //If not null

				Iterator<SinghBaseSword> itr = charactersList.iterator(); //Initalize iterator
				while (itr.hasNext()) { //While loop
					SinghBaseSword sw = itr.next(); //Iterate next
					if (chosenSword.equals(sw.getName())) { //If conditions are met
						itr.remove(); //Remove
					} 
				}
			}

			try {
				FileWriter writer = new FileWriter("SavedSwords.txt", false); //Initialize FileWriter
				PrintWriter pw = new PrintWriter(writer, false); //Initialize PrintWriter
				pw.flush(); //Flush pw
				pw.close(); //Close pw
				writer.close(); //Close writer
			}
			catch(Exception exception){
			}

			FileWriter writer; //Declare local FileWriter
			try {
				writer = new FileWriter("SavedSwords.txt", true); //Initialize FileWriter

				for(SinghBaseSword sw: charactersList) { //For arraylist
					if (sw instanceof SinghStormSword) { //If it is the storm object
						SinghStormSword stormObj  = (SinghStormSword) sw; //Set stormObj
						writer.write(stormObj.getName().toString() + ":" + stormObj.getElement().toString() + ":"
								+ stormObj.getMetal().toString() + ":" + stormObj.getHilt().toString() + ":"
								+ stormObj.getColour().toString() + ":" + stormObj.getWaterAttack() + ":" + stormObj.getStormAttack()); //Write object fields into textfile
					}
					else if (sw instanceof SinghWaterSword) { //If it is the water object
						SinghWaterSword waterObj  = (SinghWaterSword) sw; //Set waterObj
						writer.write(waterObj.getName().toString() + ":" + waterObj.getElement().toString() + ":"
								+ waterObj.getMetal().toString() + ":" + waterObj.getHilt().toString() + ":"
								+ waterObj.getColour().toString() + ":" + waterObj.getWaterAttack() + ":" + ""); //Write object fields into textfile
					}
					else if (sw instanceof SinghFireSword) { //If it is the fire object
						SinghFireSword fireObj  = (SinghFireSword) sw; //Set fireObj
						writer.write(fireObj.getName().toString() + ":" + fireObj.getElement().toString() + ":"
								+ fireObj.getMetal().toString() + ":" + fireObj.getHilt().toString() + ":"
								+ fireObj.getColour().toString() + ":" + fireObj.getFireAttack() + ":" + ""); //Write object fields into textfile
					}
					writer.write("\r\n"); // write new line
				}

				writer.close(); //Close writer

			}
			catch (IOException e1) { //Catch block in case of an error
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Populate boxes with the corresponding fields of the swordname selected
	 */
	private static void populateBoxes() {
		String chosenSword = (String) selectBox.getSelectedItem(); //Set local variable

		if (chosenSword != null) { //If not null
			for(SinghBaseSword sw: charactersList) { //For arraylist 

				if (chosenSword.equals(sw.getName())) { //If equal

					if (sw instanceof SinghFireSword) { //If it is the fire object 
						SinghFireSword fireObj  = (SinghFireSword) sw; //set fireObj

						//Set values of comboboxes
						elementBox.setSelectedItem(fireObj.getElement());
						metalBox.setSelectedItem(fireObj.getMetal());
						hiltBox.setSelectedItem(fireObj.getHilt());
						colourBox.setSelectedItem(fireObj.getColour());
						fireBox.setSelectedItem(fireObj.getFireAttack());
					}
					else if (sw instanceof SinghStormSword) { //If it is the storm object
						SinghStormSword stormObj  = (SinghStormSword) sw; //Set stormObj

						//Set values of comboboxes
						elementBox.setSelectedItem(stormObj.getElement());
						metalBox.setSelectedItem(stormObj.getMetal());
						hiltBox.setSelectedItem(stormObj.getHilt());
						colourBox.setSelectedItem(stormObj.getColour());
						waterBox.setSelectedItem(stormObj.getWaterAttack());
						stormBox.setSelectedItem(stormObj.getStormAttack());
					}
					else if (sw instanceof SinghWaterSword) { //If it is the water object
						SinghWaterSword waterObj  = (SinghWaterSword) sw; //Set waterObj

						//Set values of comboboxes
						elementBox.setSelectedItem(waterObj.getElement());
						metalBox.setSelectedItem(waterObj.getMetal());
						hiltBox.setSelectedItem(waterObj.getHilt());
						colourBox.setSelectedItem(waterObj.getColour());
						waterBox.setSelectedItem(waterObj.getWaterAttack());
					}
				}	
			}
		}
	}

	/**
	 * Add all possible options to the selectBox
	 */
	private static void populateSelectBox() {
		for(SinghBaseSword sw: charactersList) { //For arraylist
			selectBox.addItem(sw.getName()); //Add the field name of all objects to the selectBox
		}
		selectBox.setSelectedIndex(-1); //Set the default selected index as null

	}

	/**
	 * Initialize all GUI components
	 */
	private static void initialize() {
		// JButtons
		bAdd.setEnabled(true);
		bEdit.setEnabled(true);
		bDelete.setEnabled(true);
		bView.setEnabled(true);
		bSave.setEnabled(false);
		bCancel.setEnabled(false);
		bConfirmDelete.setVisible(false);
		bConfirmEdit.setVisible(false);

		// JComboBoxes
		elementBox.setVisible(false);
		metalBox.setVisible(false);
		hiltBox.setVisible(false);
		colourBox.setVisible(false);
		fireBox.setVisible(false);
		waterBox.setVisible(false);
		stormBox.setVisible(false);
		fireBox.setSelectedIndex(-1);
		waterBox.setSelectedIndex(-1);
		stormBox.setSelectedIndex(-1);
		selectBox.setVisible(false);

		// JLabels
		lblElement.setVisible(false);
		lblMetal.setVisible(false);
		lblHilt.setVisible(false);
		lblColour.setVisible(false);
		lblFire.setVisible(false);
		lblWater.setVisible(false);
		lblStorm.setVisible(false);
		lblName.setVisible(false);
		lblResult.setVisible(false);
		lblSelect.setVisible(false);

		// JTextField
		nameField.setVisible(false);
	}

	/**
	 * Action listeners for components
	 */
	private static void actionListeners() {
		bAdd.addActionListener(new ActionListener() { // listener for the JButton bAdd
			public void actionPerformed(ActionEvent e) {
				System.out.println("In add");
				//Set statuses
				lblElement.setVisible(true);
				elementBox.setVisible(true);
				elementBox.setEnabled(true);
				bCancel.setEnabled(true);
				bEdit.setEnabled(false);
				bDelete.setEnabled(false);
				bView.setEnabled(false);
				lblResult.setVisible(false);
			}
		});
		bEdit.addActionListener(new ActionListener() { // listener for the JButton bEdit
			public void actionPerformed(ActionEvent e) {
				System.out.println("In Edit");
				if (selectBox != null) {
					selectBox.removeAllItems();
				}

				//Set statuses
				lblElement.setVisible(false);
				elementBox.setVisible(false);
				bCancel.setEnabled(true);
				bView.setEnabled(false);
				bEdit.setEnabled(true);
				bDelete.setEnabled(false);
				bAdd.setEnabled(false);
				lblResult.setVisible(false);
				lblSelect.setVisible(true);
				selectBox.setVisible(true);

				populateSelectBox(); //Method call
				populateBoxes(); //Method call

			}
		});
		bConfirmEdit.addActionListener(new ActionListener() { // listener for the JButton bConfirmEdit
			public void actionPerformed(ActionEvent e) {
				System.out.println("In confirmEdit");
				if (elementBox.getSelectedItem().equals("Fire")) { //If element is fire
					if (fireBox.getSelectedItem() == null) { //If fireBox has no selected value
						lblResult.setText("Select an option for all choices!"); //Set text
						lblResult.setVisible(true); //Set visible
					}
				}
				if (elementBox.getSelectedItem().equals("Water")) { //If element is water
					if (waterBox.getSelectedItem() == null) { //If waterBox has no selected value
						lblResult.setText("Select an option for all choices!"); //Set text
						lblResult.setVisible(true); //Set visible
					}
				}
				if (elementBox.getSelectedItem().equals("Storm")) { //If element is storm
					if (waterBox.getSelectedItem() == null || stormBox.getSelectedItem() == null) { //If waterBox and stormBox have no selected values
						lblResult.setText("Select an option for all choices!"); //Set text
						lblResult.setVisible(true); //Set visible
					}
				}
				else { //Otherwise
					deleteSelected(); //Method call

					if (elementBox.getSelectedItem() != null && metalBox.getSelectedItem() != null
							&& hiltBox.getSelectedItem() != null && colourBox.getSelectedItem() != null) { //If conditions are met

						//Declare local variables
						String value = "";
						String nameVal = (String) selectBox.getSelectedItem();
						String elementBoxVal = elementBox.getSelectedItem().toString();
						String metalBoxVal = metalBox.getSelectedItem().toString();
						String hiltBoxVal = hiltBox.getSelectedItem().toString();
						String colourBoxVal = colourBox.getSelectedItem().toString();

						value = nameVal + ":" + elementBoxVal + ":" + metalBoxVal + ":" + hiltBoxVal + ":" + colourBoxVal; //Set variable with colons to separate different fields in the object


						if (elementBoxVal.equalsIgnoreCase("Fire") && fireBox.getSelectedItem() == null) { //If element is fire and fireBox has no selected value
							lblResult.setText("Select an option for all choices!"); //Set text
							lblResult.setVisible(true); //Set visible
							return; //Return to top
						} else if (elementBoxVal.equalsIgnoreCase("Fire") && fireBox.getSelectedItem() != null) { //If element is fire and fireBox has a selected value
							String fireBoxVal = fireBox.getSelectedItem().toString(); //Set variable
							value = value + ":" + fireBoxVal; //Set variable
						}

						if (elementBoxVal.equalsIgnoreCase("Water") && waterBox.getSelectedItem() == null) { //If element is water and waterBox has no selected value
							lblResult.setText("Select an option for all choices!"); //Set text
							lblResult.setVisible(true); //Set visible
							return; //Return to top
						} else if (elementBoxVal.equalsIgnoreCase("Water") && waterBox.getSelectedItem() != null) { //If element is water and waterBox has a selected value
							String waterBoxVal = waterBox.getSelectedItem().toString(); //Set variable
							value = value + ":" + waterBoxVal; //Set variable
						}

						if (elementBoxVal.equalsIgnoreCase("Storm") && stormBox.getSelectedItem() == null) { //If element is storm and stormBox has no selected value
							lblResult.setText("Select an option for all choices!"); //Set text
							lblResult.setVisible(true); //Set visible
							return; //Return to top
						} else if (elementBoxVal.equalsIgnoreCase("Storm") && stormBox.getSelectedItem() != null && waterBox.getSelectedItem() != null) { //If element is storm and stormBox has a selected value
							String waterBoxVal = waterBox.getSelectedItem().toString(); //Set variable
							String stormBoxValue = stormBox.getSelectedItem().toString(); //Set variable
							value = value + ":"+ waterBoxVal + ":" + stormBoxValue; //Set variable
						}
						lblResult.setVisible(false); //Set invisible

						FileWriter writer; //Declare local FileWriter
						try {
							writer = new FileWriter("SavedSwords.txt", true); //Initialize FileWriter

							if (elementBoxVal.equalsIgnoreCase("Fire")) { //If it is the fire element
								writer.write(selectBox.getSelectedItem().toString() + ":" + elementBox.getSelectedItem().toString() + ":"
										+ metalBox.getSelectedItem().toString() + ":" + hiltBox.getSelectedItem().toString()
										+ ":" + colourBox.getSelectedItem().toString() + ":"
										+ fireBox.getSelectedItem().toString() + ":" + ""); //Write object fields into textfile
							}
							if (elementBoxVal.equalsIgnoreCase("Water")) { //If it is the water element
								writer.write(selectBox.getSelectedItem().toString() + ":" + elementBox.getSelectedItem().toString() + ":"
										+ metalBox.getSelectedItem().toString() + ":" + hiltBox.getSelectedItem().toString()
										+ ":" + colourBox.getSelectedItem().toString() + ":"
										+ waterBox.getSelectedItem().toString() + ":" + ""); //Write object fields into textfile
							}
							if (elementBoxVal.equalsIgnoreCase("Storm")) { //If it is the storm element
								writer.write(selectBox.getSelectedItem().toString() + ":" + elementBox.getSelectedItem().toString() + ":"
										+ metalBox.getSelectedItem().toString() + ":" + hiltBox.getSelectedItem().toString()
										+ ":" + colourBox.getSelectedItem().toString() + ":"
										+ waterBox.getSelectedItem().toString() +":" + stormBox.getSelectedItem().toString()); //Write object fields into textfile
							}

							writer.write("\r\n"); // write new line

							writer.close(); //Close writer

							lblResult.setText("Sword has been edited!"); //Set text
							lblResult.setForeground(new Color(0, 128, 0)); //Set foreground colour
							lblResult.setVisible(true); //Set visible

							//Set default comboBox selections as null
							elementBox.setSelectedIndex(-1);
							metalBox.setSelectedIndex(-1);
							hiltBox.setSelectedIndex(-1);
							colourBox.setSelectedIndex(-1);
							fireBox.setSelectedIndex(-1);
							waterBox.setSelectedIndex(-1);
							stormBox.setSelectedIndex(-1);

							//Set visibility statuses
							elementBox.setVisible(false);
							metalBox.setVisible(false);
							hiltBox.setVisible(false);
							colourBox.setVisible(false);
							fireBox.setVisible(false);
							waterBox.setVisible(false);
							stormBox.setVisible(false);
							lblElement.setVisible(false);
							lblMetal.setVisible(false);
							lblHilt.setVisible(false);
							lblColour.setVisible(false);
							lblFire.setVisible(false);
							lblWater.setVisible(false);
							lblStorm.setVisible(false);
							bSave.setEnabled(false);
							bCancel.setEnabled(false);
							nameField.setVisible(false);
							nameField.setText(null);
							lblName.setVisible(false);
							bEdit.setEnabled(true);
							bView.setEnabled(true);
							bDelete.setEnabled(true);
							bConfirmEdit.setVisible(false);
							selectBox.setVisible(false);
							lblSelect.setVisible(false);
							bAdd.setEnabled(true);

							selectBox.removeAllItems(); //Remove all items from the selectBox

							ReadData(); //Method call

						} catch (IOException e1) { //Catch block in case of an error
							e1.printStackTrace();
						}
					}
					else { //Otherwise
						lblResult.setText("Select an option for all choices!"); //Set text
						lblResult.setVisible(true); //Set visible
					}
				}
			}
		});
		bDelete.addActionListener(new ActionListener() { // listener for the JButton bDelete
			public void actionPerformed(ActionEvent e) {
				System.out.println("In delete");
				if (selectBox != null) {
					selectBox.removeAllItems();
				}

				//Set visibility status
				lblElement.setVisible(false);
				elementBox.setVisible(false);
				fireBox.setVisible(false);
				lblFire.setVisible(false);
				waterBox.setVisible(false);
				lblWater.setVisible(false);
				stormBox.setVisible(false);
				lblStorm.setVisible(false);

				//Set enabled status
				bCancel.setEnabled(true);
				bView.setEnabled(false);
				bEdit.setEnabled(false);
				bDelete.setEnabled(true);
				bAdd.setEnabled(false);
				lblResult.setVisible(false);
				lblSelect.setVisible(true);
				selectBox.setVisible(true);
				bConfirmDelete.setVisible(false);

				populateSelectBox(); //Method call
				populateBoxes(); //Method call
			}
		});
		bConfirmDelete.addActionListener(new ActionListener() { // listener for the JButton bConfirmDelete
			public void actionPerformed(ActionEvent e) {
				System.out.println("In confirmDelete");
				deleteSelected(); //Method call

				lblResult.setText("Sword has been deleted!"); //Set text
				lblResult.setForeground(new Color(0, 128, 0)); //Set foreground colour
				lblResult.setVisible(true); //Set visible

				//Set selected indexes as null
				elementBox.setSelectedIndex(-1);
				metalBox.setSelectedIndex(-1);
				hiltBox.setSelectedIndex(-1);
				colourBox.setSelectedIndex(-1);
				fireBox.setSelectedIndex(-1);
				waterBox.setSelectedIndex(-1);
				stormBox.setSelectedIndex(-1);
				selectBox.setSelectedIndex(-1);

				//Set visibilities
				elementBox.setVisible(false);
				metalBox.setVisible(false);
				hiltBox.setVisible(false);
				colourBox.setVisible(false);
				fireBox.setVisible(false);
				waterBox.setVisible(false);
				stormBox.setVisible(false);
				lblElement.setVisible(false);
				lblMetal.setVisible(false);
				lblHilt.setVisible(false);
				lblColour.setVisible(false);
				lblFire.setVisible(false);
				lblWater.setVisible(false);
				lblStorm.setVisible(false);
				bSave.setEnabled(false);
				bCancel.setEnabled(false);
				nameField.setVisible(false);
				nameField.setText(null);
				lblName.setVisible(false);
				bAdd.setEnabled(true);
				bEdit.setEnabled(true);
				bView.setEnabled(true);
				bDelete.setEnabled(true);
				bConfirmDelete.setVisible(false);
				selectBox.setVisible(false);
				lblSelect.setVisible(false);

				selectBox.removeAllItems(); //Remove all items from the selectBox

			}
		});
		bView.addActionListener(new ActionListener() { // listener for the JButton bView
			public void actionPerformed(ActionEvent e) {
				System.out.println("In view");
				if (selectBox != null) {
					selectBox.removeAllItems();
				}

				//Set statuses
				bCancel.setEnabled(true);
				bView.setEnabled(true);
				bEdit.setEnabled(false);
				bDelete.setEnabled(false);
				bAdd.setEnabled(false);
				lblResult.setVisible(false);
				lblSelect.setVisible(true);
				selectBox.setVisible(true);

				ReadData(); //Method call
				populateSelectBox(); //Method call
			}
		});
		selectBox.addActionListener(new ActionListener() { // listener for the JComboBox selectBox
			public void actionPerformed(ActionEvent e) {
				System.out.println("In selectBox");

				//Set statuses
				lblElement.setVisible(true);
				elementBox.setVisible(true);
				metalBox.setVisible(true);
				hiltBox.setVisible(true);
				colourBox.setVisible(true);
				lblMetal.setVisible(true);
				lblHilt.setVisible(true);
				lblColour.setVisible(true);
				nameField.setVisible(false);
				lblName.setVisible(false);
				bSave.setEnabled(false);


				if (selectBox.getSelectedIndex() == -1) { //If nothing is selected in the selectBox

					//Set statuses
					lblElement.setVisible(false);
					elementBox.setVisible(false);
					metalBox.setVisible(false);
					hiltBox.setVisible(false);
					colourBox.setVisible(false);
					lblMetal.setVisible(false);
					lblHilt.setVisible(false);
					lblColour.setVisible(false);
					nameField.setVisible(false);
					lblName.setVisible(false);
					bSave.setEnabled(false);
					fireBox.setVisible(false);
					lblFire.setVisible(false);
				}

				populateBoxes(); //Method call

				//Set statuses
				elementBox.setEnabled(false);
				metalBox.setEnabled(false);
				hiltBox.setEnabled(false);
				colourBox.setEnabled(false);
				fireBox.setEnabled(false);
				waterBox.setEnabled(false);
				stormBox.setEnabled(false);

				if (bEdit.isEnabled() && !(bAdd.isEnabled()) && !(bDelete.isEnabled()) && !(bView.isEnabled())) { //If the conditions are met

					//Set statuses
					elementBox.setEnabled(true);
					metalBox.setEnabled(true);
					hiltBox.setEnabled(true);
					colourBox.setEnabled(true);
					fireBox.setEnabled(true);
					waterBox.setEnabled(true);
					stormBox.setEnabled(true);
					bConfirmEdit.setVisible(true);
				}
				if (bAdd.isEnabled() && bEdit.isEnabled() && bDelete.isEnabled() && bView.isEnabled()) { // If the conditions are met
					bConfirmDelete.setVisible(false); //Set status
				}

				else if (bDelete.isEnabled() && !(bAdd.isEnabled()) && !(bEdit.isEnabled()) && !(bView.isEnabled())) { //Else if the conditions are met
					bConfirmDelete.setVisible(true); //Set status
				}
			}
		});
		elementBox.addActionListener(new ActionListener() { // listener for the JComboBox elementBox
			public void actionPerformed(ActionEvent e) {
				System.out.println("In elementBox");
				//Set statuses
				metalBox.setVisible(true);
				metalBox.setEnabled(true);
				hiltBox.setVisible(true);
				hiltBox.setEnabled(true);
				colourBox.setVisible(true);
				colourBox.setEnabled(true);
				fireBox.setEnabled(true);
				waterBox.setEnabled(true);
				stormBox.setEnabled(true);
				lblMetal.setVisible(true);
				lblHilt.setVisible(true);
				lblColour.setVisible(true);
				bSave.setEnabled(true);
				lblName.setVisible(true);
				nameField.setVisible(true);

				if(selectBox.isVisible()) { //If selectBix is visible

					//Set statuses
					lblName.setVisible(false);
					nameField.setVisible(false);
					bSave.setEnabled(false);
				}

				String selectedItem = (String) elementBox.getSelectedItem(); //Declare local variable
				if (selectedItem != null) { //If an item is selected in the selectBox
					if (selectedItem.equals("Fire")) { //If the element is fire

						//Set statuses
						lblFire.setVisible(true);
						fireBox.setVisible(true);
						waterBox.setSelectedIndex(-1);
						stormBox.setSelectedIndex(-1);
						fireBox.setSelectedIndex(-1);
						lblWater.setVisible(false);
						waterBox.setVisible(false);
						lblStorm.setVisible(false);
						stormBox.setVisible(false);
					} else if (selectedItem.equals("Water")) { //If the element is water

						//Set statuses
						lblWater.setVisible(true);
						waterBox.setVisible(true);
						fireBox.setSelectedIndex(-1);
						stormBox.setSelectedIndex(-1);
						waterBox.setSelectedIndex(-1);
						lblFire.setVisible(false);
						fireBox.setVisible(false);
						lblStorm.setVisible(false);
						stormBox.setVisible(false);
					} else if (selectedItem.equals("Storm")) { //If the element is storm

						//Set statuses
						lblWater.setVisible(true);
						waterBox.setVisible(true);
						lblStorm.setVisible(true);
						stormBox.setVisible(true);
						fireBox.setSelectedIndex(-1);
						stormBox.setSelectedIndex(-1);
						waterBox.setSelectedIndex(-1);
						lblFire.setVisible(false);
						fireBox.setVisible(false);
					}
				}
			}
		});
		bSave.addActionListener(new ActionListener() { // listener for the JButton bSave
			public void actionPerformed(ActionEvent e) {
				System.out.println("In save");
				if (elementBox.getSelectedItem() != null && metalBox.getSelectedItem() != null
						&& hiltBox.getSelectedItem() != null && colourBox.getSelectedItem() != null
						&& nameField.getText().trim().length() != 0) { //If all the options are filled

					//Declare local variables
					String value = "";
					String nameVal = nameField.getText();
					String elementBoxVal = elementBox.getSelectedItem().toString();
					String metalBoxVal = metalBox.getSelectedItem().toString();
					String hiltBoxVal = hiltBox.getSelectedItem().toString();
					String colourBoxVal = colourBox.getSelectedItem().toString();

					value = nameVal + ":" + elementBoxVal + ":" + metalBoxVal + ":" + hiltBoxVal + ":" + colourBoxVal;  //Set variable with colons to separate different fields in the object

					if (elementBoxVal.equalsIgnoreCase("Fire") && fireBox.getSelectedItem() == null) { //If the element is fire and fireBox has no selected item
						lblResult.setText("Select an option for all choices!"); //Set text
						lblResult.setVisible(true); //Set visible
						return; //Return to top
					} else if (elementBoxVal.equalsIgnoreCase("Fire") && fireBox.getSelectedItem() != null) { //If the element is fire and fireBox has a selected item
						String fireBoxVal = fireBox.getSelectedItem().toString(); //Declare local variable
						value = value + ":" + fireBoxVal; //Set variable
					}

					if (elementBoxVal.equalsIgnoreCase("Water") && waterBox.getSelectedItem() == null) { //If the element is water and waterBox has no selected item
						lblResult.setText("Select an option for all choices!"); //Set text
						lblResult.setVisible(true); //Set visible
						return; //Return to top
					} else if (elementBoxVal.equalsIgnoreCase("Water") && waterBox.getSelectedItem() != null) { //If the element is water and waterBox has a selected item
						String waterBoxVal = waterBox.getSelectedItem().toString(); //Declare local variable
						value = value + ":" + waterBoxVal; //Set variable
					}

					if (elementBoxVal.equalsIgnoreCase("Storm") && stormBox.getSelectedItem() == null) { //If the element is storm and stormBox has no selected item
						lblResult.setText("Select an option for all choices!"); //Set text
						lblResult.setVisible(true); //Set visible
						return; //Return to top
					} else if (elementBoxVal.equalsIgnoreCase("Storm") && stormBox.getSelectedItem() != null) { //If the element is storm and stormBox has a selected item
						String stormBoxValue = stormBox.getSelectedItem().toString(); //Declare local variable
						value = value + ":" + stormBoxValue; //Set variable
					}
					lblResult.setVisible(false); //Set invisible

					FileWriter writer; //Declare writer
					try {

						writer = new FileWriter("SavedSwords.txt", true); //Initialize FileWriter

						if (elementBoxVal.equalsIgnoreCase("Fire")) { //If the element is fire
							writer.write(nameField.getText() + ":" + elementBox.getSelectedItem().toString() + ":"
									+ metalBox.getSelectedItem().toString() + ":" + hiltBox.getSelectedItem().toString()
									+ ":" + colourBox.getSelectedItem().toString() + ":"
									+ fireBox.getSelectedItem().toString() + ":" + ""); //Write object fields into textfile
						}
						if (elementBoxVal.equalsIgnoreCase("Water")) { //If the element is water
							writer.write(nameField.getText() + ":" + elementBox.getSelectedItem().toString() + ":"
									+ metalBox.getSelectedItem().toString() + ":" + hiltBox.getSelectedItem().toString()
									+ ":" + colourBox.getSelectedItem().toString() + ":"
									+ waterBox.getSelectedItem().toString() + ":" + ""); //Write object fields into textfile
						}
						if (elementBoxVal.equalsIgnoreCase("Storm")) { //If the element is storm
							writer.write(nameField.getText() + ":" + elementBox.getSelectedItem().toString() + ":"
									+ metalBox.getSelectedItem().toString() + ":" + hiltBox.getSelectedItem().toString()
									+ ":" + colourBox.getSelectedItem().toString() + ":"
									+ waterBox.getSelectedItem().toString() +":" + stormBox.getSelectedItem().toString()); //Write object fields into textfile
						}

						writer.write("\r\n"); // write new line

						writer.close(); //Close writer

						lblResult.setText("New sword has been created!"); //Set text
						lblResult.setForeground(new Color(0, 128, 0)); //Set foreground colour
						lblResult.setVisible(true); //Set visible

						//Set statuses
						elementBox.setSelectedIndex(-1);
						elementBox.setVisible(false);
						metalBox.setSelectedIndex(-1);
						metalBox.setVisible(false);
						hiltBox.setSelectedIndex(-1);
						hiltBox.setVisible(false);
						colourBox.setSelectedIndex(-1);
						colourBox.setVisible(false);
						fireBox.setSelectedIndex(-1);
						fireBox.setVisible(false);
						waterBox.setSelectedIndex(-1);
						waterBox.setVisible(false);
						stormBox.setSelectedIndex(-1);
						stormBox.setVisible(false);
						lblElement.setVisible(false);
						lblMetal.setVisible(false);
						lblHilt.setVisible(false);
						lblColour.setVisible(false);
						lblFire.setVisible(false);
						lblWater.setVisible(false);
						lblStorm.setVisible(false);
						bSave.setEnabled(false);
						bCancel.setEnabled(false);
						nameField.setVisible(false);
						nameField.setText(null);
						lblName.setVisible(false);
						bEdit.setEnabled(true);
						bView.setEnabled(true);
						bDelete.setEnabled(true);

						ReadData(); //Method call

					} catch (IOException e1) { //Catch block in case of an error
						e1.printStackTrace();
					}
				} else { //Otherwise
					lblResult.setText("Select an option for all choices!"); //Set text
					lblResult.setVisible(true); //Set visible
				}
			}
		});
		bCancel.addActionListener(new ActionListener() { // listener for the JButton bCancel
			public void actionPerformed(ActionEvent e) {
				System.out.println("In cancel");
				//Set statuses
				bAdd.setEnabled(true);
				bEdit.setEnabled(true);
				bDelete.setEnabled(true);
				bView.setEnabled(true);
				bConfirmDelete.setVisible(false);
				bConfirmEdit.setVisible(false);

				elementBox.setSelectedIndex(-1);
				elementBox.setVisible(false);
				metalBox.setSelectedIndex(-1);
				metalBox.setVisible(false);
				hiltBox.setSelectedIndex(-1);
				hiltBox.setVisible(false);
				colourBox.setSelectedIndex(-1);
				colourBox.setVisible(false);
				fireBox.setSelectedIndex(-1);
				fireBox.setVisible(false);
				waterBox.setSelectedIndex(-1);
				waterBox.setVisible(false);
				stormBox.setSelectedIndex(-1);
				stormBox.setVisible(false);
				selectBox.setVisible(false);
				selectBox.removeAllItems();
				lblElement.setVisible(false);
				lblMetal.setVisible(false);
				lblHilt.setVisible(false);
				lblColour.setVisible(false);
				lblFire.setVisible(false);
				lblWater.setVisible(false);
				lblStorm.setVisible(false);
				lblSelect.setVisible(false);
				bSave.setEnabled(false);
				bCancel.setEnabled(false);
				lblResult.setVisible(false);
				lblName.setVisible(false);
				nameField.setVisible(false);
			}
		});
	}
} //End of //Start of SinghCharacterCreators class
