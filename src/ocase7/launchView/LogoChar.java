/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7.launchView;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author PaulsBook
 */
public class LogoChar {
    
    private Text logoText;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    
    public Text getLogoChar() {
        return logoText;
    }
    
    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() {
        return endY;
    }


    public LogoChar(Text logoText, double startX, double startY, double endX, double endY) {
        this.logoText = logoText;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }
    
    public Timeline createTimelineForLogoChars() {
        Timeline timelineForLogoChars = new Timeline();
        
        timelineForLogoChars.getKeyFrames().addAll(new KeyFrame(Duration.ZERO,
                new KeyValue(getLogoChar().translateXProperty(), getStartX()),
                new KeyValue(getLogoChar().translateYProperty(), getStartY())
        ),
                new KeyFrame(Duration.millis(450),
                        new KeyValue(getLogoChar().translateXProperty(), getEndX()),
                        new KeyValue(getLogoChar().translateYProperty(), getEndY())
                )
        );
        return timelineForLogoChars;
    }
}
