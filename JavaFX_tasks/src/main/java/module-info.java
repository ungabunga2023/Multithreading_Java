module com.example.javafx_tasks {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.javafx_tasks.module4_lesson4;
    opens com.example.javafx_tasks.module4_lesson4 to javafx.fxml;
}