package br.ufal.ic.p2.wepayu.Exception;

public class HoraNegativa extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7056538219400194834L;

	public HoraNegativa() {
    	super("Horas devem ser positivas.");
    }
}
