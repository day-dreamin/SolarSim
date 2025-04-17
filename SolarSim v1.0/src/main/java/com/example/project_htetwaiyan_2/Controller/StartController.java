package com.example.project_htetwaiyan_2.Controller;

import com.example.project_htetwaiyan_2.Main;
import com.example.project_htetwaiyan_2.Model.*;
import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;

import java.util.concurrent.TimeUnit;
import javafx.scene.control.ProgressBar;

public class StartController {

    @FXML
    private AnchorPane AP;

    @FXML
    private Button AboutPbtn;

    @FXML
    private Button StartButton;

    @FXML Button SettingsBtn;

    @FXML
    private ImageView Image1;

    @FXML
    private ImageView Image2;

    @FXML
    private ImageView Image3;

    @FXML
    private Label Title;

    @FXML
    private Group Root;

    @FXML
    private ProgressBar Progress;

    @FXML
    private Label LoadingLabel;

    @FXML
    private ColorPicker ColorPicker;
    @FXML
    private Button ResetBtn;
    @FXML
    private TextArea Description;
    @FXML
    private Button RightBtn;
    @FXML
    private Button LeftBtn;
    @FXML
    private Button PausePlay;
    @FXML
    private Polygon PlaySymbol;
    @FXML
    private Rectangle PauseSymbol1;
    @FXML
    private Rectangle PauseSymbol2;
    @FXML
    private Button TutorialBtn;


    Canvas canvas;
    GraphicsContext gc;
    AnimationTimer animator;
    Camera cam = new Camera(900, 700, 0, 0);
    ArrayList<CelestialBody> CelestialObjects = new ArrayList<CelestialBody>();
    boolean updatePositions = true;
    CelestialBody Sun;
    CelestialBody OGplanet;
    Color ChosenColor = Color.WHITE;
    private String DescriptionText = "";
    private int focusIndex = 0;

    @FXML
    void AboutPClick(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.getIcons().add(new Image("planet0.png"));

            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project_htetwaiyan_2/View/AboutProgrammer.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("About the Programmer");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(AboutPbtn.getScene().getWindow());
            stage.showAndWait();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void HandleTutorial(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.getIcons().add(new Image("planet0.png"));
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project_htetwaiyan_2/View/Tutorial.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Tutorial");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(AboutPbtn.getScene().getWindow());
            stage.showAndWait();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void HandleSettingsBtn(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.getIcons().add(new Image("planet0.png"));

            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project_htetwaiyan_2/View/Settings.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Settings");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(SettingsBtn.getScene().getWindow());
            stage.showAndWait();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void ChooseColorAction(ActionEvent event) {
        ChosenColor = ColorPicker.getValue();
    }
    @FXML
    void MoveLeft(ActionEvent event) {
        focusIndex--;
        if (focusIndex < 0) {
            focusIndex = CelestialObjects.size()-1;
        }
    }

    @FXML
    void MoveRight(ActionEvent event) {
        focusIndex++;
        if (focusIndex >= CelestialObjects.size()) {
            focusIndex = 0;
        }
    }
    @FXML
    void HandleResetBtn(ActionEvent event) {
        gc.clearRect(0, 0, 900, 700);
        CelestialObjects.clear();
        focusIndex = 0;
        updatePositions = true;
        PlaySymbol.setVisible(false);
        PauseSymbol1.setVisible(true);
        PauseSymbol2.setVisible(true);
        Sun = new Star("Sun", Color.GOLD, 900 / 2, 700 / 2, 23, 20000);
        OGplanet = new Planet("Planet", Color.LIGHTBLUE, Sun.getCenterX(), Sun.getCenterY() / 2, 8, 200, 10, 0);
        CelestialObjects.add(Sun);
        CelestialObjects.add(OGplanet);
    }
    @FXML
    void PausePlayAction(ActionEvent event) {
        if (updatePositions) {
            updatePositions = false;
            PauseSymbol1.setVisible(false);
            PauseSymbol2.setVisible(false);
            PlaySymbol.setVisible(true);
        }
        else {
            updatePositions = true;
            PlaySymbol.setVisible(false);
            PauseSymbol1.setVisible(true);
            PauseSymbol2.setVisible(true);
        }
    }
    @FXML
    void StartButtonClick(ActionEvent event) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/com/example/project_htetwaiyan_2/View/MainPage.fxml"));
        Stage stage = (Stage) StartButton.getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
            stage.setTitle("SolarSim");
            stage.setScene(scene);
            stage.show();

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage mainStage = (Stage) StartButton.getScene().getWindow();
        FXMLLoader mainPage = new FXMLLoader(MainController.class.getResource("MainPage.fxml"));
        Scene scene = new Scene(mainPage.load(), 320, 240);
        //mainStage.setTitle("SolarSim");
        mainStage.setScene(scene);
        //mainStage.show();*/
        starter();
    }

    public void starter() {
        AboutPbtn.setVisible(false);
        AboutPbtn.setDisable(true);
        StartButton.setVisible(false);
        StartButton.setDisable(true);
        Title.setVisible(false);
        Title.setDisable(true);
        Image1.setVisible(false);
        Image1.setDisable(true);
        Image2.setVisible(false);
        Image2.setDisable(true);
        Image3.setVisible(false);
        Image3.setDisable(true);
        TutorialBtn.setVisible(false);
        TutorialBtn.setDisable(true);
        Random rn = new Random();

        new Thread(new Runnable() {
            @Override
            public void run() {
                LoadingLabel.setVisible(true);
                LoadingLabel.setDisable(false);
                Progress.setVisible(true);
                Progress.setDisable(false);
                int count = rn.nextInt(10) + 10;
                for (int iterator = 0; iterator <= 100; iterator += count) {
                    count = rn.nextInt(10) + 10;
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Progress.setProgress((double)iterator/100);
                }
                Progress.setVisible(false);
                Progress.setDisable(true);
                LoadingLabel.setVisible(false);
                LoadingLabel.setDisable(true);
                SettingsBtn.setVisible(true);
                SettingsBtn.setDisable(false);
                ColorPicker.setVisible(true);
                ColorPicker.setDisable(false);
                ResetBtn.setVisible(true);
                ResetBtn.setDisable(false);
                gc.clearRect(0, 0, 900, 700);
                CelestialObjects.clear();
                Sun = new Star("Sun", Color.GOLD, 900 / 2, 700 / 2, 23, 20000);
                OGplanet = new Planet("Planet", Color.LIGHTBLUE, Sun.getCenterX(), Sun.getCenterY() / 2, 8, 200, 10, 0);
                CelestialObjects.add(Sun);
                CelestialObjects.add(OGplanet);
                Description.setVisible(true);
                Description.setDisable(false);
                LeftBtn.setVisible(true);
                LeftBtn.setDisable(false);
                RightBtn.setVisible(true);
                RightBtn.setDisable(false);
                PausePlay.setVisible(true);
                PausePlay.setDisable(false);
                PauseSymbol1.setVisible(true);
                PauseSymbol2.setVisible(true);
                TutorialBtn.setVisible(true);
                TutorialBtn.setDisable(false);
                AboutPbtn.setVisible(true);
                AboutPbtn.setDisable(false);
            }
        }).start();
        /*try {
            TimeUnit.SECONDS.sleep(time);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/

        /*SettingsBtn.setVisible(true);
        SettingsBtn.setDisable(false);
        ColorPicker.setVisible(true);
        ColorPicker.setDisable(false);
        ResetBtn.setVisible(true);
        ResetBtn.setDisable(false);*/

        Stage stage = (Stage) SettingsBtn.getScene().getWindow();
        Scene scene = SettingsBtn.getScene();

        canvas = new Canvas(stage.getWidth(), stage.getHeight());
        //AP.getChildren().add(canvas);
        Root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        MouseHandler mouseHandler = new MouseHandler();
        scene.setOnMouseEntered(mouseHandler);
        scene.setOnMouseMoved(mouseHandler);
        scene.setOnMouseDragged(mouseHandler);
        scene.setOnMousePressed(mouseHandler);
        scene.setOnMouseClicked(mouseHandler);
        scene.setOnMouseReleased(mouseHandler);

        Sun = new Star("Sun", Color.GOLD, 900 / 2, 700 / 2, 23, 20000);

        OGplanet = new Planet("Planet", Color.LIGHTBLUE, Sun.getCenterX(), Sun.getCenterY() / 2, 8, 200, 10, 0);

        CelestialObjects.add(Sun);
        CelestialObjects.add(OGplanet);

        animator = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long currUpdate) {
                if ((currUpdate - lastUpdate) >= (20000000/(Math.pow(Settings.chosenSpeed, 2)))) {
                    for (CelestialBody object: CelestialObjects) {
                        object.setCelestialObjects(CelestialObjects);
                    }
                    if (Settings.IsFocusOff) {
                        SettingsBtn.setVisible(true);
                        AboutPbtn.setVisible(true);
                        ColorPicker.setVisible(true);
                        ResetBtn.setVisible(true);
                        Description.setVisible(true);
                        RightBtn.setVisible(true);
                        LeftBtn.setVisible(true);
                        PausePlay.setVisible(true);
                        if (!updatePositions) {
                            PauseSymbol1.setVisible(false);
                            PauseSymbol2.setVisible(false);
                            PlaySymbol.setVisible(true);
                        }
                        else {
                            PlaySymbol.setVisible(false);
                            PauseSymbol1.setVisible(true);
                            PauseSymbol2.setVisible(true);
                        }
                        TutorialBtn.setVisible(true);
                    }
                    else {
                        AboutPbtn.setVisible(false);
                        ColorPicker.setVisible(false);
                        ResetBtn.setVisible(false);
                        Description.setVisible(false);
                        RightBtn.setVisible(false);
                        LeftBtn.setVisible(false);
                        PausePlay.setVisible(false);
                        PauseSymbol1.setVisible(false);
                        PauseSymbol2.setVisible(false);
                        PlaySymbol.setVisible(false);
                        PlaySymbol.setVisible(false);
                        PauseSymbol1.setVisible(false);
                        PauseSymbol2.setVisible(false);
                        TutorialBtn.setVisible(false);
                    }
                    DescriptionText = CelestialObjects.get(focusIndex).click();
                    Description.setText(DescriptionText);

                    cam.focus(CelestialObjects.get(focusIndex));

                    gc.clearRect(0, 0, 900, 700);
                    //gc.setFill(Color.BLACK);
                    //gc.setRect(WIDTH, HEIGHT);

                    for (CelestialBody object: CelestialObjects) {

                        if (object instanceof Planet) {
                            Planet obj = (Planet) object;
                            if (updatePositions) {
                                obj.updatePosition();
                            }

                            obj.update(gc, cam);

                            for (int i = 1; i < obj.getTrail().size(); i++) {

                                Point2D lastPoint = obj.getTrail().get(i-1);
                                Point2D currentPoint = obj.getTrail().get(i);

                                double x1 = lastPoint.getX() - cam.getxPos();
                                double y1 = lastPoint.getY() - cam.getyPos();
                                double x2 = currentPoint.getX() - cam.getxPos();
                                double y2 = currentPoint.getY() - cam.getyPos();
                                gc.setStroke(Color.WHITE);
                                gc.strokeLine(x1, y1, x2, y2);

                            }
                        }
                        else if (object instanceof Star) {
                            object.update(gc, cam);
                        }
                    }
                    lastUpdate = currUpdate;
                }
            }
        };
        animator.start();
    }
    private class MouseHandler implements EventHandler<MouseEvent> {

        private Point2D start;
        private boolean settingVelocity = false;
        private Planet temp;
        private Line velocityLine = new Line();
        private Line LeftArrowTip = new Line();
        private Line RightArrowTip = new Line();
        MouseHandler() {
            Root.getChildren().add(velocityLine);
            //AP.getChildren().add(velocityLine);
        }
        public void handle(MouseEvent event) {
            if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {

                if (event.getButton() == MouseButton.PRIMARY) {
                    if (!settingVelocity) {
                        updatePositions = false;
                        start = new Point2D(event.getX(), event.getY());
                        temp = new Planet();

                        temp.setCenterX(start.getX());
                        temp.setCenterY(start.getY());
                        temp.setColor(ChosenColor);
                        CelestialObjects.add(temp);

                    }
                }
            }
            if (event.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
                if (!settingVelocity) {
                    //settingVelocity = false;
                    Point2D curr = new Point2D(event.getX(), event.getY());
                    double dragDist = calculateDistance(start, curr);

                    temp.setVisible(true);
                    temp.setRadius(dragDist);
                    temp.setMass(Math.min(300, (dragDist / 2) * (dragDist / 2) * Math.PI * 10));
                    temp.setCenterX(start.getX() + cam.getxPos());
                    temp.setCenterY(start.getY() + cam.getyPos());
                }
            }
            if (settingVelocity) {
                if (!event.getEventType().equals(MouseEvent.MOUSE_EXITED)) {

                    Point2D curr = new Point2D(event.getX(), event.getY());
                    velocityLine.setStartX(temp.getCenterX() - cam.getxPos());
                    velocityLine.setStartY(temp.getCenterY() - cam.getyPos());
                    velocityLine.setEndX(curr.getX());
                    velocityLine.setEndY(curr.getY());
                    velocityLine.setStroke(Color.WHITE);

                    /*double slope = (velocityLine.getStartY()-velocityLine.getEndY()) / (velocityLine.getStartX()-velocityLine.getEndX());
                    double lineAngle = Math.atan(slope);
                    double arrowAngle;
                    if (velocityLine.getStartX() > velocityLine.getEndX()) {
                        arrowAngle = Math.toRadians(45);
                    }
                    else {
                        arrowAngle = -Math.toRadians(225);
                    }

                    double arrowLength = 5;

                    LeftArrowTip = new Line();
                    LeftArrowTip.setStartX(velocityLine.getEndX());
                    LeftArrowTip.setStartY(velocityLine.getEndY());
                    LeftArrowTip.setEndX(velocityLine.getEndX() + arrowLength * Math.cos(lineAngle - arrowAngle));
                    LeftArrowTip.setEndY(velocityLine.getEndY() + arrowLength * Math.sin(lineAngle - arrowAngle));

                    RightArrowTip = new Line();
                    RightArrowTip.setStartX(velocityLine.getEndX());
                    RightArrowTip.setStartY(velocityLine.getEndY());
                    RightArrowTip.setEndX(velocityLine.getEndX() + arrowLength * Math.cos(lineAngle + arrowAngle));
                    RightArrowTip.setEndY(velocityLine.getEndY() + arrowLength * Math.sin(lineAngle + arrowAngle));

                    LeftArrowTip.setStroke(Color.WHITE);
                    RightArrowTip.setStroke(Color.WHITE);*/
                }
                if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {

                    Point2D currentPoint = new Point2D(event.getX(), event.getY());
                    double velocity = calculateDistance(start, currentPoint)/10;
                    double direction = calculateAngle(currentPoint, start);
                    temp.setxVelocity(velocity * Math.cos(direction));
                    temp.setyVelocity(velocity * Math.sin(direction)*(-1));

                    updatePositions = true;
                    settingVelocity = false;

                    velocityLine.setVisible(false);
                    //LeftArrowTip.setVisible(false);
                    //RightArrowTip.setVisible(false);
                }

            }
            else {

                if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
                    velocityLine.setVisible(true);
                    //LeftArrowTip.setVisible(true);
                    //RightArrowTip.setVisible(true);
                    settingVelocity = true;
                }
            }
        }
        double calculateDistance(Point2D start, Point2D end) {

            double xDist = end.getX() - start.getX();
            double yDist = end.getY() - start.getY();
            double dist = Math.sqrt((xDist*xDist) + (yDist*yDist));
            return dist;
        }

        public double calculateAngle(Point2D point1, Point2D point2) {
            double angle;
            double xDiff = point1.getX() - point2.getX();
            double yDiff = point1.getY() - point2.getY();
            angle = Math.atan2(xDiff, yDiff);
            angle -= Math.PI/2;
            //radians
            return angle;
        }
    }
}