package br.ufal.ic.p2.wepayu.models;


public class Comissionado extends Empregado{
    private String comissao;

    //private ArrayList<Comissionado> empregados = new ArrayList<Empregado>();
    public Comissionado() {}
    
    public Comissionado(String nome, String endereco, String tipo, String salario, String comissao){
    	//public Empregado(String nome, String endereco, String tipo, float salario) throws EmpregadoNaoExisteException {
        super(nome, endereco, tipo, salario);
        this.comissao = comissao;
    }

    public String getComissao() {
        return comissao;
    }
    
}