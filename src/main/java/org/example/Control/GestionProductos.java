package org.example.Control;

import org.example.Model.Producto;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class GestionProductos {

    private ArrayList<Producto> listaProductos = new ArrayList<>();

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
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
}
