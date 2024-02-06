package br.ufal.ic.p2.wepayu.Exception;

public class ComissaoNumerica extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7056538219400194834L;

	public ComissaoNumerica() {
    	super("Comissao deve ser numerica.");
    }
}
