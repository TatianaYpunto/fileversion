package es.progcipfpbatoi.controlador;

import es.progcipfpbatoi.modelo.entidades.Tarea;
import es.progcipfpbatoi.modelo.repositorios.TareaRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class TareaDetailController implements Initializable {

    @FXML
    private TextField textFieldDescripcion;

    @FXML
    private DatePicker datePickerFecha;

    @FXML
    private CheckBox checkBoxFinalizada;

    private Tarea tarea;
    private TareaRepository tareaRepository;

    private Initializable controladorPadre;
    private String vistaPadre;

    public TareaDetailController(
            Tarea tarea,
            TareaRepository tareaRepository,
            Initializable controladorPadre,
            String vistaPadre) {

        this.tarea = tarea;
        this.tareaRepository = tareaRepository;
        this.controladorPadre = controladorPadre;
        this.vistaPadre = vistaPadre;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textFieldDescripcion.setText(tarea.getDescripcion());

        datePickerFecha.setEditable(false);
        datePickerFecha.setOnMouseClicked(e -> {
            if(!datePickerFecha.isEditable()) {
                datePickerFecha.hide();
            }
        });

        datePickerFecha.setValue(tarea.getFechaAltaSinTiempo());

        this.checkBoxFinalizada.setSelected(this.tarea.isFinalizada());
    }

    @FXML
    private void handleChangeInFinalizada() {
        this.tarea.cambiarEstado();
        this.tareaRepository.save(tarea);
    }

    @FXML
    private void handleButtonBack(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ChangeScene.change(stage, controladorPadre, vistaPadre);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
