package br.ufal.ic.p2.wepayu;

import br.ufal.ic.p2.wepayu.Exception.*;
//import br.ufal.ic.p2.wepayu.Exception.EmpregadoNaoExisteException;
import br.ufal.ic.p2.wepayu.models.Empregado;

//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;

public class Facade {

	private Empregado empregado;
	
	public String criarEmpregado(String nome, String endereco, String tipo, String salario) throws NomeNulo, EnderecoNulo, TipoInvalido, SalarioNulo, SalarioNegativo, SalarioNumerico {
		empregado = new Empregado(nome, endereco, tipo, salario);
		//if(empregado.getNome() != null) {System.out.print(nome + "1");}
		return empregado.AdicionarEmpregado(nome, endereco, tipo, salario);
	}
	
	
}
