package br.ufal.ic.p2.wepayu.Exception;

public class idSindicatoNula extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7056538219400194834L;

	public idSindicatoNula() {
    	super("Identificacao do membro nao pode ser nula.");
    }
}
