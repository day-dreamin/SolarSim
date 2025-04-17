module com.example.project_htetwaiyan_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project_htetwaiyan_2 to javafx.fxml;
    exports com.example.project_htetwaiyan_2;
    exports com.example.project_htetwaiyan_2.Controller;
    opens com.example.project_htetwaiyan_2.Controller to javafx.fxml;
}