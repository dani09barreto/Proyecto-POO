package org.PUJ.View.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.PUJ.Controller.ControlDespacho;
import org.PUJ.Model.*;
import org.PUJ.utils.AlertUtils;
import org.PUJ.utils.Fechaerror;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import java.net.URL;

public class ControllerFX implements Initializable {
    private ControlDespacho controlDespacho = new ControlDespacho();
    private ArrayList<ServicioAdicional> servicios = new ArrayList<>();

    //-------variables pedido--------//
    @FXML private ToggleGroup tipoProducto;
    @FXML private ToggleGroup tipoProductoModificar;
    @FXML private ComboBox<Long> clientesList;
    @FXML private ComboBox<UUID> productosList;
    @FXML private DatePicker fechaEntrega;
    @FXML private TextField nameReparidor;
    @FXML private Button btnAgregar;
    @FXML private RadioButton envioPrimeAgregar;
    @FXML private ToggleGroup tipoServicio;
    @FXML private RadioButton bonoRegaloAgregar;
    @FXML private Label textEnvio;
    @FXML private Label textBono;
    @FXML private TextField precioEnvio;
    @FXML private TextField distanciaEnvio;
    @FXML private ComboBox<TipoTransporte> tipoTransporte;
    @FXML private Spinner<Integer> cantidadCajas;
    @FXML private TextField comercioBono;
    @FXML private TextField precioBono;
    @FXML private TextField mensajeBono;
    @FXML private Button btnAgregarSA;
    @FXML private ListView<UUID> listaEliminar;
    @FXML private Button btnEliminar;
    @FXML private TableView<Pedido> listaPedidos;
    @FXML private TableColumn<Pedido, UUID> colid;
    @FXML private TableColumn<Pedido, String> colfecha;
    @FXML private TableColumn<Pedido, String> colRepartidor;
    @FXML private TableColumn<Cliente, String> colCliente;
    @FXML private TableColumn<Pedido, String> colPedido;
    @FXML private TableView<ServicioAdicional> listaServicioAdicional = new TableView<ServicioAdicional>();
    @FXML private TableColumn<ServicioAdicional, UUID> colidSA = new TableColumn<ServicioAdicional,UUID>("CodigoServicio");
    @FXML private TableColumn<ServicioAdicional, String> colNombreSA = new TableColumn<ServicioAdicional,String>("NombreServicio");
    @FXML private TableColumn<ServicioAdicional, Double> colPrecioSA = new TableColumn<ServicioAdicional, Double>("Precio");
    /*
    @FXML private TableColumn<EnvioPrime, Double> colDistanciaSA = new TableColumn<EnvioPrime, Double>("Distancia");
    @FXML private TableColumn<EnvioPrime, TipoTransporte> colTransporteSA = new TableColumn<EnvioPrime, TipoTransporte>("Tipo");
    @FXML private TableColumn<EnvioPrime, Integer> colCajasSA = new TableColumn<EnvioPrime, Integer>("NumeroCajas");
    @FXML private TableColumn<BonoRegalo, String> colComercioSA = new TableColumn<BonoRegalo, String>("ComercioAsociado");
    @FXML private TableColumn<BonoRegalo, String> colMensajeSA = new TableColumn<BonoRegalo, String>("Mensaje");
    @FXML private TableColumn<BonoRegalo, String> colFechaSA = new TableColumn<BonoRegalo, String>("FechaVencimientostring");
     */
    @FXML private ToggleGroup tipoServicioModificar;
    @FXML private Button btnVerServicios;
    @FXML private ComboBox<UUID> listPedidos;

    //----------iniciador-------funciona para dar valores al programa sin acciones como los tipos de transporte o tipo de aseo//
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Cliente cliente1 = new Cliente(1000077617L, "Nicolas David", 316009L, "BG 6447");
        Producto producto1 = new Producto("Papas fritas", 2500d, "La esquina");
        controlDespacho.getGestionCliente().getListaClientes().put(cliente1.getCedula(),cliente1);
        controlDespacho.getGestionProductos().getListaProductos().put(producto1.getProdId(),producto1);
        renderWindoPedido();
        tipoTransporte.getItems().setAll(TipoTransporte.values());
        fechaEntrega.setValue(LocalDate.now());
    }
    //---------Funciones Pedido-----------//
    public void renderWindoPedido (){
        clearWindowPedido();
        listaPedidos.getItems().addAll(controlDespacho.getPedidos());
        for (Cliente cli : controlDespacho.getGestionCliente().getListaClientes().values()){
            clientesList.getItems().add(cli.getCedula());
        }
        for (Producto prod: controlDespacho.getGestionProductos().getListaProductos().values()){
            productosList.getItems().add(prod.getProdId());
        }
        for (Pedido pedtemp : controlDespacho.getPedidos()){
            listPedidos.getItems().add(pedtemp.getNumeroPedido());
            listaEliminar.getItems().add(pedtemp.getNumeroPedido());
        }
    }
    public void clearWindowPedido (){
        listaPedidos.getItems().clear();
        listaEliminar.getItems().clear();
        listPedidos.getItems().clear();
    }
    @FXML
    void activarBono(ActionEvent event) {
        if (tipoServicio.getSelectedToggle().equals(bonoRegaloAgregar)){
            textBono.setDisable(false);
            comercioBono.setDisable(false);
            precioBono.setDisable(false);
            mensajeBono.setDisable(false);
            textEnvio.setDisable(true);
            precioEnvio.setDisable(true);
            distanciaEnvio.setDisable(true);
            tipoTransporte.setDisable(true);
            cantidadCajas.setDisable(true);
        }
    }

    @FXML
    void activarPrime(ActionEvent event) {
        if (tipoServicio.getSelectedToggle().equals(envioPrimeAgregar)){
            textEnvio.setDisable(false);
            precioEnvio.setDisable(false);
            distanciaEnvio.setDisable(false);
            tipoTransporte.setDisable(false);
            cantidadCajas.setDisable(false);
            textBono.setDisable(true);
            comercioBono.setDisable(true);
            precioBono.setDisable(true);
            mensajeBono.setDisable(true);
        }
    }
    @FXML
    void AgregarPedido(ActionEvent event) {
        try{
            Cliente cliente = controlDespacho.getGestionCliente().existeCliente(Long.valueOf(clientesList.getSelectionModel().getSelectedItem()));
            Producto producto = controlDespacho.getGestionProductos().existeProducto(productosList.getSelectionModel().getSelectedItem());
            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate localDate = fechaEntrega.getValue();
            Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
            Calendar fecha = Calendar.getInstance();
            fecha.setTime(date);
            String repartidor = nameReparidor.getText();
            controlDespacho.ReservarPedido(producto, cliente, fecha, repartidor, new ArrayList<ServicioAdicional>(this.servicios));
            this.servicios.clear();
            AlertUtils.alertConfirmation("Pedido agendado", "Su pedido a sido agendado Exitosamente", "hay: " + controlDespacho.getPedidos().size() + " Pedidos agendados");
        } catch (Fechaerror e){
            e.getMessage();
            AlertUtils.alertError("Error Fecha", "La fecha digitada no es mayor a dos dias", "Intentalo nuevamente");
        } catch (Exception ex){
            ex.printStackTrace();
            AlertUtils.alertError("ERROR", "No se pudo agregar el pedido", "Por favor, intente de nuevo");
        }
        renderWindoPedido();
    }

    @FXML
    void agregarSA(ActionEvent event) {
        try {
            if (tipoServicio.getSelectedToggle().equals(envioPrimeAgregar)){
                ServicioAdicional servtemp = new EnvioPrime("Envio Prime", Double.valueOf(precioEnvio.getText()), Double.valueOf(distanciaEnvio.getText()), tipoTransporte.getSelectionModel().getSelectedItem(), cantidadCajas.getValue());
                this.servicios.add(servtemp);
            }
            else if (tipoServicio.getSelectedToggle().equals(bonoRegaloAgregar)){
                ServicioAdicional servtemp = new BonoRegalo("Bono Regalo", Double.valueOf(precioBono.getText()), comercioBono.getText(), mensajeBono.getText(), Calendar.getInstance());
                this.servicios.add(servtemp);
            }
            AlertUtils.alertConfirmation("Servicio Adicional añadido", "Su servicio adicional a sido añadido", "Har: "+ servicios.size() + " Servicios Adicionales");
        }catch (Exception ex){
            ex.printStackTrace();
            AlertUtils.alertError("ERROR", "No se pudo agregar el Servicio Adicional", "Por favor, intente de nuevo");
        }
    }

    @FXML
    void eliminarPedido(ActionEvent event) {
        try {
            Optional<ButtonType> opcion = AlertUtils.alertConfirmation("Eliminar Pedido", "Se eliminara el pedido seleccionado", "¿Esta seguro?");
            if (opcion.get().equals(ButtonType.OK)){
                if (controlDespacho.EliminarPedido(listaEliminar.getSelectionModel().getSelectedItem())){
                    AlertUtils.alertConfirmation("Pedido Elimando", "El pedido seleccionado a sido eliminado correctamente", "");
                }
            }
            renderWindoPedido();
        }catch (Exception e){
            e.printStackTrace();
            AlertUtils.alertError("ERROR", "No se pudo Eliminar el pedido", "Por favor, intente de nuevo");
        }
    }
    @FXML
    void verservicios(ActionEvent event) {
        listaServicioAdicional.getItems().clear();
        try{
            UUID idped = listPedidos.getSelectionModel().getSelectedItem();
            Pedido pedido = controlDespacho.ExistePedido(idped);
            listaServicioAdicional.getItems().addAll(pedido.getServiciosAdicionales());
            System.out.println(listaServicioAdicional.getItems().size());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
