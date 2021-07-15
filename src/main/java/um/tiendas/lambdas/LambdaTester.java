package um.tiendas.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaTester {

	public static void main(String[] args) {

		List<String> lista = Arrays.asList(new String[]{"Casa","Mesa","Coche","Barco"});		
		
		List<String> listaFiltrada = cumpleCondicion(lista, a -> a.toLowerCase().startsWith("c"));
		System.out.println("Mi propia Interfaz funcional");
		
		for(String s: listaFiltrada) {
			System.out.print(s+" ");
		}
		System.out.println("\n");
		
		
		System.out.println("Con predicate");
		List<String> listaFiltradaPredicate = cumpleCondicionPredicate(lista, a -> a.toLowerCase().startsWith("c"));
		
		for(String s: listaFiltradaPredicate) {
			System.out.print(s+" ");
		}
		System.out.println("\n");
		
		System.out.println("Con predicate + Streams");
		List<String> listaFiltradaPredicateStream = cumpleCondicionPredicateStream(lista, a -> a.toLowerCase().startsWith("c"));
		
		for(String s: listaFiltradaPredicateStream) {
			System.out.print(s+" ");
		}
		
		
	}	
	
	/**
	 * Mi propia Interfaz Funcional
	 * @param lista
	 * @param testeador
	 * @return
	 */
	public static List<String> cumpleCondicion(List<String> lista, Testea testeador){
		ArrayList<String> listaFiltrada= new ArrayList<String>();
		for(String s: lista) {
			if(testeador.testea(s)) {
				listaFiltrada.add(s);
			}
		}
		return listaFiltrada;
	}
	
	/**
	 * Predicate
	 * @param lista
	 * @param testeador
	 * @return
	 */
	public static List<String> cumpleCondicionPredicate(List<String> lista, Predicate<String> testeador){
		ArrayList<String> listaFiltrada= new ArrayList<String>();
		for(String s: lista) {
			if(testeador.test(s)) {
				listaFiltrada.add(s);
			}
		}
		return listaFiltrada;
	}
	
	/**
	 * Predicate + stream
	 * @param lista
	 * @param testeador
	 * @return
	 */
	public static List<String> cumpleCondicionPredicateStream(List<String> lista, Predicate<String> testeador){
//		return lista.stream().filter(testeador).peek(System.out::println).collect(Collectors.toList());		
		return lista.stream().filter(testeador).collect(Collectors.toList());	
	}

}
