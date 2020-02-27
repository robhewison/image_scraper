package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    String versionNumber = "v0.1.12"; // major.minor.patch
    private String webPageURL = "https://unsplash.com/t/wallpapers"; // This site is just a placeholder for testing
    ArrayList<String> imageURLs = new ArrayList<String>();

    public void setWebPageURL(String webPageURL) {
        this.webPageURL = webPageURL;
    }

    public String getWebPageURL() {
        return webPageURL;
    }

    @Override
    public void start(Stage stage) {

        initUI(stage);
    }

    private void initUI(Stage stage) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 423, 442);

        Text sceneTitle = new Text("Website Image Ripper");
        sceneTitle.setFont(Font.font("Helvetica", FontWeight.NORMAL, 30));
        HBox title = new HBox();
        title.setAlignment(Pos.CENTER);
        title.getChildren().addAll(sceneTitle);
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label urlName = new Label("URL:");
        TextField userTextField = new TextField();
        Button btn = new Button("Download");
        Button btn2 = new Button("Exit");

        HBox textField = new HBox();
        textField.setSpacing(5.0);
        HBox.setHgrow(urlName, Priority.ALWAYS);
        HBox.setHgrow(userTextField, Priority.ALWAYS);
        urlName.setMinWidth(25);
        userTextField.setPrefWidth(100000);
        textField.getChildren().addAll(urlName, userTextField);
        grid.add(textField, 0, 1, 3, 1);

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);
        HBox.setHgrow(btn, Priority.ALWAYS);
        HBox.setHgrow(btn2, Priority.ALWAYS);
        btn.setPrefWidth(100000);
        btn2.setPrefWidth(100000);
        hbButtons.getChildren().addAll(btn, btn2);
        grid.add(hbButtons, 0, 2, 3, 1);

        final Text actionTarget = new Text();
        grid.add(actionTarget, 1, 3);

        ImageRip rip = new ImageRip(getWebPageURL());

        TextArea text = new TextArea();
        rip.setWebsite(getWebPageURL());
        text.setText("");
        VBox textDisplay = new VBox();
        textDisplay.setSpacing(10.0);
        VBox.setVgrow(text, Priority.ALWAYS);
        text.setPrefHeight(100000);
        textDisplay.getChildren().addAll(text);
        int caretPosition = text.caretPositionProperty().get();
        grid.add(textDisplay, 0, 4, 3, 1);

        btn.setOnAction(e -> {
            setWebPageURL(String.valueOf(userTextField.getText()));
            text.setText("");
            rip.setWebsite(getWebPageURL());
            imageURLs.addAll(rip.getImages());
            text.setText(rip.getImageAmount());
            for (Object o : imageURLs) {
                text.appendText("\n");
                text.appendText((String) o);
            }
            text.positionCaret(caretPosition);
            actionTarget.setFill(Color.FIREBRICK);
            actionTarget.setText(getWebPageURL() + " downloaded");
        });

        btn2.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });

        stage.setTitle("Image Ripper " + versionNumber);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
