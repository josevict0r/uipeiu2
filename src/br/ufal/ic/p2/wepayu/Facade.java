package br.ufal.ic.p2.wepayu;

import br.ufal.ic.p2.wepayu.Exception.*;
//import br.ufal.ic.p2.wepayu.Exception.EmpregadoNaoExisteException;
//import br.ufal.ic.p2.wepayu.models.Empregado;
import br.ufal.ic.p2.wepayu.controlador.ControladorEmpregados;

//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;

public class Facade {

	//private ControladorEmpregados controlador;
	
	public void zerarSistema() {
		ControladorEmpregados.esqueci();
	}
	
	public void encerrarSistema() {
		ControladorEmpregados.encerrarSistema();
	}
	
	public String getAtributoEmpregado(String emp, String atributo) throws EmpregadoNaoExiste, IdNula, AtributoNaoExiste {
		return ControladorEmpregados.getAtributoEmpregado(emp, atributo);
	}	
	public String criarEmpregado(String nome, String endereco, String tipo, String salario) throws NomeNulo, EnderecoNulo, TipoInvalido, SalarioNulo, SalarioNegativo, SalarioNumerico, TipoNaoAplicavel {
		//controller = new Empregado(nome, endereco, tipo, salario);
		//if(empregado.getNome() != null) {System.out.print(nome + "1");}
		//System.out.print(nome + "    1");
		return ControladorEmpregados.AdicionarEmpregado(nome, endereco, tipo, salario);
	}
	
	public String criarEmpregado(String nome, String endereco, String tipo, String salario, String comissao) throws NomeNulo, EnderecoNulo, TipoInvalido, SalarioNulo, SalarioNegativo, SalarioNumerico, ComissaoNula, ComissaoNumerica, ComissaoNegativa, TipoNaoAplicavel {
		return ControladorEmpregados.AdicionarComissionado(nome, endereco, tipo, salario, comissao);
	}
	
	
}
