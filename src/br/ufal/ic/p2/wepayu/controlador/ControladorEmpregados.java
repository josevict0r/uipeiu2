package br.ufal.ic.p2.wepayu.controlador;

import br.ufal.ic.p2.wepayu.Exception.*;
import br.ufal.ic.p2.wepayu.models.*;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.FileNotFoundException;
import java.util.UUID;

import java.util.LinkedHashMap;
import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;

public class ControladorEmpregados {
	static LinkedHashMap<String, Empregado> empregados = new LinkedHashMap<String, Empregado>();
	static ArrayList<Empregado> empregadosPersistencia = new ArrayList<Empregado>();
	static ArrayList<Cartao> cartoes = new ArrayList<Cartao>();
	static ArrayList<Venda> vendas = new ArrayList<Venda>();
	static ArrayList<TaxaServico> taxas = new ArrayList<TaxaServico>();
	static LinkedHashMap<String, Sindicato> sindicatos = new LinkedHashMap<String, Sindicato>();
	
	
	public static void encerrarSistema() throws FileNotFoundException {
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("empregados.xml")));
		
		encoder.writeObject(empregadosPersistencia);
		//encoder.writeObject(empregados);
		encoder.close();
	}
	
	@SuppressWarnings("unchecked")
	public static void iniciarSistema() throws FileNotFoundException {
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("empregados.xml")));
		empregadosPersistencia = (ArrayList<Empregado>) decoder.readObject();
		//empregados = (LinkedHashMap<String, Empregado>) decoder.readObject();
		for(Empregado i : empregadosPersistencia) {
			empregados.put(i.getId(), i);
			//System.out.println("--->" + i.getSalario());
		}
		
		decoder.close();
	}
	
	public static void esqueci() throws FileNotFoundException {
		empregados.clear();
		//XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("empregados.xml")));
		//for(Empregado i : empregadosPersistencia) {
		//	encoder.remove(i);// nao testei
		//}
		empregadosPersistencia.clear();
		
	}
	
	public static void alteraAtributoEmpregado(String emp, String atributo, String valor1) throws NaoComissionado, NomeNulo, EnderecoNulo, TipoInvalido, SalarioNulo {
		
		/*
		if(atributo.equals("nome") && valor1.equals("false")) {
    		throw new NomeNulo();
    	}
    	if(atributo.equals("endereco") && valor1.equals("false")) {
    		throw new EnderecoNulo();
    	}
    	//System.out.print(novo.tipo);
    	if(atributo.equals("tipo") && valor1.equals("false")) {
    		throw new TipoInvalido();
    	}
    	if(atributo.equals("salario") && valor1.equals("false")) {
    		throw new SalarioNulo();
    	}
    	if(novo.getSalario().contains("-")) {
    		throw new SalarioNegativo();
    	}
    	if(!isNumeric(novo.getSalario().replace(",", "."))) {
    		throw new SalarioNumerico();
    	}*/
		
		
		if(atributo.equals("nome")) {
			empregados.get(emp).setNome(valor1);
			//System.out.println(empregados.get(emp));
		}
		if(atributo.equals("endereco")) {
			empregados.get(emp).setEndereco(valor1);
		}
		if(atributo.equals("tipo")) {
			empregados.get(emp).setTipo(valor1);//criar outro com a classe do tipo novo e excluir o que ja tem
		}
		if(atributo.equals("salario")) {
			//String salarioPonto = valor1.replace(",", ".");
	        //double salarioD = Double.parseDouble(salarioPonto);
			empregados.get(emp).setSalario(valor1);
		}
		if(atributo.equals("comissao")) {
			if(!empregados.get(emp).getTipo().equals("comissionado")) throw new NaoComissionado();
			empregados.get(emp).setTipo(valor1);
			//empregados.get(emp)
		}
		/*if(atributo.equals("metodoPagamento")) {
			empregados.get(emp).setMetodoPagamento(valor1);
		}
		if(atributo.equals("banco")) {
			empregados.get(emp).setEndereco(valor1);
		}
		if(atributo.equals("agencia")) {
			empregados.get(emp).setEndereco(valor1);
		}
		if(atributo.equals("contaCorrente")) {
			empregados.get(emp).setEndereco(valor1);
		}*/
	}
	
	public static void adicionaMetodoPagamento(String emp, String atributo, String valor1, String banco, String agencia,
			String contaCorrente) {
		Banco bancoobj = new Banco(banco, agencia, contaCorrente);
		empregados.get(emp).setMetodoPagamento(valor1);;
		
		if(valor1.equals("banco")) {
			empregados.get(emp).setBanco(bancoobj);
		}
	}
	
	public static String getTaxasServico(String emp, String dataInicial, String dataFinal) throws dataInicialInvalida, NaoComissionado, dataFinalInvalida, ViagemNoTempo, NaoSindicalizado {
		double taxasServico = 0;
		if(!empregados.get(emp).isSindicalizado()) throw new NaoSindicalizado();
		
        String[] diaMesAnoStrI = dataInicial.split("/");
        ArrayList<Integer> diaMesAnoI = new ArrayList<Integer>();
		diaMesAnoI.add(Integer.parseInt(diaMesAnoStrI[0]));
		diaMesAnoI.add(Integer.parseInt(diaMesAnoStrI[1]));
		diaMesAnoI.add(Integer.parseInt(diaMesAnoStrI[2]));
        
		String[] diaMesAnoStrF = dataFinal.split("/");
		ArrayList<Integer> diaMesAnoF = new ArrayList<Integer>();
		diaMesAnoF.add(Integer.parseInt(diaMesAnoStrF[0]));
		diaMesAnoF.add(Integer.parseInt(diaMesAnoStrF[1]));
		diaMesAnoF.add(Integer.parseInt(diaMesAnoStrF[2]));
        
        
        if(diaMesAnoI.get(0) > 31) throw new dataInicialInvalida();
		if(diaMesAnoF.get(1) == 2) {
			if(diaMesAnoF.get(0) > 29) throw new dataFinalInvalida();
		}
		if(diaMesAnoI.get(1) >= diaMesAnoF.get(1)){
			if(diaMesAnoI.get(0) > diaMesAnoF.get(0)) throw new ViagemNoTempo();
			}
		
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate Inicial, Final;
        
        Inicial = LocalDate.parse(dataInicial, formatoData);
        Final = LocalDate.parse(dataFinal, formatoData);
        
		for(TaxaServico taxa : taxas) {
			
				//if(venda.getId().equals(emp))
				if(taxa.getIdSindicato().equals(empregados.get(emp).getSindicato().getIdSindicato())) {
					//System.out.println("-----------------------------------");
					if(empregados.get(emp).getSindicato().getIdSindicato().equals(taxa.getIdSindicato()) && taxa.getData().equals(Final)) break;
					if (taxa.getData().isEqual(Inicial) || (taxa.getData().isAfter(Inicial) && taxa.getData().isBefore(Final))) {//DA ERRADO DE UM DIA PRO OUTRO
						taxasServico += taxa.getValor();
					}
				}
			

			//System.out.println("        valores =" + venda.getValor());
		}
		String taxasServicoStr = (Double.toString(taxasServico)).replace(".", ",");//0,00
		String[] taxasServicoStrsplit = taxasServicoStr.split(",");
		if(taxasServico - (int) taxasServico == 0) taxasServicoStr = taxasServicoStrsplit[0].concat(",00");
		if(taxasServicoStrsplit[1].length() == 2) taxasServicoStr = taxasServicoStrsplit[0].concat("," + taxasServicoStrsplit[1]);
		else taxasServicoStr = taxasServicoStrsplit[0].concat("," + taxasServicoStrsplit[1] + "0");
		
		
		//System.out.println("----->" + taxasServicoStr);
		return taxasServicoStr;
	}
	
	public static void dessindicaliza(String emp, String atributo, boolean valor1) {
		empregados.get(emp).setSindicalizado(valor1);
		empregados.get(emp).setSindicato(null);
	}
	
	public static void sindicaliza(String emp, String atributo, boolean valor, String idSindicato,
			String taxaSindical) throws SindicatoRepetido {
		if(sindicatos.containsKey(idSindicato)) throw new SindicatoRepetido();
		
		String taxaSindicalPonto = taxaSindical.replace(",", ".");
        double taxaSindicalD = Double.parseDouble(taxaSindicalPonto);
        
		Sindicato sindicato = new Sindicato(idSindicato, taxaSindicalD);
		sindicatos.put(sindicato.getIdSindicato(), sindicato);
		
		empregados.get(emp).setSindicalizado(valor);
		empregados.get(emp).setSindicato(sindicato);
	}
	
	public static void lancaTaxaServico(String idSindicato, String data, String valor) throws EmpregadoNaoExiste, NaoComissionado, DataInvalida, ValorNegativo, MembroNaoExiste, idSindicatoNula {
		if(idSindicato.isEmpty()) throw new idSindicatoNula();
		if(sindicatos.get(idSindicato) == null) throw new MembroNaoExiste();
		
		
		String[] diaMesAnoStr = data.split("/"); 
		ArrayList<Integer> diaMesAno = new ArrayList<Integer>();
		diaMesAno.add(Integer.parseInt(diaMesAnoStr[0]));
		diaMesAno.add(Integer.parseInt(diaMesAnoStr[1]));
		diaMesAno.add(Integer.parseInt(diaMesAnoStr[2]));             //Convertendo a data para int pra poder fazer os testes de erro
		data = (Integer.toString((int) diaMesAno.get(0)).concat("/" + Integer.toString((int)diaMesAno.get(1)))).concat("/" + Integer.toString((int)diaMesAno.get(2)));
			
		if(diaMesAno.get(1) >= 13) throw new DataInvalida();
		
		
		
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate dataobj;
        dataobj = LocalDate.parse(data, formatoData);
        
        String valorPonto = valor.replace(",", ".");
        double valorD = Double.parseDouble(valorPonto);
        if(valorD<= 0) throw new ValorNegativo();
        //System.out.println("        idSindicato ==" + idSindicato);
        TaxaServico taxaServico = new TaxaServico(idSindicato, dataobj, valorD);
        
        taxas.add(taxaServico);
	}
	
	public static String getVendasRealizadas(String emp, String dataInicial, String dataFinal) throws dataInicialInvalida, NaoComissionado, dataFinalInvalida, ViagemNoTempo {
		double vendasRequisitadas = 0;
		if(!empregados.get(emp).getTipo().equals("comissionado")) throw new NaoComissionado();
		
		
        String[] diaMesAnoStrI = dataInicial.split("/");
        ArrayList<Integer> diaMesAnoI = new ArrayList<Integer>();
		diaMesAnoI.add(Integer.parseInt(diaMesAnoStrI[0]));
		diaMesAnoI.add(Integer.parseInt(diaMesAnoStrI[1]));
		diaMesAnoI.add(Integer.parseInt(diaMesAnoStrI[2]));
        
		String[] diaMesAnoStrF = dataFinal.split("/");
		ArrayList<Integer> diaMesAnoF = new ArrayList<Integer>();
		diaMesAnoF.add(Integer.parseInt(diaMesAnoStrF[0]));
		diaMesAnoF.add(Integer.parseInt(diaMesAnoStrF[1]));
		diaMesAnoF.add(Integer.parseInt(diaMesAnoStrF[2]));
        
        
        if(diaMesAnoI.get(0) > 31) throw new dataInicialInvalida();
		if(diaMesAnoF.get(1) == 2) {
			if(diaMesAnoF.get(0) > 29) throw new dataFinalInvalida();
		}
		if(diaMesAnoI.get(1) >= diaMesAnoF.get(1)){
			if(diaMesAnoI.get(0) > diaMesAnoF.get(0)) throw new ViagemNoTempo();
			}
		
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate Inicial, Final;
        
        Inicial = LocalDate.parse(dataInicial, formatoData);
        Final = LocalDate.parse(dataFinal, formatoData);
        
		for(Venda venda : vendas) {
			if(venda.getId().equals(emp)) {
				if(venda.getId().equals(emp) && venda.getData().equals(Final)) break;
				if (venda.getData().isEqual(Inicial) || (venda.getData().isAfter(Inicial) && venda.getData().isBefore(Final))) {//DA ERRADO DE UM DIA PRO OUTRO
					vendasRequisitadas += venda.getValor();
					//System.out.println(venda.getData().toString());
					//System.out.println("---->" + vendasRequisitadas);
				}
			}

			//System.out.println("        valores =" + venda.getValor());
		}
		String vendasRequisitadasStr = (Double.toString(vendasRequisitadas)).replace(".", ",");//0,00
		String[] vendasRequisitadasStrsplit = vendasRequisitadasStr.split(",");
		if(vendasRequisitadas - (int) vendasRequisitadas == 0) vendasRequisitadasStr = vendasRequisitadasStrsplit[0].concat(",00");
		if(vendasRequisitadasStrsplit[1].length() == 2) vendasRequisitadasStr = vendasRequisitadasStrsplit[0].concat("," + vendasRequisitadasStrsplit[1]);
		else vendasRequisitadasStr = vendasRequisitadasStrsplit[0].concat("," + vendasRequisitadasStrsplit[1] + "0");
		
		return vendasRequisitadasStr;
		
	}
	
	public static void lancaVenda(String emp, String data, String valor) throws IdNula, EmpregadoNaoExiste, NaoComissionado, ValorNegativo, DataInvalida {
		
		if(emp.isEmpty()) {
			throw new IdNula();
		}
		if(empregados.get(emp) == null) {
			throw new EmpregadoNaoExiste();
		}
		if(!empregados.get(emp).getTipo().equals("comissionado")) {
			throw new NaoComissionado();
		}		
		String[] diaMesAnoStr = data.split("/"); 
		ArrayList<Integer> diaMesAno = new ArrayList<Integer>();
		diaMesAno.add(Integer.parseInt(diaMesAnoStr[0]));
		diaMesAno.add(Integer.parseInt(diaMesAnoStr[1]));
		diaMesAno.add(Integer.parseInt(diaMesAnoStr[2]));             //Convertendo a data para int pra poder fazer os testes de erro
		data = (Integer.toString((int) diaMesAno.get(0)).concat("/" + Integer.toString((int)diaMesAno.get(1)))).concat("/" + Integer.toString((int)diaMesAno.get(2)));
			
		if(diaMesAno.get(1) >= 13) throw new DataInvalida();
		
		
		
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate dataobj;
        dataobj = LocalDate.parse(data, formatoData);
        String valorPonto = valor.replace(",", ".");
        //System.out.println("valor q deveria colocar na venda -->" + valor);
        double valorD = Double.parseDouble(valorPonto);
        if(valorD<= 0) throw new ValorNegativo();
        //System.out.println("valor q coloca na venda -->" + valorD);
        Venda venda = new Venda(emp, dataobj, valorD);
        vendas.add(venda);
	}
	
	public static String getHorasTrabalhadas(String emp, String dataInicial, String dataFinal, int NormalOuExtra) throws NaoHorista, dataInicialInvalida, dataFinalInvalida, ViagemNoTempo {
		double horasNormais = 0;
		double horasExtras = 0;
		if(!empregados.get(emp).getTipo().equals("horista")) throw new NaoHorista();
		
		
        String[] diaMesAnoStrI = dataInicial.split("/");
        ArrayList<Integer> diaMesAnoI = new ArrayList<Integer>();
		diaMesAnoI.add(Integer.parseInt(diaMesAnoStrI[0]));
		diaMesAnoI.add(Integer.parseInt(diaMesAnoStrI[1]));
		diaMesAnoI.add(Integer.parseInt(diaMesAnoStrI[2]));
        
		String[] diaMesAnoStrF = dataFinal.split("/");
		ArrayList<Integer> diaMesAnoF = new ArrayList<Integer>();
		diaMesAnoF.add(Integer.parseInt(diaMesAnoStrF[0]));
		diaMesAnoF.add(Integer.parseInt(diaMesAnoStrF[1]));
		diaMesAnoF.add(Integer.parseInt(diaMesAnoStrF[2]));
        
        
        if(diaMesAnoI.get(0) > 31) throw new dataInicialInvalida();
		if(diaMesAnoF.get(1) == 2) {
			if(diaMesAnoF.get(0) > 29) throw new dataFinalInvalida();
		}
		if(diaMesAnoI.get(1) >= diaMesAnoF.get(1)){
			if(diaMesAnoI.get(0) > diaMesAnoF.get(0)) throw new ViagemNoTempo();
			}
		
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate Inicial, Final;
        
        Inicial = LocalDate.parse(dataInicial, formatoData);
        Final = LocalDate.parse(dataFinal, formatoData);
        
		for(Cartao cartao : cartoes) {
			//if(cartao.getId().equals(emp) && cartao.getData().equals(Final)) {// CHEGOU NA DATA FINAL ENTAO BREAK
			//	System.out.println(horasNormais);
			//	break;
			//}
			if(cartao.getId().equals(emp)) {
				//System.out.println("data = " + cartao.getData());
				//System.out.println("inicial = " + Inicial.toString());
				//System.out.println("final = " + Final.toString());
				if(cartao.getId().equals(emp) && cartao.getData().equals(Final)) break;
				if (cartao.getData().isEqual(Inicial) || (cartao.getData().isAfter(Inicial) && cartao.getData().isBefore(Final))) {//DA ERRADO DE UM DIA PRO OUTRO
					if(cartao.getHoras() > 8) {
						horasNormais += 8;
						
						horasExtras += cartao.getHoras()-8;
						//((cartoes.get(i).getData().isAfter(Inicial) && cartoes.get(i).getData().isBefore(Final))
					}
					else horasNormais += cartao.getHoras();
				}
				//System.out.println(horasNormais);
			}
		}
		String horasNormaisStr = (Double.toString(horasNormais)).replace(".", ",");
		if(horasNormais - (int) horasNormais == 0) {
			String[] horasNormaisStrsplit = horasNormaisStr.split(",");
			horasNormaisStr = horasNormaisStrsplit[0];
		}
		
		String horasExtrasStr = (Double.toString(horasExtras)).replace(".", ",");
		if(horasExtras - (int) horasExtras == 0) {
			String[] horasExtrasStrsplit = horasExtrasStr.split(",");
			horasExtrasStr = horasExtrasStrsplit[0];
		}
		
		//System.out.println("---->normais = " + horasNormais);
        //System.out.println("---->extras = " + horasExtras);
		if(NormalOuExtra == 1) return horasNormaisStr;
		else return horasExtrasStr;	
	}
	
	public static void lancaCartao(String emp, String data, String horas) throws IdNula, EmpregadoNaoExiste, NaoHorista, DataInvalida, HoraNegativa {
		double horasNum = Double.parseDouble(horas.replace(",", "."));
		
		if(emp.isEmpty()) {
			throw new IdNula();
		}
		if(empregados.get(emp) == null) {
			throw new EmpregadoNaoExiste();
		}
		if(!empregados.get(emp).getTipo().equals("horista")) {
			throw new NaoHorista();
		}
		String[] diaMesAnoStr = data.split("/"); 
		ArrayList<Integer> diaMesAno = new ArrayList<Integer>();
		diaMesAno.add(Integer.parseInt(diaMesAnoStr[0]));
		diaMesAno.add(Integer.parseInt(diaMesAnoStr[1]));
		diaMesAno.add(Integer.parseInt(diaMesAnoStr[2]));             //Convertendo a data para int pra poder fazer os testes de erro
		data = (Integer.toString((int) diaMesAno.get(0)).concat("/" + Integer.toString((int)diaMesAno.get(1)))).concat("/" + Integer.toString((int)diaMesAno.get(2)));
		//System.out.println("---->" + diaMesAno.get(1));
		//System.out.println(data);		
		if(diaMesAno.get(1) >= 13) throw new DataInvalida();
		if(horasNum <= 0) throw new HoraNegativa();
		
		
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate dataobj;
        dataobj = LocalDate.parse(data, formatoData);
        
        
		Cartao cartao = new Cartao(emp, dataobj, horasNum);
		cartoes.add(cartao);
	}
	
	public static void removerEmpregado(String id) throws EmpregadoNaoExiste, IdNula {
		if(id.isEmpty()) throw new IdNula();
		if(!(empregados.containsKey(id))) throw new EmpregadoNaoExiste();
		empregadosPersistencia.remove(empregados.get(id));
		empregados.remove(id);
	}
	
	public static String getEmpregadoPorNome(String nome, int indice) throws NaoTrabalhaAqui {
		String idAchado = null;
		
		//System.out.println("assôoooooooo");
		for (String key: empregados.keySet()) {
			
			if(empregados.get(key).getNome().equals(nome)) {
				indice--;
				if(indice == 0) {
					idAchado = empregados.get(key).getId();
					return idAchado;
				}
				//System.out.println("assôoooooooo");	
			}
		}
		throw new NaoTrabalhaAqui();
		//System.out.println(idAchado);
		
	}
	
	public static String getAtributoEmpregado(String id, String atributo) throws EmpregadoNaoExiste, IdNula, AtributoNaoExiste, NaoComissionado, NaoSindicalizado, NaoBanco {
		String resultado = null;
		Empregado buscado = empregados.get(id);
		
		
		
		if(id.isEmpty()) {
			throw new IdNula();
		}
		if(empregados.get(id) == null) {
			throw new EmpregadoNaoExiste();
		}
		
		if(atributo.equals("nome")) {
			resultado = buscado.getNome();
		}
		else if(atributo.equals("endereco")) {
			resultado = buscado.getEndereco();
		}
		else if(atributo.equals("tipo")) {
			resultado = buscado.getTipo();
		}
		else if(atributo.equals("salario")) {
			if(!(buscado.getSalario().contains(",")) && !(buscado.getSalario().contains("."))) {
				resultado = buscado.getSalario().concat(",00");
			}
			else resultado = buscado.getSalario();
		}
		else if(atributo.equals("sindicalizado")) {
			boolean cut = buscado.isSindicalizado();// CONVERTER DE BOOL PRA STRING
			resultado = Boolean.toString(cut);
		}
		else if(atributo.equals("idSindicato")) {
			if(empregados.get(id).isSindicalizado()) resultado = empregados.get(id).getSindicato().getIdSindicato();
			else throw new NaoSindicalizado();
		}
		else if(atributo.equals("taxaSindical")) {
			if(empregados.get(id).isSindicalizado()) 
			{
				resultado = String.valueOf(empregados.get(id).getSindicato().getTaxaSindical()).replace(".", ",").concat("0");
			}
			else throw new NaoSindicalizado();
		}
		else if (atributo.equals("comissao") && buscado.getTipo().equals("comissionado")) {
			Comissionado casted = (Comissionado) buscado;
			resultado = casted.getComissao();
		}
		
		else if (atributo.equals("comissao") && !buscado.getTipo().equals("comissionado")) {
			throw new NaoComissionado();
		}
		else if(atributo.equals("metodoPagamento")) {
			if (empregados.get(id).getMetodoPagamento() == null) {
				resultado = "emMaos";
				}
		}
		else if(atributo.equals("agencia")) {
			if(empregados.get(id).getBanco() == null) throw new NaoBanco();
			else resultado = empregados.get(id).getBanco().getAgencia();
		}
		else if(atributo.equals("contaCorrente")) {
			if(empregados.get(id).getBanco() == null) throw new NaoBanco();
			else resultado = empregados.get(id).getBanco().getContaCorrente();
		}
		else if(atributo.equals("banco")) {
			if(empregados.get(id).getBanco() == null) throw new NaoBanco();
			else resultado = empregados.get(id).getBanco().getNome();
		}
		else {
			throw new AtributoNaoExiste();
		}
		return resultado;
	}
	
	public static String AdicionarEmpregado(String nome, String endereco, String tipo, String salario) throws NomeNulo, EnderecoNulo, TipoInvalido, SalarioNulo, SalarioNegativo, SalarioNumerico, TipoNaoAplicavel {
    	Empregado novo = new Empregado(nome, endereco, tipo, salario);
    	String id = UUID.randomUUID().toString();
    	//char menos = novo.salario.charAt(0);
    	
    	if(nome.isEmpty()) {
    		throw new NomeNulo();
    	}
    	if(novo.getEndereco().isBlank()) {
    		throw new EnderecoNulo();
    	}
    	//System.out.print(novo.tipo);
    	if(novo.getTipo().equals("comissionado")) {
    		throw new TipoNaoAplicavel();
    	}
    	if(!(novo.getTipo().equals("horista")) && !(novo.getTipo().equals("assalariado"))) {
    
    		throw new TipoInvalido();
    	}
    	if(novo.getSalario().isBlank()) {
    		throw new SalarioNulo();
    	}
    	if(novo.getSalario().contains("-")) {
    		throw new SalarioNegativo();
    	}
    	if(!isNumeric(novo.getSalario().replace(",", "."))) {
    		throw new SalarioNumerico();
    	}
    	
    	empregados.put(id, novo);
    	novo.setId(id);
    	empregadosPersistencia.add(novo);
    	return id;
    }
	
    public static String AdicionarComissionado(String nome, String endereco, String tipo, String salario, String comissao) throws NomeNulo, EnderecoNulo, TipoInvalido, SalarioNulo, SalarioNegativo, SalarioNumerico, ComissaoNula, ComissaoNumerica, ComissaoNegativa, TipoNaoAplicavel {
    	Empregado novo = new Comissionado(nome, endereco, tipo, salario, comissao);
    	String id = UUID.randomUUID().toString();
    	//char menos = novo.salario.charAt(0);
    	
    	if(nome.isEmpty()) {
    		throw new NomeNulo();
    	}
    	if(novo.getEndereco().isBlank()) {
    		throw new EnderecoNulo();
    	}
    	//System.out.print(novo.tipo);
    	Comissionado aux = (Comissionado)novo;
    	if(!(novo.getTipo().equals("comissionado"))) {
    		if(!(aux.getComissao().isBlank())) {
    			throw new TipoNaoAplicavel();
    		}
    		else {
    			throw new TipoInvalido();
    		}
    	}
    	if(novo.getSalario().isBlank()) {
    		throw new SalarioNulo();
    	}
    	if(novo.getSalario().contains("-")) {
    		throw new SalarioNegativo();
    	}
    	if(!isNumeric(novo.getSalario().replace(",", "."))) {
    		throw new SalarioNumerico();
    	}
    	if(novo.getTipo().equals("comissionado")) {
    		if(comissao.isBlank()) {
    			throw new ComissaoNula();
    		}
    		if(!isNumeric(comissao.replace(",", "."))) {
    			throw new ComissaoNumerica();
    		}
    		if(comissao.contains("-")) {
    			throw new ComissaoNegativa();
    		}
    	}
    	
    	empregados.put(id, novo);
    	novo.setId(id);
    	empregadosPersistencia.add(novo);
    	return id;
	}
    
    public static boolean isNumeric(String str) { 
    	  try {  
    	    Double.parseDouble(str);  
    	    return true;
    	  } catch(NumberFormatException e){  
    	    return false;  
    	  }  
    }			
}