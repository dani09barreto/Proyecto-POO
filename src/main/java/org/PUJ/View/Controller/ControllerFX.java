package org.PUJ.View.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.PUJ.Controller.ControlDespacho;
import org.PUJ.Model.Pedido;
import org.PUJ.Model.ServicioAdicional;
import org.PUJ.Model.TipoTransporte;

import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.UUID;

public class ControllerFX implements Initializable {
    ControlDespacho controlDespacho = new ControlDespacho();
    //-------variables pedido--------//
    @FXML private ToggleGroup tipoProducto;
    @FXML private ToggleGroup tipoProductoModificar;
    @FXML private ComboBox<String> clientesList;
    @FXML private ComboBox<String> productosList;
    @FXML private DatePicker fechaEntrega;
    @FXML private TextField nameReparidor;
    @FXML private Button btnAgregar;
    @FXML private RadioButton envioPrimeAgregar;
    @FXML private ToggleGroup tipoServicio;
    @FXML private RadioButton bonoRegaloAgregar;
    @FXML private Label textBono;
    @FXML private Label textEnvio;
    @FXML private TextField precioEnvio;
    @FXML private TextField distanciaEnvio;
    @FXML private ComboBox<TipoTransporte> tipoTransporte;
    @FXML private Spinner<Integer> cantidadCajas;
    @FXML private Button btnAgregarEnvio;
    @FXML private TextField comercioBono;
    @FXML private TextField precioBono;
    @FXML private TextField mensajeBono;
    @FXML private Button btnAgregarBono;
    @FXML private ListView<UUID> listaEliminar;
    @FXML private Button btnEliminar;
    @FXML private TableView<Pedido> listaPedidos;
    @FXML private TableColumn<Pedido, UUID> colid;
    @FXML private TableColumn<Pedido, Calendar> colfecha;
    @FXML private TableColumn<Pedido, String> colRepartidor;
    @FXML private TableColumn<Pedido, Integer> colNumeroSA;
    @FXML private TableColumn<ServicioAdicional, UUID> colidSA;
    @FXML private TableColumn<ServicioAdicional, String> colNombreSA;
    @FXML private TableColumn<ServicioAdicional, Double> colPrecioSA;
    @FXML private TableColumn<ServicioAdicional, Double> colDistanciaSA;
    @FXML private TableColumn<ServicioAdicional, TipoTransporte> colTipoSA;
    @FXML private TableColumn<ServicioAdicional, String> colComercioSA;
    @FXML private TableColumn<ServicioAdicional, String> colMensajeSA;
    @FXML private TableColumn<ServicioAdicional, Calendar> colFechaSA;
    @FXML private TableColumn<ServicioAdicional, Integer> colCajasSA;
    @FXML private TableView<ServicioAdicional> listaServicioAdicional;
    @FXML private ToggleGroup tipoServicioModificar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tipoTransporte.getItems().setAll(TipoTransporte.values());
        fechaEntrega.setValue(LocalDate.now());
    }
    public void renderWindoPedido (){
        clearWindowPedido();
        listaPedidos.getItems().addAll(controlDespacho.getPedidos());
        for (Pedido pedtemp : controlDespacho.getPedidos()){
            listaEliminar.getItems().add(pedtemp.getNumeroPedido());
        }
    }
    public void clearWindowPedido (){
        listaPedidos.getItems().clear();
        listaEliminar.getItems().clear();
    }
    @FXML
    void AgregarPedido(ActionEvent event) {
        String repartidor = nameReparidor.getText();

    }
    @FXML
    void agregarBono(ActionEvent event) {

    }

    @FXML
    void agregarEnvio(ActionEvent event) {

    }

}
