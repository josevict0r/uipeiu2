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
    private Sindicato sindicato;
    protected String metodoPagamento;
    protected Banco banco;

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
        this.sindicato = null;
        this.metodoPagamento = null;
        this.banco = null;
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

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void setSalario(String salario) {
		this.salario = salario;
	}
	
	public void setSindicalizado(boolean sindicalizado) {
		this.sindicalizado = sindicalizado;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public Sindicato getSindicato() {
		return sindicato;
	}

	public void setSindicato(Sindicato sindicato) {
		this.sindicato = sindicato;
	}

	public String getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(String metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}	
	
	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}	
}
