/*
 * Date: 05/06/19
 * Author: Dush Hewa
 */
package hellodraganddrop;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javafx.scene.control.Hyperlink;

public class HelloDragAndDrop extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Drag And Drop Cart");
        Group root = new Group();
        Scene scene = new Scene(root, 400, 260);
        scene.setFill(Color.ANTIQUEWHITE);
        //add text with defferent items  
        final Text cpu = new Text(50, 30, "CPU");
        final Text memory = new Text(50, 70, "Memory");
        final Text mBoard = new Text(50, 120, "Mother Board");
        final Text gpu = new Text(50, 170, "GPU");
        final Text Keyboard = new Text(50, 220, "Keyboard");
        //adding a textarea as the target
        final TextArea target = new TextArea();
        target.setWrapText(true);
        target.setLayoutX(200);
        target.setLayoutY(20);
        target.setPrefWidth(150);
        target.setPrefHeight(200);

        //add a help Hyperlink to the application
        Hyperlink link = new Hyperlink();
        link.setText("Click here to get Help");
        link.setLayoutX(270);
        link.setLayoutY(230);
        link.setOnAction(e -> {
            String url = "help.html"; // the help files, e.g. index.html 
            File htmlFile = new File(url); // file object of the help file 
            try {
                Desktop.getDesktop().browse(htmlFile.toURI()); // help file in a default browser 
            } catch (IOException ex) {
                System.out.println("IOException occrus: " + ex);
            }
        });

        //Use Lambda Expression for all the event handling methods
        //start drag-and-drop gesture for all the text items
        cpu.setOnDragDetected(e -> {
            Dragboard db = cpu.startDragAndDrop(TransferMode.MOVE);
            /* put a string on dragboard */
            ClipboardContent content = new ClipboardContent();
            content.putString(cpu.getText());
            db.setContent(content);
            e.consume();
        });
        memory.setOnDragDetected(e -> {
            Dragboard db = memory.startDragAndDrop(TransferMode.MOVE);
            /* put a string on dragboard */
            ClipboardContent content = new ClipboardContent();
            content.putString(memory.getText());
            db.setContent(content);
            e.consume();
        });
        mBoard.setOnDragDetected(e -> {
            Dragboard db = mBoard.startDragAndDrop(TransferMode.MOVE);
            /* put a string on dragboard */
            ClipboardContent content = new ClipboardContent();
            content.putString(mBoard.getText());
            db.setContent(content);
            e.consume();
        });
        gpu.setOnDragDetected(e -> {
            Dragboard db = gpu.startDragAndDrop(TransferMode.MOVE);
            /* put a string on dragboard */
            ClipboardContent content = new ClipboardContent();
            content.putString(gpu.getText());
            db.setContent(content);
            e.consume();
        });
        Keyboard.setOnDragDetected(e -> {
            Dragboard db = Keyboard.startDragAndDrop(TransferMode.MOVE);
            /* put a string on dragboard */
            ClipboardContent content = new ClipboardContent();
            content.putString(Keyboard.getText());
            db.setContent(content);
            e.consume();
        });

        //data is dragged over the target
        target.setOnDragOver(e -> {
            if (e.getGestureSource() != target && e.getDragboard().hasString()) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
            e.consume();
        });

        //drag-and-drop gesture entered the target        
        target.setOnDragEntered(e -> e.consume());

        //mouse moved away, remove the graphical cues
        target.setOnDragExited(e -> e.consume());

        // data dropped in to the textarea
        target.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                target.appendText(db.getString());
                target.appendText("\n");
                success = true;
            }
            e.setDropCompleted(success);
            e.consume();
        });

        // data was successfully copied
        cpu.setOnDragDone(e -> e.consume());
        memory.setOnDragDone(e -> e.consume());
        mBoard.setOnDragDone(e -> e.consume());
        gpu.setOnDragDone(e -> e.consume());
        Keyboard.setOnDragDone(e -> e.consume());

        // add text items to the root and diaplay on screen     
        root.getChildren().add(link);
        root.getChildren().add(cpu);
        root.getChildren().add(memory);
        root.getChildren().add(mBoard);
        root.getChildren().add(gpu);
        root.getChildren().add(Keyboard);
        root.getChildren().add(target);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);

    }
}
