package org.example.Control;

import org.example.Model.Cliente;
import org.example.Model.Producto;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class GestionProductos {

    private ArrayList<Producto> listaProductos = new ArrayList<>();

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public Producto existeProducto (UUID codigo){
        for (Producto protemp: listaProductos){
            if (protemp.getProdId() == codigo){
                return protemp;
            }
        }
        return null;
    }

    public ArrayList<Producto> VerListadoDeProductos () {

        ArrayList<Producto> listatemp = new ArrayList<>();
        listatemp.addAll(listaProductos);
        return listatemp;
    }

    public void modificarProducto(UUID codigoPorducto){
        boolean productoEncontrado=false;
        Producto prod=new Producto();
        for(Producto auxProd: listaProductos){
            if(auxProd.getProdId().equals(codigoPorducto)){
                productoEncontrado=true;
                prod=auxProd;
            }
        }
        if(productoEncontrado){
            System.out.println("El producto a modificar es: ");
            System.out.println(prod.toString());
            int opcion=1;
            Scanner in = new Scanner(System.in);
            do{
                System.out.println("¿Qué aspecto desea modificar del producto? Digite la opción deseada");
                System.out.println("1. Nombre Comercial");
                System.out.println("2. Precio");
                System.out.println("3. IVA");
                System.out.println("4. Tienda");
                System.out.println("0. No modificar datos");
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
                        prod.setPrecio(precioNuevo);
                        break;
                    case 3:
                        Double ivaNuevo;
                        System.out.println("Digite el nuevo IVA: ");
                        ivaNuevo=in.nextDouble();
                        prod.setIva(ivaNuevo);
                        break;
                    case 4:
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
        }
    }

    public void eliminarProduct(UUID codigo){
        boolean ver = true;
        char confi = 'j';
        for(Producto p: listaProductos){
            if (p.getProdId().equals(codigo)) {
                ver = false;
                System.out.println("\t Desea eliminar el producto? \nS = si\nN = no");
                Scanner s = new Scanner(System.in);
                confi = s.next().charAt(0);
                if(confi=='S'){
                    listaProductos.remove(p);
                    System.out.println("\tEl codigo del producto elimado es: "+p.getProdId());
                    System.out.println("\tPRODUCTO ELIMINADO CON EXITO");
                }
                break;
            }
        }
        if(ver && confi!='N')
            System.out.println("\t PRODUCTO NO ENCONTRADO");
    }

    public void insertarProductos(Producto nuevoProducto) {
        boolean agregar = true;
        for (Producto prod : this.listaProductos){
            if (prod.getProdId() == nuevoProducto.getProdId()){
                agregar = false;
                System.out.println("[!] Error al agregar producto: Ya existe un producto con este ID.");
                break;
            }

        }
        if (agregar){
            nuevoProducto.setIva(nuevoProducto.getPrecio()*0.19);
            this.listaProductos.add(nuevoProducto);
        }
    }

}
