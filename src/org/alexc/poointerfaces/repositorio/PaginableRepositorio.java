package org.alexc.poointerfaces.repositorio;

import org.alexc.poointerfaces.modelo.Cliente;

import java.util.List;

public interface PaginableRepositorio {
    List<Cliente> listar(int desde, int hasta);
}
