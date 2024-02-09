package br.ufal.ic.p2.wepayu.Exception;

public class SindicatoRepetido extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7056538219400194834L;

	public SindicatoRepetido() {
    	super("Ha outro empregado com esta identificacao de sindicato");
    }
}