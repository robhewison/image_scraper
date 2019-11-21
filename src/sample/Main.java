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

    private String urlMain = "https://unsplash.com/t/wallpapers";
    ArrayList<String> urls = new ArrayList<String>();

    public void setUrlMain(String urlMain) {
        this.urlMain = urlMain;
    }

    public String getUrlMain() {
        return urlMain;
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

        Text scenetitle = new Text("Website Image Ripper");
        scenetitle.setFont(Font.font("Helvetica", FontWeight.NORMAL, 30));
        HBox title = new HBox();
        title.setAlignment(Pos.CENTER);
        title.getChildren().addAll(scenetitle);
        grid.add(scenetitle, 0, 0, 2, 1);

        Label urlName = new Label("URL:");
        TextField userTextField = new TextField();
        Button btn = new Button("Download");
        Button btn2 = new Button("Exit");

        HBox textfield = new HBox();
        textfield.setSpacing(5.0);
        HBox.setHgrow(urlName, Priority.ALWAYS);
        HBox.setHgrow(userTextField, Priority.ALWAYS);
        urlName.setMinWidth(25);
        userTextField.setPrefWidth(100000);
        textfield.getChildren().addAll(urlName, userTextField);
        grid.add(textfield, 0, 1, 3, 1);

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);
        HBox.setHgrow(btn, Priority.ALWAYS);
        HBox.setHgrow(btn2, Priority.ALWAYS);
        btn.setPrefWidth(100000);
        btn2.setPrefWidth(100000);
        hbButtons.getChildren().addAll(btn, btn2);
        grid.add(hbButtons, 0, 2, 3, 1);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 3);

        ImageRip rip = new ImageRip(getUrlMain());

        TextArea text = new TextArea();
        rip.setWebsite(getUrlMain());
        text.setText("");
        VBox textdisplay = new VBox();
        textdisplay.setSpacing(10.0);
        VBox.setVgrow(text, Priority.ALWAYS);
        text.setPrefHeight(100000);
        textdisplay.getChildren().addAll(text);
        int caretPosition = text.caretPositionProperty().get();
        grid.add(textdisplay, 0, 4, 3, 1);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                String url = String.valueOf(userTextField.getText());
                setUrlMain(url);
                text.setText("");
                rip.setWebsite(getUrlMain());
                urls.addAll(rip.getImages());
                text.setText(rip.getImageAmount());
                for (Object o : urls) {
                    text.appendText("\n");
                    text.appendText((String) o);
                }
                text.positionCaret(caretPosition);
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText(url + " downloaded");
            }
        });

        btn2.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });

        stage.setTitle("Image Ripper v0.1.10");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
