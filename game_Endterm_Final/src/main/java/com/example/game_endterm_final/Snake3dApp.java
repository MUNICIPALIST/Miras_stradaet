package com.example.game_endterm_final;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Box;
import javafx.scene.paint.Color;
import javafx.scene.*;

import java.util.Random;

public class Snake3dApp extends Application {

    private Point3D dir = new Point3D(1, 0, 0);
    private Point3D next = new Point3D(0, 0, 0);

    private Group root = new Group();
    private Group snake = new Group();

    private Cube food = new Cube(Color.YELLOW);

    private Random random = new Random();



    private double t = 0;
    private AnimationTimer timer;


    private Scene createScene() {




        Cube cube = new Cube(Color.BLUE);
        snake.getChildren().add(cube);

        food.setTranslateX(random.nextInt(10) - 5);


        root.getChildren().addAll(snake, food);

         Scene scene = new Scene(root, 1280, 720, true);

         PerspectiveCamera camera = new PerspectiveCamera(true);
         camera.getTransforms().addAll(new Translate(0, -20, -20), new Rotate(-45, Rotate.X_AXIS));

         scene.setCamera(camera);

         timer = new AnimationTimer() {
             @Override
             public void handle(long l) {
                 t += 0.016;

                 if (t > 0.1) {
                     onUpdate();
                     t = 0;
                 }
             }
         };

         timer.start();

         return  scene;
    }

    private void onUpdate() {
        next = next.add(dir);
        Cube c = (Cube) snake.getChildren().remove(0);
        c.set(next);
        snake.getChildren().add(c);

    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = createScene();

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {

                case W:
                    dir = new Point3D(0, 0, 1);
                        break;

                case S:
                    dir = new Point3D(0, 0, -1);
                    break;

                case A:
                    dir = new Point3D(-1, 0, 10);
                    break;

                case D:
                    dir = new Point3D(1, 0, 0);
                    break;

                case UP:
                    dir = new Point3D(0, -1, 1);
                    break;

                case DOWN:
                    dir = new Point3D(0, 1, 0);
                    break;

            }
        });


        stage.setScene(scene);
        stage.show();
    }

    private static  class Cube extends Box {

        public Cube(Color color) {
            super(1, 1, 1);
            setMaterial(new PhongMaterial(color));
        }

        public void set(Point3D p) {
            setTranslateX(p.getX());
            setTranslateY(p.getY());
            setTranslateZ(p.getZ());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
