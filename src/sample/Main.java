package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class Main extends Application {
    public final int height = 720;
    public final int width = 1280;
    int size = 30;
    int quote_num = 77;

//Get access to dango music everywhere here
    Media sound = new Media(new File("src/music.mp3").toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(sound);
    Random random = new Random();
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Use random to get random quotes
        Random random = new Random();
        int rand = random.nextInt(quote_num - 1);
        //We create an array so we can modify it everywhere, second value is total num of quotes
        final int[] rand1 = {5 + random.nextInt(5), 0};
        //Creating path so we won't mess everything up
        final Path[] path = {Paths.get("src/Quotes.txt")};


        String quotes[] = new String[quote_num - 1];
        //I didn't know the other effective way so let's stick to traditions
        for(int i = 0; i < quote_num - 1; i++ ){
            quotes[i] = Files.readAllLines(path[0]).get(i);
        }

        String read = quotes[rand];
        //Creating a label representing a quote
        Label label = new Label(read);
        //Creating a label representing quotes left to see
        Label tot_quot = new Label(String.valueOf(rand1[0]));

        //Configuring tot_quot label
        tot_quot.setFont(new Font("Arial", size+20));
        tot_quot.setTranslateY(height - height/7.0);
        tot_quot.setTranslateX(width / 10.0);


        //Configuring label label
        label.setFont(new Font("Times_New_Roman", 30));
        label.setWrapText(true);
        label.setTranslateX(size + 10);
        label.setTranslateY(size + 10);
        label.setMaxWidth(width / 2.0);

        //Use this Class so we can set path instead of url
        FileInputStream inputstream = new FileInputStream("src/image.jpg");
        //Adding image "in the background", makes it look more decent
        ImageView image = new ImageView(new Image(inputstream));
        image.setOpacity(0.3);

        //Set in group with name "root" and show in scene later

        Button button = new Button("Next");
        button.setPrefSize(200, 50);
        button.setTranslateX(width - 300);
        button.setTranslateY(height - 100);

        //Actions on clicking the button
        button.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                String read = quotes[10];
                rand1[0] -= 1;
                rand1[1]++;
                if(rand1[0] > 0){
                    magic(rand1[0], width, height, size, quote_num, label, tot_quot, image, button, primaryStage);
                }else{
                    mediaPlayer.stop();
                    dameDane(width, height, primaryStage);
                }
            }

        });

        Group root = new Group();
        root.getChildren().addAll(label, image, button, tot_quot);

        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));


        mediaPlayer.setVolume(0.05);
        mediaPlayer.play();

        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dango!");
        primaryStage.show();
/*        for(int i = 0; i < 10; i++){
            Scene scene1 = new Scene(root,width, height);
        }*/



    }
    public static void magic(int random, int width, int height, int size,  int quotes, Label label, Label tot_quot,
                             ImageView image, Button button, Stage primaryStage){
        Random random1 = new Random();
        int rand = random1.nextInt(quotes - 1);

        Path path = Paths.get("src/Quotes.txt");
        String quote[] = new String[quotes - 1];
        //I didn't know the other effective way so let's stick to traditions
        for(int i = 0; i < quotes - 1; i++ ){
            try {
                quote[i] = Files.readAllLines(path).get(i);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String read = quote[rand];

        //Creating a label representing a quote
        label = new Label(read);
        //Creating a label representing quotes left to see
        tot_quot = new Label(String.valueOf(random));

        //Configuring tot_quot label
        tot_quot.setFont(new Font("Arial", size+20));
        tot_quot.setTranslateY(height - height/7.0);
        tot_quot.setTranslateX(width / 10.0);


        //Configuring label label
        label.setFont(new Font("Times_New_Roman", 30));
        label.setWrapText(true);
        label.setTranslateX(size + 10);
        label.setTranslateY(size + 10);
        label.setMaxWidth(width / 2.0);


        Group root = new Group();
        root.getChildren().addAll(label, image, button, tot_quot);
        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void dameDane(int width, int height, Stage primaryStage){

        Media media = new Media(new File("src/damero.mp4").toURI().toString());
        MediaPlayer video = new MediaPlayer(media);
        MediaView mediaView = new MediaView(video);


        Group root = new Group();
        root.getChildren().add(mediaView);

        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
        video.play();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
