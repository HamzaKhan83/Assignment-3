import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class PersonForm extends Application {
    private final ArrayList<Person> personList = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        TextField nameField = new TextField();
        TextField fatherNameField = new TextField();
        TextField cnicField = new TextField();
        DatePicker datePicker = new DatePicker();
        ComboBox<String> genderComboBox = new ComboBox<>();
        genderComboBox.getItems().addAll("Male", "Female", "Other");
        TextField cityField = new TextField();
        Label uploadedFileName = new Label();
        Button uploadButton = new Button("Upload Image");

        uploadButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) uploadedFileName.setText(file.getAbsolutePath());
        });

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            String name = nameField.getText();
            String fatherName = fatherNameField.getText();
            String cnic = cnicField.getText();
            String date = datePicker.getValue() != null ? datePicker.getValue().toString() : "";
            String gender = genderComboBox.getValue();
            String city = cityField.getText();
            String imagePath = uploadedFileName.getText();
            Person person = new Person(name, fatherName, cnic, date, gender, city, imagePath);
            personList.add(person);
            System.out.println("Person Saved: " + person);
            nameField.clear();
            fatherNameField.clear();
            cnicField.clear();
            datePicker.setValue(null);
            genderComboBox.setValue(null);
            cityField.clear();
            uploadedFileName.setText("");
        });

        Button displayButton = new Button("Display List");
        TextArea displayArea = new TextArea();
        displayArea.setEditable(false);

        displayButton.setOnAction(e -> {
            StringBuilder displayText = new StringBuilder();
            for (Person person : personList) {
                displayText.append(person.toString()).append("\n");
            }
            displayArea.setText(displayText.toString());
        });

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Father Name:"), 0, 1);
        grid.add(fatherNameField, 1, 1);
        grid.add(new Label("CNIC:"), 0, 2);
        grid.add(cnicField, 1, 2);
        grid.add(new Label("Date:"), 0, 3);
        grid.add(datePicker, 1, 3);
        grid.add(new Label("Gender:"), 0, 4);
        grid.add(genderComboBox, 1, 4);
        grid.add(new Label("City:"), 0, 5);
        grid.add(cityField, 1, 5);
        grid.add(new Label("Image:"), 0, 6);
        grid.add(uploadButton, 1, 6);
        grid.add(uploadedFileName, 1, 7);
        grid.add(saveButton, 1, 8);
        grid.add(displayButton, 1, 9);
        grid.add(displayArea, 1, 10, 2, 1);

        primaryStage.setScene(new Scene(grid, 400, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


