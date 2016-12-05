/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7;

import ocase7.view3.View3;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ocase7.launchView.LaunchView;

/**
 *
 * @author PaulsBook
 */
public class mainView extends Application {
    
     @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ocase 7");
        View3 view3 = new View3();
        Scene view3Scene =  view3.createView3();
//        LaunchView launchView = new LaunchView();
//        Scene launchViewScene = launchView.createLaunchView();
//        primaryStage.setScene(launchViewScene);
//        //LoginView loginView = new LoginView();
//        //Scene loginscene = loginView.createLoginView();
//        Task<Void> sleeper = new Task<Void>() {
//            @Override
//            protected Void call() throws Exception {
//                try {
////                    ExecutorService es = Executors.newFixedThreadPool(7);
////                    es.shutdown();
////                    es.awaitTermination(10, TimeUnit.SECONDS);
////                    es.shutdownNow();
////                    Thread thread = new Thread();
////                    thread.setDaemon(true);
//                    Thread.sleep(10000);
//                    System.out.println(Thread.activeCount());
//                } catch (InterruptedException e) {
//                    throw new ExceptionInInitializerError(e.getMessage());
//                }
//                return null;
//            }
//        };
//        
//        System.out.println(Thread.activeCount());
//        
//        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
//            @Override
//            public void handle(WorkerStateEvent event) {
//                //primaryStage.setScene(loginscene);
//            }
//        });
//         new Thread(sleeper).start();
        
        //ocaseLoginView.LoginView loginView = new LoginView();
        //Scene loginScene = loginView.createLoginView();
        primaryStage.setScene(view3Scene);
        //primaryStage.setScene(launchViewScene);
        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
