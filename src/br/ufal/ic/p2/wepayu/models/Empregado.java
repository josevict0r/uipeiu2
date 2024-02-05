package br.ufal.ic.p2.wepayu.models;

import java.util.ArrayList;
import br.ufal.ic.p2.wepayu.Exception.*;
import java.util.UUID;

public class Empregado {
    private String nome;
    private String endereco;
    private String tipo;
    private float salario;

    private ArrayList<Empregado> empregados = new ArrayList<Empregado>();
    
    
    public Empregado(String nome, String endereco, String tipo, float salario){
    	//public Empregado(String nome, String endereco, String tipo, float salario) throws EmpregadoNaoExisteException {
        this.nome = nome;
        this.endereco = endereco;
        this.tipo = tipo;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTipo() {
        return tipo;
    }

    public float getSalario() {
        return salario;
    }

    
    public String AdicionarEmpregado(String nome, String endereco, String tipo, float salario) throws NomeNulo, EnderecoNulo, TipoInvalido {
    	Empregado novo = new Empregado(nome, endereco, tipo, salario);
    	String id = UUID.randomUUID().toString();
    	
    	if(novo.nome.isBlank()) {
    		//System.out.print("CUUUUU");
    		throw new NomeNulo();
    	}
    	if(novo.endereco.isBlank()) {
    		//System.out.print("CUUUUU");
    		throw new EnderecoNulo();
    	}
    	if(novo.tipo != "horista" && novo.tipo != "assalariado" && novo.tipo != "comissionado") {
    		throw new TipoInvalido();
    	}
    	
    	empregados.add(novo);
    	return id;
    }
}
