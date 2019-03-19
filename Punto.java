public class Punto implements Comparable<Punto> {

	public double x;	//Coordenada x del Punto
	public double y;	//Coordenada y del punto
	public boolean isFirst; // True si es el punto de entrada o False si es el de salida.
	public Segment segment; //Segmento asociado al punto


	public Punto(double x, double y) {
		this.x = x;
		this.y = y;
		this.isFirst = true; //Valor por defecto pero se va a modificar cuando creemos su segmento asociado.
		this.segment = null;
	}

	public boolean equals(Punto p) {
		return this.x == p.x && this.y == p.y;
	}

	public double crossProduct(Punto p) {
		return this.x * p.y - this.y * p.x;
	}

	public Punto difference(Punto p) {
		return new Punto(this.x-p.x, this.y-p.y);
	}

	//Metodo para ordenar los puntos
	//Necesario para que los eventos esten ordenados.
	//Deben ser coherentes con su orden, es decir si el orden que quieren para sus eventos es de arriba hacia abajo
	//entonces los segmentos deben estar ordenados de derecha a izquierda, es decir si ordenan los puntos por su coordenada
	//y entonces los segmentos por su coordenada x y viceversa.
	public int compareTo(Punto p) {
		return this.y - p.y;//se organizan de arriba a abajo
	}

	//Metodo necesario para determinar el sentido de 3 puntos. necesario para saber si 2 segmentos se intersectan.
	//Elegir un representante para direccion y facilita mas las cosas.
	//0 para colineales, 1 para derecha y -1 para izquierda.
    //se verifica donde esta el punto THIS con respecto al segmento PQ.
	public int turn(Punto p, Punto q) {
		double t = (q.x-p.x)*(this.y-p.y) - (q.y-p.y)*(this.x-p.x);
        if(t<0)//DERECHA
            return 1;
        if(t>0)//IZQUIERDA
            return -1;
        return 0;
	}

	//auxiliar para imprimir un punto.
	public void imprime() {
		System.out.print("(" + this.x + "," + this.y + ")");
	}

}
