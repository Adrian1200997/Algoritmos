package floyd;

/**
 *
 * @author Adrian
 */
public class Floyd {

    static int INF = 99999, V = 5;

    public void floydWarshall(int[][] grafo) {
         V= grafo.length;
        int dist[][] = new int[V][V];
        int i, j, k;

        /*Inicialice la matriz de solución igual que la matriz de 
          gráfico de entrada. O podemos decir que los valores iniciales 
          de distancias más cortas se basan en caminos más cortos sin 
          considerar ningún vértice intermedio.*/
        for (i = 0; i < V; i++) {
            for (j = 0; j < V; j++) {
                dist[i][j] = grafo[i][j];
            }
        }

        /*Agregue todos los vértices uno por uno al conjunto de vértices intermedios. 
         ---> Antes de comenzar una iteración, tenemos las distancias más cortas 
              entre todos los pares de vértices, de modo que las distancias más 
              cortas consideren solo los vértices del conjunto {0, 1, 2, .. k-1} 
              como vértices intermedios. 
        
         ----> Después del final de una iteración, el vértice no. k se agrega al 
               conjunto de vértices intermedios y el conjunto se convierte en 
               {0, 1, 2, .. k}*/
        
        for (k = 0; k < V; k++) {
            // Elige todos los vértices como fuente uno por uno
            for (i = 0; i < V; i++) {
                /* Elija todos los vértices como destino 
                   para la fuente elegida anteriormente */
                for (j = 0; j < V; j++) {
                    /* Si el vértice k está en la ruta más corta de
                       i a j, actualice el valor de dist [i] [j]*/ 
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Imprime la matriz de distancia más corta
        imprimirSolucion(dist);
    }

    public void imprimirSolucion(int dist[][]) {
        System.out.println("La siguiente matriz muestra la distancia más "
                + "corta entre cada par de vértices");
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + "   ");
                }
            }
            System.out.println();
        }
    }

    // Método principal
    public static void main(String[] args) {
        /* creamos un grafo
         10 
         (0)------->(3) 
         |         /|\ 
       5 |          | 
         |          | 1 
        \|/         | 
        (1)------->(2) 
         3           */
//        int grafo[][] = { {0,   5,  INF, 10}, 
//                          {INF, 0,   3, INF}, 
//                          {INF, INF, 0,   1}, 
//                          {INF, INF, INF, 0} 
//                        }; 
        int[][] grafo = {{0, 3, 7, INF, INF},
        {3, 0, INF, 5, INF},
        {7, INF, 0, 4, 8},
        {INF, 5, 4, 0, 6},
        {INF, INF, 8, 6, 0}
        };

        Floyd a = new Floyd();        
        a.floydWarshall(grafo);
    }
}
