package org.PUJ.Control;

import org.PUJ.Model.Producto;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class GestionProductos {
    private Map<UUID, Producto> listaProductos = new HashMap<>();

    public Map<UUID, Producto> getListaProductos() {
        return listaProductos;
    }
    public Producto existeProducto (UUID codigo){
        if (listaProductos.containsKey(codigo)){
            return listaProductos.get(codigo);
        }
        return null;
    }

    public void modificarProducto(UUID codigoPorducto){
        boolean productoEncontrado=false;
        Producto prod=new Producto();
        for(Producto auxProd: listaProductos.values()){
            if(auxProd.getProdId().equals(codigoPorducto)){
                productoEncontrado=true;
                prod = auxProd;
            }
        }
        if(productoEncontrado){
            System.out.println("El producto a modificar es: ");
            System.out.println(prod.toString());
            int opcion=1;
            Scanner in = new Scanner(System.in);
            do{
                System.out.println("¿Qué aspecto desea modificar del producto? Digite la opción deseada");
                System.out.println("\t1. Nombre Comercial");
                System.out.println("\t2. Precio");
                System.out.println("\t3. Tienda");
                System.out.println("\t0. No modificar datos");
                opcion = in.nextInt();
                in.useDelimiter("\n");
                switch (opcion){
                    case 1:
                        String nombreNuevo;
                        System.out.println("Digite el nuevo nombre: ");
                        nombreNuevo=in.next();
                        prod.setNombreComercial(nombreNuevo);
                        break;
                    case 2:
                        Double precioNuevo;
                        System.out.println("Digite el nuevo precio: ");
                        precioNuevo=in.nextDouble();
                        Double nuevoIva= precioNuevo*0.19;
                        prod.setPrecio(precioNuevo);
                        prod.setIva(nuevoIva);
                        break;
                    case 3:
                        String tiendaNueva;
                        System.out.println("Digite la nueva tienda: ");
                        tiendaNueva=in.next();
                        prod.setTienda(tiendaNueva);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Digite una opción válida");
                        break;
                }
            }while(opcion!=0);
        }else{
            System.out.println("\tEl producto no fue encontrado");
        }
    }
    public void verListadoDeProductos () {
        if (!listaProductos.isEmpty()) {
            for (UUID key : listaProductos.keySet()) {
                System.out.println(listaProductos.get(key).toString());
            }
        } else {
            System.out.println("\t[!] No hay productos registrados en el sistema.");
        }
    }

    public void insertarProducto(String nombreProducto, Double precioProducto, String nombreTienda) {
        Producto nuevoProducto = new Producto(nombreProducto, precioProducto, nombreTienda);
        if (!listaProductos.containsKey(nuevoProducto.getProdId())) {
            listaProductos.put(nuevoProducto.getProdId(), nuevoProducto);
            System.out.println("\t[-] El producto " + nuevoProducto.getNombreComercial() + " fue agregado a la tienda " + nuevoProducto.getTienda() + ".");
        } else {
            System.out.println("\t[!] Error. Ya existe un producto con este ID.");
        }
    }

    public void eliminarProducto(UUID nKey) {
        if (listaProductos.containsKey(nKey)) {
            listaProductos.remove(nKey);
            System.out.println("\t[!] El producto fue eliminado correctamente.");
        } else {
            System.out.println("\t[!] No existe un producto con este ID.");
        }
    }
}
