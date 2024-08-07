import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Stack;
import java.net.URI;
import java.awt.Font;
/*
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;*/


public class Intro_Page extends JFrame {

	private int roundedCalories;
	
    private JPanel mainPanel;
    private CardLayout cardLayout;

    // Components for creating account
    private JTextField createUsernameField;
    private JPasswordField createPasswordField;
    private JTextField createFirstNameField;
    private JTextField createLastNameField;

    // Components for logging in
    private JTextField loginUsernameField;
    private JPasswordField loginPasswordField;

    // Components for additional information
    private JComboBox<Integer> heightFeetComboBox;
    private JComboBox<Integer> heightInchComboBox;
    private JComboBox<Integer> ageComboBox;
    private JComboBox<String> genderComboBox;
    private JTextField weightTextField;

    // Account information
    private String createdUsername;
    private String createdPassword;
    private String createdFirstName;

    // Welcome message label
    private JLabel welcomeMessageLabel;

    // Shared button panel
    private JPanel buttonPanel;

    private String state, lbsPerWeek;

    // Define a stack to maintain navigation history
    private Stack<String> panelHistory = new Stack<>();

    // Define a variable to keep track of the current panel name
    private String currentPanelName;
    private String currentMonth = "January"; // Default to January or any initial value
    // Define a class variable to store the selected month
    private String selectedMonth;
    private long caloriesfordietplan;


    // Method to navigate to the next panel
    private void goToNextPanel(String nextPanelName) {
        // Push the current panel to the history stack
        panelHistory.push(currentPanelName);
        // Show the next panel
        cardLayout.show(mainPanel, nextPanelName);
        // Update the current panel name
        currentPanelName = nextPanelName;
    }

// Method to navigate to the previous panel
    private void goToPreviousPanel() {
        // Check if there is a previous panel in the history stack
        if (!panelHistory.isEmpty()) {
            // Pop the previous panel from the history stack
            String previousPanelName = panelHistory.pop();
            // Show the previous panel
            cardLayout.show(mainPanel, previousPanelName);
        }
    }

    
   
    
// Method to add navigation buttons to a panel
    private void addNavigationButtons(JPanel panel, String previousPanel, String nextPanel) {
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            // Navigate to the next panel
            cardLayout.show(mainPanel, nextPanel);
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            // Navigate to the previous panel
            cardLayout.show(mainPanel, previousPanel);
        });

        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    /* only use this part of the code if you want to add any functionality for the user besides what is already there
    

    private void showAdditionalInfoPanel(String selectedFrequencyOption) {
        JPanel additionalInfoPanel = new JPanel(new BorderLayout());

        // Create a panel for exercise routine with a "Change Program" button at the top
        JPanel exerciseRoutinePanel = new JPanel(new BorderLayout());
        JButton changeProgramButton = new JButton("Change Program");
        changeProgramButton.addActionListener(e -> {
            // Implement logic to change the exercise routine
         //   String nextMonth = getNextMonth(currentMonth);
         //   showExerciseRoutinePanel(nextMonth, selectedFrequencyOption);
        });
        exerciseRoutinePanel.add(changeProgramButton, BorderLayout.NORTH);
        // Here, you can add components to display the exercise routine for the current month
     //   exerciseRoutinePanel.add(createMonthExercisePanel(currentMonth), BorderLayout.CENTER);
        additionalInfoPanel.add(exerciseRoutinePanel, BorderLayout.CENTER);

        // Add navigation buttons for "Next" and "Back"
        addNavigationButtons(additionalInfoPanel, "loseWeightPanel", "nextPanelName");
        
        

        // Add the panel to the main panel and display it
        mainPanel.add(additionalInfoPanel, "additionalInfoPanel");
        cardLayout.show(mainPanel, "additionalInfoPanel");
    }

    */
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
    
    
    
    private void showFinalReviewPanel(String selectedMeals, String selectedExerciseRoutine) {
        JPanel finalReviewPanel = new JPanel(new BorderLayout());

        // Display selected meals
        JTextArea mealsTextArea = new JTextArea("Selected Meals:\n" + selectedMeals);
        mealsTextArea.setEditable(false);
        finalReviewPanel.add(new JScrollPane(mealsTextArea), BorderLayout.CENTER);

        // Display sshowFinalReviewPanelelected exercise routine
        JTextArea exerciseRoutineTextArea = new JTextArea("\nSelected Exercise Routine:\n" + selectedExerciseRoutine);
        exerciseRoutineTextArea.setEditable(false);
        finalReviewPanel.add(new JScrollPane(exerciseRoutineTextArea), BorderLayout.CENTER);

        // Add exit button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            // Display thank you message
            JOptionPane.showMessageDialog(null, "Thank you f            // Terminate the application gracefully\n"
                    + "or using our program!");
            System.exit(0);
        });
        finalReviewPanel.add(exitButton, BorderLayout.SOUTH);

        // Show the final review panel
        mainPanel.add(finalReviewPanel, "finalReviewPanel");
        cardLayout.show(mainPanel, "finalReviewPanel");
    }

    
    */
    
    
    
    
    public Intro_Page() {
    	
        setTitle("WAS Fitness Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 640); // Set the size to resemble a phone screen
        setLocationRelativeTo(null);

        // Welcome label to display the name of the app using our last name initials,
        // can change later
        JLabel welcomeLabel = new JLabel("<html><br><br><br><br><div style='font-size: 20px; text-align: center;'>Welcome to WAS Fitness Tracker</div></html>");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialize welcomeMessageLabel
        welcomeMessageLabel = new JLabel("");

        // Log In button
        JButton loginButton = new JButton("Log In");
        loginButton.addActionListener(e -> cardLayout.show(mainPanel, "loginInputPanel"));

        // Create Account button
        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(e -> cardLayout.show(mainPanel, "createAccountPanel"));

        // Statement above buttons
        JLabel statementLabel = new JLabel("Please Log In or Create Account");
        statementLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statementLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Shared button panel
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(createAccountButton); // Add Create Account button first
        buttonPanel.add(loginButton); // Then add Log In button

        // Panel for buttons and statement
        JPanel buttonStatementPanel = new JPanel();
        buttonStatementPanel.setLayout(new BorderLayout());
        buttonStatementPanel.add(statementLabel, BorderLayout.NORTH);
        buttonStatementPanel.add(buttonPanel, BorderLayout.CENTER);

        // Main panel
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

    	
    	
     // Panel for log in
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BorderLayout());
        loginPanel.add(welcomeLabel, BorderLayout.NORTH);
/*
        // Load the image using getResource() to ensure it works inside a JAR file
        // Adjusted width, maintains aspect ratio
        ImageIcon imageIcon = new ImageIcon(Intro_Page.class.getResource("/project_pic.png")); // Change ImageLoader to Intro_Page
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(290, -1, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledImageIcon);
        loginPanel.add(imageLabel, BorderLayout.CENTER);*/

        loginPanel.add(buttonStatementPanel, BorderLayout.SOUTH);
        
        // Panel for creating an account
        JPanel createAccountPanel = new JPanel();
        createAccountPanel.setLayout(new GridLayout(7, 2));

        createUsernameField = new JTextField(10);
        createPasswordField = new JPasswordField(10);
        /*createFirstNameField = new JTextField(10);
        createLastNameField = new JTextField(10);*/

        createAccountPanel.add(createLabelWithTextField("Enter username:", createUsernameField));
        createAccountPanel.add(createLabelWithPasswordField("Enter password:", createPasswordField));
        
        /*createAccountPanel.add(createLabelWithTextField("Enter first name:", createFirstNameField));
        createAccountPanel.add(createLabelWithTextField("Enter last name:", createLastNameField));*/

        // Buttons for createAccountPanel
        JPanel createAccountButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton doneCreateAccountButton = new JButton("Done");
        doneCreateAccountButton.addActionListener(e -> {
            // Check if username, password, and first name are provided
            if (createUsernameField.getText().isEmpty() || new String(createPasswordField.getPassword()).isEmpty()
                    /*|| createFirstNameField.getText().isEmpty()*/) {
                // Display an error message if any of the required information is missing
                JOptionPane.showMessageDialog(this, "Please input required information to continue.");
                return; // Do not proceed with account creation
            }

            // Process the create account information
            createdUsername = createUsernameField.getText();
            createdPassword = new String(createPasswordField.getPassword());
            //createdFirstName = createFirstNameField.getText();

            // Show welcome message
            JOptionPane.showMessageDialog(this, "Hello " + createdUsername
                    + ", thank you for choosing WAS Fitness! Please proceed to log in to continue your personalized fitness journey!");

            // Go back to the homepage
            cardLayout.show(mainPanel, "loginPanel");
        });
        createAccountButtonPanel.add(doneCreateAccountButton);

        JButton backToHomepageFromCreateAccountButton = new JButton("Cancel");
        backToHomepageFromCreateAccountButton.addActionListener(e -> cardLayout.show(mainPanel, "loginPanel"));
        createAccountButtonPanel.add(backToHomepageFromCreateAccountButton);

        createAccountPanel.add(createAccountButtonPanel); // Add the buttons to the create account panel

        // Panel for logging in
        JPanel loginInputPanel = new JPanel();
        loginInputPanel.setLayout(new GridLayout(7, 2));

        loginUsernameField = new JTextField(10);
        loginPasswordField = new JPasswordField(10);

        loginInputPanel.add(createLabelWithTextField("Enter your username:", loginUsernameField));
        loginInputPanel.add(createLabelWithPasswordField("Enter your password:", loginPasswordField));

        // Shared button panel for login input panel
        JPanel loginInputButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton doneLoginButton = new JButton("Done");
        doneLoginButton.addActionListener(e -> {
            // Process the login information
            String enteredUsername = loginUsernameField.getText();
            String enteredPassword = new String(loginPasswordField.getPassword());

            // check if the login information is correct
            if (enteredUsername.equals(createdUsername) && enteredPassword.equals(createdPassword)) {
                // Show next page for personal information input
                cardLayout.show(mainPanel, "additionalInfoPanel");
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect username or password, please try again.");
            }
        });

        JButton backToHomepageButton = new JButton("Cancel");
        backToHomepageButton.addActionListener(e -> cardLayout.show(mainPanel, "loginPanel"));

        loginInputButtonPanel.add(doneLoginButton);
        loginInputButtonPanel.add(backToHomepageButton);

        loginInputPanel.add(loginInputButtonPanel); // Add the buttons to the login input panel

        // Panel for personal information (height, age, gender, weight)
        JPanel additionalInfoPanel = new JPanel();
        additionalInfoPanel.setLayout(new GridLayout(5, 2));

        heightFeetComboBox = new JComboBox<>(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
        heightInchComboBox = new JComboBox<>(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11});
        ageComboBox = new JComboBox<>(new Integer[]{16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
            32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57,
            58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70});
        genderComboBox = new JComboBox<>(new String[]{"Male", "Female", "Other", "Decline to Specify"});
        weightTextField = createTextFieldForWeight(); // Create text field for weight input

        additionalInfoPanel
                .add(createLabelWithComboBoxPanel("Height:", "ft:", "in:", heightFeetComboBox, heightInchComboBox));
        additionalInfoPanel.add(createLabelWithComboBoxPanelForInt("Age:", ageComboBox));
        additionalInfoPanel.add(createLabelWithComboBoxPanel("Gender:", genderComboBox));
        additionalInfoPanel.add(createLabelWithTextField("Weight (lbs):", weightTextField));

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            // Get the user input from the combo boxes and text field
            int feet = (int) heightFeetComboBox.getSelectedItem();
            int inches = (int) heightInchComboBox.getSelectedItem();
            int weight = Integer.parseInt(weightTextField.getText());

            // Perform BMI calculation
            double weightInLbs = weight * 0.453592;
            double height = (((feet * 12) + inches) * 0.0254);
            double bmi = weightInLbs / Math.pow(height, 2.0);

            // Round BMI to the nearest tenth
            bmi = Math.round(bmi * 10.0) / 10.0;
            
            // Display BMI and corresponding message
            String resultMessage;
            if (bmi < 18.5) {
                resultMessage = "Underweight";
                state = resultMessage;
                // Display the BMI and corresponding message
                JOptionPane.showMessageDialog(this, "Your BMI is: " + bmi + "\n" + resultMessage);

                // Show the new screen for gaining weight
                showGainWeightScreen();
            } else if (bmi >= 18.5 && bmi < 25) {
                resultMessage = "Great standing";
                state = resultMessage;
                // Display the BMI and corresponding message
                JOptionPane.showMessageDialog(this, "Your BMI is: " + bmi + "\n" + resultMessage);

                // Show the message for maintaining weight
                JOptionPane.showMessageDialog(this, "Great, let's make sure you stay on track!");

                showWorkoutFrequencyScreen();

            } else if (bmi >= 25 && bmi < 30) {
                resultMessage = "Overweight";
                state = resultMessage;
                // Display the BMI and corresponding message
                JOptionPane.showMessageDialog(this, "Your BMI is: " + bmi + "\n" + resultMessage);

                // Show the new screen for losing weight
                showLoseWeightScreen();
            } else {
                resultMessage = "Obese";
                state = resultMessage;
                // Display the BMI and corresponding message
                JOptionPane.showMessageDialog(this, "Your BMI is: " + bmi + "\n" + resultMessage);

                // Show the new screen for extensive weight loss
                showExtensiveWeightLossScreen();
            }
        });

        additionalInfoPanel.add(nextButton);

        // Add panels to the main panel with different names for CardLayout
        mainPanel.add(loginPanel, "loginPanel");
        mainPanel.add(createAccountPanel, "createAccountPanel");
        mainPanel.add(loginInputPanel, "loginInputPanel");
        mainPanel.add(additionalInfoPanel, "additionalInfoPanel");

        // Add the main panel to the frame
        add(mainPanel);

        // Make the frame visible
        setVisible(true);
    }

    // Function to show the screen for gaining weight
    private void showGainWeightScreen() {
        // Create a new panel for gaining weight
        JPanel gainWeightPanel = new JPanel();
        gainWeightPanel.setLayout(new BorderLayout());

        // Label for gaining weight
        String labelText = "   Let's help you gain some weight!\n      How many pounds per week\n           are you aiming to gain?";
        JTextArea gainWeightTextArea = new JTextArea(labelText);
        gainWeightTextArea.setWrapStyleWord(true);
        gainWeightTextArea.setLineWrap(true);
        gainWeightTextArea.setEditable(false);

        // Adjust font size dynamically based on screen size
        Font currentFont = gainWeightTextArea.getFont();
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int fontStyle = Font.PLAIN;

        // Check if the screen width is greater than or equal to 800
        if (screenWidth >= 800) {
            fontStyle = Font.BOLD; // Set to bold style
        }

        float fontSize = screenWidth >= 800 ? 18 : 14;
        Font newFont = currentFont.deriveFont(fontStyle, fontSize);
        gainWeightTextArea.setFont(newFont);

        // Panel for message area
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.add(gainWeightTextArea, BorderLayout.NORTH);

        // Options for gaining weight as radio buttons
        JRadioButton radio0_5 = new JRadioButton("0.5 lb/week");
        JRadioButton radio1_0 = new JRadioButton("1.0 lb/week");
        JRadioButton radio1_5 = new JRadioButton("1.5 lbs/week");
        JRadioButton radio2_0 = new JRadioButton("2.0 lbs/week");

        radio1_0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbsPerWeek = radio1_0.getText();
            }
        });

        radio2_0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbsPerWeek = radio2_0.getText();
            }
        });

        radio0_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbsPerWeek = radio0_5.getText();
            }
        });

        radio1_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbsPerWeek = radio1_5.getText();
            }
        });

        // Group the radio buttons so that only one can be selected at a time
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radio0_5);
        buttonGroup.add(radio1_0);
        buttonGroup.add(radio1_5);
        buttonGroup.add(radio2_0);

        // Panel for radio buttons
        JPanel radioPanel = new JPanel(new GridLayout(4, 1));
        radioPanel.add(radio0_5);
        radioPanel.add(radio1_0);
        radioPanel.add(radio1_5);
        radioPanel.add(radio2_0);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected weight gain option
                String selectedWeightOption = getSelectedRadioButtonText(buttonGroup);

                if (selectedWeightOption != null) {
                    // Display the message
                    /*JOptionPane.showMessageDialog(Intro_Page.this,
                            "Great! Let's get you started with gaining " + selectedWeightOption);*/

                    // Show the workout frequency panel
                    showWorkoutFrequencyScreen();

                } else {
                    JOptionPane.showMessageDialog(Intro_Page.this, "Please select a weight gain option.");
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to the previous panel for user input information
                cardLayout.show(mainPanel, "additionalInfoPanel");
            }
        });

        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        // Add components to the gainWeightPanel
        gainWeightPanel.add(messagePanel, BorderLayout.NORTH);
        gainWeightPanel.add(radioPanel, BorderLayout.CENTER);
        gainWeightPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Show the gainWeightPanel
        mainPanel.add(gainWeightPanel, "gainWeightPanel");
        cardLayout.show(mainPanel, "gainWeightPanel");
    }

    // Function to show the screen for losing weight
    private void showLoseWeightScreen() {
        // Create a new panel for losing weight
        JPanel loseWeightPanel = new JPanel();
        loseWeightPanel.setLayout(new BorderLayout());

        // Label for losing weight
        String labelText = "   Let's help you lose some weight!\n      How many pounds per week\n          are you aiming to lose?";
        JTextArea loseWeightTextArea = new JTextArea(labelText);
        loseWeightTextArea.setWrapStyleWord(true);
        loseWeightTextArea.setLineWrap(true);
        loseWeightTextArea.setEditable(false);

        // Adjust font size dynamically based on screen size
        Font currentFont = loseWeightTextArea.getFont();
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int fontStyle = Font.PLAIN;

        // Check if the screen width is greater than or equal to 800
        if (screenWidth >= 800) {
            fontStyle = Font.BOLD; // Set to bold style
        }

        float fontSize = screenWidth >= 800 ? 18 : 14;
        Font newFont = currentFont.deriveFont(fontStyle, fontSize);
        loseWeightTextArea.setFont(newFont);

        // Panel for message area
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.add(loseWeightTextArea, BorderLayout.NORTH);

        // Options for losing weight as radio buttons
        JRadioButton radio0_5 = new JRadioButton("0.5 lb/week");
        JRadioButton radio1_0 = new JRadioButton("1.0 lb/week");
        JRadioButton radio1_5 = new JRadioButton("1.5 lbs/week");
        JRadioButton radio2_0 = new JRadioButton("2.0 lbs/week");

        radio1_0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbsPerWeek = radio1_0.getText();
            }
        });

        radio2_0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbsPerWeek = radio2_0.getText();
            }
        });

        radio0_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbsPerWeek = radio0_5.getText();
            }
        });

        radio1_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbsPerWeek = radio1_5.getText();
            }
        });

        // Group the radio buttons so that only one can be selected at a time
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radio0_5);
        buttonGroup.add(radio1_0);
        buttonGroup.add(radio1_5);
        buttonGroup.add(radio2_0);

        // Panel for radio buttons
        JPanel radioPanel = new JPanel(new GridLayout(4, 1));
        radioPanel.add(radio0_5);
        radioPanel.add(radio1_0);
        radioPanel.add(radio1_5);
        radioPanel.add(radio2_0);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected weight loss option
                String selectedWeightOption = getSelectedRadioButtonText(buttonGroup);

                if (selectedWeightOption != null) {
                    // Display the message
                    /*JOptionPane.showMessageDialog(Intro_Page.this,
                            "Great! Let's get you started with losing " + selectedWeightOption);*/

                    // Show the workout frequency panel
                    showWorkoutFrequencyScreen();

                } else {
                    JOptionPane.showMessageDialog(Intro_Page.this, "Please select a weight loss option.");
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to the previous panel for user input information
                cardLayout.show(mainPanel, "additionalInfoPanel");
            }
        });

        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        // Add components to the loseWeightPanel
        loseWeightPanel.add(messagePanel, BorderLayout.NORTH);
        loseWeightPanel.add(radioPanel, BorderLayout.CENTER);
        loseWeightPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Show the loseWeightPanel
        mainPanel.add(loseWeightPanel, "loseWeightPanel");
        cardLayout.show(mainPanel, "loseWeightPanel");
    }

    // Function to show the screen for extensive weight loss
    private void showExtensiveWeightLossScreen() {
        // Create a new panel for extensive weight loss
        JPanel extensiveWeightLossPanel = new JPanel();
        extensiveWeightLossPanel.setLayout(new BorderLayout());

        // Label for extensive weight loss
        String labelText = "       Extensive Weight Loss Plan\n      How many pounds per week\n           are you aiming to lose?";
        JTextArea extensiveWeightLossTextArea = new JTextArea(labelText);
        extensiveWeightLossTextArea.setWrapStyleWord(true);
        extensiveWeightLossTextArea.setLineWrap(true);
        extensiveWeightLossTextArea.setEditable(false);

        // Adjust font size dynamically based on screen size
        Font currentFont = extensiveWeightLossTextArea.getFont();
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int fontStyle = Font.PLAIN;

        // Check if the screen width is greater than or equal to 800
        if (screenWidth >= 800) {
            fontStyle = Font.BOLD; // Set to bold style
        }

        float fontSize = screenWidth >= 800 ? 18 : 14;
        Font newFont = currentFont.deriveFont(fontStyle, fontSize);
        extensiveWeightLossTextArea.setFont(newFont);

        // Panel for message area
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.add(extensiveWeightLossTextArea, BorderLayout.NORTH);

        // Options for extensive weight loss as radio buttons
        JRadioButton radio1_0 = new JRadioButton("1.0 lb/week");
        JRadioButton radio2_0 = new JRadioButton("2.0 lbs/week");
        JRadioButton radio3_0 = new JRadioButton("3.0 lbs/week");
        JRadioButton radio4_0 = new JRadioButton("4.0 lbs/week");

        radio1_0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbsPerWeek = radio1_0.getText();
            }
        });

        radio2_0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbsPerWeek = radio2_0.getText();
            }
        });

        radio3_0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbsPerWeek = radio3_0.getText();
            }
        });

        radio4_0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbsPerWeek = radio4_0.getText();
            }
        });

        // Group the radio buttons so that only one can be selected at a time
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radio1_0);
        buttonGroup.add(radio2_0);
        buttonGroup.add(radio3_0);
        buttonGroup.add(radio4_0);

        // Panel for radio buttons
        JPanel radioPanel = new JPanel(new GridLayout(4, 1));
        radioPanel.add(radio1_0);
        radioPanel.add(radio2_0);
        radioPanel.add(radio3_0);
        radioPanel.add(radio4_0);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected extensive weight loss option
                String selectedWeightOption = getSelectedRadioButtonText(buttonGroup);

                if (selectedWeightOption != null) {
                    // Display the message
                    /*JOptionPane.showMessageDialog(Intro_Page.this,
                            "Great! Let's get you started with an extensive weight loss plan of "
                            + selectedWeightOption);*/

                    // Show the workout frequency panel
                    showWorkoutFrequencyScreen();

                } else {
                    JOptionPane.showMessageDialog(Intro_Page.this, "Please select an extensive weight loss option.");
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to the previous panel for user input information
                cardLayout.show(mainPanel, "additionalInfoPanel");
            }
        });

        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        // Add components to the extensiveWeightLossPanel
        extensiveWeightLossPanel.add(messagePanel, BorderLayout.NORTH);
        extensiveWeightLossPanel.add(radioPanel, BorderLayout.CENTER);
        extensiveWeightLossPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Show the extensiveWeightLossPanel
        mainPanel.add(extensiveWeightLossPanel, "extensiveWeightLossPanel");
        cardLayout.show(mainPanel, "extensiveWeightLossPanel");
    }

    // Function to show the screen for workout frequency and calorie intake
    private void showWorkoutFrequencyScreen() {
    	
        // Create a new panel for workout frequency
        JPanel workoutFrequencyPanel = new JPanel();
        workoutFrequencyPanel.setLayout(new BorderLayout());

        // Label for workout frequency
        String labelText = "   How many days a week are you\n        able to work out for 45-75\n                      minutes?";
        JTextArea workoutFrequencyTextArea = new JTextArea(labelText);
        workoutFrequencyTextArea.setWrapStyleWord(true);
        workoutFrequencyTextArea.setLineWrap(true);
        workoutFrequencyTextArea.setEditable(false);

        // Adjust font size dynamically based on screen size
        Font currentFont = workoutFrequencyTextArea.getFont();
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int fontStyle = Font.PLAIN;

        // Check if the screen width is greater than or equal to 800
        if (screenWidth >= 800) {
            fontStyle = Font.BOLD; // Set to bold style
        }

        float fontSize = screenWidth >= 800 ? 18 : 14;
        Font newFont = currentFont.deriveFont(fontStyle, fontSize);
        workoutFrequencyTextArea.setFont(newFont);

        // Panel for message area
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.add(workoutFrequencyTextArea, BorderLayout.NORTH);

        // Options for workout frequency as radio buttons
        JRadioButton radio3_0 = new JRadioButton("3 days/week");
        JRadioButton radio4_0 = new JRadioButton("4 days/week");
        JRadioButton radio5_0 = new JRadioButton("5 days/week");
        JRadioButton radio6_0 = new JRadioButton("6 days/week");

        // Group the radio buttons so that only one can be selected at a time
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radio3_0);
        buttonGroup.add(radio4_0);
        buttonGroup.add(radio5_0);
        buttonGroup.add(radio6_0);

        // Panel for radio buttons
        JPanel radioPanel = new JPanel(new GridLayout(4, 1));
        radioPanel.add(radio3_0);
        radioPanel.add(radio4_0);
        radioPanel.add(radio5_0);
        radioPanel.add(radio6_0);


        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        String selectedWeightOption = lbsPerWeek + "";

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected workout frequency option
                String selectedFrequencyOption = getSelectedRadioButtonText(buttonGroup);

                if (selectedFrequencyOption != null) {
                    // Access gender, weight, age, and height
                    String gender = (String) genderComboBox.getSelectedItem();
                    int weight = Integer.parseInt(weightTextField.getText());
                    int age = (int) ageComboBox.getSelectedItem();
                    int feet = (int) heightFeetComboBox.getSelectedItem();
                    int inches = (int) heightInchComboBox.getSelectedItem();

                    // Calculate BMR based on user input
                    double bmr;
                    double heightInInches = (feet * 12) + inches; // Calculate height in inches
                    if (gender.equals("Male") || gender.equals("Other") || gender.equals("Decline to Specify")) {
                        bmr = 66 + (6.23 * weight) + (12.7 * heightInInches) - (6.8 * age);
                    } else {
                        bmr = 655 + (4.3 * weight) + (4.7 * heightInInches) - (4.7 * age);
                    }

                    // Calculate TDEE based on selected workout frequency
                    double tdee;
                    switch (selectedFrequencyOption) {
                        case "3 days/week":
                            tdee = 1.375 * bmr;
                            break;
                        case "4 days/week":
                            tdee = 1.55 * bmr;
                            break;
                        case "5 days/week":
                            tdee = 1.725 * bmr;
                            break;
                        case "6 days/week":
                            tdee = 1.9 * bmr;
                            break;
                        default:
                            tdee = bmr; // Default to BMR if no option matches
                            break;
                    }

                    // Calculate calorie intake goal based on weight loss preference
                    double calorieIntakeGoal = 0.00;
                    int roundedCalories = (int) Math.round(calorieIntakeGoal);
                    // Check if the user in underweight, overweight etc and calculate the calories
                    if (state.equalsIgnoreCase("Underweight")) {
                        switch (selectedWeightOption) {
                            case "0.5 lb/week":
                                roundedCalories = (int) (tdee + 250);
                                break;
                            case "1.0 lb/week":
                                roundedCalories = (int) (tdee + 500);
                                break;
                            case "1.5 lbs/week":
                                roundedCalories = (int) (tdee + 750);
                                break;
                            case "2.0 lbs/week":
                                roundedCalories = (int) (tdee + 1000);
                                break;
                            default:
                                roundedCalories = (int) tdee; // Default to TDEE if no option matches
                                break;
                        }
                    } else if (state.equalsIgnoreCase("Great Standing")) {
                        switch (selectedWeightOption) {
                            case "0.5 lb/week":
                                roundedCalories = (int) tdee;
                                break;
                            case "1.0 lb/week":
                                roundedCalories = (int) tdee;
                                break;
                            case "1.5 lbs/week":
                                roundedCalories = (int) tdee;
                                break;
                            case "2.0 lbs/week":
                                roundedCalories = (int) tdee;
                                break;
                            default:
                                roundedCalories = (int) tdee; // Default to TDEE if no option matches
                                break;
                        }
                    } else if (state.equalsIgnoreCase("Overweight") || state.equalsIgnoreCase("Obese")) {
                        switch (selectedWeightOption) {
                            case "0.5 lb/week":
                                roundedCalories = (int) (tdee - 250);
                                break;
                            case "1.0 lb/week":
                                roundedCalories = (int) (tdee - 500);
                                break;
                            case "1.5 lbs/week":
                                roundedCalories = (int) (tdee - 750);
                                break;
                            case "2.0 lbs/week":
                                roundedCalories = (int) (tdee - 1000);
                                break;
                            case "3.0 lbs/week":
                                roundedCalories = (int) (tdee - 1750);
                                break;
                            case "4.0 lbs/week":
                                roundedCalories = (int) (tdee - 2000);
                                break;
                            default:
                                roundedCalories = (int) tdee; // Default to TDEE if no option matches
                                break;
                        }
                    }

                    // Display the message with calculated calorie intake goal
                    JOptionPane.showMessageDialog(Intro_Page.this,
                            "Your calorie intake goal per day: " + roundedCalories
                            + "\nAnd following will be your diet plan and exercise routine for "
                            + selectedFrequencyOption);

                    caloriesfordietplan = roundedCalories;
                    showOptionSelectionPanel(selectedFrequencyOption);
                    // For now, let's go back to the homepage
//					cardLayout.show(mainPanel, "loginPanel");
                } else {
                    JOptionPane.showMessageDialog(Intro_Page.this, "Please select a workout frequency option.");
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to the previous panel for weight loss or gain
                // Depending on the user's previous selection
                cardLayout.show(mainPanel, "additionalInfoPanel");
            }
        });

        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        // Add components to the workoutFrequencyPanel
        workoutFrequencyPanel.add(messagePanel, BorderLayout.NORTH);
        workoutFrequencyPanel.add(radioPanel, BorderLayout.CENTER);
        workoutFrequencyPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Show the workoutFrequencyPanel
        mainPanel.add(workoutFrequencyPanel, "workoutFrequencyPanel");
        cardLayout.show(mainPanel, "workoutFrequencyPanel");
    }

    
    
    // Method to show the option selection panel
    // Update showOptionSelectionPanel method to include buttons for all options
    private void showOptionSelectionPanel(String selectedFrequencyOption) {
        JPanel optionSelectionPanel = new JPanel(new BorderLayout());

      //  System.out.println("Got radio button value: " + selectedFrequencyOption);

        // Create a panel for buttons with GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1)); // Vertical arrangement

        // Buttons for Diet Plan, Exercise Routine, Additional Info, and Final Review
        JButton dietPlanButton = new JButton("Diet Plan");
        dietPlanButton.addActionListener(e -> {
            showDietPlanPanel();
        });
        buttonPanel.add(dietPlanButton);

        JButton exerciseRoutineButton = new JButton("Exercise Routine");
        exerciseRoutineButton.addActionListener(e -> {
            displayMonthRoutine("Strength Gain", selectedFrequencyOption);
            showExerciseRoutinePanel(selectedFrequencyOption, selectedFrequencyOption);
        });
        buttonPanel.add(exerciseRoutineButton);

    

        JButton finalReviewButton = new JButton("Final Review");
        finalReviewButton.addActionListener(e -> {
            showFinalReviewPanel();
        });
        buttonPanel.add(finalReviewButton);

        // Add the button panel to the center of the option selection panel
        optionSelectionPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add the "Back" button to go back to the previous page at the bottom
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            // Go back to the previous page
            cardLayout.show(mainPanel, "workoutFrequencyPanel");
        });
        optionSelectionPanel.add(backButton, BorderLayout.SOUTH);

        // Add the "Exit" button to exit the program
        // Add the "Exit" button to exit the program
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            // Display a thank you message and exit the program
            JOptionPane.showMessageDialog(mainPanel, "Thank you for using our program!", "Exit", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0); // Terminate the application
        });
        optionSelectionPanel.add(exitButton, BorderLayout.NORTH);

        // Add the option selection panel to the main panel
        mainPanel.add(optionSelectionPanel, "optionSelectionPanel");
        cardLayout.show(mainPanel, "optionSelectionPanel");
    }


/*
// Method to get the next month
    private String getNextMonth(String currentMonth) {
        String[] months = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};

        // Find the index of the current month
        int currentMonthIndex = -1;
        for (int i = 0; i < months.length; i++) {
            if (months[i].equalsIgnoreCase(currentMonth)) {
                currentMonthIndex = i;
                break;
            }
        }

        if (currentMonthIndex != -1) {
            // Calculate the index of the next month, considering December loops back to January
            int nextMonthIndex = (currentMonthIndex + 1) % months.length;
            return months[nextMonthIndex];
        } else {
            // Handle the case where the current month is not found
            return null;
        }
    }
    */
  
    
    
    //updated version
    private void showDietPlanPanel() {
        JPanel dietPlanPanel = new JPanel();
        dietPlanPanel.setLayout(new BorderLayout());

        // Create the JTextArea for the meals plan message
        String labelText = "<html><div style='text-align: center; width: 220px;'>" 
        		+ "<html>To get your meal plan each day, click the button that says 'Eat This Much'. After clicking the button, you will be taken to a website that allows you to input your calculated calories and personalize what you want to be included or excluded in your meals. You will be able to choose how many meals you want per day to split up your calories accordingly. Once done, follow the plan that is specialized for you. Thank you!</html>";
        JLabel mealsPlanLabel = new JLabel(labelText);
        mealsPlanLabel.setVerticalAlignment(JLabel.CENTER);
        mealsPlanLabel.setHorizontalAlignment(JLabel.CENTER); // Center align the text
        mealsPlanLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Create a panel for the calculated calories label
        JPanel caloriesPanel = new JPanel(new BorderLayout());
        JLabel caloriesLabel = new JLabel("  Your calculated calories: ");
        caloriesLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel roundedCaloriesLabel = new JLabel("<html><body style='text-align: center;'><div style='border: 1px solid black; width: 70px;'>" + caloriesfordietplan + "</div></body></html>");
        roundedCaloriesLabel.setVerticalAlignment(SwingConstants.CENTER);
        roundedCaloriesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        caloriesPanel.add(caloriesLabel, BorderLayout.WEST);
        caloriesPanel.add(roundedCaloriesLabel, BorderLayout.CENTER);

        // Add the calculated calories panel to the center of the dietPlanPanel
        dietPlanPanel.add(caloriesPanel, BorderLayout.CENTER);

        // Rest of the code for buttons and their panel
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "optionSelectionPanel");
        });

        JButton eatThisMuchButton = new JButton("Eat This Much");
        eatThisMuchButton.addActionListener(e -> {
            String link = "https://www.eatthismuch.com";
            try {
                Desktop.getDesktop().browse(new URI(link));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);
        buttonPanel.add(eatThisMuchButton);

        // Add the button panel to the south of the dietPlanPanel
        dietPlanPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the meals plan label to the north of the dietPlanPanel
        dietPlanPanel.add(mealsPlanLabel, BorderLayout.NORTH);

        // Add the Diet Plan panel to the main panel and show it
        mainPanel.add(dietPlanPanel, "dietPlanPanel");
        cardLayout.show(mainPanel, "dietPlanPanel");
    }
    
   
    // Method to show the panel for Exercise Routine //
    private void showExerciseRoutinePanel(String month, String selectedFrequencyOption) {
        JPanel exerciseRoutinePanel = new JPanel(new BorderLayout());

     // Create buttons for each month with stacked words
        JButton month1Button = new JButton("<html><center>Month 1:<br>Strength Gain</center></html>");
        JButton month2Button = new JButton("<html><center>Month 2:<br>Maintenance</center></html>");
        JButton month3Button = new JButton("<html><center>Month 3:<br>Muscle Gain</center></html>");

        // Action listeners for each month button
        month1Button.addActionListener(e -> {
            selectedMonth = "Month 1";
            displayMonthRoutine("Month 1: Strength Gain", selectedFrequencyOption);
        });
        month2Button.addActionListener(e -> {
            selectedMonth = "Month 2";
            displayMonthRoutine("Month 2: Maintenance", selectedFrequencyOption);
        });
        month3Button.addActionListener(e -> {
            selectedMonth = "Month 3";
            displayMonthRoutine("Month 3: Muscle Gain", selectedFrequencyOption);
        });
        

        // Panel for month buttons
        JPanel monthButtonsPanel = new JPanel(new GridLayout(1, 3));
        monthButtonsPanel.add(month1Button);
        monthButtonsPanel.add(month2Button);
        monthButtonsPanel.add(month3Button);
        exerciseRoutinePanel.add(monthButtonsPanel, BorderLayout.NORTH);

        int n = 0;

        if(selectedFrequencyOption.equals("4 days/week")) n = 4;
        else if(selectedFrequencyOption.equals("3 days/week")) n = 3;
        else if(selectedFrequencyOption.equals("5 days/week")) n = 5;
        else if(selectedFrequencyOption.equals("6 days/week")) n = 6;
        else System.out.println("N value can't be assigned");


        String[] texts = {""};
        int limit = n;
        String[] newArray = createNewArray(texts, limit);

        // Panel to display days of workout
        JPanel daysofworkout = new JPanel();

        StringBuilder newText = new StringBuilder();
        for (String text : newArray) {
            newText.append(text).append("<br>"); // Add a line break after each element
        }

        newText.append("</html>");

        JLabel label = new JLabel("<html><div style='text-align: center; width: 220px;'>" +
                "<br><br><br><br><br> Please select a month to see your personalized workout routine. <br><br><br>" +
                newText + "</div></html>");
        label.setFont(new Font("Arial", Font.PLAIN, 24)); // Set font if needed
        label.setPreferredSize(new Dimension(280, 480)); // Set preferred size
        label.setVerticalAlignment(SwingConstants.TOP); // Align the label to the top
        daysofworkout.add(label, BorderLayout.CENTER);
        exerciseRoutinePanel.add(daysofworkout, BorderLayout.CENTER);
        
        // Add "Change Program" button
        JButton changeProgramButton = new JButton("Change Program");
        changeProgramButton.addActionListener(e -> {
            // Prompt the user to confirm the change
            int choice = JOptionPane.showConfirmDialog(exerciseRoutinePanel,
                    "Are you sure you want to change the exercise program?",
                    "Confirm Program Change", JOptionPane.YES_NO_OPTION);

            // If the user confirms the change
            if (choice == JOptionPane.YES_OPTION) {
                // Logic to load a new exercise routine based on the selected month
                // For now, let's just print a message
              //  System.out.println("Loading new exercise routine...");
                loadExerciseRoutineForMonth(selectedMonth);
            } else {
                // If the user cancels the change, do nothing
                System.out.println("Program change canceled.");
            }
        });

        
        
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            // Go back to the previous page
            cardLayout.show(mainPanel, "optionSelectionPanel");
        });
        
        JPanel navigationButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        navigationButtonPanel.add(backButton);
       // navigationButtonPanel.add(nextButton);
        exerciseRoutinePanel.add(navigationButtonPanel, BorderLayout.SOUTH);

        // Add the Exercise Routine panel to the main panel
        mainPanel.add(exerciseRoutinePanel, "exerciseRoutinePanel");
        cardLayout.show(mainPanel, "exerciseRoutinePanel");
    }

    private String[] createNewArray(String[] texts, int limit) {
        // Create a new array with the specified limit
        String[] newArray = new String[Math.min(texts.length, limit)];

        // Copy elements from the original array to the new array
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = texts[i];
        }

        return newArray;
    }

    // Define the loadExerciseRoutineForMonth method in the Intro_Page class
    private void loadExerciseRoutineForMonth(String month) {
        // Add logic to load exercise routine for the specified month
        // For example, you might load different exercise routines based on the month
        if (month.equalsIgnoreCase("Month 1: Strength Gain")) {
            System.out.println("Loading exercise routine for Month 1: Strength Gain");
            // Add logic to load the exercise routine for Month 1
        } else if (month.equalsIgnoreCase("Month 2: Maintenance")) {
            System.out.println("Loading exercise routine for Month 2: Maintenance");
            // Add logic to load the exercise routine for Month 2
        } else if (month.equalsIgnoreCase("Month 3: Muscle Gain")) {
            System.out.println("Loading exercise routine for Month 3: Muscle Gain");
            // Add logic to load the exercise routine for Month 3
        } else {
            // Handle the case where the month is not recognized
            System.out.println("Exercise routine for " + month + " not found.");
        }
    }

// Method to display routine for a specific month
    private void displayMonthRoutine(String monthTitle, String selectedFrequencyOption) {
        // Create a panel for the month's routine
        JPanel monthRoutinePanel = new JPanel(new BorderLayout());

        // Add month title label
        JLabel monthLabel = new JLabel(monthTitle);
        monthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        monthRoutinePanel.add(monthLabel, BorderLayout.NORTH);

        // Add text and components for the routine
        // For now, let's add some sample text
        JTextArea routineText = new JTextArea("This is the routine for " + monthTitle);
        routineText.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(routineText);
        monthRoutinePanel.add(scrollPane, BorderLayout.CENTER);

     
        
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
//            cardLayout.show(mainPanel, "workoutFrequencyPanel");
            showExerciseRoutinePanel("January", selectedFrequencyOption);

            // Go back to the previous page
            //cardLayout.show(mainPanel, "showExerciseRoutinePanel");
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton changeProgramButton = new JButton("Change Program");

        buttonPanel.add(backButton);
      //  buttonPanel.add(nextButton);
        buttonPanel.add(changeProgramButton);

        monthRoutinePanel.add(buttonPanel, BorderLayout.SOUTH);
        
        String[] switchingarrn3 = {
        	    "<html><div style='font-size: 9px;'><b>Day 1: Push</b><br>" +
        	    "Barbell Incline bench press, 3 sets x 12 reps, 2 mins rest" +
        	    "Dumbbell Bench Press, 3 sets x 12 reps, 2 mins rest" +
        	    "Dumbbell Shoulder Press, 3 sets x 12 reps, 2 mins rest" +
        	    "Cable Lateral Raises, 3 sets x 12 reps, 1.5 mins rest" +
        	    "Rope Tricep Pushdown, 3 sets x 12 reps, 1.5 mins rest" +
        	    "Barbell Overhead Tricep Press, 3 sets x 12 reps, 1.5 mins rest</div>",

        	    "<html><div style='font-size: 9px;'><b>Day 2: Pull</b><br>" +
        	    "Cable Seated Row, 3 sets x 12 reps, 2 mins rest" +
        	    "Barbell Bent Over Row, 3 sets x 12 reps, 1.5 mins rest" +
        	    "Barbell Shrug, 3 sets x 12 reps, 1.5 mins rest" +
        	    "Cable Lat Pulldowns, 3 sets x 12 reps, 1.5 mins rest" +
        	    "Dumbbell Bicep Hammer Curl, 3 sets x 12 reps, 1.5 mins rest" +
        	    "Machine Bicep Curl, 3 sets x 12 reps, 1.5 mins rest" +
        	    "Machine Reverse Fly, 3 sets x 12 reps, 1.5 mins rest</div>",


        	    "<html><div style='font-size: 9px;'><b>Day 3: Legs</b><br>" +
                        "Barbell Squat, 4 sets x 12 reps, 2 mins rest \n" +
                        "Machine Seated Leg Press, 4 sets x 12 reps, 2 mins rest \n" +
                        "Leg Press Calf Raises, 4 sets x 12 reps, 2 mins rest \n" +
                        "Dumbbell Lunges, 4 sets x 12 reps, 2 mins rest \n" +
                        "Machine Seated Leg Curl, 4 sets x 12 reps, 2 mins rest </br>",

                        "<html><div style='font-size: 9px;'><b>Day 4: Push 2nd Iteration</b><br>" +
                        "Barbell Bench Press, 3 sets x 12 reps, 2 mins rest \n" +
                        "Dumbbell Incline Bench Press, 3 sets x 12 reps, 2 mins rest \n" +
                        "Dumbbell Lateral Raises, 3 sets x 12 reps, 1.5 mins rest \n" +
                        "Barbell Shoulder Press, 3 sets x 12 reps, 2 mins rest \n" +
                        "Straight Bar Tricep Pushdown, 3 sets x 12 reps, 1.5 mins rest \n" +
                        "Body Weight Tricep Dip, 3 sets x 12 reps, 1.5 mins rest </br>\n",

                        "<html><div style='font-size: 9px;'><b>Day 5: Pull 2nd Iteration</b><br>" +
                        "Machine Seated Row, 3 sets x 12 reps, 1.5 mins rest \n" +
                        "Underhand Cable Lat Pulldowns, 3 sets x 12 reps, 1.5 mins rest \n" +
                        "Dumbbell Shrug, 3 sets x 12 reps, 1.5 mins rest \n" +
                        "Dumbbell Bicep Curl, 3 sets x 12 reps, 1.5 mins rest \n" +
                        "Cable Rope Bicep Hammer Curl, 3 sets x 12 reps, 1.5 mins rest </br>\n",

                        "<html><div style='font-size: 9px;'><b>Day 6: Legs 2nd Iteration</b><br>" +
                        "Dumbbell Front Squat, 4 sets x 12 reps, 2 mins rest \n" +
                        "Machine Seated Leg Press, 4 sets x 12 reps, 2 mins rest \n" +
                        "Barbell Calf Raises, 4 sets x 12 reps, 2 mins rest \n" +
                        "Barbell Lunges, 4 sets x 12 reps, 2 mins rest </br>"};
        
        String[] switchingarrn4 = {
        		"<html><div style='font-size: 9px;'><b>Day 1: Chest and Back</b><br>" +
                "Barbell Incline bench press, 3 sets x 12 reps, 2 mins rest \n" +
                "Dumbbell Bench Press, 3 sets x 12 reps, 2 mins rest \n" +
                "Dumbbell Pullovers, 3 sets x 12 reps, 1.5 mins rest \n" +
                "Chin Ups, 3 sets x 15 reps, 2 mins rest \n" +
                "Barbell Bent Over Row, 3 sets x 12 reps, 1.5 mins rest \n" +
                "Barbell Deadlift, 3 sets x 12 reps, 2 mins rest </br>\n",

                "<html><div style='font-size: 9px;'><b>Day 2: Shoulders, Biceps, and Triceps</b><br>" +
                        "Dumbbell Lateral Raise, 3 sets x 12 reps, 1.5 mins rest \n" +
                        "Barbell Upright Row, 3 sets x 12 reps, 1.5 mins rest \n" +
                        "Standing Barbell Bicep Curl, 3 sets x 12 reps, 1.5 mins rest \n" +
                        "Seated Dumbbell Bicep Curl, 3 sets x 12 reps, 1.5 mins rest \n" +
                        "Standing Barbell Tricep Extension, 3 sets x 12 reps, 1.5 mins rest \n" +
                        "Dumbbell Shoulder Press, 3 sets x 12 reps, 2 mins rest </br>",
                        
                        "<html><div style='font-size: 9px;'><b>Day 3: Legs and Lower Back</b><br>" +
                        "Barbell Squat, 4 sets x 12 reps, 2 mins rest \n" +
                        "Dumbbell Lunge, 4 sets x 12 reps, 2 mins rest \n" +
                        "Machine Leg Curl, 4 sets x 12 reps, 2 mins rest \n" +
                        "Standing Dumbbell Calf Raise, 4 sets x 12 reps, 2 mins rest \n" +
                        "Barbell Deadlift, 4 sets x 12 reps, 2 mins rest </br>",

                        "<html><div style='font-size: 9px;'><b>Day 4: Chest and Back 2nd Iteration</b></br>" +
                        "<br>Barbell Incline bench press, 3 sets x 12 reps, 2 mins rest  \n" +
                        "Dumbbell Bench Press, 3 sets x 12 reps, 2 mins rest \n" +
                        "Dumbbell Pullovers, 3 sets x 12 reps, 1.5 mins rest  \n" +
                        "Chin Ups, 3 sets x 15 reps, 1.5 mins rest \n" +
                        "Barbell Bent Over Row, 3 sets x 12 reps, 1.5 mins rest \n" +
                        "Barbell Deadlift, 3 sets x 12 reps, 2 mins rest </br>",

                        "<html><div style='font-size: 9px;'><b>Day 5: Shoulders, Biceps, and Triceps 2nd Iteration</b></br>" +
                        "<br>Dumbbell Lateral Raise, 3 sets x 12 reps, 1.5 mins rest \n" +
                        "Barbell Upright Row, 3 sets x 12 reps, 1.5 mins rest \n" +
                        "Standing Barbell Bicep Curl, 3 sets x 12 reps, 1.5 mins rest \n" +
                        "Seated Dumbbell Bicep Curl, 3 sets x 12 reps, 1.5 mins rest \n" +
                        "Standing Barbell Tricep Extension, 3 sets x 12 reps, 1.5 mins rest \n" +
                        "Dumbbell Shoulder Press, 3 sets x 12 reps, 2 mins rest </br> \n",

                        "<html><div style='font-size: 9px;'><b>Day 6: Legs and Lower Back 2nd Iteration</b></br>" +
                        "<br>Barbell Squat, 4 sets x 12 reps, 2 mins rest \n" +
                        "Dumbbell Lunge, 4 sets x 12 reps, 2 mins rest \n" +
                        "Machine Leg Curl, 4 sets x 12 reps, 2 mins rest \n" +
                        "Standing Dumbbell Calf Raise, 4 sets x 12 reps, 2 mins rest \n" +
                        "Barbell Deadlift, 4 sets x 12 reps, 2 mins rest "};

        
        JLabel switchinglabel = new JLabel("<html><div style='text-align: center; width: 220px;'>" + "Plans will be changed <br> when you click the <br> 'Change Program' button <br><br><br>");
        switchinglabel.setFont(new Font("Arial", Font.PLAIN, 24)); // Set font if needed
       
        
        
        // Add change program button switching feature using flag ----
        final int[] ii = {0};
        changeProgramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the value of ii when the button is clicked
                if(monthTitle.equals("Month 1: Strength Gain")){ //month 1

                if(ii[0] == 0){
                    String[] switchingarrn1 = {
                    		"<html><div style='font-size: 9px;'><b>Day 1: Push</b><br>" +
                            "Barbell Incline bench press, 3 sets x 8 reps, 3 mins rest\n" +
                            "Dumbbell Bench Press, 3 sets x 8 reps, 3 mins rest\n" +
                            "Dumbbell Shoulder Press, 3 sets x 8 reps, 3 mins rest\n" +
                            "Cable Lateral Raises, 3 sets x 8 reps, 2 mins rest\n" +
                            "Rope Tricep Pushdown, 3 sets x 8 reps, 2 mins rest\n" +
                            "Barbell Overhead Tricep Press, 3 sets x 8 reps, 2 mins rest </br>\n",
                            
                            "<html><div style='font-size: 9px;'><b>Day 2: Pull</b><br>" +
                            "Cable Seated Row, 3 sets x 8 reps, 3 mins rest\n" +
                            "Barbell Bent Over Row, 3 sets x 8 reps, 2 mins rest\n" +
                            "Barbell Shrug, 3 sets x 8 reps, 2 mins rest\n" +
                            "Cable Lat Pulldowns, 3 sets x 8 reps, 3 mins rest\n" +
                            "Dumbbell Bicep Hammer Curl, 3 sets x 8 reps, 2 mins rest\n" +
                            "Machine Bicep Curl, 3 sets x 8 reps, 2 mins rest\n" +
                            "Machine Reverse Fly, 3 sets x 12 reps, 2 mins rest </br>\n",
                            
                            "<html><div style='font-size: 9px;'><b>Day 3: Legs</b><br>" +
                            "Barbell Squat, 4 sets x 8 reps, 3 mins rest\n" +
                            "Machine Seated Leg Press, 4 sets x 8 reps, 3 mins rest\n" +
                            "Leg Press Calf Raises, 4 sets x 8 reps, 3 mins rest\n" +
                            "Dumbbell Lunges, 4 sets x 8 reps, 3 mins rest\n" +
                            "Machine Seated Leg Curl, 4 sets x 8 reps, 3 mins rest </br>\n",
                            
                            "<html><div style='font-size: 9px;'><b>Day 4: Push 2nd Iteration</b><br>" +
                            "Barbell Bench Press, 3 sets x 8 reps, 3 mins rest\n" +
                            "Dumbbell Incline Bench Press, 3 sets x 8 reps, 3 mins rest\n" +
                            "Dumbbell Lateral Raises, 3 sets x 8 reps, 2 mins rest\n" +
                            "Barbell Shoulder Press, 3 sets x 8 reps, 3 mins rest\n" +
                            "Straight Bar Tricep Pushdown, 3 sets x 8 reps, 2 mins rest\n" +
                            "Body Weight Tricep Dip, 3 sets x 8 reps, 2 mins rest</br>\n",
                            
                            "<html><div style='font-size: 9px;'><b>Day 5: Pull 2nd Iteration</b><br>" +
                            "Machine Seated Row, 3 sets x 8 reps, 3 mins rest\n" +
                            "Underhand Cable Lat Pulldowns, 3 sets x 8 reps, 3 mins rest\n" +
                            "Dumbbell Shrug, 3 sets x 8 reps, 2 mins rest\n" +
                            "Dumbbell Bicep Curl, 3 sets x 8 reps, 2 mins rest\n" +
                            "Cable Rope Bicep Hammer Curl, 3 sets x 8 reps, 2 mins rest </br>\n",
                            
                            "<html><div style='font-size: 9px;'><b>Day 6: Legs 2nd Iteration</b><br>" +
                            "Dumbbell Front Squat, 4 sets x 8 reps, 3 mins rest\n" +
                            "Machine Seated Leg Press, 4 sets x 8 reps, 3 mins rest\n" +
                            "Barbell Calf Raises, 4 sets x 8 reps, 3 mins rest\n" +
                            "Barbell Lunges, 4 sets x 8 reps, 3 mins rest </br>"};
                    
                    switchinglabel.setText(getTextFromSwitchingArray(switchingarrn1, selectedFrequencyOption));
                    switchinglabel.setVerticalAlignment(SwingConstants.TOP);
                //    switchinglabel.setVerticalAlignment(SwingConstants.CENTER);
                //    System.out.println("Frequency " + selectedFrequencyOption);
                    ii[0]++;
                }
                else {
                    String[] switchingarrn2 = {
                    		"<html><div style='font-size: 9px;'><b>Day 1: Chest and Back</b><br>" +
                            "Barbell Incline bench press, 3 sets x 8 reps, 3 mins rest\n" +
                            "Dumbbell Bench Press, 3 sets x 8 reps, 3 mins rest\n" +
                            "Dumbbell Pullovers, 3 sets x 8 reps, 3 mins rest\n" +
                            "Chin Ups, 3 sets x 12 reps, 2 mins rest\n" +
                            "Barbell Bent Over Row, 3 sets x 8 reps, 2 mins rest\n" +
                            "Barbell Deadlift, 3 sets x 8 reps, 3 mins rest </br>\n"
                            ,
                            "<html><div style='font-size: 9px;'><b>Day 2: Shoulders, Biceps, and Triceps</b><br>" +
                            "Dumbbell Lateral Raise, 3 sets x 8 reps, 2 mins rest\n" +
                            "Barbell Upright Row, 3 sets x 8 reps, 3 mins rest\n" +
                            "Standing Barbell Bicep Curl, 3 sets x 8 reps, 2 mins rest\n" +
                            "Seated Dumbbell Bicep Curl, 3 sets x 8 reps, 2 mins rest\n" +
                            "Standing Barbell Tricep Extension, 3 sets x 8 reps, 2 mins rest\n" +
                            "Dumbbell Shoulder Press, 3 sets x 8 reps, 3 mins rest </br>\n"
                            ,
                            "<html><div style='font-size: 9px;'><b>Day 3: Legs and Lower Back</b><br>" +
                            "Barbell Squat, 3 sets x 8 reps, 3 mins rest\n" +
                            "Dumbbell Lunge, 3 sets x 8 reps, 3 mins rest\n" +
                            "Machine Leg Curl, 3 sets x 8 reps, 3 mins rest\n" +
                            "Standing Dumbbell Calf Raise, 3 sets x 8 reps, 3 mins rest\n" +
                            "Barbell Deadlift, 3 sets x 8 reps, 3 mins rest </br>\n"
                            ,
                            "<html><div style='font-size: 9px;'><b>Day 4: Chest and Back 2nd Iteration</b></br>" +
                            "<br>Barbell Incline bench press, 3 sets x 8 reps, 3 mins rest\n" +
                            "Dumbbell Bench Press, 3 sets x 8 reps, 3 mins rest\n" +
                            "Dumbbell Pullovers, 3 sets x 8 reps, 3 mins rest\n" +
                            "Chin Ups, 3 sets x 12 reps, 2 mins rest\n" +
                            "Barbell Bent Over Row, 3 sets x 8 reps, 2 mins rest\n" +
                            "Barbell Deadlift, 3 sets x 8 reps, 3 mins rest </br>\n"
                            ,
                            "<html><div style='font-size: 9px;'><b>Day 5: Shoulders, Biceps, and Triceps 2nd Iteration</b></br>" +
                            "<br>Dumbbell Lateral Raise, 3 sets x 8 reps, 2 mins rest\n" +
                            "Barbell Upright Row, 3 sets x 8 reps, 3 mins rest\n" +
                            "Standing Barbell Bicep Curl, 3 sets x 8 reps, 2 mins rest\n" +
                            "Seated Dumbbell Bicep Curl, 3 sets x 8 reps, 2 mins rest\n" +
                            "Standing Barbell Tricep Extension, 3 sets x 8 reps, 2 mins rest\n" +
                            "Dumbbell Shoulder Press, 3 sets x 8 reps, 3 mins rest </br>\n"
                            ,
                            "<html><div style='font-size: 9px;'><b>Day 6: Legs and Lower Back 2nd Iteration</b></br>" +
                            "<br>Barbell Squat, 3 sets x 8 reps, 3 mins rest\n" +
                            "Dumbbell Lunge, 3 sets x 8 reps, 3 mins rest\n" +
                            "Machine Leg Curl, 3 sets x 8 reps, 3 mins rest\n" +
                            "Standing Dumbbell Calf Raise, 3 sets x 8 reps, 3 mins rest\n" +
                            "Barbell Deadlift, 3 sets x 8 reps, 3 mins rest </br>"};
                    
                    switchinglabel.setText(getTextFromSwitchingArray(switchingarrn2, selectedFrequencyOption));
                   // System.out.println("Frequency " + selectedFrequencyOption);
                        ii[0]--;
                }
                }
                else if(monthTitle.equals("Month 2: Maintenance")){
                    if(ii[0] == 0){
                        switchinglabel.setText(getTextFromSwitchingArray(switchingarrn3, selectedFrequencyOption));
                        switchinglabel.setVerticalAlignment(SwingConstants.TOP);
                        //    switchinglabel.setVerticalAlignment(SwingConstants.CENTER);
                        //    System.out.println("Frequency " + selectedFrequencyOption);
                        ii[0]++;
                    }
                    else {
                        switchinglabel.setText(getTextFromSwitchingArray(switchingarrn4, selectedFrequencyOption));
                        // System.out.println("Frequency " + selectedFrequencyOption);
                        ii[0]--;
                    }
                }
                else {
                    if(ii[0] == 0){
                        String[] switchingarrn5 = {
                        		"<html><div style='font-size: 9px;'><b>Day 1: Push</b><br>" +
                                "Dumbbell Bench Press, 3 sets x 15 reps, 1 min rest\n" +
                                "Dumbbell Chest Fly, 3 sets x 15 reps, 1 min rest\n" +
                                "Dumbbell Shoulder Press, 3 sets x 15 reps, 1 min rest\n" +
                                "Dumbbell Lateral Raises, 3 sets x 15 reps, 1 min rest\n" +
                                "Rope Tricep Pushdown, 3 sets x 15 reps, 1 min rest\n" +
                                "Rope Tricep Overhead Press, 3 sets x 15 reps, 1 min rest\n" +
                                "Incline Dumbbell Bench Press, 3 sets x 15 reps, 1 min rest </br>\n",
                                
                                "<html><div style='font-size: 9px;'><b>Day 2: Pull</b><br>" +
                                "Barbell Deadlift, 3 sets x 12 reps, 1 min rest\n" +
                                "Cable Wide Grip Lat Pulldowns, 3 sets x 12 reps, 1 min rest\n" +
                                "Cable Underhand Lat Pulldowns, 3 sets x 12 reps, 1 min rest\n" +
                                "Seated Cable Rows, 3 sets x 20 reps, 1 min rest\n" +
                                "Cable Rope Bicep Hammer Curl, 3 sets x 15 reps, 1 min rest\n" +
                                "Cable Straight Bar Shrug, 3 sets x 15 reps, 1 min rest\n" +
                                "Dumbbell Bicep Curl, 3 sets x 15 reps, 1 min rest </br>\n",
                                
                                "<html><div style='font-size: 9px;'><b>Day 3: Legs</b><br>" +
                                "Barbell Squat, 4 sets x 15 reps, 1 mins rest\n" +
                                "Machine Seated Leg Press, 4 sets x 15 reps, 1 mins rest\n" +
                                "Leg Press Calf Raises, 4 sets x 15 reps, 1 mins rest\n" +
                                "Dumbbell Lunges, 4 sets x 15 reps, 1 mins rest\n" +
                                "Machine Seated Leg Curl, 4 sets x 15 reps, 1 mins rest </br>\n",
                                
                                "<html><div style='font-size: 9px;'><b>Day 4: Push 2nd Iteration</b><br>" +
                                "Incline Dumbbell Bench Press, 3 sets x 15 reps, 1 min rest\n" +
                                "Dumbbell Incline Chest Fly, 3 sets x 15 reps, 1 min rest\n" +
                                "Upright Barbell Row, 3 sets x 15 reps, 1 min rest\n" +
                                "Cable Lateral Raise, 3 sets x 15 reps, 1 min rest\n" +
                                "Barbell Overhead Tricep Press, 3 sets x 15 reps, 1 min rest\n" +
                                "Barbell Skullcrusher, 3 sets x 15 reps, 1 min rest </br>\n",
                                
                                "<html><div style='font-size: 9px;'><b>Day 5: Pull 2nd Iteration</b><br>" +
                                "Dumbbell Incline Bench Press, 3 sets x 15 reps, 1 min rest\n" +
                                "Dumbbell Incline Chest Fly, 3 sets x 15 reps, 1 min rest\n" +
                                "Barbell Shoulder Press, 3 sets x 15 reps, 1 min rest\n" +
                                "Dumbbell Lateral Raises, 3 sets x 15 reps, 1 min rest\n" +
                                "Straight Bar Tricep Pushdown, 3 sets x 15 reps, 1 min rest\n" +
                                "Barbell Overhead Press, 3 sets x 15 reps, 1 min rest\n" +
                                "Dumbbell Bench Press, 3 sets x 15 reps, 1 min rest </br>",
                                
                                "<html><div style='font-size: 9px;'><b>Day 6: Legs 2nd Iteration</b><br>" +
                                "Dumbbell Front Squat, 4 sets x 15 reps, 1 min rest\n" +
                                "Machine Seated Leg Press, 4 sets x 15 reps, 1 min rest\n" +
                                "Barbell Calf Raises, 4 sets x 15 reps, 1 min rest\n" +
                                "Barbell Lunges, 4 sets x 15 reps, 1 min rest </br>\n"};
                        switchinglabel.setText(getTextFromSwitchingArray(switchingarrn5, selectedFrequencyOption));
                        switchinglabel.setVerticalAlignment(SwingConstants.TOP);
                        
                        ii[0]++;
                    }
                    else {
                        String[] switchingarrn6 = {
                        		"<html><div style='font-size: 9px;'><b>Day 1: Chest and Back</b><br>" +
                                "Barbell Incline bench press, 3 sets x 15 reps, 1 mins rest\n" +
                                "Dumbbell Bench Press, 3 sets x 15 reps, 1 mins rest\n" +
                                "Dumbbell Pullovers, 3 sets x 15 reps, 1 mins rest\n" +
                                "Chin Ups, 3 sets x 15 reps, 1 mins rest\n" +
                                "Barbell Bent Over Row, 3 sets x 15 reps, 1 mins rest\n" +
                                "Barbell Deadlift, 3 sets x 15 reps, 1 mins rest </br>\n"
                                ,
                                "<html><div style='font-size: 9px;'><b>Day 2: Shoulders, Biceps, and Triceps</b><br>" +
                                "Dumbbell Lateral Raise, 3 sets x 15 reps, 1 mins rest\n" +
                                "Barbell Upright Row, 3 sets x 15 reps, 1 mins rest\n" +
                                "Standing Barbell Bicep Curl, 3 sets x 15 reps, 1 mins rest\n" +
                                "Seated Dumbbell Bicep Curl, 3 sets x 15 reps, 1 mins rest\n" +
                                "Standing Barbell Tricep Extension, 3 sets x 15 reps, 1 mins rest\n" +
                                "Dumbbell Shoulder Press, 3 sets x 15 reps, 1 mins rest </br>\n"
                                ,
                                "<html><div style='font-size: 9px;'><b>Day 3: Legs and Lower Back</b><br>" +
                                "Barbell Squat, 4 sets x 15 reps, 1 mins rest\n" +
                                "Dumbbell Lunge, 4 sets x 15 reps, 1 mins rest\n" +
                                "Machine Leg Curl, 4 sets x 15 reps, 1 mins rest\n" +
                                "Standing Dumbbell Calf Raise, 4 sets x 15 reps, 1 mins rest\n" +
                                "Barbell Deadlift, 4 sets x 15 reps, 1 mins rest </br>\n"
                                ,
                                "<html><div style='font-size: 9px;'><b>Day 4: Chest and Back 2nd Iteration</b></br>" +
                                "<br>Barbell Incline bench press, 3 sets x 15 reps, 1 min rest\n" +
                                "Dumbbell Bench Press, 3 sets x 15 reps, 1 min rest\n" +
                                "Dumbbell Pullovers, 3 sets x 15 reps, 1 min rest\n" +
                                "Chin Ups, 3 sets x 20 reps, 1 min rest\n" +
                                "Barbell Bent Over Row, 3 sets x 15 reps, 1 min rest\n" +
                                "Barbell Deadlift, 3 sets x 15 reps, 1 min rest </br>\n"
                               , 
                               "<html><div style='font-size: 9px;'><b>Day 5: Shoulders, Biceps, and Triceps 2nd Iteration</b></br>" +
                                "<br>Dumbbell Lateral Raise, 3 sets x 15 reps, 1 min rest\n" +
                                "Barbell Upright Row, 3 sets x 15 reps, 1 min rest\n" +
                                "Standing Barbell Bicep Curl, 3 sets x 15 reps, 1 min rest\n" +
                                "Seated Dumbbell Bicep Curl, 3 sets x 15 reps, 1 min rest\n" +
                                "Standing Barbell Tricep Extension, 3 sets x 15 reps, 1 min rest\n" +
                                "Dumbbell Shoulder Press, 3 sets x 15 reps, 1 min rest </br>\n"
                               , 
                               "<html><div style='font-size: 9px;'><b>Day 6: Legs and Lower Back 2nd Iteration</b></br>" +
                                "<br>Barbell Squat, 4 sets x 15 reps, 1 mins rest\n" +
                                "Dumbbell Lunge, 4 sets x 15 reps, 1 mins rest\n" +
                                "Machine Leg Curl, 4 sets x 15 reps, 1 mins rest\n" +
                                "Standing Dumbbell Calf Raise, 4 sets x 15 reps, 1 mins rest\n" +
                                "Barbell Deadlift, 4 sets x 15 reps, 1 mins rest </br>"};
                        
                        switchinglabel.setText(getTextFromSwitchingArray(switchingarrn6, selectedFrequencyOption));
                        
                        ii[0]--;
                    }
                }

                switchinglabel.setFont(new Font("Arial", Font.BOLD, 18));
              
            }
        });


        JScrollPane sp = new JScrollPane(switchinglabel);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       // sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        monthRoutinePanel.add(sp, BorderLayout.CENTER);

        // Add the panel to the main panel
        mainPanel.add(monthRoutinePanel, "monthRoutinePanel_" + monthTitle);
        cardLayout.show(mainPanel, "monthRoutinePanel_" + monthTitle);
    }


    private String getTextFromSwitchingArray(String[] strings, String limitString) {
        int limit = Integer.parseInt(String.valueOf(limitString.charAt(0)));
        StringBuilder result = new StringBuilder("<html>");
        for (int i = 0; i < limit && i < strings.length; i++) {
            result.append("<div>").append(strings[i]).append("</div><br>"); // Enclose each day and workout in <div> tags
        }
        return result.toString().replace("rest", "rest <br/>");
    }
        
    
    
    // Final panel with navigation buttons
    private void showFinalPanel() {
        // Create final panel with navigation buttons
        JPanel finalPanel = new JPanel(new GridLayout(0, 1));

        // Add buttons for each panel
        JButton optionSelectionButton = new JButton("Option Selection Panel");
        optionSelectionButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "optionSelectionPanel");
        });
        finalPanel.add(optionSelectionButton);

        JButton mealPlanButton = new JButton("Meal Plan Panel");
        mealPlanButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "mealPlanPanel");
        });
        finalPanel.add(mealPlanButton);

        JButton exerciseRoutineButton = new JButton("Exercise Routine Panel");
        exerciseRoutineButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "exerciseRoutinePanel");
        });
        finalPanel.add(exerciseRoutineButton);

        // Add an exit button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            // Display a message and exit the program
            JOptionPane.showMessageDialog(finalPanel, "Thank you for using the fitness app. We hope you achieve your fitness goals!");
            System.exit(0);
        });
        finalPanel.add(exitButton);

        // Add the final panel to the main panel
        mainPanel.add(finalPanel, "finalPanel");
        cardLayout.show(mainPanel, "finalPanel");
    }

    
 // Method to show the panel for Final Review
    private void showFinalReviewPanel() {
        JPanel finalReviewPanel = new JPanel(new BorderLayout());

        // Label for navigation instructions
        JLabel navigationLabel = new JLabel("<html><div style='text-align: center;'>Here you can navigate to any of the previous screens in one click.</div></html>");
        finalReviewPanel.add(navigationLabel, BorderLayout.NORTH);

        // Button panel for navigation
        JPanel navigationButtonPanel = new JPanel(new GridLayout(2, 3)); // Grid layout to arrange buttons
        
     // Change the buttons and where they navigate to
        String[] panelNames = {"additionalInfoPanel", "workoutFrequencyPanel", "exerciseRoutinePanel", "dietPlanPanel", "loginPanel"}; // Names of the panels
        String[] buttonLabels = {"Personal Info", "Workout Frequency", "Exercises", "Meals", "Log Out"}; // Labels for buttons

        for (int i = 0; i < panelNames.length; i++) {
            JButton button = new JButton("<html><center>" + buttonLabels[i].replace(" ", "<br>") + "</center></html>"); // Replace space with <br> for stacking words
            int index = i; // Capture the value of i for the ActionListener
            button.addActionListener(e -> cardLayout.show(mainPanel, panelNames[index])); // Switch to the corresponding panel
            navigationButtonPanel.add(button);
        }
        finalReviewPanel.add(navigationButtonPanel, BorderLayout.CENTER);


        // Add the "Back" button to go back to the previous page
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            // Go back to the previous page
            cardLayout.show(mainPanel, "optionSelectionPanel");
        });

        // Add the "Exit" button to exit the program
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            // Display a thank you message and exit the program
            JOptionPane.showMessageDialog(mainPanel, "Thank you for using WAS fitness. Please make sure to keep track of your daily targets and we look forward to helping you achieve your goals!", "Exit", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0); // Terminate the application
        });

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);
        buttonPanel.add(exitButton);

        // Add the button panel to the south of the dietPlanPanel
        finalReviewPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the Final Review panel to the main panel
        mainPanel.add(finalReviewPanel, "finalReviewPanel");
        cardLayout.show(mainPanel, "finalReviewPanel");
    }


    
    private String getSelectedRadioButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

        
    private JPanel createLabelWithTextField(String labelText, JTextField textField) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(labelText);

        panel.add(label);
        panel.add(textField);

        return panel;
    }

    private JPanel createLabelWithPasswordField(String labelText, JPasswordField passwordField) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(labelText);

        panel.add(label);
        panel.add(passwordField);

        return panel;
    }

    private JPanel createLabelWithComboBoxPanel(String labelText, String unit1, String unit2,
            JComboBox<Integer> comboBox1, JComboBox<Integer> comboBox2) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(labelText);

        panel.add(label);
        panel.add(comboBox1);
        panel.add(new JLabel(unit1));
        panel.add(comboBox2);
        panel.add(new JLabel(unit2));

        return panel;
    }

    private JPanel createLabelWithComboBoxPanelForInt(String labelText, JComboBox<Integer> comboBox) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(labelText);

        panel.add(label);
        panel.add(comboBox);

        return panel;
    }

    private JPanel createLabelWithComboBoxPanel(String labelText, JComboBox<String> comboBox) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(labelText);

        panel.add(label);
        panel.add(comboBox);

        return panel;
    }

    private JTextField createTextFieldForWeight() {
        JTextField textField = new JTextField(10);
        // Add additional formatting or validation if needed
        return textField;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Intro_Page();
        });
    }
}
