package um.tiendas.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LambdaMultiplica {

	public static void main(String[] args) {

		List<Integer> lista = Arrays.asList(new Integer[]{1,2,3,4,5,6});		
		
		List<Integer> listaMultiplicada = multiplica(lista, a -> {return a * 2;});
		
		for(Integer i: listaMultiplicada) {
			System.out.println(i);
		}
		
	}	
	
	public static List<Integer> multiplica(List<Integer> lista, Multiplica multiplicador){
		ArrayList<Integer> listaMultiplicada= new ArrayList<Integer>();
		for(Integer i: lista) {
			listaMultiplicada.add(multiplicador.multiplica(i));
		}
		return listaMultiplicada;
	}

}
