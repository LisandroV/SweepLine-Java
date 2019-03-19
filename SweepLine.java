import java.util.*;
import java.io.*;

public class SweepLine {


	public static AVLTree state = new AVLTree(); //Linea de barrido.
	public static TreeSet<Punto> intersection = new TreeSet<>(); //Es igual que un Set solo que mantiene ordenada la informacion.

    //Metodo que implementa el algoritmo de SweepLinea
    //Se pueden ayudar imprimiendo la linea y la interseccion encontrada y que punto estan procesando en cada paso
    //Asi saben si estan ordenando bien o mal o como va su algoritmo.
	public static void sweepLine(ArrayList<Punto> pts) {
        for(Punto p: pts)
            state.insert(p.segment);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        ArrayList<Punto> pts = new ArrayList<>();
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
            pts.add(p);
            pts.add(q);

        }
        Collections.sort(pts);
        for(Punto p: pts) {
        	System.out.print("Punto (" + p.x + "," + p.y + ") -- segmento : ");
        	p.segment.imprime();
        	System.out.println();
        }

        sweepLine(pts);
        Segment.print(state.getRoot());

        System.out.println("Puntos de interseccion");
        for(Punto p: intersection) {
        	p.imprime();
        	System.out.println();
        }
	}
}
