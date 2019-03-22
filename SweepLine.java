import java.util.*;
import java.io.*;

public class SweepLine {


	public static AVLTree state = new AVLTree(); //Linea de barrido.
	public static TreeSet<Punto> intersectionSet = new TreeSet<>(); //Es igual que un Set solo que mantiene ordenada la informacion.
    public static PriorityQueue<Punto> eventos = new PriorityQueue<>();//cola de prioridades para los eventos

    //Metodo que implementa el algoritmo de SweepLinea
    //Se pueden ayudar imprimiendo la linea y la interseccion encontrada y que punto estan procesando en cada paso
    //Asi saben si estan ordenando bien o mal o como va su algoritmo.
	public static void sweepLine(PriorityQueue<Punto> eventos) {
        while(eventos.size()>0){
            Punto p = eventos.poll();
            eventHandler(p);
        }
	}

    public static void addIntersection(Segment s1,Segment s2){//los segmentos deben ser pasados en orden
        if(s1 == null || s2 == null) return;
        Punto intersection = s1.getIntersection(s2);
        if(intersection!=null){
            intersection.isIntersection = true;
            intersection.s1 = s1;
            intersection.s2 = s2;
            eventos.offer(intersection);
        }
    }

    public static void eventHandler(Punto e){
        //if(!e.isIntersection) e.segment.imprime(); //para pruebas
        if(e.isFirst && !e.isIntersection){
            state.insert(e.segment);//se agrega segmento al arbol AVL
            AVLNode a = state.leftNeighbour(e.segment);
            AVLNode b = state.rightNeighbour(e.segment);
            addIntersection((Segment)a,e.segment);//se pasan los segmentos en orden
            addIntersection(e.segment,(Segment)b);
        }
        else if(!e.isFirst && !e.isIntersection){//punto final del segmento
            AVLNode a = state.leftNeighbour(e.segment);
            AVLNode b = state.rightNeighbour(e.segment);
            addIntersection((Segment)a,e.segment);
            addIntersection(e.segment,(Segment)b);
            state.delete(e.segment);//se elimina segmento del arbol AVL
        }
        else if(e.isIntersection){
            AVLNode a = state.leftNeighbour(e.s1);
            AVLNode d = state.rightNeighbour(e.s2);
            addIntersection((Segment)a, e.s1);
            addIntersection(e.s2, (Segment)d);
            intersectionSet.add(e);
        }
        //Segment.print(state.getRoot());//se imprime el arbol para hacer pruebas
        //System.out.println("-----------Termina-paso-de-Handler----------\n");
    }

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < n; i++) {
            String[] linea = sc.nextLine().split(" ");
            int x1 = Integer.parseInt(linea[0].trim());
            int y1 = Integer.parseInt(linea[1].trim());
            int x2 = Integer.parseInt(linea[2].trim());
            int y2 = Integer.parseInt(linea[3].trim());
            /*
            Implementar la construccion de los puntos y de los segmentos mientras se
            van obteniendo los datos, deben de construir bien los puntos y segmentos,
            ligarlos y etiquetarlos bien.
            */
            Punto p = new Punto(x1,y1);
            Punto q = new Punto(x2,y2);
            q.isFirst = false;
            Segment s = new Segment(p,q);
            p.segment = s;
            q.segment = s;
            eventos.offer(p);
            eventos.offer(q);
        }

        sweepLine(eventos);

        System.out.println("Puntos de interseccion");
        for(Punto p: intersectionSet) {
        	p.imprime();
        	System.out.println();
        }
	}
}
