package br.ufal.ic.p2.wepayu.models;


//import java.util.ArrayList;
//import br.ufal.ic.p2.wepayu.Exception.*;
//import java.util.UUID;

public class Empregado {
	private String nome;
    private String endereco;
    private String tipo;
    private String salario;
    private boolean sindicalizado;
    private String id;

    //private ArrayList<Empregado> empregados = new ArrayList<Empregado>();
    public Empregado() {}
    
    public Empregado(String nome, String endereco, String tipo, String salario){
    	//public Empregado(String nome, String endereco, String tipo, float salario) throws EmpregadoNaoExisteException {
        this.nome = nome;
        this.endereco = endereco;
        this.tipo = tipo;
        this.salario = salario;
        this.sindicalizado = false;
        this.id = null;
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

	public boolean isSindicalizado() {
		return sindicalizado;
	}
	
	public String getId() {
        return id;
    }

	public void setId(String id) {
		this.id = id;
	} 
}
