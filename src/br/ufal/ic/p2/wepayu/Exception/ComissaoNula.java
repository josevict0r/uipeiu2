package br.ufal.ic.p2.wepayu.Exception;

public class ComissaoNula extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7056538219400194834L;

	public ComissaoNula() {
    	super("Comissao nao pode ser nula.");
    }
}