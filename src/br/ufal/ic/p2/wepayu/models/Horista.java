package br.ufal.ic.p2.wepayu.models;

import java.util.ArrayList;
import br.ufal.ic.p2.wepayu.Exception.*;
import java.util.UUID;

public class Horista extends Empregado{

    //private ArrayList<Comissionado> empregados = new ArrayList<Empregado>();
    
    
    public Horista(String nome, String endereco, String tipo, String salario, String comissao){
    	//public Empregado(String nome, String endereco, String tipo, float salario) throws EmpregadoNaoExisteException {
        super(nome, endereco, tipo, salario);
    }

}