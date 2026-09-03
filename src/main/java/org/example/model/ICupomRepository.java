package org.example.model;

import java.util.List;
import java.util.Optional;

public interface ICupomRepository {
    void adicionarCupom(Cupom cupom);
    Optional<Cupom> getPorCodigo(String codigo);
    List<Cupom> getCuponsDisponiveis();
}
