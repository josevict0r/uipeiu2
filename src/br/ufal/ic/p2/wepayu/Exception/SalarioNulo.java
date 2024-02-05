package br.ufal.ic.p2.wepayu.Exception;

public class SalarioNulo extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7056538219400194834L;

	public SalarioNulo() {
    	super("Salario nao pode ser nulo.");
    }
}
