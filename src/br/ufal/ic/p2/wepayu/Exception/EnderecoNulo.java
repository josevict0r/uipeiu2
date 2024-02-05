package br.ufal.ic.p2.wepayu.Exception;

public class EnderecoNulo extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7056538219400194834L;

	public EnderecoNulo() {
    	super("Endereco nao pode ser nulo.");
    }
}
