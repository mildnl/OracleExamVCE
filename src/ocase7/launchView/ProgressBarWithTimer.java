/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7.launchView;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

/**
 *
 * @author PaulsBook
 */
public class ProgressBarWithTimer {
    
    private int starttime = 9;
    private Timeline timeline;

    public int getStarttime() {
        return starttime;
    }

    public void setStarttime(int starttime) {
        this.starttime = starttime;
    }

    public ProgressBarWithTimer() {
    }
    
    /**
     * bindet ein {@link ProgressBar ProgressBar} Object an die eingegebene Startzeit,<br> 
     * setzen der Koordinaten und der Breite für die ProgressBar erforderlich
     * <br>
     * <br>
     * Returns the {@link Timeline Timelines} Object for the scene  
    **/ 
    public Timeline createTimelineForProgressBar(ProgressBar progressBar, int starttime, double minWidth, double layoutXPos, double layoutYPos) {
        IntegerProperty timeSeconds = new SimpleIntegerProperty(starttime * 100);
//        final DoubleBinding divide = timeSeconds.divide(starttime * 100.0);
//        final DoubleBinding multiply = divide.subtract(1).multiply(-1);
//        progressBar.progressProperty().bind(multiply);
        progressBar.progressProperty().bind(
                timeSeconds.divide(starttime * 100.0).subtract(1).multiply(-1));
        progressBar.setMinWidth(minWidth);
        progressBar.setLayoutX(layoutXPos);
        progressBar.setLayoutY(layoutYPos);
        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(starttime + 1),
                        new KeyValue(timeSeconds, 0)));
        return timeline;
    }
    
    /**
     * bindet ein {@link ProgressBar ProgressBar} Object an die eingegebene Startzeit,<br> 
     * setzen der Koordinaten und der Breite für die ProgressBar NICHT erforderlich<br>
     * erwartet eine ProgressBar und int startzeit <br>
     * <br>
     * Return the {@link Timeline Timelines} Object for the scene  
    **/ 
    public Timeline createTimelineForProgressBar(ProgressBar progressBar, int starttime) {
        IntegerProperty timeSeconds = new SimpleIntegerProperty(starttime * 100);
//        final DoubleBinding divide = timeSeconds.divide(starttime * 100.0);
//        final DoubleBinding multiply = divide.subtract(1).multiply(-1);
//        progressBar.progressProperty().bind(multiply);
        progressBar.progressProperty().bind(
                timeSeconds.divide(starttime * 100.0).subtract(1).multiply(-1));
        progressBar.setMinWidth(280);
        progressBar.setLayoutX(160);
        progressBar.setLayoutY(420);
        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(starttime + 1),
                        new KeyValue(timeSeconds, 0)));
        return timeline;
    }
}
