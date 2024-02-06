package br.ufal.ic.p2.wepayu.Exception;

public class IdNula extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7056538219400194834L;

	public IdNula() {
    	super("Identificacao do empregado nao pode ser nula.");
    }
}