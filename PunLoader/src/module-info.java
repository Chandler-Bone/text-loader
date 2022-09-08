module PunLoader {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires java.desktop;
	
	opens application to javafx.graphics, javafx.fxml;
	opens ui to javafx.fxml;
	opens data to javafx.base;
}
