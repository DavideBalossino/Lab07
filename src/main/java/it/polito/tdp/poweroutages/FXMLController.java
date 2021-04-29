/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Guasti;
import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbNerc"
    private ComboBox<Nerc> cmbNerc; // Value injected by FXMLLoader

    @FXML // fx:id="txtYears"
    private TextField txtYears; // Value injected by FXMLLoader

    @FXML // fx:id="txtHours"
    private TextField txtHours; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    private Model model;
    
    @FXML
    void doRun(ActionEvent event) {
    	txtResult.clear();
    	String x=txtYears.getText();
    	Integer anni=0;
    	try {
    		anni=Integer.parseInt(x);
    	} catch(NumberFormatException e) {
    		txtResult.setText("Inserire come anni un numero");
    		return;
    	}
    	String y=txtHours.getText();
    	Integer ore=0;
    	try {
    		ore=Integer.parseInt(y);
    	} catch(NumberFormatException e) {
    		txtResult.setText("Inserire come ore un numero");
    		return;
    	}
    	
    	Nerc nerc=cmbNerc.getValue();
    	if(nerc==null) {
    		txtResult.setText("Scegliere un nerc");
    		return;
    	}
    	
    	StringBuilder sb=new StringBuilder();
    	List<Guasti> l=model.worstCase(nerc, anni, ore);
    	sb.append("Tot people affected: "+model.totPersone()+"\n");
    	
    	sb.append("Tot hours of outage: "+model.totDiss()+"\n");
    	
    	
    //	System.out.println(l);
    //	System.out.println(anni);
    //	System.out.println(ore);
    //	System.out.println(model.worstCase(nerc, 4, 200));
    	for (Guasti g:l){
    		sb.append(String.format("%-4d ",g.getInizio().getYear()));
    		sb.append(String.format("%-18s ",g.getInizio()));
    		sb.append(String.format("%-18s ",g.getFine()));
    		sb.append(String.format("%-2d ",g.oreDisservizio()));
    		sb.append(String.format("%-8d\n",g.getPersoneAffette()));
    	}
    	
    	txtResult.appendText(sb.toString());
    	
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbNerc != null : "fx:id=\"cmbNerc\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        
        // Utilizzare questo font per incolonnare correttamente i dati;
        txtResult.setStyle("-fx-font-family: monospace");
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	cmbNerc.getItems().addAll(model.getNercList());
    	txtResult.setStyle("-fx-font-family:monospace");
    }
}
