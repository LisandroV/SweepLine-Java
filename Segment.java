import java.util.*;
import java.lang.*;

public class Segment extends AVLNode<Segment> {
	
	Punto begin; //Punto que representara el Punto de entrada, es decir el menor de los 2 puntos
	Punto end; //Punto que representa el Punto de salida, el mayor de los 2 puntos

	public Segment(Punto b, Punto e) {
		this.begin = b;
		this.end = e;
	}

	public boolean equals(Segment s) {
		return this.begin.x == s.begin.x && 
				this.begin.y == s.begin.y &&
				this.end.x == s.end.x &&
				this.end.y == s.end.y;	
	}

	//Metodo necesario para mantener bien ordenado el AVLTree con los segmentos, decidir el orden
	//que quieren darle a sus segmentos, es decir leer de arriba hacia abajo o de derecha a izquierda.  
	@Override
	public int compareTo(Segment s) {
		return 0;	
	} 

	//Metodo que ayudara a saber si existe una interseccion entre 2 segmentos.
	public boolean intersection(Segment s) {
		return true;
	}	

	//Metodo que regresa el punto de interseccion entre 2 Puntos.
	public Punto getIntersection(Segment s) { 		
		return null;
	}

	//Metodo que ayuda a la representacion visual del punto en terminal.
	//Modificarlo si quieren, esta sensillo pero me sirvio para saber como voy  
	public void imprime() {
		System.out.print("(" + this.begin.x + "," + this.begin.y + ")-(" + this.end.x + "," + this.end.y + ")");
	}

	//Metodo para imprimir el arbol en terminal de manera descente. 
	//Este igual modificarlo si quieren, Esta bastante bueno para saber como van con su barrido y su orden. 
	public static void print(AVLNode root) {
        if(root == null) {
            System.out.println("(VACIO)");
            return;
        }
        int height = Math.max(root.leftHeight, root.rightHeight) + 1,
            width = (int)Math.pow(2, height-1);
        List<AVLNode> current = new ArrayList<AVLNode>(1),
            next = new ArrayList<AVLNode>(2);
        current.add(root);
        final int maxHalfLength = 4;
        int elements = 1;
        StringBuilder sb = new StringBuilder(maxHalfLength*width);
        for(int i = 0; i < maxHalfLength*width; i++)
            sb.append(' ');
        String textBuffer;
        for(int i = 0; i < height; i++) {
            sb.setLength(maxHalfLength * ((int)Math.pow(2, height-1-i) - 1));
            textBuffer = sb.toString();
            for(AVLNode n : current) {
                System.out.print(textBuffer);
                if(n == null) {
                    System.out.print("       ");
                    next.add(null);
                    next.add(null);
                } else {
                	Segment s = (Segment) n;
                    s.imprime();
                    next.add(n.left);
                    next.add(n.right);
                }
                System.out.print(textBuffer);
            }
            System.out.println();
            if(i < height - 1) {
                for(AVLNode n : current) {
                    System.out.print(textBuffer);
                    if(n == null)
                        System.out.print("     ");
                    else
                        System.out.printf("%s          %s",
                                n.left == null ? " " : "/", n.right == null ? " " : "\\");
                    System.out.print(textBuffer);
                }
                System.out.println();
            }
            elements *= 2;
            current = next;
            next = new ArrayList<AVLNode>(elements);
        }
    }

}

