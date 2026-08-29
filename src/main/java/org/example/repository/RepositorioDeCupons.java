package org.example.repository;

import org.example.model.Cupom;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeCupons {
    private List<Cupom> cuponsDisponiveis;

    public RepositorioDeCupons(){
        this.cuponsDisponiveis = new ArrayList<>();
    }

    public void adicionarCupom(Cupom cupom){
        this.cuponsDisponiveis.add(cupom);
    }

    public Cupom buscarPorCodigo(String codigo){
        for(Cupom cupom: cuponsDisponiveis){
            if(cupom.getCodigo().equalsIgnoreCase(codigo)){
                return cupom;
            }
        }
        return null;
    }

    public List<Cupom> getCuponsDisponiveis(){
        return cuponsDisponiveis;
    }

}
