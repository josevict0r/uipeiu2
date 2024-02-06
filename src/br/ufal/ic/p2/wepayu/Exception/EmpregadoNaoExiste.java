package br.ufal.ic.p2.wepayu.Exception;

public class EmpregadoNaoExiste extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7056538219400194834L;

	public EmpregadoNaoExiste() {
    	super("Empregado nao existe.");
    }
}