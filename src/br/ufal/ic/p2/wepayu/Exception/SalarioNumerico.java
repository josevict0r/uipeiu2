package br.ufal.ic.p2.wepayu.Exception;

public class SalarioNumerico extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7056538219400194834L;

	public SalarioNumerico() {
    	super("Salario deve ser numerico.");
    }
}