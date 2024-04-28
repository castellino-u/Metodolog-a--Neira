package ejemplodeagoritmo; // Declara el paquete al que pertenece la clase

import java.util.Arrays; // Importa la clase Arrays del paquete java.util

class Grafo { // Declara la clase Grafo
    private int V; // Declara una variable para almacenar el número de nodos en el grafo
    private int[][] grafo; // Declara una matriz para representar el grafo

    // Constructor de la clase Grafo que recibe el número de nodos y la matriz de adyacencia como parámetros
    public Grafo(int v, int[][] grafo) {
        V = v; // Asigna el número de nodos a la variable V
        this.grafo = grafo; // Asigna la matriz de adyacencia proporcionada a la variable grafo
    }

    // Método para imprimir la solución del algoritmo de Dijkstra
    public void imprimirSolucion(int[] distancia) {
        System.out.println("Distancias mínimas desde el nodo fuente:"); // Imprime un encabezado
        for (int i = 0; i < V; i++) {
            System.out.println("Nodo " + i + ": " + distancia[i]); // Imprime la distancia mínima para cada nodo
        }
    }

    // Método para obtener el nodo con la menor distancia no incluido en el conjunto corto
    public int obtenerNodoMenorDistancia(int[] distancia, boolean[] conjuntoCorto) {
        int minDistancia = Integer.MAX_VALUE; // Inicializa la distancia mínima como el máximo valor entero posible
        int minIndex = -1; // Inicializa el índice del nodo con la menor distancia

        // Itera sobre todos los nodos del grafo
        for (int v = 0; v < V; v++) {
            // Si el nodo no está en el conjunto corto y su distancia es menor o igual que la mínima distancia actual
            if (!conjuntoCorto[v] && distancia[v] <= minDistancia) {
                minDistancia = distancia[v]; // Actualiza la distancia mínima
                minIndex = v; // Actualiza el índice del nodo con la menor distancia
            }
        }
        return minIndex; // Retorna el índice del nodo con la menor distancia
    }

    // Método principal que implementa el algoritmo de Dijkstra
    public void dijkstra(int nodoFuente) {
        int[] distancia = new int[V]; // Arreglo para almacenar las distancias mínimas desde el nodo fuente
        boolean[] conjuntoCorto = new boolean[V]; // Arreglo para marcar los nodos incluidos en el conjunto corto

        // Inicializa todas las distancias como infinito y marca el nodo fuente con distancia 0
        Arrays.fill(distancia, Integer.MAX_VALUE);
        distancia[nodoFuente] = 0;

        // Itera V-1 veces, donde V es el número de nodos en el grafo
        for (int count = 0; count < V - 1; count++) {
            // Obtiene el nodo con la menor distancia no incluido en el conjunto corto
            int u = obtenerNodoMenorDistancia(distancia, conjuntoCorto);
            conjuntoCorto[u] = true; // Marca el nodo u como incluido en el conjunto corto

            // Actualiza las distancias mínimas de los nodos vecinos de u
            for (int v = 0; v < V; v++) {
                // Si el nodo v no está en el conjunto corto, hay una arista de u a v, y
                // la distancia a través de u es más corta que la distancia actual a v
                if (!conjuntoCorto[v] && grafo[u][v] != 0 && distancia[u] != Integer.MAX_VALUE
                        && distancia[u] + grafo[u][v] < distancia[v]) {
                    distancia[v] = distancia[u] + grafo[u][v]; // Actualiza la distancia mínima a v
                }
            }
        }

        // Imprime la solución del algoritmo de Dijkstra
        imprimirSolucion(distancia);
    }
}