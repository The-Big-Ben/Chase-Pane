
package chase.pane;

import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class ChasePaneStarter extends Pane{
	static final int SIDE = 40;  	
	Rectangle last = null;   //the last rectangle added to this CP
        
        //Button scoreboard = new Button();
        
        
	
	public ChasePaneStarter(){
		backgroundProperty().set(new Background( 
								new BackgroundFill(Color.PINK,null,null) ) );
	}
	// add one random square to this CP, entirely contained,possibly
	// overlapping other squares
	public void addSquare(){
		Rectangle square = new Rectangle();
                square.setWidth(SIDE);
                square.setHeight(SIDE);
                square.setX(Math.random()*(getWidth()-SIDE));
                square.setY(Math.random()*(getHeight()-SIDE));
                this.getChildren().add(square);
                last = square;
                
	}
	// post: true iff x,y is inside the last one in this CP
	public boolean isInSq(double x, double y){
            double xlenpos = last.getX()+ last.getWidth();//width position
            double ylenpos = last.getY()+ last.getHeight();// width position
            
            if (last.getX()<= x && x <= xlenpos && last.getY() <= y && y <= ylenpos) {
                return true;
            }
		return false;
	}
	// post:  this CP has just one random Rectangle
	public void startGame(){
            this.getChildren().clear();
            this.addSquare();
		System.out.println("New Game Started!");
	}
}
