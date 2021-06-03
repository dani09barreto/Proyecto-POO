package org.PUJ.View.Controller;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.PUJ.Controller.ControlDespacho;
import org.PUJ.Model.*;
import org.PUJ.utils.*;
import org.PUJ.utils.Exceptions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    //Variables pestaña otros
    @FXML
    private ComboBox<UUID> productoEspecifico;
    @FXML
    private DatePicker fechaEspecifica;
    @FXML
    private Button generarListado;
    @FXML
    private TableView<Pedido> tablaFechaEspecifica;
    @FXML
    private TableColumn<Pedido, String> columnClienteEspecifico;
    @FXML
    private TableColumn<Pedido, String> colProdEsspecifico;
    @FXML
    private TableColumn<Pedido, Calendar> colFechEspecifica;
    @FXML
    private ComboBox<TipoProducto> tipoProdGuardar;
    @FXML
    private DatePicker fechaInicialGuardar;
    @FXML
    private DatePicker fechaFinalGuardar;
    @FXML
    private ComboBox<TipoTransporte> tipoTransporteguardar;

    // Variables Producto

    @FXML
    private RadioButton tipoAseo;
    @FXML
    private RadioButton tipoFruver;
    @FXML
    private RadioButton tipoOtro;
    @FXML
    private TextField insertarNombreProducto;
    @FXML
    private TextField insertarPrecioProducto;
    @FXML
    private TextField insertarTiendaProducto;
    @FXML
    private Button btnAgregarProducto;
    @FXML
    private CheckBox actModificarProducto;
    @FXML
    private Button btnModificarProducto;
    @FXML
    private Button btnEliminarProducto;
    @FXML
    private Label textAseo;
    @FXML
    private Label textFruver;
    @FXML
    private TextField insertarNombreEmpresa;
    @FXML
    private TextField insertarImpuestoLocal;
    @FXML
    private TextField insertarNombreHacienda;
    @FXML
    private CheckBox checkInvima;
    @FXML
    private CheckBox checkOrganico;
    @FXML
    private ListView<UUID> tablaEliminarProductos;
    @FXML
    private TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<Producto, UUID> C_ID_PROD;
    @FXML
    private TableColumn<Producto, String> C_NOMBRE_PROD;
    @FXML
    private TableColumn<Producto, Double> C_PRECIO_PROD;
    @FXML
    private TableColumn<Producto, String> C_TIENDA_PROD;
    @FXML
    private TableColumn<Producto, TipoProducto> C_TIPO_PROD;
    @FXML
    private TextField insertarNombreModProd;
    @FXML
    private TextField insertarPrecioModProd;
    @FXML
    private TextField insertarTiendaModProd;
    @FXML
    private ComboBox<UUID> listaProductosModificar;
    @FXML
    private RadioButton tipoFruverModificar;
    @FXML
    private RadioButton tipoOtroModificar;
    @FXML
    private RadioButton tipoAseoModificar;
    @FXML
    private Label textAseoMod;
    @FXML
    private TextField insertarNombreAseoMod;
    @FXML
    private CheckBox checkInvimaMod;
    @FXML
    private ComboBox<TipoProducto> listTipoProductoMod;
    @FXML
    private Label textFruverMod;
    @FXML
    private TextField insertarImpuestoLocalMod;
    @FXML
    private TextField insertarNombreHaciendaMod;
    @FXML
    private CheckBox checkOrganicoMod;


    //----------iniciador-------funciona para dar valores al programa sin acciones como los tipos de transporte o tipo de aseo//
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listTipoProducto.getItems().setAll(TipoProducto.values());
        tipoTransporte.getItems().setAll(TipoTransporte.values());
        tipoProdGuardar.getItems().setAll(TipoProducto.values());
        tipoTransporteguardar.getItems().setAll(TipoTransporte.values());
        fechaEntrega.setValue(LocalDate.now());
        listaProductosModificar.getItems().setAll(controlDespacho.getGestionProductos().getListaProductos().keySet());
        listTipoProductoMod.getItems().setAll(TipoProducto.values());
    }

    //---------Funciones Clientes----------//
    @FXML
    void Agregar_cliente_accion(ActionEvent event) throws ClienteAsociadoAPedido {
        try {
            Cliente temp = new Cliente(Long.valueOf(entrada_cedula_clientes.getText()), Entrada_Nombre_clientes.getText(), Long.valueOf(entrada_tel_clientes.getText()), entrada_dir_clientes.getText());
            this.controlDespacho.getGestionCliente().InsertarCliente(temp.getCedula(), temp.getNombreCompleto(), temp.getTelefonoContacto(), temp.getDireccion());
            AlertUtils.alertInformation("Agregar cliente", "El cliente se ha agregado correctamente", "Hay: " + controlDespacho.getGestionCliente().getListaClientes().size() + " clientes en el sistema.");
            Ver_mod_clientes.setDisable(false);
            Entrada_Nombre_clientes.setText("");
            entrada_cedula_clientes.setText("");
            entrada_tel_clientes.setText("");
            entrada_dir_clientes.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtils.alertError("ERROR", "El cliente no ha sido agregado", "Inténtalo nuevamente");
        }
        renderWindowCliente();
    }

    @FXML
    void Eliminar_Cliente_accion(ActionEvent event) {
        Optional<ButtonType> opcion = AlertUtils.alertConfirmation("Eliminar cliente", "Se eliminará el cliente con cedula:  " + Lista_eliminar_clientes.getSelectionModel().getSelectedItem().toString(), "¿Está seguro?");
        try {
            if (opcion.get().equals(ButtonType.OK)) {
                if (controlDespacho.validarCliente(Lista_eliminar_clientes.getSelectionModel().getSelectedItem())) {
                    throw new ClienteAsociadoAPedido("Cliente asociado a un pedido");
                } else {
                    this.controlDespacho.getGestionCliente().EliminarCliente(Lista_eliminar_clientes.getSelectionModel().getSelectedItem());
                    AlertUtils.alertInformation("Eliminar cliente", "El cliente se ha eliminado correctamente", "");
                }
            }
            if (this.controlDespacho.getGestionCliente().getListaClientes().isEmpty()) {
                Entrada_Mod_nombre_cliente.setText("");
                Entrada_Mod_telefono_cliente.setText("");
                Entrada_Mod_dir_cliente.setText("");
                Selecion_clientes.setDisable(true);
                Entrada_Mod_dir_cliente.setDisable(true);
                Entrada_Mod_nombre_cliente.setDisable(true);
                Entrada_Mod_telefono_cliente.setDisable(true);
                boton_modi_clientes.setDisable(true);
                Ver_mod_clientes.setDisable(true);
            }

        } catch (ClienteAsociadoAPedido e) {
            e.printStackTrace();
            AlertUtils.alertError("Eliminar cliente", "El cliente no pudo ser eliminado ya que está asociado a un pedido", "Primero eliminé el pedido para eliminar el cliente");
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtils.alertError("ERROR", "El cliente no ha sido eliminado", "Inténtalo nuevamente");
        }
        renderWindowCliente();
    }

    public void renderWindowCliente() {
        clearWindowCliente();
        for (Cliente t : this.controlDespacho.getGestionCliente().getListaClientes().values()) {
            Selecion_clientes.getItems().add(t.getCedula());
            Lista_eliminar_clientes.getItems().add(t.getCedula());
            clientesList.getItems().add(t.getCedula());
        }
        Tabla_clientes.getItems().addAll(this.controlDespacho.getGestionCliente().getListaClientes().values());
    }

    public void clearWindowCliente() {
        Tabla_clientes.getItems().clear();
        Lista_eliminar_clientes.getItems().clear();
        Selecion_clientes.getItems().clear();
        clientesList.getItems().clear();
    }

    @FXML
    void clienteModificar(ActionEvent event) {
        Long cedula = Selecion_clientes.getSelectionModel().getSelectedItem();
        Cliente cliente = controlDespacho.getGestionCliente().existeCliente(cedula);
        Entrada_Mod_nombre_cliente.setText(cliente.getNombreCompleto());
        Entrada_Mod_telefono_cliente.setText(cliente.getTelefonoContacto().toString());
        Entrada_Mod_dir_cliente.setText(cliente.getDireccion());
    }

    @FXML
    void ModificarCliente(ActionEvent event) {
        Optional<ButtonType> opcion = AlertUtils.alertConfirmation("Modificar cliente", "Se modificará el cliente de cédula: " + Selecion_clientes.getValue().toString(), "¿Está seguro?");
        try {
            if (opcion.get().equals(ButtonType.OK)) {
                Long cedulaMod = Selecion_clientes.getValue();
                for (Long c : this.controlDespacho.getGestionCliente().getListaClientes().keySet()) {
                    if (this.controlDespacho.getGestionCliente().getListaClientes().get(c).getCedula().equals(cedulaMod)) {
                        this.controlDespacho.getGestionCliente().getListaClientes().get(c).setTelefonoContacto(Long.valueOf(Entrada_Mod_telefono_cliente.getText()));
                        this.controlDespacho.getGestionCliente().getListaClientes().get(c).setNombreCompleto(Entrada_Mod_nombre_cliente.getText());
                        this.controlDespacho.getGestionCliente().getListaClientes().get(c).setDireccion(Entrada_Mod_dir_cliente.getText());
                    }
                }
            }
            AlertUtils.alertInformation("Modificar cliente", "El cliente se ha modificado correctamente", "");
            Entrada_Mod_nombre_cliente.setText("");
            Entrada_Mod_telefono_cliente.setText("");
            Entrada_Mod_dir_cliente.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtils.alertError("ERROR", "El cliente no se ha modificado", "Inténtalo nuevamente");
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
            Pedido nuevopedido = new Pedido(fecha, nameReparidor.getText(), cliente, producto);
            controlDespacho.ReservarPedido(nuevopedido, new ArrayList<ServicioAdicional>(this.servicios));
            long costoPedido = 0;
            long PrecioSA = 0;
            long ivaAdicional = 0;
            costoPedido += nuevopedido.getProductoSolicitado().calcularPrecio() + nuevopedido.getProductoSolicitado().getIva();
            for (ServicioAdicional servtemp : nuevopedido.getServiciosAdicionales()) {
                costoPedido += servtemp.calcularPrecio();
            }
            costoPedido += costoPedido * 0.10;
            if (nuevopedido.getProductoSolicitado().getIva() > 50000d) {
                costoPedido += 8000;
            }
            nuevopedido.setPagado(true);
            for (ServicioAdicional sev : nuevopedido.getServiciosAdicionales()) {
                PrecioSA += sev.calcularPrecio();
            }
            if (nuevopedido.getProductoSolicitado().getIva() > 50000d) {
                ivaAdicional = 8000;
            }
            AlertUtils.alertInformation("Información del pedido",
                    "Precio producto: $" + nuevopedido.getProductoSolicitado().calcularPrecio() + "\n" +
                            "Precio IVA producto: $" + nuevopedido.getProductoSolicitado().getIva() + "\n" +
                            "Precio servicios adicionales: $" + PrecioSA + "\n" +
                            "Precio IVA adicional: $" + ivaAdicional + "\n" +
                            "Costo del despacho: $" + costoPedido * 0.10 + "\n" +
                            "Costo total: $" + (costoPedido + costoPedido * 0.10), "Pedido almacenado");
            fechaEntrega.setValue(LocalDate.now());
            nameReparidor.setText("");
            this.servicios.clear();
        } catch (FechaMenor e) {
            AlertUtils.alertError("Error en la fecha", "La fecha no es válida, ya que es menor a la fecha de hoy", "Inténtalo nuevamente");
        } catch (Fechaerror e) {
            e.getMessage();
            AlertUtils.alertError("Error en la fecha", "La fecha ingresada no es mayor a dos dias", "Inténtalo nuevamente");
        } catch (PedidoFechaIgual ex) {
            ex.printStackTrace();
            AlertUtils.alertError("Error en la fecha", "La fecha ya pertenece a un pedido con un mismo producto y cliente", "Inténtalo nuevamente");
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
            AlertUtils.alertConfirmation("Servicio adicional añadido", "Su servicio adicional ha sido añadido", "Hay: " + servicios.size() + " servicios adicionales");
            precioEnvio.setText("");
            distanciaEnvio.setText("");
            comercioBono.setText("");
            precioBono.setText("");
            mensajeBono.setText("");
            tipoTransporte.getItems().clear();
            tipoTransporte.getItems().setAll(TipoTransporte.values());

        } catch (Exception ex) {
            ex.printStackTrace();
            AlertUtils.alertError("ERROR", "No se pudo agregar el servicio adicional", "Por favor, intente de nuevo");
        }
    }

    @FXML
    void eliminarPedido(ActionEvent event) {
        try {
            Optional<ButtonType> opcion = AlertUtils.alertConfirmation("Eliminar pedido", "Se eliminará el pedido seleccionado", "¿Está seguro?");
            if (opcion.get().equals(ButtonType.OK)) {
                if (controlDespacho.EliminarPedido(listaEliminar.getSelectionModel().getSelectedItem())) {
                    AlertUtils.alertConfirmation("Pedido eliminado", "El pedido seleccionado ha sido eliminado correctamente", "");
                }
            }
            renderWindowPedido();
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtils.alertError("ERROR", "No se pudo eliminar el pedido", "Por favor, intente de nuevo");
        }
    }

    @FXML
    void mostrarModificar(ActionEvent event) {
        if (checkModificar.isSelected()) {
            listPedidosModificar.setDisable(false);
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
            btnAgregarSA.setDisable(true);
        } else {
            listPedidosModificar.setDisable(true);
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
            btnAgregarSA.setDisable(false);
        }
    }

    @FXML
    void cargarServiciosInfoPedido(ActionEvent event) {
        listEnviosModificar.getItems().clear();
        listBonosModificar.getItems().clear();
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
        Date date = pedido.getFechaRecibido().getTime();
        FechaModificar.setValue(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        repartidorModificar.setText(pedido.getNombreRepartidor());
    }
    @FXML
    void cargarInfoBono(ActionEvent event) {
        UUID idpedido = listPedidosModificar.getSelectionModel().getSelectedItem();
        Pedido pedido = controlDespacho.ExistePedido(idpedido);
        for (ServicioAdicional serv : pedido.getServiciosAdicionales()){
            if (serv.getCodigoServicio().equals(listBonosModificar.getSelectionModel().getSelectedItem())){
                precioBono.setText(serv.getPrecio().toString());
                comercioBono.setText(((BonoRegalo) serv).getComercioAsociado());
                mensajeBono.setText(((BonoRegalo) serv).getMensaje());
            }
        }
    }

    @FXML
    void cargarInfoEnvio(ActionEvent event) {
        UUID idpedido = listPedidosModificar.getSelectionModel().getSelectedItem();
        Pedido pedido = controlDespacho.ExistePedido(idpedido);
        for (ServicioAdicional serv : pedido.getServiciosAdicionales()){
            if (serv.getCodigoServicio().equals(listEnviosModificar.getSelectionModel().getSelectedItem())){
                precioEnvio.setText(serv.getPrecio().toString());
                distanciaEnvio.setText(((EnvioPrime) serv).getDistancia().toString());
                tipoTransporte.getSelectionModel().select(((EnvioPrime) serv).getTipo());
                cantidadCajas.setValueFactory(cantidadCajas.getValueFactory());
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
            AlertUtils.alertConfirmation("Modificar servicio adicional", "Se ha modificado el servicio adicional correctamente", "");
            precioEnvio.setText("");
            distanciaEnvio.setText("");
            comercioBono.setText("");
            precioBono.setText("");
            mensajeBono.setText("");
            tipoTransporte.getItems().clear();
            tipoTransporte.getItems().setAll(TipoTransporte.values());
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtils.alertError("ERROR", "No se pudo modificar el servicio adicional", "Por favor, intente de nuevo");
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

            if (fecha.before(fechanow)){
                throw new FechaMenor("fecha menor");
            }
            if (dias <= 2) {
                throw new Fechaerror("fecha error");
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
            AlertUtils.alertConfirmation("Modificar pedido", "Se ha modificado el pedido seleccionado", "");
            renderWindowPedido();
            repartidorModificar.setText("");
            FechaModificar.setValue(LocalDate.now());
        } catch (Fechaerror e) {
            e.printStackTrace();
            AlertUtils.alertError("Error en la fecha", "La fecha digitada no es mayor a dos dias", "Inténtalo nuevamente");
        } catch (FechaMenor ex) {
            ex.printStackTrace();
            AlertUtils.alertError("Error en la fecha", "La fecha ya pertenece a un pedido con un mismo producto y cliente", "Inténtalo nuevamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            AlertUtils.alertError("ERROR", "No se pudo modificar el pedido", "Inténtalo nuevamente");
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

    //Seccion otros

    public void renderWindowOtros() {
    }

    public void clearWindowOtros() {
    }

    @FXML
    void verPedidosProductoYFechaEspecifica(ActionEvent event) {
        tablaFechaEspecifica.getItems().clear();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = fechaEspecifica.getValue();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(date);
        UUID prodID = productoEspecifico.getValue();
        try {
            ArrayList<Pedido> productosFecha = controlDespacho.verListadoDePedidosDeProductoYFechaEspecífica(prodID, fecha);
            if (productosFecha.size() == 0) {
                throw new ColeccionVacia("Arreglo vacio");
            }
            tablaFechaEspecifica.getItems().addAll(productosFecha);
            AlertUtils.alertConfirmation("Pedidos obtenidos", "Se obtenieron los pedidos satisfactoriamente", "Presiona Aceptar para continuar");
        } catch (ColeccionVacia e) {
            AlertUtils.alertError("Error", "No se pueden obtener los pedidos", "No existen pedidos con este producto en esta fecha");
        } catch (Exception ex) {
            ex.printStackTrace();
            AlertUtils.alertError("Error", "No se pueden obtener los pedidos", "Revise la fecha que ingresó e inténtelo de nuevo");
        }
    }

    @FXML
    void guardarProductoFruver(ActionEvent event) {
        controlDespacho.verProductosTipoFruver();
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter(FileType.XML.getFilter(), FileType.XML.getFilter());
        File ruta = AlertUtils.openFileChooserModeWrite(filtro, ((Button) event.getSource()).getScene().getWindow());
        try {
            if (controlDespacho.getReporteFruver().getProductosFruver().size() == 0){
                throw new ColeccionVacia("vacio");
            }
            FileUtils.saveXML(ruta, controlDespacho.getReporteFruver());
            AlertUtils.alertConfirmation("Generar reporte", "El reporte de productos tipo fruver se ha generado exitosamente", "Presiona OK para continuar");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            AlertUtils.alertError("Error", "No se pueden obtener los productos", "Revise los datos que ingresó e inténtelo de nuevo");
        } catch (JAXBException jex) {
            jex.printStackTrace();
            AlertUtils.alertError("Error", "No se pueden obtener los productos", "Revise los datos que ingresó e inténtelo de nuevo");
        } catch (ColeccionVacia coleccionVacia) {
            AlertUtils.alertInformation("No hay productos", "Sin productos", "No se encuentran productos fruver, revise los productos en el sistema y vuelva a intentarlo");
        }

    }

    @FXML
    void guardarProductosAseoTipo(ActionEvent event) {
        controlDespacho.PedidosDeAseoPorTipo(tipoProdGuardar.getSelectionModel().getSelectedItem());
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter(FileType.XML.getFilter(), FileType.XML.getFilter());
        File ruta = AlertUtils.openFileChooserModeWrite(filtro, ((Button) event.getSource()).getScene().getWindow());
        try {
            if (controlDespacho.getReporteAseoTipo().getPedidosProductosAseo().size() == 0) {
                throw new ColeccionVacia("Arreglo vacio");
            }
            FileUtils.saveXML(ruta, controlDespacho.getReporteAseoTipo());
            AlertUtils.alertConfirmation("Generar reporte", "El reporte de pedidos de productos de aseo de un tipo específico fue exitoso", "Presiona OK para continuar");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            AlertUtils.alertError("Error", "No se pueden obtener los pedidos", "Revise los datos que ingresó e inténtelo de nuevo");
        } catch (JAXBException jex) {
            jex.printStackTrace();
            AlertUtils.alertError("Error", "No se pueden obtener los pedidos", "Revise los datos que ingresó e inténtelo de nuevo");
        } catch (ColeccionVacia coleccionVacia) {
            AlertUtils.alertInformation("No hay pedidos", "Sin pedidos", "No se encuentran pedidos asociados a productos de aseo de este tipo, revise los productos en el sistema o su selección de tipo y vuelva a intentarlo");
        }
    }

    @FXML
    void guardarRangoFechas(ActionEvent event) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = fechaInicialGuardar.getValue();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        Calendar fechaInicio = Calendar.getInstance();
        fechaInicio.setTime(date);
        localDate = fechaFinalGuardar.getValue();
        date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        Calendar fechaFinal = Calendar.getInstance();
        fechaFinal.setTime(date);
        controlDespacho.pedidoEnRangoDeFechas(fechaInicio, fechaFinal);
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("XML", FileType.XML.getFilter());
        File ruta = AlertUtils.openFileChooserModeWrite(filtro, ((Button) event.getSource()).getScene().getWindow());

        try {
            if (controlDespacho.getReporteRangoFecha().getPedidos().size() == 0) {
                throw new ColeccionVacia("Arreglo vacio");
            }
            FileUtils.saveXML(ruta, controlDespacho.getReporteRangoFecha());
            AlertUtils.alertConfirmation("Generar reporte", "El reporte de rango de fechas se ha generado exitosamente", "Presiona OK para continuar");
        } catch (IOException | JAXBException ioe) {
            ioe.printStackTrace();
            AlertUtils.alertError("Error", "El archivo no pudo ser generado", "Inténtelo nuevamente");
        } catch (ColeccionVacia coleccionVacia) {
            AlertUtils.alertInformation("No hay pedidos", "Sin pedidos", "No se encuentran pedidos asociados a este rango de fechas, revise su selección de fecha y vuelva a intentarlo");
        }
    }

    @FXML
    void guardarTipoTransporte(ActionEvent event) {
        TipoTransporte tipo = tipoTransporteguardar.getSelectionModel().getSelectedItem();
        controlDespacho.enviosPrimePorTipo(tipo);
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("XML", FileType.XML.getFilter());
        File ruta = AlertUtils.openFileChooserModeWrite(filtro, ((Button) event.getSource()).getScene().getWindow());

        try {
            if (controlDespacho.getReporteEnvioTipo().getServciosEnvios().size() == 0) {
                throw new ColeccionVacia("Arreglo vacio");
            }
            FileUtils.saveXML(ruta, controlDespacho.getReporteEnvioTipo());
            AlertUtils.alertConfirmation("Generar reporte", "El reporte de servicios con envio Prime del sistema se ha generado exitosamente", "Presiona Aceptar para continuar");
        } catch (IOException | JAXBException ioe) {
            ioe.printStackTrace();
            AlertUtils.alertError("Error", "El archivo no pudo ser generado", "Inténtelo nuevamente");
        } catch (ColeccionVacia e) {
            AlertUtils.alertError("Error", "No existe reporte de este tipo o no seleccionó ninguno", "Inténtelo nuevamente");
        }
    }

    // Funciones Producto
    public void clearWindowProducto() {
        tablaProductos.getItems().clear();
        tablaEliminarProductos.getItems().clear();
        listaProductosModificar.getItems().clear();
        productosList.getItems().clear();
        productoEspecifico.getItems().clear();
    }

    public void renderWindowProducto() {
        clearWindowProducto();
        tablaProductos.getItems().addAll(controlDespacho.getGestionProductos().getListaProductos().values());
        tablaEliminarProductos.getItems().addAll(controlDespacho.getGestionProductos().getListaProductos().keySet());
        listaProductosModificar.getItems().addAll(controlDespacho.getGestionProductos().getListaProductos().keySet());
        if (controlDespacho.getGestionProductos().getListaProductos().size() > 0) {
            actModificarProducto.setDisable(false);
        }
        for (Producto prod : controlDespacho.getGestionProductos().getListaProductos().values()) {
            productosList.getItems().add(prod.getProdId());
            productoEspecifico.getItems().add(prod.getProdId());
        }
    }

    @FXML
    void switchTipoProducto(ActionEvent event) {
        boolean aseo = false;
        boolean fruver = false;

        if (tipoProducto.getSelectedToggle().equals(tipoFruver)) {
            aseo = true;
        } else if (tipoProducto.getSelectedToggle().equals(tipoAseo)) {
            fruver = true;
        } else if (tipoProducto.getSelectedToggle().equals(tipoOtro)) {
            aseo = true;
            fruver = true;
        }

        textAseo.setDisable(aseo);
        insertarNombreEmpresa.setDisable(aseo);
        checkInvima.setDisable(aseo);
        listTipoProducto.setDisable(aseo);

        textFruver.setDisable(fruver);
        insertarImpuestoLocal.setDisable(fruver);
        insertarNombreHacienda.setDisable(fruver);
        checkOrganico.setDisable(fruver);
    }

    @FXML
    void agregarProducto() {
        try {
            Producto nuevoProducto = null;
            if (tipoProducto.getSelectedToggle().equals(tipoAseo)) {
                nuevoProducto = new Aseo(insertarNombreProducto.getText(), Double.valueOf(insertarPrecioProducto.getText()), insertarTiendaProducto.getText(), insertarNombreEmpresa.getText(), listTipoProducto.getValue(), checkInvima.isSelected());
            } else if (tipoProducto.getSelectedToggle().equals(tipoFruver)) {
                nuevoProducto = new Fruver(insertarNombreProducto.getText(), Double.valueOf(insertarPrecioProducto.getText()), insertarTiendaProducto.getText(), Double.valueOf(insertarImpuestoLocal.getText()), insertarNombreHacienda.getText());
            } else {
                nuevoProducto = new Producto(insertarNombreProducto.getText(), Double.valueOf(insertarPrecioProducto.getText()), insertarTiendaProducto.getText());
            }
            controlDespacho.getGestionProductos().insertarProducto(nuevoProducto);
            renderWindowProducto();
            AlertUtils.alertConfirmation("Producto agregado correctamente", "Se ha añadido el nuevo producto.", "Hay " + controlDespacho.getGestionProductos().getListaProductos().size() + " productos en el sistema.");
            insertarNombreProducto.setText("");
            insertarPrecioProducto.setText("");
            insertarTiendaProducto.setText("");
            insertarNombreEmpresa.setText("");
            checkInvima.setSelected(false);
            insertarImpuestoLocal.setText("");
            insertarNombreHacienda.setText("");
            checkOrganico.setSelected(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            AlertUtils.alertError("ERROR", "No se ha podido agregar este producto.", "Por favor, intente de nuevo.");
        }
    }

    @FXML
    void eliminarProducto() {
        Optional<ButtonType> confirmacion = AlertUtils.alertConfirmation("Eliminar producto", "Se eliminará el producto con UUID " + tablaEliminarProductos.getSelectionModel().getSelectedItem().toString(), "¿Está seguro?");
        try {
            if (confirmacion.get().equals(ButtonType.OK)) {
                if (controlDespacho.ValidarProducto(tablaEliminarProductos.getSelectionModel().getSelectedItem())) {
                    throw new ProductoAsociadoAPedido("Producto asociado a un pedido");
                } else {
                    controlDespacho.getGestionProductos().eliminarProducto(tablaEliminarProductos.getSelectionModel().getSelectedItem());
                    AlertUtils.alertInformation("Producto eliminado correctamente", "Se ha eliminado el producto.", "");
                }
            }
            if (this.controlDespacho.getGestionProductos().getListaProductos().isEmpty()) {
                insertarNombreModProd.setText("");
                insertarPrecioModProd.setText("");
                insertarPrecioModProd.setText("");
                insertarTiendaModProd.setText("");
                switchModificarProducto();
            }

            renderWindowProducto();
        } catch (ProductoAsociadoAPedido ex) {
            ex.printStackTrace();
            AlertUtils.alertError("ERROR", "No se puede eliminar el producto, ya que pertenece a un pedido", "Primero elimine el pedido para eliminar el producto selccionado");
        } catch (Exception ex) {
            ex.printStackTrace();
            AlertUtils.alertError("ERROR", "No se ha podido eliminar este producto.", "Por favor, intente de nuevo.");
        }
    }

    @FXML
    void switchModificarProducto() {
        boolean accion = false;
        if (!actModificarProducto.isSelected() || this.controlDespacho.getGestionProductos().getListaProductos().isEmpty()) {
            accion = true;
        }
        listaProductosModificar.setDisable(accion);
        insertarNombreModProd.setDisable(accion);
        insertarPrecioModProd.setDisable(accion);
        insertarPrecioModProd.setDisable(accion);
        insertarTiendaModProd.setDisable(accion);
        btnModificarProducto.setDisable(accion);
        tipoAseoModificar.setDisable(accion);
        tipoFruverModificar.setDisable(accion);
        tipoOtroModificar.setDisable(accion);
    }

    @FXML
    void modificarProducto() {
        try {
            if (tipoFruverModificar.isSelected()) {
                Producto productoAnterior = controlDespacho.getGestionProductos().getListaProductos().get(listaProductosModificar.getSelectionModel().getSelectedItem());
                Producto modificarProducto = null;
                if (!(productoAnterior instanceof Fruver)) {
                    modificarProducto = new Fruver(productoAnterior.getProdId(), productoAnterior.getNombreComercial(), productoAnterior.getPrecio(), productoAnterior.getTienda(), Double.valueOf(insertarImpuestoLocalMod.getText()), insertarNombreHaciendaMod.getText());
                    controlDespacho.getGestionProductos().eliminarProducto(productoAnterior.getProdId());
                    controlDespacho.getGestionProductos().insertarProducto(modificarProducto);
                } else {
                    productoAnterior.setNombreComercial(insertarNombreModProd.getText());
                    productoAnterior.setPrecio(Double.valueOf(insertarPrecioModProd.getText()));
                    productoAnterior.setTienda(insertarTiendaModProd.getText());
                    ((Fruver) productoAnterior).setImpuestoLocal(Double.valueOf(insertarImpuestoLocalMod.getText()));
                    ((Fruver) productoAnterior).setNombreHacienda(insertarNombreHaciendaMod.getText());
                }
            } else if (tipoAseoModificar.isSelected()) {
                Producto productoAnterior = controlDespacho.getGestionProductos().getListaProductos().get(listaProductosModificar.getSelectionModel().getSelectedItem());
                Producto modificarProducto = null;
                if (!(productoAnterior instanceof Aseo)) {
                    modificarProducto = new Aseo(productoAnterior.getProdId(), productoAnterior.getNombreComercial(), productoAnterior.getPrecio(), productoAnterior.getTienda(), insertarNombreAseoMod.getText(), listTipoProductoMod.getSelectionModel().getSelectedItem(), checkInvimaMod.isSelected());
                    controlDespacho.getGestionProductos().eliminarProducto(productoAnterior.getProdId());
                    controlDespacho.getGestionProductos().insertarProducto(modificarProducto);
                } else {
                    productoAnterior.setNombreComercial(insertarNombreModProd.getText());
                    productoAnterior.setPrecio(Double.valueOf(insertarPrecioModProd.getText()));
                    productoAnterior.setTienda(insertarTiendaModProd.getText());
                    ((Aseo) productoAnterior).setNombreEmpresa(insertarNombreAseoMod.getText());
                    ((Aseo) productoAnterior).setTipo(listTipoProductoMod.getSelectionModel().getSelectedItem());
                    ((Aseo) productoAnterior).setTieneInvima(checkInvimaMod.isSelected());
                }
            } else if (tipoOtroModificar.isSelected()) {
                Producto productoAnterior = controlDespacho.getGestionProductos().getListaProductos().get(listaProductosModificar.getSelectionModel().getSelectedItem());
                Producto modificarProducto = null;
                if (!(productoAnterior.getClass().equals(Producto.class))) {
                    modificarProducto = new Producto(productoAnterior.getProdId(), productoAnterior.getNombreComercial(), productoAnterior.getPrecio(), productoAnterior.getTienda());
                    controlDespacho.getGestionProductos().eliminarProducto(productoAnterior.getProdId());
                    controlDespacho.getGestionProductos().insertarProducto(modificarProducto);
                } else {
                    productoAnterior.setNombreComercial(insertarNombreModProd.getText());
                    productoAnterior.setPrecio(Double.valueOf(insertarPrecioModProd.getText()));
                    productoAnterior.setTienda(insertarTiendaModProd.getText());
                }
            }
            AlertUtils.alertInformation("Producto modificado correctamente", "Se ha modificado el producto.", "");
        } catch (Exception ex) {
            ex.printStackTrace();
            AlertUtils.alertError("ERROR", "No se ha podido modificar este producto.", "Por favor, intente de nuevo.");
        }
        renderWindowProducto();
    }

    @FXML
    void actualizarInformacionModificar() {
        try {
            insertarNombreModProd.setText(controlDespacho.getGestionProductos().getListaProductos().get(listaProductosModificar.getSelectionModel().getSelectedItem()).getNombreComercial());
            insertarPrecioModProd.setText(controlDespacho.getGestionProductos().getListaProductos().get(listaProductosModificar.getSelectionModel().getSelectedItem()).getPrecio().toString());
            insertarTiendaModProd.setText(controlDespacho.getGestionProductos().getListaProductos().get(listaProductosModificar.getSelectionModel().getSelectedItem()).getTienda().toString());

            if (controlDespacho.getGestionProductos().getListaProductos().get(listaProductosModificar.getSelectionModel().getSelectedItem()) instanceof Aseo) {
                tipoAseoModificar.fire();
            } else if (controlDespacho.getGestionProductos().getListaProductos().get(listaProductosModificar.getSelectionModel().getSelectedItem()) instanceof Fruver) {
                tipoFruverModificar.fire();
            } else {
                tipoOtroModificar.fire();
            }

            actualizarTipoProdMod();

        } catch (Exception ex) {

        }
    }

    @FXML
    void actualizarTipoProdMod() {
        boolean aseo = false;
        boolean fruver = false;
        if (tipoProductoModificar.getSelectedToggle().equals(tipoAseoModificar)) {
            aseo = true;
        } else if (tipoProductoModificar.getSelectedToggle().equals(tipoFruverModificar)) {
            fruver = true;
        }

        if (controlDespacho.getGestionProductos().getListaProductos().get(listaProductosModificar.getSelectionModel().getSelectedItem()) instanceof Aseo) {
            insertarNombreAseoMod.setText(((Aseo) controlDespacho.getGestionProductos().getListaProductos().get(listaProductosModificar.getSelectionModel().getSelectedItem())).getNombreEmpresa());
            if (((Aseo) controlDespacho.getGestionProductos().getListaProductos().get(listaProductosModificar.getSelectionModel().getSelectedItem())).getTieneInvima()) {
                checkInvimaMod.fire();
            }
            listTipoProductoMod.getSelectionModel().select(((Aseo) controlDespacho.getGestionProductos().getListaProductos().get(listaProductosModificar.getSelectionModel().getSelectedItem())).getTipo());
        } else if (controlDespacho.getGestionProductos().getListaProductos().get(listaProductosModificar.getSelectionModel().getSelectedItem()) instanceof Fruver) {
            insertarImpuestoLocalMod.setText(((Fruver) controlDespacho.getGestionProductos().getListaProductos().get(listaProductosModificar.getSelectionModel().getSelectedItem())).getImpuestoLocal().toString());
            insertarNombreHaciendaMod.setText(((Fruver) controlDespacho.getGestionProductos().getListaProductos().get(listaProductosModificar.getSelectionModel().getSelectedItem())).getNombreHacienda());
            if (((Fruver) controlDespacho.getGestionProductos().getListaProductos().get(listaProductosModificar.getSelectionModel().getSelectedItem())).getEsOrganico()) {
                checkOrganicoMod.fire();
            }
        }

        textAseoMod.setDisable(!aseo);
        insertarNombreAseoMod.setDisable(!aseo);
        checkInvimaMod.setDisable(!aseo);
        listTipoProductoMod.setDisable(!aseo);

        textAseoMod.setVisible(aseo);
        insertarNombreAseoMod.setVisible(aseo);
        checkInvimaMod.setVisible(aseo);
        listTipoProductoMod.setVisible(aseo);

        textFruverMod.setDisable(!fruver);
        insertarImpuestoLocalMod.setDisable(!fruver);
        insertarNombreHaciendaMod.setDisable(!fruver);
        checkOrganicoMod.setDisable(!fruver);

        textFruverMod.setVisible(fruver);
        insertarImpuestoLocalMod.setVisible(fruver);
        insertarNombreHaciendaMod.setVisible(fruver);
        checkOrganicoMod.setVisible(fruver);
    }

}
