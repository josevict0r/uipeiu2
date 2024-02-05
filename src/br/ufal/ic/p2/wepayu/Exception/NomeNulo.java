package br.ufal.ic.p2.wepayu.Exception;

public class NomeNulo extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7056538219400194834L;

	public NomeNulo() {
    	super("Nome nao pode ser nulo.");
    }
}
