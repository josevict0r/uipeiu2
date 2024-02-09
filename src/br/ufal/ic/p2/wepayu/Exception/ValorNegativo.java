package br.ufal.ic.p2.wepayu.Exception;

public class ValorNegativo extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7056538219400194834L;

	public ValorNegativo() {
    	super("Valor deve ser positivo.");
    }
}
