package com.tech;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StudentManagementSystemGUI extends Application {
	private StudentManagementSystem sms = new StudentManagementSystem();

	private TextField nameField = new TextField();
	private TextField rollNumberField = new TextField();
	private TextField gradeField = new TextField();
	private TextArea displayArea = new TextArea();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Student Management System");

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(50, 10, 10, 400));
		grid.setVgap(8);
		grid.setHgap(10);
		grid.setStyle(
				"-fx-background-color: white; -fx-border-color: #009900; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5;");

		Label nameLabel = new Label("Name:");
		GridPane.setConstraints(nameLabel, 0, 0);
		GridPane.setConstraints(nameField, 1, 0);
		nameField.setStyle("-fx-border-color: #009900;");

		Label rollNumberLabel = new Label("Roll Number:");
		GridPane.setConstraints(rollNumberLabel, 0, 1);
		GridPane.setConstraints(rollNumberField, 1, 1);
		rollNumberField.setStyle("-fx-border-color: #009900;");
		
		Label gradeLabel = new Label("Grade:");
		GridPane.setConstraints(gradeLabel, 0, 2);
		GridPane.setConstraints(gradeField, 1, 2);
		gradeField.setStyle("-fx-border-color: #009900;");
		
		Button addButton = new Button("Add Student");
		addButton.setStyle("-fx-background-color: #009900; -fx-text-fill: white;");
		addButton.setPrefWidth(230);
		GridPane.setConstraints(addButton, 0, 3);
		addButton.setOnAction(e -> addStudent());

		Button removeButton = new Button("Remove Student");
		removeButton.setStyle("-fx-background-color: #009900; -fx-text-fill: white;");
		removeButton.setPrefWidth(230);
		GridPane.setConstraints(removeButton, 1, 3);
		removeButton.setOnAction(e -> removeStudent());

		Button searchButton = new Button("Search Student");
		searchButton.setStyle("-fx-background-color: #009900; -fx-text-fill: white;");
		searchButton.setPrefWidth(230);
		GridPane.setConstraints(searchButton, 0, 4);
		searchButton.setOnAction(e -> searchStudent());

		Button displayButton = new Button("Display All Students");
		displayButton.setStyle("-fx-background-color: #009900; -fx-text-fill: white;");
		displayButton.setPrefWidth(230);
		GridPane.setConstraints(displayButton, 1, 4);
		displayButton.setOnAction(e -> displayAllStudents());

		displayArea.setPrefHeight(200);
		GridPane.setConstraints(displayArea, 0, 5, 2, 1);
		displayArea.setStyle("-fx-border-color: #009900;");

		grid.getChildren().addAll(nameLabel, nameField, rollNumberLabel, rollNumberField, gradeLabel, gradeField,
				addButton, removeButton, searchButton, displayButton, displayArea);

		Scene scene = new Scene(grid, 1350, 700);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void addStudent() {
		String name = nameField.getText();
		String rollNumber = rollNumberField.getText();
		String grade = gradeField.getText();

		if (name.isEmpty() || rollNumber.isEmpty() || grade.isEmpty()) {
			displayArea.setText("All fields are required.");
			return;
		}

		sms.addStudentInfo(new StudentInfo(name, rollNumber, grade));
		displayArea.setText("Student added successfully.");
		clearFields();
	}

	private void removeStudent() {
		String rollNumber = rollNumberField.getText();

		if (rollNumber.isEmpty()) {
			displayArea.setText("Roll number is required.");
			return;
		}

		sms.removeStudentInfo(rollNumber);
		displayArea.setText("Student removed successfully.");
		clearFields();
	}

	private void searchStudent() {
		String rollNumber = rollNumberField.getText();

		if (rollNumber.isEmpty()) {
			displayArea.setText("Roll number is required.");
			return;
		}

		StudentInfo student = sms.searchStudentInfo(rollNumber);
		if (student != null) {
			displayArea.setText(student.toString());
		} else {
			displayArea.setText("Student not found.");
		}
	}

	private void displayAllStudents() {
		StringBuilder builder = new StringBuilder();
		for (StudentInfo student : sms.getAllStudentInfos()) {
			builder.append(student).append("\n");
		}
		displayArea.setText(builder.toString());
	}

	private void clearFields() {
		nameField.clear();
		rollNumberField.clear();
		gradeField.clear();
	}
}
