package br.ufal.ic.p2.wepayu.Exception;

public class ViagemNoTempo extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7056538219400194834L;

	public ViagemNoTempo() {
    	super("Data inicial nao pode ser posterior aa data final.");
    }
}
