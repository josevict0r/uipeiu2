package br.ufal.ic.p2.wepayu.Exception;

public class SalarioNegativo extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7056538219400194834L;

	public SalarioNegativo() {
    	super("Salario deve ser nao-negativo.");
    }
}
