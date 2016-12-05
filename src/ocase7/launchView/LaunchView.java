/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7.launchView;

import ocase7.launchView.ProgressBarWithTimer;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author PaulsBook
 */
public class LaunchView {
    public Scene createLaunchView() {
        Group launchViewRoot = new Group();
        Scene launchViewScene = new Scene(launchViewRoot, 600, 800, Color./*GREENYELLOW*/DEEPSKYBLUE);
        

        Text logoO = new Text("O");
        setLogoStyleAndEfx(logoO);
        LogoChar o = new LogoChar(logoO, -10, 200, 130, 300);
        Timeline timelineForO = o.createTimelineForLogoChars();

        Text logoC = new Text("c");
        setLogoStyleAndEfx(logoC);
        LogoChar c = new LogoChar(logoC, -150, 500, 195, 300);
        Timeline timelineForC = c.createTimelineForLogoChars();

        Text logoA = new Text("a");
        setLogoStyleAndEfx(logoA);
        LogoChar a = new LogoChar(logoA, -90, -300, 245, 300);
        Timeline timelineForA = a.createTimelineForLogoChars();

        Text logoS = new Text("s");
        setLogoStyleAndEfx(logoS);
        LogoChar s = new LogoChar(logoS, -40, -900, 300, 300);
        Timeline timelineForS = s.createTimelineForLogoChars();

        Text logoE = new Text("e");
        setLogoStyleAndEfx(logoE);
        LogoChar e = new LogoChar(logoE, -60, -20, 345, 300);
        Timeline timelineForE = e.createTimelineForLogoChars();

        Text logoSeven = new Text("7");
        logoSeven.setFont(Font.font("Arial Black", 130));
        logoSeven.setFill(Color.RED);
        logoSeven.setEffect(new Reflection(-70, 0.7, 0.3, 0.0));
        //logoSeven.setEffect(new Lighting());
        LogoChar seven = new LogoChar(logoSeven, -100, 900, 385, 300);
        Timeline timelineForSeven = seven.createTimelineForLogoChars();

        RotateTransition rotateSeven = new RotateTransition(Duration.seconds(3), logoSeven);
        //rotateSeven.setFromAngle(0);
        rotateSeven.setToAngle(360);
        rotateSeven.setAutoReverse(true);
        rotateSeven.setCycleCount(3);
        rotateSeven.setAxis(new Point3D(180, 100, 55));
        
        ProgressBar pb = new ProgressBar();
        ProgressBarWithTimer pbwt = new ProgressBarWithTimer();
        Timeline tl = pbwt.createTimelineForProgressBar(pb, 8, 280, 160, 420);

        launchViewRoot.getChildren().addAll(logoO, logoC, logoA, logoS, logoE, logoSeven, pb);
        SequentialTransition seqTrans = new SequentialTransition(timelineForO, timelineForC, timelineForA, timelineForS, timelineForE, timelineForSeven);
        seqTrans.play();
        tl.play();
        rotateSeven.play();
        //System.out.println(tl.setOnFinished());
        return launchViewScene;
    }

    private void setLogoStyleAndEfx(Text logo) {
        //setFontFamilyColorAndSize(logo);
        logo.setEffect(new Reflection(-50, 0.7, 0.3, 0.0));
        logo.setFont(Font.font("Arial Black", 100));
        logo.setFill(Color.BLACK);
        //logo.setEffect(new Lighting());

    }
}
