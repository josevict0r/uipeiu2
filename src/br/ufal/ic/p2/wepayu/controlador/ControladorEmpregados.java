package br.ufal.ic.p2.wepayu.controlador;

import br.ufal.ic.p2.wepayu.Exception.*;
import br.ufal.ic.p2.wepayu.models.Empregado;
import br.ufal.ic.p2.wepayu.models.Comissionado;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.UUID;

import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;

public class ControladorEmpregados {
	static LinkedHashMap<String, Empregado> empregados = new LinkedHashMap<String, Empregado>();
	static ArrayList<Empregado> empregadosPersistencia = new ArrayList<Empregado>();
	
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
		//System.out.println("cuuu" + empregadosPersistencia.toString());
		//empregados = (LinkedHashMap<String, Empregado>) decoder.readObject();
		for(Empregado i : empregadosPersistencia) {
			empregados.put(i.getId(), i);
			//System.out.println("--->" + i.getSalario());
		}
		
		decoder.close();
	}
	
	public static void esqueci() throws FileNotFoundException {
		empregados.clear();
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("empregados.xml")));
		for(Empregado i : empregadosPersistencia) {
			encoder.remove(i);// nao testei
		}
		empregadosPersistencia.clear();
		
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
    	//System.out.println("therezaaa" + id);
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