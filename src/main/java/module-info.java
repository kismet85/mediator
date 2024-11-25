module localdb.mediator {
    requires javafx.controls;
    requires javafx.fxml;


    opens localdb.mediator to javafx.fxml;
    exports localdb.mediator;
}