package org.alexc.poointerfaces.repositorio;

import org.alexc.poointerfaces.modelo.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteListRepositorio implements OrdenablePaginableCrudRepositorio{

    private List<Cliente> datasource;

    public ClienteListRepositorio() {
        this.datasource = new ArrayList<>();
    }

    @Override
    public List<Cliente> listar() {
        return this.datasource;
    }

    @Override
    public Cliente porId(Integer id) {
        Cliente c = null;
        for (Cliente cl : this.datasource){
            if(cl.getId().equals(id)){
                c = cl;
                break;
            }
        }
        return c;
    }

    @Override
    public void crear(Cliente cliente) {
        this.datasource.add(cliente);
    }

    @Override
    public void editar(Cliente cliente) {
        Cliente c = this.porId(cliente.getId());
        c.setNombre(cliente.getNombre());
        c.setApellido(cliente.getApellido());
    }

    @Override
    public void eliminar(Integer id) {
        this.datasource.remove(this.porId(id));
    }

    //Ordenable usando una clase anonima...
//    @Override
//    public List<Cliente> listar(String campo, Direccion dir) {
//        List<Cliente> listaOrdenada = new ArrayList<>(this.datasource);
//        listaOrdenada.sort(new Comparator<Cliente>() { //usando clase anonima se puede crear un metodo
//            @Override
//            public int compare(Cliente a, Cliente b) {
//                int resultado = 0;
//                if(dir == Direccion.ASC){
//                    resultado = this.ordenar(a,b);
//                }else if(dir == Direccion.DESC){
//                    resultado = this.ordenar(b,a);
//                }
//                return resultado;
//            }
//
//            private int ordenar(Cliente a, Cliente b){
//                int resultadoComparador =0;
//                switch (campo){
//                    case "id" ->
//                            resultadoComparador = a.getId().compareTo(b.getId());
//                    case "nombre" ->
//                            resultadoComparador = a.getNombre().compareTo(b.getNombre());
//                    case "apellido" ->
//                            resultadoComparador = a.getApellido().compareTo(b.getApellido());
//                }
//                return resultadoComparador;
//            }
//
//        });
//        return listaOrdenada;
//    }
    //Ordenable usando expresion lambda, que es mejor !
    @Override
    public List<Cliente> listar(String campo, Direccion dir) {
        List<Cliente> listaOrdenada = new ArrayList<>(this.datasource);
        //usando clase anonima se puede crear un metodo
        listaOrdenada.sort((a, b) -> {
            int resultado = 0;
            if(dir == Direccion.ASC){
                resultado = ordenar(campo,a,b);
            }else if(dir == Direccion.DESC){
                resultado = ordenar(campo,b,a);
            }
            return resultado;
        });
        return listaOrdenada;
    }

    //Paginable
    @Override
    public List<Cliente> listar(int desde, int hasta) {
        return datasource.subList(desde, hasta);
    }


    //metodos de la clase
    public static int ordenar(String campo,Cliente a, Cliente b){
        int resultadoComparador =0;
        switch (campo){
            case "id" ->
                    resultadoComparador = a.getId().compareTo(b.getId());
            case "nombre" ->
                    resultadoComparador = a.getNombre().compareTo(b.getNombre());
            case "apellido" ->
                    resultadoComparador = a.getApellido().compareTo(b.getApellido());
        }
        return resultadoComparador;
    }

    @Override
    public int total() {
        return this.datasource.size();
    }
}
