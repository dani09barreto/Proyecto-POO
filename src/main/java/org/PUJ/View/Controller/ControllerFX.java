package org.PUJ.View.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.PUJ.Controller.ControlDespacho;
import org.PUJ.Model.*;
import org.PUJ.utils.AlertUtils;
import org.PUJ.utils.Fechaerror;
import org.PUJ.utils.PedidoFechaIgual;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import java.net.URL;

public class ControllerFX implements Initializable {
    private ControlDespacho controlDespacho = new ControlDespacho();

    //---------variables cliente-------//
    @FXML
    private TextField Entrada_Mod_nombre_cliente;
    @FXML
    private TextField Entrada_Mod_telefono_cliente;
    @FXML
    private TextField Entrada_Mod_dir_cliente;
    @FXML
    private CheckBox Ver_mod_clientes;
    @FXML
    private Button boton_modi_clientes;
    @FXML
    private ComboBox<Long> Selecion_clientes;
    @FXML
    private TextField Entrada_Nombre_clientes;
    @FXML
    private TextField entrada_cedula_clientes;
    @FXML
    private TextField entrada_tel_clientes;
    @FXML
    private TextField entrada_dir_clientes;
    @FXML
    private Button Agregar_cliente;
    @FXML
    private ListView<Long> Lista_eliminar_clientes;
    @FXML
    private Button boton_eliminar_clientes;
    @FXML
    private TableView<Cliente> Tabla_clientes;
    @FXML
    private TableColumn<Cliente, String> tabla_nombre_clientes = new TableColumn<Cliente, String>("NombreCompleto");
    @FXML
    private TableColumn<Cliente, Long> tabla_cedula_clientes = new TableColumn<Cliente, Long>("Cedula");
    @FXML
    private TableColumn<Cliente, Long> tabla_tel_clientes = new TableColumn<Cliente, Long>("TelefonoContacto");
    @FXML
    private TableColumn<Cliente, String> tabal_dir_clientes = new TableColumn<Cliente, String>("Direccion");
    //-------variables producto------//
    @FXML
    private ComboBox<TipoProducto> listTipoProducto;
    @FXML
    private ToggleGroup tipoProducto;
    @FXML
    private ToggleGroup tipoProductoModificar;
    //-------variables pedido--------//
    private ArrayList<ServicioAdicional> servicios = new ArrayList<>();
    @FXML
    private ComboBox<Long> clientesList;
    @FXML
    private ComboBox<UUID> productosList;
    @FXML
    private DatePicker fechaEntrega;
    @FXML
    private TextField nameReparidor;
    @FXML
    private Button btnAgregar;
    @FXML
    private RadioButton envioPrimeAgregar;
    @FXML
    private ToggleGroup tipoServicio;
    @FXML
    private RadioButton bonoRegaloAgregar;
    @FXML
    private Label textEnvio;
    @FXML
    private Label textBono;
    @FXML
    private TextField precioEnvio;
    @FXML
    private TextField distanciaEnvio;
    @FXML
    private ComboBox<TipoTransporte> tipoTransporte;
    @FXML
    private Spinner<Integer> cantidadCajas;
    @FXML
    private TextField comercioBono;
    @FXML
    private TextField precioBono;
    @FXML
    private TextField mensajeBono;
    @FXML
    private Button btnAgregarSA;
    @FXML
    private ListView<UUID> listaEliminar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TableView<Pedido> listaPedidos;
    @FXML
    private TableColumn<Pedido, UUID> colid;
    @FXML
    private TableColumn<Pedido, String> colfecha;
    @FXML
    private TableColumn<Pedido, String> colRepartidor;
    @FXML
    private TableColumn<Cliente, String> colCliente;
    @FXML
    private TableColumn<Pedido, String> colPedido;
    @FXML
    private TableView<ServicioAdicional> listaServicioAdicional = new TableView<ServicioAdicional>();
    @FXML
    private TableColumn<ServicioAdicional, UUID> colidSA = new TableColumn<ServicioAdicional, UUID>("CodigoServicio");
    @FXML
    private TableColumn<ServicioAdicional, String> colNombreSA = new TableColumn<ServicioAdicional, String>("NombreServicio");
    @FXML
    private TableColumn<ServicioAdicional, Double> colPrecioSA = new TableColumn<ServicioAdicional, Double>("Precio");
    @FXML
    private TableColumn<EnvioPrime, Double> colDistanciaSA = new TableColumn<EnvioPrime, Double>("Distancia");
    @FXML
    private TableColumn<EnvioPrime, TipoTransporte> colTransporteSA = new TableColumn<EnvioPrime, TipoTransporte>("Tipo");
    @FXML
    private TableColumn<EnvioPrime, Integer> colCajasSA = new TableColumn<EnvioPrime, Integer>("NumeroCajas");
    @FXML
    private TableColumn<BonoRegalo, String> colComercioSA = new TableColumn<BonoRegalo, String>("ComercioAsociado");
    @FXML
    private TableColumn<BonoRegalo, String> colMensajeSA = new TableColumn<BonoRegalo, String>("Mensaje");
    @FXML
    private TableColumn<BonoRegalo, String> colFechaSA = new TableColumn<BonoRegalo, String>("FechaVencimientostring");
    @FXML
    private ToggleGroup tipoServicioModificar;
    @FXML
    private Button btnVerServicios;
    @FXML
    private ComboBox<UUID> listPedidosModificar;
    @FXML
    private DatePicker FechaModificar;
    @FXML
    private TextField repartidorModificar;
    @FXML
    private RadioButton envioPrimeModificar;
    @FXML
    private RadioButton BonoModificar;
    @FXML
    private CheckBox checkModificar;
    @FXML
    private Button btnModificarPedido;
    @FXML
    private ComboBox<UUID> listPedidos;
    @FXML
    private ComboBox<UUID> listEnviosModificar;
    @FXML
    private ComboBox<UUID> listBonosModificar;
    @FXML
    private Button btnAgregarModServicio;
    @FXML
    private Button btnCargarPedido;


    //----------iniciador-------funciona para dar valores al programa sin acciones como los tipos de transporte o tipo de aseo//
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        renderWindowPedido();
        listTipoProducto.getItems().setAll(TipoProducto.values());
        tipoTransporte.getItems().setAll(TipoTransporte.values());
        fechaEntrega.setValue(LocalDate.now());

    }

    //---------Funciones Clientes----------//
    @FXML
    void Agregar_cliente_accion(ActionEvent event) {
        try {
            Cliente temp = new Cliente(Long.valueOf(entrada_cedula_clientes.getText()), Entrada_Nombre_clientes.getText(), Long.valueOf(entrada_tel_clientes.getText()), entrada_dir_clientes.getText());
            this.controlDespacho.getGestionCliente().InsertarCliente(temp.getCedula(), temp.getNombreCompleto(), temp.getTelefonoContacto(), temp.getDireccion());

        } catch (Exception e) {
            e.printStackTrace();
        }
        renderWindowCliente();
    }

    @FXML
    void Eliminar_Cliente_accion(ActionEvent event) {
        this.controlDespacho.getGestionCliente().EliminarCliente(Lista_eliminar_clientes.getSelectionModel().getSelectedItem());
        renderWindowCliente();
    }

    public void renderWindowCliente() {
        clearWindowCliente();
        for (Cliente t : this.controlDespacho.getGestionCliente().getListaClientes().values()) {
            Selecion_clientes.getItems().add(t.getCedula());
        }
        for (Cliente t : this.controlDespacho.getGestionCliente().getListaClientes().values()) {
            Lista_eliminar_clientes.getItems().add(t.getCedula());
            clientesList.getItems().add(t.getCedula());
        }
        Tabla_clientes.getItems().addAll(this.controlDespacho.getGestionCliente().getListaClientes().values());

    }

    public void clearWindowCliente() {
        Tabla_clientes.getItems().clear();
        Lista_eliminar_clientes.getItems().clear();
        Selecion_clientes.getItems().clear();
    }

    @FXML
    void ModificarCliente(ActionEvent event) {
        try {
            Long cedulaMod = Selecion_clientes.getValue();
            for (Long c : this.controlDespacho.getGestionCliente().getListaClientes().keySet()) {
                if (this.controlDespacho.getGestionCliente().getListaClientes().get(c).getCedula().equals(cedulaMod)) {
                    this.controlDespacho.getGestionCliente().getListaClientes().get(c).setTelefonoContacto(Long.valueOf(Entrada_Mod_telefono_cliente.getText()));
                    this.controlDespacho.getGestionCliente().getListaClientes().get(c).setNombreCompleto(Entrada_Mod_nombre_cliente.getText());
                    this.controlDespacho.getGestionCliente().getListaClientes().get(c).setDireccion(Entrada_Mod_dir_cliente.getText());

                }
            }
            AlertUtils.alertConfirmation("Agregar Cliente","El cliente se ha agregado correctamente","");
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtils.alertError("z","<","s");
        }

        renderWindowCliente();
    }

    @FXML
    void VerModificar(ActionEvent event) {
        if (Ver_mod_clientes.isSelected()) {
            Selecion_clientes.setDisable(false);
            Entrada_Mod_dir_cliente.setDisable(false);
            Entrada_Mod_nombre_cliente.setDisable(false);
            Entrada_Mod_telefono_cliente.setDisable(false);
            boton_modi_clientes.setDisable(false);
        } else {
            Selecion_clientes.setDisable(true);
            Entrada_Mod_dir_cliente.setDisable(true);
            Entrada_Mod_nombre_cliente.setDisable(true);
            Entrada_Mod_telefono_cliente.setDisable(true);
            boton_modi_clientes.setDisable(true);
        }

    }

    //---------Funciones Pedido-----------//
    public void renderWindowPedido() {
        clearWindowPedido();
        listaPedidos.getItems().addAll(controlDespacho.getPedidos());
        for (Cliente cli : controlDespacho.getGestionCliente().getListaClientes().values()) {
            clientesList.getItems().add(cli.getCedula());
        }
        for (Producto prod : controlDespacho.getGestionProductos().getListaProductos().values()) {
            productosList.getItems().add(prod.getProdId());
        }
        for (Pedido pedtemp : controlDespacho.getPedidos()) {
            listPedidos.getItems().add(pedtemp.getNumeroPedido());
            listaEliminar.getItems().add(pedtemp.getNumeroPedido());
            listPedidosModificar.getItems().add(pedtemp.getNumeroPedido());
        }
    }

    public void clearWindowPedido() {
        listaPedidos.getItems().clear();
        listaEliminar.getItems().clear();
        listPedidos.getItems().clear();
        listPedidosModificar.getItems().clear();
    }

    @FXML
    void activarBono(ActionEvent event) {
        if (tipoServicio.getSelectedToggle().equals(bonoRegaloAgregar)) {
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
        if (tipoServicio.getSelectedToggle().equals(envioPrimeAgregar)) {
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
        try {
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
        } catch (Fechaerror e) {
            e.getMessage();
            AlertUtils.alertError("Error Fecha", "La fecha digitada no es mayor a dos dias", "Intentalo nuevamente");
        } catch (PedidoFechaIgual ex) {
            ex.printStackTrace();
            AlertUtils.alertError("Error Fecha", "La fecha ya pertenece a un pedido con mismo producto y cliente", "Intentalo nuevamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            AlertUtils.alertError("ERROR", "No se pudo agregar el pedido", "Por favor, intente de nuevo");
        }
        renderWindowPedido();
    }

    @FXML
    void agregarSA(ActionEvent event) {
        try {
            listaServicioAdicional.setDisable(true);
            if (tipoServicio.getSelectedToggle().equals(envioPrimeAgregar)) {
                ServicioAdicional servtemp = new EnvioPrime("Envio Prime", Double.valueOf(precioEnvio.getText()), Double.valueOf(distanciaEnvio.getText()), tipoTransporte.getSelectionModel().getSelectedItem(), cantidadCajas.getValue());
                this.servicios.add(servtemp);
            } else if (tipoServicio.getSelectedToggle().equals(bonoRegaloAgregar)) {
                ServicioAdicional servtemp = new BonoRegalo("Bono Regalo", Double.valueOf(precioBono.getText()), comercioBono.getText(), mensajeBono.getText(), Calendar.getInstance());
                this.servicios.add(servtemp);
            }
            AlertUtils.alertConfirmation("Servicio Adicional añadido", "Su servicio adicional a sido añadido", "Har: " + servicios.size() + " Servicios Adicionales");
        } catch (Exception ex) {
            ex.printStackTrace();
            AlertUtils.alertError("ERROR", "No se pudo agregar el Servicio Adicional", "Por favor, intente de nuevo");
        }
    }

    @FXML
    void eliminarPedido(ActionEvent event) {
        try {
            Optional<ButtonType> opcion = AlertUtils.alertConfirmation("Eliminar Pedido", "Se eliminara el pedido seleccionado", "¿Esta seguro?");
            if (opcion.get().equals(ButtonType.OK)) {
                if (controlDespacho.EliminarPedido(listaEliminar.getSelectionModel().getSelectedItem())) {
                    AlertUtils.alertConfirmation("Pedido Elimando", "El pedido seleccionado a sido eliminado correctamente", "");
                }
            }
            renderWindowPedido();
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtils.alertError("ERROR", "No se pudo Eliminar el pedido", "Por favor, intente de nuevo");
        }
    }

    @FXML
    void mostrarModificar(ActionEvent event) {
        if (checkModificar.isSelected()) {
            listPedidosModificar.setDisable(false);
            btnCargarPedido.setDisable(false);
            FechaModificar.setDisable(false);
            repartidorModificar.setDisable(false);
            envioPrimeModificar.setDisable(false);
            BonoModificar.setDisable(false);
            listEnviosModificar.setDisable(false);
            listBonosModificar.setDisable(false);
            btnAgregarModServicio.setDisable(false);
            btnModificarPedido.setDisable(false);
            envioPrimeAgregar.setDisable(true);
            bonoRegaloAgregar.setDisable(true);
        } else {
            listPedidosModificar.setDisable(true);
            btnCargarPedido.setDisable(true);
            FechaModificar.setDisable(true);
            repartidorModificar.setDisable(true);
            envioPrimeModificar.setDisable(true);
            BonoModificar.setDisable(true);
            listEnviosModificar.setDisable(true);
            listBonosModificar.setDisable(true);
            btnAgregarModServicio.setDisable(true);
            btnModificarPedido.setDisable(true);
            envioPrimeAgregar.setDisable(false);
            bonoRegaloAgregar.setDisable(false);
        }
    }

    @FXML
    void cargarServicios(ActionEvent event) {
        UUID idpedido = listPedidosModificar.getSelectionModel().getSelectedItem();
        Pedido pedido = controlDespacho.ExistePedido(idpedido);
        for (ServicioAdicional serv : pedido.getServiciosAdicionales()) {
            if (serv instanceof EnvioPrime) {
                listEnviosModificar.getItems().add(serv.getCodigoServicio());
            }
            if (serv instanceof BonoRegalo) {
                listBonosModificar.getItems().add(serv.getCodigoServicio());
            }
        }
    }

    @FXML
    void modificarServicio(ActionEvent event) {
        try {
            if (tipoServicioModificar.getSelectedToggle().equals(envioPrimeModificar)) {
                UUID idped = listPedidosModificar.getSelectionModel().getSelectedItem();
                Pedido pedido = controlDespacho.ExistePedido(idped);
                for (ServicioAdicional serv : pedido.getServiciosAdicionales()) {
                    if (serv.getCodigoServicio().equals(listEnviosModificar.getSelectionModel().getSelectedItem())) {
                        serv.setPrecio(Double.valueOf(precioEnvio.getText()));
                        ((EnvioPrime) serv).setDistancia(Double.valueOf(distanciaEnvio.getText()));
                        ((EnvioPrime) serv).setTipo(tipoTransporte.getSelectionModel().getSelectedItem());
                        ((EnvioPrime) serv).setNumeroCajas(cantidadCajas.getValue());
                    }
                }
            } else if (tipoServicioModificar.getSelectedToggle().equals(BonoModificar)) {
                UUID idped = listPedidosModificar.getSelectionModel().getSelectedItem();
                Pedido pedido = controlDespacho.ExistePedido(idped);
                for (ServicioAdicional serv : pedido.getServiciosAdicionales()) {
                    if (serv.getCodigoServicio().equals(listBonosModificar.getSelectionModel().getSelectedItem())) {
                        serv.setPrecio(Double.valueOf(precioBono.getText()));
                        ((BonoRegalo) serv).setComercioAsociado(comercioBono.getText());
                        ((BonoRegalo) serv).setMensaje(mensajeBono.getText());
                    }
                }
            }
            AlertUtils.alertConfirmation("Modificar Servicio Adicional", "Se ha modificado el servicio adicional seleccionado", "");
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtils.alertError("ERROR", "No se pudo Modificar el servicio Adicional", "Por favor, intente de nuevo");
        }
    }

    @FXML
    void modificarPedido(ActionEvent event) throws Fechaerror, PedidoFechaIgual {
        try {
            UUID idped = listPedidosModificar.getSelectionModel().getSelectedItem();
            Pedido pedido = controlDespacho.ExistePedido(idped);
            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate localDate = FechaModificar.getValue();
            Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
            Calendar fecha = Calendar.getInstance();
            fecha.setTime(date);

            //resta de la fecha actual con la fecha de despacho
            Calendar fechanow = Calendar.getInstance();
            long finMs = fechanow.getTimeInMillis();
            long inicioMs = fecha.getTimeInMillis();
            int dias = (int) (Math.abs(finMs - inicioMs) / (1000 * 60 * 60 * 24));

            if (dias <= 2) {
                throw new Fechaerror("fecha error");
            }
            if (controlDespacho.ExistePedido(pedido.getSolicitante(), pedido.getProductoSolicitado(), fecha) != null) {
                throw new PedidoFechaIgual("Fecha igual");
            }
            pedido.setFechaRecibido(fecha);
            pedido.setNombreRepartidor(repartidorModificar.getText());
            for (ServicioAdicional serv : pedido.getServiciosAdicionales()) {
                if (serv instanceof BonoRegalo) {
                    Calendar fechaservicio = (Calendar) fecha.clone();
                    fechaservicio.set(Calendar.MONTH, fechaservicio.get(Calendar.MONTH) + 6);
                    ((BonoRegalo) serv).setFechaVencimiento(fechaservicio);
                }
            }
            AlertUtils.alertConfirmation("Modificar Pedido", "Se ha modificado Pedido seleccionado", "");
            renderWindowPedido();
        } catch (Fechaerror e) {
            e.printStackTrace();
            AlertUtils.alertError("Error Fecha", "La fecha digitada no es mayor a dos dias", "Intentalo nuevamente");
        } catch (PedidoFechaIgual ex) {
            ex.printStackTrace();
            AlertUtils.alertError("Error Fecha", "La fecha ya pertenece a un pedido con mismo producto y cliente", "Intentalo nuevamente");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void activarPrimeModificar(ActionEvent event) {
        if (tipoServicioModificar.getSelectedToggle().equals(envioPrimeModificar)) {
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
    void activarBonoModificar(ActionEvent event) {
        if (tipoServicioModificar.getSelectedToggle().equals(BonoModificar)) {
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
    void verservicios(ActionEvent event) {
        listaServicioAdicional.getItems().clear();
        listaServicioAdicional.setDisable(false);
        try {
            UUID idped = listPedidos.getSelectionModel().getSelectedItem();
            Pedido pedido = controlDespacho.ExistePedido(idped);
            listaServicioAdicional.getItems().addAll(pedido.getServiciosAdicionales());
            System.out.println(listaServicioAdicional.getItems().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
