package chase.pane;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.*;

public class ShowCP extends Application{
	Pane root;
	ChasePaneStarter cp;  Button testStart; 
        int score = 0;
        int secondspassed;
        
        Label scoreLabel = new Label();
        Label directions = new Label();
        boolean mouseHandler = false;
        Label timeLabel = new Label();
        Timer timer = new Timer();
        //Timer updateTimer = new Timer();
        final long endTime = 300000;
        DateFormat timeFormat = new SimpleDateFormat( "HH:mm:ss" );
        
	public void start(Stage stage) throws Exception {
            root = new Pane();
            //timeLabel.setText("Timer not started yet");
            
            
            /*final Timeline timeline = new Timeline(
    new KeyFrame(
        Duration.millis( 500 ),
        event -> {
            final long diff = endTime - System.currentTimeMillis();
            if ( diff < 0 ) {
            //  timeLabel.setText( "00:00:00" );
                timeLabel.setText( timeFormat.format( 0 ) );
            } else {
                timeLabel.setText( timeFormat.format( diff ) );
            }
        }
    )
);*/
            TimerTask task = new TimerTask(){
                @Override
                public void run() {
                   mouseHandler = false;
                        cp.last.setFill(Color.DARKSLATEBLUE);
                        directions.setText("Game Over! The correct square is in blue");
                }
            };
            
            /*TimerTask updateTime = new TimerTask(){
                @Override
                public void run(){
                    secondspassed+=1;
                    System.out.println(secondspassed);
                    timeLabel.setText("Current time = " + secondspassed);
                }
                
            
            };*/
            
            
		cp = new ChasePaneStarter(); cp.setPrefSize(400, 400); 
                directions.setText("Game not started");
        
		
		testStart = new Button("click to start new game");
		testStart.setOnAction(e->{  
                    
                    //timer.schedule(task, 5000);
                    score = 0;
                    scoreLabel.setText("current score = " + score);
                    directions.setText("click on the spawned square to play!");
                    cp.startGame();
                    mouseHandler = true;
                        });
                
                cp.setOnMouseClicked((MouseEvent ev)->{             
                    double x=ev.getX();
                    double y=ev.getY();
            
                    if(mouseHandler == true){
                    if (cp.isInSq(x, y)){
				cp.addSquare(); 
                                score +=1;
                                scoreLabel.setText("current score = "+ score);
                                }
                    else {
                        //score = 0;
                        mouseHandler = false;
                        cp.last.setFill(Color.DARKSLATEBLUE);
                        directions.setText("Game Over! The correct square is in blue");
                    }
                    }
                    else{
                        ev = null;
                    }
            
        });
				
		
        scoreLabel.setText("current score ");
        root.getChildren().addAll(testStart,cp,directions,scoreLabel,timeLabel);
		testStart.relocate(200,10);
		cp.relocate(200,100);
                directions.relocate(500, 0);
                scoreLabel.relocate(0, 0);
                timeLabel.relocate(700,200);
        Scene scene = new Scene(root, 900, 700);
        stage.setTitle("Chase Game");
        stage.setScene(scene);
        stage.show(); 
    }
		
    public static void main(String[] args) {
        launch(args);
    }	
}
