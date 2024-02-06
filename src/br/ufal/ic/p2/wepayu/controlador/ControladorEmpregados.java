package br.ufal.ic.p2.wepayu.controlador;

import br.ufal.ic.p2.wepayu.Exception.*;
import br.ufal.ic.p2.wepayu.models.Empregado;
import br.ufal.ic.p2.wepayu.models.Comissionado;

import java.util.LinkedHashMap;
import java.util.UUID;

//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;

public class ControladorEmpregados {
	static LinkedHashMap<String, Empregado> empregados = new LinkedHashMap<String, Empregado>();
	
	public static void esqueci() {
		empregados.clear();
	}
	
	public static String getAtributoEmpregado(String id, String atributo) throws EmpregadoNaoExiste, IdNula, AtributoNaoExiste {
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
			//System.out.println(atributo + "CU");
			//System.out.println(resultado + "cuzinho");
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
		else if (atributo.equals("comissao")) {
			Comissionado casted = (Comissionado) buscado;
			resultado = casted.getComissao();
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
    		//System.out.print("CUUUUU");
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
    		//System.out.print("CUUUUU");
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

	public static void encerrarSistema() {
				
	}

	
	
	
}