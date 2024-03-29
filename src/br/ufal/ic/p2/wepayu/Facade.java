package br.ufal.ic.p2.wepayu;

import java.io.FileNotFoundException;

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
	
	public Facade() throws FileNotFoundException {
		//ControladorEmpregados.encerrarSistema();
		ControladorEmpregados.iniciarSistema();
	}
	
	public void zerarSistema() throws FileNotFoundException {
		ControladorEmpregados.esqueci();
	}
	
	public void encerrarSistema() throws FileNotFoundException {
		ControladorEmpregados.encerrarSistema();
	}
	
	public static String getTaxasServico(String emp, String dataInicial, String dataFinal) throws dataInicialInvalida, NaoComissionado, dataFinalInvalida, ViagemNoTempo, NaoSindicalizado {
		return ControladorEmpregados.getTaxasServico(emp, dataInicial, dataFinal);
	}
	
	public void alteraEmpregado(String emp, String atributo, String valor1) throws NaoComissionado, NomeNulo, EnderecoNulo, TipoInvalido, SalarioNulo {
		ControladorEmpregados.alteraAtributoEmpregado(emp, atributo, valor1);
	}
	
	public void alteraEmpregado(String emp, String atributo, boolean valor1) {
		ControladorEmpregados.dessindicaliza(emp, atributo, valor1);
	}
	
	public void alteraEmpregado(String emp, String atributo, boolean valor, String idSindicato, String taxaSindical) throws SindicatoRepetido {
		ControladorEmpregados.sindicaliza(emp, atributo, valor, idSindicato, taxaSindical);
	}
	public void alteraEmpregado(String emp, String atributo, String valor1, String banco, String agencia, String contaCorrente) {
		ControladorEmpregados.adicionaMetodoPagamento(emp, atributo, valor1, banco, agencia, contaCorrente);
	}

	
	public void lancaTaxaServico(String emp, String data, String valor) throws IdNula, EmpregadoNaoExiste, NaoComissionado, DataInvalida, ValorNegativo, MembroNaoExiste, idSindicatoNula {
		ControladorEmpregados.lancaTaxaServico(emp, data, valor);
	}
	
	public String getVendasRealizadas(String emp,String dataInicial, String dataFinal) throws dataInicialInvalida, NaoComissionado, dataFinalInvalida, ViagemNoTempo {
		return ControladorEmpregados.getVendasRealizadas(emp, dataInicial, dataFinal);
	}

	
	public void lancaVenda(String emp, String data, String valor) throws IdNula, EmpregadoNaoExiste, NaoComissionado, ValorNegativo, DataInvalida {
		ControladorEmpregados.lancaVenda(emp, data, valor);
	}
	
	public String getHorasExtrasTrabalhadas(String emp, String dataInicial, String dataFinal) throws NaoHorista, dataInicialInvalida, dataFinalInvalida, ViagemNoTempo {
		return ControladorEmpregados.getHorasTrabalhadas(emp, dataInicial, dataFinal, 0);
	}
	
	public String getHorasNormaisTrabalhadas(String emp, String dataInicial, String dataFinal) throws NaoHorista, dataInicialInvalida, dataFinalInvalida, ViagemNoTempo {
		return ControladorEmpregados.getHorasTrabalhadas(emp, dataInicial, dataFinal, 1);
	}
	
	public void lancaCartao(String emp, String data, String horas) throws IdNula, EmpregadoNaoExiste, NaoHorista, DataInvalida, HoraNegativa {
		ControladorEmpregados.lancaCartao(emp, data, horas);
	}
	
	public void removerEmpregado(String emp) throws EmpregadoNaoExiste, IdNula {
		ControladorEmpregados.removerEmpregado(emp);
	}
	
	public String getEmpregadoPorNome(String nome, int indice) throws NaoTrabalhaAqui {
		return ControladorEmpregados.getEmpregadoPorNome(nome, indice);
	}
	
	public String getAtributoEmpregado(String emp, String atributo) throws EmpregadoNaoExiste, IdNula, AtributoNaoExiste, NaoComissionado, NaoSindicalizado, NaoBanco {
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
