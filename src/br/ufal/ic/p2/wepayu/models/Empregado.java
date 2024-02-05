package br.ufal.ic.p2.wepayu.models;

import java.util.ArrayList;
import br.ufal.ic.p2.wepayu.Exception.*;
import java.util.UUID;

public class Empregado {
    private String nome;
    private String endereco;
    private String tipo;
    private String salario;

    private ArrayList<Empregado> empregados = new ArrayList<Empregado>();
    
    
    public Empregado(String nome, String endereco, String tipo, String salario){
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

    public String getSalario() {
        return salario;
    }

    
    public String AdicionarEmpregado(String nome, String endereco, String tipo, String salario) throws NomeNulo, EnderecoNulo, TipoInvalido, SalarioNulo, SalarioNegativo, SalarioNumerico {
    	Empregado novo = new Empregado(nome, endereco, tipo, salario);
    	String id = UUID.randomUUID().toString();
    	//char menos = novo.salario.charAt(0);
    	if(novo.nome.isBlank()) {
    		//System.out.print("CUUUUU");
    		throw new NomeNulo();
    	}
    	if(novo.endereco.isBlank()) {
    		//System.out.print("CUUUUU");
    		throw new EnderecoNulo();
    	}
    	//System.out.print(novo.tipo);
    	if(!(novo.tipo.equals("horista")) && !(novo.tipo.equals("assalariado")) && !(novo.tipo.equals("comissionado"))) {
    
    		throw new TipoInvalido();
    	}
    	if(novo.salario.isBlank()) {
    		throw new SalarioNulo();
    	}
    	if(novo.salario.contains("-")) {
    		throw new SalarioNegativo();
    	}
    	if(!isNumeric(novo.salario)) {
    		throw new SalarioNumerico();
    	}
    	
    	empregados.add(novo);
    	return id;
    }
    
    public static boolean isNumeric(String str) { 
    	  try {  
    	    Double.parseDouble(str);  
    	    return true;
    	  } catch(NumberFormatException e){  
    	    return false;  
    	  }  
    	}
}
