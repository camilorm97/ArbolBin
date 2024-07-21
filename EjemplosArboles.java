import java.util.Objects;

public class EjemplosArboles<T> {

    public static <T> int contar_nodos(ArbolBin<T> my_tree) {
        if (my_tree.esVacio())
            return 0;
        else {
            return contar_nodos(my_tree.hijoIzquierdo()) + contar_nodos(my_tree.hijoDerecho()) + 1;
        }
    }

    public static int sumar_nodos(ArbolBin<Integer> my_tree) {
        if (my_tree.esVacio())
            return 0;
        else {
            return sumar_nodos(my_tree.hijoIzquierdo()) + sumar_nodos(my_tree.hijoDerecho()) + my_tree.raiz();
        }
    }

    public static int contar_nodos_pares(ArbolBin<Integer> my_tree) {
        if (my_tree.esVacio())
            return 0;
        else {
            int cont = my_tree.raiz() % 2 == 0 ? 1 : 0;
            return contar_nodos_pares(my_tree.hijoIzquierdo()) + contar_nodos_pares(my_tree.hijoDerecho()) + cont;
        }
    }

    public static int contar_nodos_hojas(ArbolBin<Integer> my_tree) {
        if (my_tree.esVacio())
            return 0;
        else {
            if (my_tree.hijoIzquierdo().esVacio() && my_tree.hijoDerecho().esVacio())
                return 1;
            else {
                return contar_nodos_hojas(my_tree.hijoIzquierdo()) + contar_nodos_hojas(my_tree.hijoDerecho());
            }
        }
    }

    public static int calcular_profundidad(ArbolBin<Integer> my_tree) {
        if (my_tree.esVacio())
            return 0;
        else {

            int hijo_izq = calcular_profundidad(my_tree.hijoIzquierdo());
            int hijo_dcho = calcular_profundidad(my_tree.hijoDerecho());
            return 1 + Math.max(hijo_izq, hijo_dcho);
        }
    }

    public static boolean es_lleno_o_no(ArbolBin<Integer> my_tree) {
        if (my_tree.esVacio())
            return true;
        else {
            int es_lleno = (int) Math.pow(2, calcular_profundidad(my_tree)) - 1;
            int cont = contar_nodos(my_tree);
            return es_lleno == cont;
        }
    }

    // Averiguar recorrido en anchura

    public static void valores_mismo_nivel(ArbolBin<Integer> my_tree, int k) {
        if (!my_tree.esVacio()) {
            if (k == 1) {
                System.out.print(my_tree.raiz() + "\t");
            } else {
                valores_mismo_nivel(my_tree.hijoIzquierdo(), k - 1);
                valores_mismo_nivel(my_tree.hijoDerecho(), k - 1);
            }
        }
    }

    public static boolean arboles_iguales(ArbolBin<Integer> my_tree, ArbolBin<Integer> my_tree2) {
        if (my_tree.esVacio() && my_tree2.esVacio())
            return true;

        else {
            if (Objects.equals(my_tree.raiz(), my_tree2.raiz()))
                return true;

            else {
                return arboles_iguales(my_tree.hijoIzquierdo(), my_tree2.hijoIzquierdo())
                        && arboles_iguales(my_tree.hijoDerecho(), my_tree2.hijoDerecho());
            }
        }
    }


    public static int contar_nodos_nivel(ArbolBin<Integer> my_tree, int nivel) {
        if (my_tree.esVacio())
            return 0;
        else {
            int nodos = 0;
            if (nivel >= 1) {
                nivel -= 1;
                nodos += contar_nodos_nivel(my_tree.hijoIzquierdo(), nivel) + contar_nodos_nivel(my_tree.hijoDerecho(), nivel) + 1;
            }
            return nodos;
        }
    }


    public static int comprobar_nodos_hoja(ArbolBin<Integer> my_tree, int nivel) {
        if (nivel == 1) {
            if (my_tree.hijoIzquierdo().esVacio() && !my_tree.hijoDerecho().esVacio())
                return -1;
            if (my_tree.esVacio())
                return 0;
            else {
                int cont = 0;
                if (!my_tree.hijoIzquierdo().esVacio())
                    cont++;
                if (!my_tree.hijoDerecho().esVacio())
                    cont++;
                return cont;
            }
        }
        else {
            int hijo_derecho = comprobar_nodos_hoja(my_tree.hijoDerecho(), nivel - 1);
            int hijo_izquierdo = comprobar_nodos_hoja(my_tree.hijoIzquierdo(), nivel - 1);
            if (hijo_izquierdo == -1 || hijo_derecho == -1)
                return -1;
            else if (hijo_derecho > 0 && hijo_izquierdo % 2 != 0)
                return -1;
            else if (hijo_derecho > 0 && hijo_izquierdo == 0)
                return -1;
            return hijo_izquierdo + hijo_derecho;
        }
    }


    public static boolean es_completo(ArbolBin<Integer> my_tree) {
        if (my_tree.esVacio())
            return true;
        else {
            int profundidad = calcular_profundidad(my_tree) - 1; // Calculamos la profundidad - 1. Ya que en -1 el arbol debe de estar lleno
            int nodos = contar_nodos_nivel(my_tree, profundidad); // Calculamos los nodos que debe de haber en nivel - 1.

            if (nodos == ((int) Math.pow(2, profundidad) - 1)) { // Si los nodos que tenemos son los mismos que tiene que haber en nivel - 1 es lleno
                return comprobar_nodos_hoja(my_tree, profundidad) != -1; // Comprobamos si los nodos hijos van de izquierda a derecha en el último nivel
            }

            return false;
        }
    }


    // Función pasar dos arboles de enteros que nos diga si son simétricos

    public static void main(String[] args) {

        /*
         *                10
         *        7                12
         *    x       2        20       4
         *  x  x    x   x    x   x    x
         *
         * */

        ArbolBin<Integer> n_2 = new ArbolBin<Integer>(new ArbolBin<Integer>(), 2, new ArbolBin<Integer>());
        ArbolBin<Integer> n_20 = new ArbolBin<Integer>(new ArbolBin<Integer>(), 20, new ArbolBin<Integer>());
        ArbolBin<Integer> n_4 = new ArbolBin<Integer>(new ArbolBin<Integer>(), 4, new ArbolBin<Integer>());

        ArbolBin<Integer> n_7 = new ArbolBin<Integer>(new ArbolBin<>(), 7, n_2);
        ArbolBin<Integer> n_12 = new ArbolBin<Integer>(n_20, 12, n_4);

        ArbolBin<Integer> my_tree = new ArbolBin<Integer>(n_7, 10, n_12);

        //my_tree.dibujar(1);
        System.out.println(contar_nodos(my_tree));
        System.out.println(sumar_nodos(my_tree));
        System.out.println(contar_nodos_pares(my_tree));
        System.out.println(contar_nodos_hojas(my_tree));

        System.out.println(calcular_profundidad(my_tree));
        System.out.println(es_lleno_o_no(my_tree));
        valores_mismo_nivel(my_tree, 2);
        System.out.println();

        System.out.println(arboles_iguales(my_tree, my_tree));

        System.out.println();
        System.out.println("¿Es completo? " + es_completo(my_tree));
    }

}
