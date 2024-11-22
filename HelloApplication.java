package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;

public class HelloApplication extends Application {

    ArrayList<Person> personList = new ArrayList<>();


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {


        BorderPane mainLayout = new BorderPane();

        Label banner = new Label("Entry Form");
        banner.setStyle("-fx-font-size: 28px; -fx-font-weight: 900; -fx-text-fill: white;" +
                " -fx-font-family: 'Segoe UI Variable' ");
        banner.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        banner.setAlignment(Pos.CENTER);
        banner.setMaxWidth(Double.MAX_VALUE);
        banner.setPadding(new Insets(20));

        mainLayout.setTop(banner);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(40));
        grid.setHgap(20);
        grid.setVgap(25);


        Label name = new Label("Name");
        TextField nameText = new TextField();
        nameText.setPromptText("Your Name");

        Label fatherName = new Label("Father Name");
        TextField fatherNameText = new TextField();
        fatherNameText.setPromptText("Father Name");

        Label cnic = new Label("CNIC");
        TextField cnicText = new TextField();
        cnicText.setPromptText("CNIC Number");

        Label dob = new Label("Date of Birth");
        DatePicker dobPicker = new DatePicker();
        dobPicker.setPromptText("dd/MM/yyyy");

        Label genderLabel = new Label("Gender:");
        RadioButton male = new RadioButton("Male");
        RadioButton female = new RadioButton("Female");
        ToggleGroup genderGroup = new ToggleGroup();
        male.setToggleGroup(genderGroup);
        female.setToggleGroup(genderGroup);

        Label cityLabel = new Label("City:");
        ComboBox<String> cityComboBox = new ComboBox<>();
        cityComboBox.getItems().addAll("Lahore", "Islamabad", "Multan");
        cityComboBox.setPromptText("Select City");

        Label imageLabel = new Label("Image");
        Button imageButton = new Button("Choose Image");
        imageButton.setMinWidth(100);

        Button saveButton = new Button("Save");
        saveButton.setMinWidth(80);

        grid.add(name, 0, 0);
        grid.add(nameText, 1, 0);

        grid.add(fatherName, 0, 1);
        grid.add(fatherNameText, 1, 1);

        grid.add(cnic, 0, 2);
        grid.add(cnicText, 1, 2);

        grid.add(dob, 0, 3);
        grid.add(dobPicker, 1, 3);

        grid.add(genderLabel, 0, 4);
        grid.add(male, 1, 4);
        grid.add(female, 1, 5);

        grid.add(cityLabel, 0, 6);
        grid.add(cityComboBox, 1, 6);

        grid.add(imageLabel, 0, 7);
        grid.add(imageButton, 1, 7);

        grid.add(saveButton, 1, 8);

        VBox rightSection = new VBox(10);
        rightSection.setPadding(new Insets(20));
        rightSection.setPadding(new Insets(40));

        ImageView imageView = new ImageView();
        imageView.setFitWidth(250);
        imageView.setFitHeight(280);
        imageView.setStyle("-fx-border-color: black; -fx-border-width: 1;");

        imageButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "" +
                    ".png", ".jpg", "*.gif"));
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                Image image = new Image(file.toURI().toString());
                imageView.setImage(image);
            }
        });

        saveButton.setOnAction(e -> {

            String selectedGender = male.isSelected() ? "Male" : female.isSelected() ? "Female" : null;

            Person person = new Person(
                    nameText.getText(),
                    fatherNameText.getText(),
                    cnicText.getText(),
                    dobPicker.getValue() != null ? dobPicker.getValue().toString() : "",
                    selectedGender,
                    cityComboBox.getValue()
            );

            personList.add(person);


        });

        rightSection.getChildren().add(imageView);

        mainLayout.setLeft(grid);
        mainLayout.setRight(rightSection);

        Scene scene = new Scene(mainLayout, 800, 600);
        stage.setTitle("Form Layout Example");
        stage.setScene(scene);
        stage.show();

    }
}