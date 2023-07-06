package trabajopractico01;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class OrdenamientoPorCasilleros {
	public static void main(String[] args) {
        // El rango maximo permitido es 525.074.427
		int[] vector1 = {500_000_000, 4, 3, 2, 1, 1};
		int[] vector2 = new int[1_000_000];
		for (int i = 0; i < vector2.length; i++) {
			int numeroAleatorio = (new Random().nextInt(100_000_000) + 1);
			vector2[i] = numeroAleatorio;
		}

		System.out.println("EJEMPLO 1");
		bucketSort(vector1);
//		System.out.println("EJEMPLO 2");
//		bucketSort(vector2);
	}
	public static void bucketSort(int[] vector) {
		Instant inicio;
		Instant fin;
		inicio = Instant.now();

		// Encontrar el rango
		int rango = 0;
		for (int valor : vector) {
			if (valor > rango) {
				rango = valor;
			}
		}
		int[] casilleros = new int[rango + 1]; // (rango + 1) es para que exista la posición casilleros[rango]

		System.out.printf("El tamaño del vector a ser ordenado es de %,d%n", vector.length);
		System.out.printf("El rango para esta ordenamiento es de: 0 a %,d%n", rango);

		// Colocar elementos en los casilleros
		for (int valor : vector) {
			casilleros[valor]++;
		}

		int memoriaSinUso = 0;
		int memoriaUsada = 0;

		// Ordenar el array en función de los casilleros
		int indice = 0;
		for (int i = 0; i <= rango; i++) {
			memoriaUsada += 4;
			if (casilleros[i] == 0) {
				memoriaSinUso += 4;
			} else {
				while (casilleros[i] > 0) {
					vector[indice] = i;
					casilleros[i]--;
					indice++;
				}
			}
		}
		fin = Instant.now();
		// Calculo del tiempo en nanosegundos de lo que demora el algoritmo de ordenamiento
		long duracion = Duration.between(inicio, fin).toNanos();

		double mb = (double) memoriaUsada / 1_000_000;
		System.out.println("Memoria reservada para los casilleros en el Heap " + mb + " mb");
		System.out.println("Memoria sin uso que fue reservada en Heap: " + memoriaSinUso + " bytes");
		System.out.println("EL ordenamiento por Bucket Sort demoro: " + duracion / 1_000_000 + "ms" );

		// Imprimir el vector ordenado
		if (vector.length <= 10) {
			for (int numero : vector) {
				System.out.print(numero + " ");
			}
			System.out.println();
		}
	}
}
