package org.alexc.generics;

import org.alexc.poointerfaces.modelo.Cliente;
import org.alexc.poointerfaces.modelo.ClientePremium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EjemploGenericos {
    public static void main(String[] args) {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("Alex", "Coro"));

//        Cliente alex = (Cliente)clientes.get(0);
        Cliente alex = clientes.iterator().next();

        Cliente[] clientesArr = {
                alex,
                new Cliente("Pablo", "Torres"),
                new Cliente("Tomas", "Mazza")
        };
        Integer[] enterosArr = {1, 2, 3};

        List<Cliente> clientesLista = fromArraytoList(clientesArr);
        List<Integer> enterosLista = fromArraytoList(enterosArr);

        clientesLista.forEach(System.out::println);
        enterosLista.forEach(System.out::println);

        List<String> nombres = fromArraytoList(new String[]{"Andres", "Pepe", "Lucy"}, enterosArr);
        nombres.forEach(System.out::println);


        List<ClientePremium> clientesPremiumList = fromArraytoList(new ClientePremium[]{
                new ClientePremium("Leonel", "Messi"),
                new ClientePremium("Cristiano", "Ronaldo")
        });
        clientesPremiumList.forEach(System.out::println);


        //***************////
        imprimirClientes(clientes);
        imprimirClientes(clientesLista);
        imprimirClientes(clientesPremiumList);

        //*******************//
        System.out.println("Maximo de 1, 9 ,4 es " + maximo(1,9,4));
        System.out.println("Maximo de 3.9, 11.6, 7.78 es "+ maximo(3.9,11.6,7.78));
        System.out.println("Maximo de zanahori, arandanos, manzana es " + maximo("zanahoria",
                "arandano", "manzana"));
    }






//    //pasando un arreglo de clientes a una lista
//    public static List<Cliente> fromArraytoList(Cliente[] arr){
//        return Arrays.asList(arr);
//    }
    //usando genericos para no atarnos a un solo tipo de datos
    public static <T> List<T> fromArraytoList(T[] arr){
        return Arrays.asList(arr);
    }

    public static <T,G> List<T> fromArraytoList(T[] arr, G[] g){
        for (G elemento: g){
            System.out.println(elemento);
        }
        return Arrays.asList(arr);
    }

    //Metodos genericos con limites, Bounded Generics
    //solo acepta tipos de dato Number
    public static <T extends Number> List<T> fromArraytoList(T[] arr){
        return Arrays.asList(arr);
    }

//    //Mismo metodo pero que acepta datos del tipo Cliente y subclases de Cliente (que hereden de cliente)
//    public static <T extends Cliente> List<T> fromArraytoList(T[] arr){
//        return Arrays.asList(arr);
//    }

    //Mismo metodo pero que acepta datos del tipo Cliente y subclases de Cliente (que hereden de cliente)
    //ademas que utilice Comparable
    public static <T extends Cliente & Comparable<T>> List<T> fromArraytoList(T[] arr){
        return Arrays.asList(arr);
    }

    /*WILDCARDS
    //con este metodo no puedo imprimir clases hijas de Cliente
    public static void imprimirClientes(List<Cliente> clientes){
        clientes.forEach(System.out::println);
    }
    */
    //para poder imprimir cualquier tipo de Listas de Cliente
    public static void imprimirClientes(List<? extends Cliente> clientes){
        clientes.forEach(System.out::println);
    }

    //Maximo entre 3 objetos usando Comparable
    /*
    public static <T extends Comparable<T>> T maximo(T a, T b, T c){
        T max = a;
        if(b.compareTo(max)>0){
            max = b;
        }
        if(c.compareTo(max)>0){
            max = c;
        }
        return max;
    }*/
    //Optimizando usando operador ternario
    public static <T extends Comparable<T>> T maximo(T a, T b, T c) {
        T maxAB = a.compareTo(b) > 0 ? a : b;
        return maxAB.compareTo(c) > 0 ? maxAB : c;
        //puede ser en una sola linea
//        return (a.compareTo(b) > 0 ? (a.compareTo(c) > 0 ? a : c) : (b.compareTo(c) > 0 ? b : c));
    }

    /* ********** */


}
