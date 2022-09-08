package ui;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;

import data.Pun;
import data.PunManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainSceneController {

	PunManager pm = new PunManager();
	
    @FXML
    private Button add_btn;

    @FXML
    private Button copy_btn;

    @FXML
    private TableColumn<Pun, Integer> id_column;

    @FXML
    private TableColumn<Pun, String> pun_column;

    @FXML
    private Button random_btn;

    @FXML
    private Button remove_btn;

    @FXML
    private TextField text_entry;

    @FXML
    private TableView<Pun> text_table;

    @FXML
    private Button update_btn;
    
    @FXML
    private Button updatechange_btn;
    
    @FXML
    private Button cancelchange_btn;
    
    
    
    @FXML
    public void initialize() {
    	cancelchange_btn.setVisible(false);
		updatechange_btn.setVisible(false);
    	id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
    	pun_column.setCellValueFactory(new PropertyValueFactory<>("text"));

    	pm.load_puns();
    	
    	for(int i = 0; i < pm.size(); i++) {
    		text_table.getItems().add(pm.getPun(i));
    	}
    	
    }
    
	// Event Listener on MenuItem[#add_btn].onAction
	@FXML
	public void addText(ActionEvent event) {
		
		Pun p = pm.createPun(text_entry.getText());
		text_table.getItems().add(p);
		
		pm.save_puns();
		//text_list.getItems().add(text_entry.getText());
	}
	// Event Listener on MenuItem[#update_btn].onAction
	@FXML
	public void updateText(ActionEvent event) {
		
		if(text_table.getSelectionModel().getSelectedItem() == null)
			return;
		
		Pun selected_pun = text_table.getSelectionModel().getSelectedItem();
		text_entry.setText(selected_pun.getText());
		
		cancelchange_btn.setVisible(true);
		updatechange_btn.setVisible(true);
		add_btn.setVisible(false);
		remove_btn.setVisible(false);
    	update_btn.setVisible(false);
	}
	// Event Listener on MenuItem[#remove_btn].onAction
	@FXML
	public void removeText(ActionEvent event) {
		Pun selected_pun = text_table.getSelectionModel().getSelectedItem();
		text_table.getItems().remove(selected_pun);
		
		pm.removePun(selected_pun);
		pm.save_puns();
		text_table.refresh();
	}
	// Event Listener on Button[#copy_btn].onAction
	@FXML
	public void copyText(ActionEvent event) {
		Pun selected_pun = text_table.getSelectionModel().getSelectedItem();

		StringSelection stringSelection = new StringSelection(selected_pun.getText());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}
	// Event Listener on Button[#random_btn].onAction
	@FXML
	public void selectRandom(ActionEvent event) {
		
		if(pm.size() == 0)
			return;
		
		int random_index = (int) Math.floor(Math.random() * Double.valueOf(pm.size()));
		
		text_table.getSelectionModel().select(pm.getPun(random_index));
		text_entry.setText(pm.getPun(random_index).getText());
	}
	
    @FXML
    void updateChange(ActionEvent event) {
    	Pun selected_pun = text_table.getSelectionModel().getSelectedItem();
    	selected_pun.setText(text_entry.getText());
    	
    	text_table.refresh();
    	pm.save_puns();
    	
    	cancelchange_btn.setVisible(false);
    	updatechange_btn.setVisible(false);
    	add_btn.setVisible(true);
    	remove_btn.setVisible(true);
    	update_btn.setVisible(true);
    	
    }
    

    @FXML
    void cancelChange(ActionEvent event) {
    	text_entry.clear();
    	cancelchange_btn.setVisible(false);
    	updatechange_btn.setVisible(false);
    }
}
