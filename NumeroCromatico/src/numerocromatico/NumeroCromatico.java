package numerocromatico;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Un programa Java para implementar un algoritmo codicioso para colorear gráficos
 * @author Adrian
 */

import java.util.LinkedList;



// Esta clase representa un gráfico no dirigido usando la lista de adyacencia
public class NumeroCromatico {
    String[] color = {"Rojo", "Azul", "Verde", "Blanco", "Amarillo", "Rosado"};
    private int V;   // Número de vertices 
    private LinkedList<Integer> adj[]; //Lista de adyacencia

    //Costructor 
    NumeroCromatico(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList();
        }
    }

    //Función para agregar una arista al gráfico
    void agregarArista(int v, int w) {
        adj[v].add(w);
        adj[w].add(v); //El gráfico no está dirigido
    }

    /* Asigna colores (a partir de 0) a todos los vértices y
       imprime la asignación de colores */
    void asignarColores() {
        int resultado[] = new int[V];

        // Inicialice todos los vértices como no asignados
        Arrays.fill(resultado, -1);

        // Asigna el primer color al primer vértice 
        resultado[0] = 0;

        /*Una matriz temporal para almacenar los colores disponibles. Falso
           el valor de [cr] disponible significaría que el color cr es
           asignado a uno de sus vértices adyacentes*/
        boolean disponible[] = new boolean[V];

        // Inicialmente, todos los colores están disponibles.
        Arrays.fill(disponible, true);

        // Asigna colores a los vértices V-1 restantes
        for (int u = 1; u < V; u++) {
            /* Procese todos los vértices adyacentes y marque 
               sus colores como no disponibles.*/ 
            Iterator<Integer> it = adj[u].iterator();
            while (it.hasNext()) {
                int i = it.next();
                if (resultado[i] != -1) {
                    disponible[resultado[i]] = false;
                }
            }

            // Encuentra el primer color disponible
            int cr;
            for (cr = 0; cr < V; cr++) {
                if (disponible[cr]) {
                    break;
                }
            }
            color[u]= color[cr];
            resultado[u] = cr; // Asignar el color encontrado

            // Restablezca los valores a verdadero para la próxima iteración
            Arrays.fill(disponible, true);
        }

        // imprimir el resultado
        for (int u = 0; u < V; u++) {
            System.out.println("Vertice " + u + " --->  Color " + color[u]);
        }
    }

    // Metodo principal
    public static void main(String args[]) {
        NumeroCromatico g1 = new NumeroCromatico(5);
        g1.agregarArista(0, 1);
        g1.agregarArista(0, 2);
        g1.agregarArista(1, 2);
        g1.agregarArista(1, 3);
        g1.agregarArista(2, 3);
        g1.agregarArista(3, 4);
        System.out.println("Coloring of graph 1");
        g1.asignarColores();

        System.out.println();
        NumeroCromatico g2 = new NumeroCromatico(5);
        g2.agregarArista(0, 1);
        g2.agregarArista(0, 2);
        g2.agregarArista(1, 2);
        g2.agregarArista(1, 4);
        g2.agregarArista(2, 4);
        g2.agregarArista(4, 3);
        System.out.println("Coloring of graph 2 ");
        g2.asignarColores();
    }
}
// This code is contributed by Aakash Hasija 

