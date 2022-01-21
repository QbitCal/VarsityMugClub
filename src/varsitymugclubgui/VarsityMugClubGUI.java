
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.geometry.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 * This program acts as the Graphical User Interface
 * for the Varsity Mug Club report generating application.
 * 
 * 
 * SO FAR:
 * This application is able to create new queries and had buttons that function 
 * to generate a report based on these queries.
 * 
 * TODO:
 * Recreate each individual report query (make sure all relevant info is included)
 * 
 * OTHER TODO:
 * Remove result set and statement class variables and closing statements.
 * 
 * @author Quenten Calvano
 * @version 1
 */

public class VarsityMugClubGUI extends Application {
    
    //Information relevant to establish the connection...
    static final String DATABASE_URL = "SETdatabaseURL"; //The path to my berks DB space.
    public static String userName = "SETusername"; //The user's username for the database.
    public static String password = "SETpassword"; //The user's password for the database.
    public static Connection connection = null; //Manages connection.
    public static boolean loggedIn = false; //Start in a logged out state.
    public static int reportWidth = 600; //Int of the report width based on columns.

    //Color codes for the application's color palette...
    public static String bgColor = "BDC3CB"; //A light bluish Gray.
    public static String accentOne = "CCAFA5"; //A Dusty Rose color.
    public static String accentTwo = "DCD2CC"; //Sand Dollar color.
    public static String accentThree = "EDE7DC"; //An Ivory color.

    
    public void start(Stage primaryStage) {
        
        //Create a Vbox to hold the Title and Grid with login...
        VBox root = new VBox();
        root.setPadding(new Insets(10, 50, 50, 50)); //Set the padding.
        root.setAlignment(Pos.CENTER); //Center the VBox.
        root.setStyle("-fx-background-color: BDC3CB;"); //Stylize the VBox.

        //Create report screen buttons...
        Button reportOne = new Button("Report 1"); //Displays Report 1.
        Button reportTwo = new Button("Report 2"); //Displays Report 2.
        Button reportThree = new Button("Report 3"); //Displays Report 3.
        Button exitBtn = new Button("Exit"); //Exits the program.
        //Set Buttons to accentThree (remove this part for responsive but 
        //unstylized buttons)...
        exitBtn.setStyle("-fx-background-color: " + accentThree);
        reportOne.setStyle("-fx-background-color: " + accentThree);
        reportTwo.setStyle("-fx-background-color: " + accentThree);
        reportThree.setStyle("-fx-background-color: " + accentThree);

        //Create a button bar to hold all of the buttons...
        ButtonBar options = new ButtonBar();
        //Add buttons to the button Bar...
        options.getButtons().addAll(reportOne, reportTwo, reportThree, exitBtn);
        //Create a FlowPane to hold the options bar...
        FlowPane optionsBar = new FlowPane();
        //Add button bar to new flowpane...
        optionsBar.getChildren().add(options);
        //Center the new Flowpane...
        optionsBar.setAlignment(Pos.CENTER);
        
        //Create a back button to return to the report screen...
        Button backButton = new Button("< Back");
        //Set back button style...
        backButton.setStyle("-fx-background-color: " + accentThree);
        //Create Flowpane for back button...
        FlowPane backPane = new FlowPane();
        //Set the flowpane to the left...
        backPane.setAlignment(Pos.BASELINE_LEFT);
        //Add the back button to the flowpane...
        backPane.getChildren().add(backButton);
        //Generate an imageview to include as the title...
        FlowPane mainTitle = buildLogo();
        

        
        //Create Action Event Handlers for the various report buttons...
        //Handle the action events for the first report button.
        reportOne.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                //Whichever report generation occurs here...
                String reportQuery = "SELECT * FROM Car;";
                
                //Create a Label to name the report...
                Label repName = new Label("Test Report From Car Table");
                repName.setFont(new Font("Myriad", 30));//Set font to Cooper Black;
                FlowPane temp = new FlowPane();//Create a FlowPane for title.
                temp.setPadding(new Insets(50,50,50,50));
                temp.getChildren().add(repName);//Add the title.
                temp.setAlignment(Pos.TOP_CENTER);//Center the title.
                
                //Create a Vbox to show the report...
                VBox reportScreen = new VBox();
                //Stylize the Vbox...
                reportScreen.setStyle("-fx-background-color: BDC3CB;");
                //Generate the report as a TableView...
                TableView<ObservableList> report = newReport(reportQuery);
                reportScreen.getChildren().addAll(buildLogo(), backPane
                        , repName, report
                        , generateExportButtons());
                //Set the main scene dimensions with root flowpane...
                Scene rep1Scene = new Scene(reportScreen, reportWidth, 900);
                primaryStage.setScene(rep1Scene);
                primaryStage.show();
                
                
            }
        });
        //Handle the action events for the second report button.
        reportTwo.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                //Whichever report generation occurs here...
                //Whichever report generation occurs here...
                String reportQuery = "SELECT * FROM VMC_Members;";
                //Create a Label to name the report...
                Label repName = new Label("Test Report From VMC_Members");
                repName.setFont(new Font("Myriad", 30));//Set font to Cooper Black;
                FlowPane temp = new FlowPane();//Create a FlowPane for title.
                temp.setPadding(new Insets(50,50,50,50));
                temp.getChildren().add(repName);//Add the title.
                temp.setAlignment(Pos.TOP_CENTER);//Center the title.
                
                //Create a Vbox to show the report...
                VBox reportScreen = new VBox();
                //Stylize the Vbox...
                reportScreen.setStyle("-fx-background-color: BDC3CB;");
                //Generate the report as a TableView...
                TableView<ObservableList> report = newReport(reportQuery);
                reportScreen.getChildren().addAll(buildLogo(), backPane
                        , repName, report
                        , generateExportButtons());
                //Set the main scene dimensions with root flowpane...
                Scene rep1Scene = new Scene(reportScreen, reportWidth, 900);
                primaryStage.setScene(rep1Scene);
                primaryStage.show();
            }
        });
        //Handle the action events for the third report button.
        reportThree.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                //Whichever report generation occurs here...
                //Whichever report generation occurs here...
                String reportQuery = "SHOW TABLES;";
                //Create a Label to name the report...
                Label repName = new Label("Test Report To Show Tables");
                repName.setFont(new Font("Myriad", 30));//Set font to Cooper Black;
                FlowPane temp = new FlowPane();//Create a FlowPane for title.
                temp.setPadding(new Insets(50,50,50,50));
                temp.getChildren().add(repName);//Add the title.
                temp.setAlignment(Pos.TOP_CENTER);//Center the title.
                
                //Create a Vbox to show the report...
                VBox reportScreen = new VBox();
                //Stylize the Vbox...
                reportScreen.setStyle("-fx-background-color: BDC3CB;");
                //Generate the report as a TableView...
                TableView<ObservableList> report = newReport(reportQuery);
                reportScreen.getChildren().addAll(buildLogo(), backPane
                        , repName, report
                        , generateExportButtons());
                //Set the main scene dimensions with root flowpane...
                Scene rep1Scene = new Scene(reportScreen, reportWidth, 900);
                primaryStage.setScene(rep1Scene);
                primaryStage.show();
            }
        });
        //Handle the action events for the exit button.
        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try{
                    
                    //Always close the resultset, the statement, 
                    //and the connection upon finishing...
                    //resultSet.close(); //Close the resultSet.
                    //statement.close(); //Close the statement.
                    connection.close(); //Close the connection.
                } catch (Exception closeError) {
                    System.out.println("ERROR: Could not close "
                            + "resultset/statement/connection");
                }finally {
                    System.exit(0);
                }
            }
        });
        
        //Create the rootNode with a flow layout with a vertical gap of 10...
        GridPane rootNode = new GridPane();

        //Center the controls...
        rootNode.setAlignment(Pos.CENTER);
        
        //Create username and password textfield boxes...
        TextField userName = new TextField();//Username.
        TextField passWord = new TextField();//Password.
        //Set the prompt text for the textfields.
        userName.setPromptText("Enter username.");//Username.
        passWord.setPromptText("Enter password.");//PassWord.
        //Create labels to go next to the textfields...
        Label logLabel = new Label("Username: ");
        Label passLabel = new Label("Password: ");
        //Create a login button to start login process...
        Button login = new Button("Log In");
        //Stylize the login button...
        login.setStyle("-fx-background-color: " + accentThree);
        
        
        //Create a Vbox to hold the Title and Grid with login...
        VBox gridAndTitle = new VBox();
        gridAndTitle.setStyle("-fx-background-color: BDC3CB;"); //Stylize the FlowPane.
        FlowPane titles = buildLogo(); //Create a flowpane for the title.
        
        //Set constraints for the grid...
        GridPane.setConstraints(logLabel, 0, 1);//Format the login label.
        GridPane.setConstraints(userName, 1, 1);//Format the username textfield.
        GridPane.setConstraints(passLabel, 0, 2);//Format the password label.
        GridPane.setConstraints(passWord, 1, 2);//Format the password texfield.
        GridPane.setConstraints(login, 0, 4);//Format the login button.
        //Set the column constraints for the 
        rootNode.getColumnConstraints().add(new ColumnConstraints(100));
        
        //Finish adding to login page to rootNode...
        rootNode.getChildren().addAll(logLabel, userName);
        rootNode.getChildren().addAll(passLabel, passWord);
        rootNode.getChildren().add(login);
        //Add titles and rootNode to the VBox for Grid and Title...
        gridAndTitle.getChildren().addAll(titles, rootNode);
        
        //Set the main scene dimensions with root flowpane...
        Scene scene = new Scene(root, 500, 450);
        //Set the login screen dimensions with rootNode flowpane...
        Scene logScene = new Scene (gridAndTitle, 500, 425);
        
        login.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                //If the currently entered credentials are equal to 
                //the hardcoded credentials, login...
                if (userName.getText().equals("admin") 
                        && passWord.getText().equals("password")) {
                    System.out.println("Login successful...");
                    loggedIn = true; //set logged in to true...
                    //Set the primary stage to the scene with the reports...
                    primaryStage.setScene(scene);//Set the Scene.
                    primaryStage.show();//Show the stage.
                } 
                else {
                    //Send error message to the console stating login failed.
                    //DO NOT change loggedIn flag.
                    System.out.println("ERROR: Login failed....");
                }
            }
        });
        //Handle the action events for the exit button.
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                //Set the primary stage to the scene with the reports...
                    primaryStage.setScene(scene);
                    primaryStage.show();//Show the stage.
            }
        });
        
        //Finish adding buttons to primaryStage...
        root.getChildren().addAll(mainTitle, optionsBar);
        //Set the title for the primary stage...
        primaryStage.setTitle("Varsity Mug Club");
        
        //If not yet logged in, show the login screen...
        if (!loggedIn){
            //Set primary stage scene to the login scene while the user
            //is not logged in...
            primaryStage.setScene(logScene);
        }
        primaryStage.setResizable(true);//Set the stage to be resizable.
        primaryStage.show();//Show the primary stage.

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Start by getting the connection upon running the program...
        try {
            //Establish connection to database...
            connection = DriverManager.getConnection(DATABASE_URL, userName
                    , password);
            
            //Launch the JAVAFX application...
            launch(args);

        } catch (SQLException e) {
            //If a SQL Exception occurs, notify the user via the console...
            System.out.println("ERROR: SQLException.");
        }
        finally {
            try{
                connection.close(); //Close the connection.
            } catch (Exception e) {
                System.out.println("ERROR: Issue closing connection.");
            }
        }
    }
    
    /**
     * This method is a test method that prints the records in the result set.
     * It is currently set up to print 12 columns of information that correspond
     * to the VMC_Members table. Will need to be reworked to accommodate other 
     * tables and function with the GUI.
     * 
     * @param temp - A ResultSet object used to print the report.
     */
    public static void printReport(ResultSet temp){
        try {
        //Test print the query...
        while (temp.next()) {
            
            //Each value for the current record is grabbed via the .getObject()
            //method. The column being grabbed is indicated by the number passed
            //in.
            System.out.print(temp.getObject(1) + " ");
            System.out.print(temp.getObject(2) + " ");
            System.out.print(temp.getObject(3) + " ");
            System.out.print(temp.getObject(4) + " ");
            System.out.print(temp.getObject(5) + " ");
            System.out.print(temp.getObject(6) + " ");
            System.out.print(temp.getObject(7) + " ");
            System.out.print(temp.getObject(8) + " ");
            System.out.print(temp.getObject(9) + " ");
            System.out.print(temp.getObject(10) + " ");
            System.out.print(temp.getObject(11) + " ");
            System.out.print(temp.getObject(12) + " ");
            System.out.println();   
        }
        }catch (SQLException e) {
            System.out.println();
        }
    }
            
    /**
     * This method is used to generate a new Statement and ResultSet based on 
     * which report is chosen to be executed. It is passed a String
     * representing the report query as a parameter. Upon calling to the
     * generateTable() method and retrieving a TableView of the ResultSet,
     * the ResultSet and Statement are closed
     * 
     * @param reportQuery - A String representing the desired report query.
     * @return table - A TableView object that displays the ResultSet.
     */
    public static TableView newReport(String reportQuery) {
        TableView table;
        try {
            //Generate a new statement, with this report query...    
            Statement tempStatement = connection.createStatement();
            //Create a new result set as well...
            ResultSet tempResults = tempStatement.executeQuery(reportQuery);
            
            //This is used to set the TableView to the one retrieved 
            //by instantiating the generateTable() method...
            table = generateTable(tempResults);
            
            //TODO: Link to method that displays the new table instead of the 
            //other elements on screen...
            printReport(tempResults); //FIXME: This is used for testing...
            
            //Finally close the Statement and ResultSet...
            tempStatement.close();//Close the Statement.
            tempResults.close();//Close the ResultSet.
            
            //Finally, return table...
            return table;
            
        } catch (Exception state) {
            //If an exception occurs, inform the user...
            System.out.println("ERROR: Could not generate report.");
        }
        //If there is an issue, return a blank tableview instead...
        System.out.println("Issue generating report... Blank Table returned.");
        //Return empty TableView...
        return new TableView();
    }
    
    /**
     * This method is used to generate a Text-Based Title within a FlowPane
     * Object that is stylized and centered on the screen. 
     * 
     * @return titles - A FlowPane object that holds a text-based VMC title.
     */
    public static FlowPane buildTitle(){
        
        FlowPane titles = new FlowPane(); //Create a flowpane for the title.
        //Create a label for the title...
        Label myLabel = new Label("Varsity Mug Club");
        //Create a label for my name...
        Label myName = new Label("\t\t\t\t\t\tPenn Steak Pub & Grill");
        myName.setFont(new Font("Myriad", 20));//Set font to Cooper Black
        myLabel.setFont(new Font("Myriad", 60));//Set font to Cooper Black
        
        //Add labels to title flowpane...
        titles.getChildren().addAll(myName, myLabel);
        
        //Center the titles...
        titles.setAlignment(Pos.CENTER);
        
        //Return titles flowpane...
        return titles;
    }
    
    /**
     * This method is used to generate an Image-Based Title within a FlowPane
     * Object that is centered at the top of the screen. 
     * 
     * @return titles - A FlowPane object that holds an image-based VMC title.
     */
    public static FlowPane buildLogo(){
        //Create a string to hold the imageURL...
        String imageURL = "Penn Steak Transparent.png";
        //Create a new image view from the chosen image...
        ImageView logo = new ImageView();
        //Set an input stream to the image file...
        Image image = null;
        
        try {
            //Create a FileInputStream to read the data from the image...
            FileInputStream input = new FileInputStream(imageURL);
            //Set the Image to the Image URL and read the data...
            image = new Image(input);
            //Close the input stream...
            input.close();
        } catch (FileNotFoundException e) {
            //If there is no image found...
            System.out.println("ERROR: Issue Finding the Image.");
        } catch (IOException eeks) {
            //If there is an issue reading the image...
            System.out.println("ERROR: Issue Reading the Image.");
        }
        //Set the imageview to the logo image...
        logo.setImage(image);
        logo.setFitHeight(300);//Set the height.
        logo.setFitWidth(500);//Set the width.
        
        //Create a flowpane for the title logo.
        FlowPane titles = new FlowPane(); 
        //Add the imageview to the flowpane for positioning...
        titles.getChildren().add(logo);
        //apply top center positioning...
        titles.setAlignment(Pos.TOP_CENTER);
        
        //Return the flowpane...
        return titles;
    }
    
    /**
     * This method creates the cosmetic buttons that appear to allow the user
     * to export a report to a PDF or CSV. Actual export of reports is 
     * nonfunctional and the buttons are for appearance only.
     * 
     * @return exportOptions - A ButtonBar object that holds non-functional
     *                         export buttons.
     */
    public static ButtonBar generateExportButtons(){
        //Create a button bar to hold all of the buttons...
        ButtonBar exportOptions = new ButtonBar();
        //Create media player buttons...
        Button exportPDF = new Button("Export to PDF"); //Plays the song.
        Button exportCSV = new Button("Export to CSV"); //Pauses the song in place.
        
        //Set the button styles...
        exportPDF.setStyle("-fx-background-color: " + accentThree); //PDF
        exportCSV.setStyle("-fx-background-color: " + accentThree); //CSV
        
        //Add buttons to the button Bar...
        exportOptions.getButtons().addAll(exportPDF, exportCSV);
        //Set padding for the button bar so it is spaced from TableView...
        exportOptions.setPadding(new Insets(25));
        
        //Return the ButtonBar...
        return exportOptions;
    }
    
    /**
     * This method is used to generate a new Statement and ResultSet based on 
     * which report is chosen to be executed. It is passed a String
     * representing the report query as a parameter. Upon calling to the
     * generateTable() method and retrieving a TableView of the ResultSet,
     * the ResultSet and Statement are closed
     * 
     * @param results - A ResultSet used to create the TableView of the desired
     *                  report query.
     * @return table - A TableView object that displays the ResultSet.
     */
    public static TableView generateTable (ResultSet results) {
        //Create a tableView object to return to the GUI...
        TableView table = new TableView();
        //Make the TableView Editable...
        table.setEditable(true); 
        //Create an observable list to make the TableView Object...
        ObservableList<ArrayList<String>> tList = FXCollections.observableArrayList();
        //Create a single arrayList of strings to save meta data column names...
        ArrayList<String> columns = new ArrayList<>();
        
        try{
            //Get the ResultSet Metadata to find the column count...
            ResultSetMetaData resMeta = results.getMetaData(); 
            //Create an integer to store the column count and assign it...
            int numCol = resMeta.getColumnCount();
            //Loop through record...
            while (results.next()) {
                //Create a counter to be reset to 0 each iteration of the outer
                //loop to go through each record's data values...
                int i = 0;
                
                //First add the column name..
                //columns.add(resMeta.getColumnName(i+1));
                
                //Create an ArrayList for each individual record...
                ArrayList<String> currentRow = new ArrayList<>();
                
                //This should grab as many objects are in the current row
                //and add them to the current row list...
                while (i < numCol) {
                    //Make sure to add 1 to I since SQL column counts do not
                    //start at 0...
                    //this is to test...
                    System.out.print(results.getObject(i + 1).toString());
                    //Add to the row's arraylist...
                    currentRow.add(results.getObject(i+1).toString());
                    //Increment the counter...
                    i++;
                    
                }
                //Finally, add the current row which represents the current
                //record to the observable list.
                tList.add(currentRow);
                System.out.println();//For testing purposes...
            }
            
            //Create another counter for the columns to grab column names...
            int i = 0;
            
            
            /*
            //Go through the columns and store the metadata...
            while (i < numCol) {
                //First add the column name..
                columns.add(resMeta.getColumnName(i+1));
                //increment the counter..
                i++;
            }
            */

            //Set the tableview to use the arraylists as items...
            table.setItems(tList);
            
            //Iterate through the resultset metadata and assign the column
            //names to the tableview...
            for (int j = 0; j < numCol; j++) {
                //System.out.println(resMeta.getColumnName(j+1));
                TableColumn<ArrayList<String>, String> colTitle = new TableColumn<>(resMeta.getColumnName(j+1));
                colTitle.setMinWidth(125);
                colTitle.setCellValueFactory(new PropertyValueFactory<>(resMeta.getColumnName(j+1)));
                table.getColumns().addAll(colTitle);//TODO: FIX THIS     
            }
 
            for(ArrayList<String> list : tList) {
                System.out.println(list.toString());
            }
            
            //Set the temporary report screen width to
            //the number of tables * 125 (column width)...
            int tempRepWidth = numCol * 125;
            
            //If the temp width is less than 600, set report width to 600,
            //otherwise, set to tempRepWidth...
            if (tempRepWidth < 600) {
                reportWidth = 600;//Set to 600.
            }else {
                reportWidth = tempRepWidth;//Set to tempRepWidth.
            }
            
        }catch (SQLException e) {
            //If SQLException, inform the user...
            System.out.println("SQLEXCEPTION ERROR while generating tables.");
        }catch (Exception eeks){
            //If other exception, inform the user...
            System.out.println(eeks);
        }
        
        //Return the TableView...
        return table;
    }
}