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
		double dif_x = this.begin.x - s.begin.x;
        if(dif_x != 0) return (int)dif_x;
        return (int)(this.begin.y - s.begin.y);
	}

	//Metodo que ayudara a saber si existe una interseccion entre 2 segmentos.
	public boolean intersection(Segment s) {
		return true;
	}

	//Metodo que regresa el punto de interseccion entre 2 Puntos.
	public Punto getIntersection(Segment s) {
        // Line AB represented as a1x + b1y = c1
        double a1 = this.end.y - this.begin.y;
        double b1 = this.begin.x - this.end.x;
        double c1 = a1*(this.begin.x) + b1*(this.begin.y);

        // Line CD represented as a2x + b2y = c2
        double a2 = s.end.y - s.begin.y;
        double b2 = s.begin.x - s.end.x;
        double c2 = a2*(s.begin.x) + b2*(s.begin.y);

        double determinant = a1*b2 - a2*b1;

        //if (determinant == 0){
            // The lines are parallel. This is simplified
            // by returning a pair of FLT_MAX
            //return make_pair(FLT_MAX, FLT_MAX);
        //}
        //else{
            double x = (b2*c1 - b1*c2)/determinant;
            double y = (a1*c2 - a2*c1)/determinant;
            return new Punto(x, y);
        //}
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
