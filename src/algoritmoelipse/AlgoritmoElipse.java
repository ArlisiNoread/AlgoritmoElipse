package algoritmoelipse;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Roberto
 */
public class AlgoritmoElipse extends Canvas {

    AlmacenDeCoordenadas almacen;

    public AlgoritmoElipse(AlmacenDeCoordenadas almacen) {
        this.almacen = almacen;

    }

    @Override
    public void paint(Graphics g) {

        setBackground(Color.WHITE);
        //this.CircleMidPoint(g, 400, 300, 100);
        pintaPuntosEn4Regiones(g, this.almacen);

    }

    public static void main(String[] args) {
        AlmacenDeCoordenadas almacen = new AlmacenDeCoordenadas();
        Elipse(400, 300, 20, 10, almacen);
        almacen.imprimeLasCoordenadas();
        // En este punto ya tengo las coordenadas de 1/4 parte... ya puedo imprimirlas

        AlgoritmoElipse m = new AlgoritmoElipse(almacen);
        JFrame f = new JFrame("Algoritmo de la Elipse");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(m);

        f.setSize(800, 600);
        //f.setSize(null);
        f.setVisible(true);
    }

    public static void pintaPuntosEn4Regiones(Graphics g, AlmacenDeCoordenadas almacen) {
        int anchoDelPunto = 4;
        int desplazox = 400;
        int desplazoy = 300;

        //Parte 1
        g.setColor(Color.red);
        for (int cnt = 0; cnt < almacen.getAlmacen().size(); cnt++) {
            g.fillOval(almacen.getCoordenada(cnt).getX() + desplazox, almacen.getCoordenada(cnt).getY() + desplazoy, anchoDelPunto, anchoDelPunto);
        }
        //Parte 2
        g.setColor(Color.blue);
        for (int cnt = 0; cnt < almacen.getAlmacen().size(); cnt++) {
            g.fillOval(-almacen.getCoordenada(cnt).getX() + desplazox, almacen.getCoordenada(cnt).getY() + desplazoy, anchoDelPunto, anchoDelPunto);
        }

        //Parte 3
        g.setColor(Color.green);
        for (int cnt = 0; cnt < almacen.getAlmacen().size(); cnt++) {
            g.fillOval(-almacen.getCoordenada(cnt).getX() + desplazox, -almacen.getCoordenada(cnt).getY() + desplazoy, anchoDelPunto, anchoDelPunto);
        }
        
        //Parte 4
        g.setColor(Color.black);
        for (int cnt = 0; cnt < almacen.getAlmacen().size(); cnt++) {
            g.fillOval(almacen.getCoordenada(cnt).getX() + desplazox, -almacen.getCoordenada(cnt).getY() + desplazoy, anchoDelPunto, anchoDelPunto);
        }

    }

    public static void Elipse(int xc, int yc, int rx, int ry, AlmacenDeCoordenadas almacen) {

        int anchoDelPunto = 1;
        int desplazox = 400;
        int desplazoy = 300;

        int x, y, p, px, py;
        int rx2, ry2, tworx2, twory2;
        ry2 = ry * ry;
        rx2 = rx * rx;
        twory2 = 2 * ry2;
        tworx2 = 2 * rx2;
        /* región 1 */
        x = 0;
        y = ry;
        //PlotPoint(x, y);
        /*
        g.setColor(Color.red);
        g.fillOval(x + desplazox, y + desplazoy, anchoDelPunto, anchoDelPunto);
         */
        almacen.agregarCoordenadas(new Coordenada(x, y));

        p = (int) Math.round(ry2 - rx2 * ry + 0.25 * rx2);
        px = 0;
        py = tworx2 * y;
        while (px < py) {
            /* se cicla hasta trazar la región 1 */
            x = x + 1;
            px = px + twory2;
            if (p < 0) {
                p = p + ry2 + px;
            } else {
                y = y - 1;
                py = py - tworx2;
                p = p + ry2 + px - py;
            }
            // PlotPoint(x, y);
            /*
            g.setColor(Color.blue);
            g.fillOval(x + desplazox, y + desplazoy, anchoDelPunto, anchoDelPunto);
             */
            almacen.agregarCoordenadas(new Coordenada(x, y));
        }

        /* región 2 */
        p = (int) Math.round(ry2 * (x + 0.5) * (x + 0.5) + rx2 * (y - 1) * (y - 1) - rx2 * ry2);
        px = 0;
        py = tworx2 * y;
        while (y > 0) {
            /* se cicla hasta trazar la región 2 */
            y = y - 1;
            py = py - tworx2;
            if (p > 0) {
                p = p + rx2 - py;
            } else {
                x = x + 1;
                px = px + twory2;
                p = p + rx2 + py + px;
            }
            // PlotPoint(x, y);
            /*
            g.setColor(Color.green);
            g.fillOval(x + desplazox, y + desplazoy, anchoDelPunto, anchoDelPunto);
             */
            almacen.agregarCoordenadas(new Coordenada(x, y));
        }

    }

    /*void CircleMidPoint(Graphics g, int xc, int yc, int r) {
        ArrayList<Coordenadas> coordenadas = new ArrayList<>();

        int anchoDelPunto = 1;
        int desplazox = 400;
        int desplazoy = 300;

        int x, y, p;
        x = 0;
        y = r;
        p = 1 - r;
        //g.setColor(Color.green);
        //g.fillOval(x + desplazox, y + desplazoy, anchoDelPunto, anchoDelPunto);
        coordenadas.add(new Coordenadas(x, y));
        // se cicla hasta trazar todo un octante 
        // Si entendí bien... ahora lo hacemos alrevés 
        while (x < y) {
            x = x + 1;
            if (p < 0) {
                p = p + 2 * x + 1;
            } else {
                y = y - 1;
                p = p + 2 * (x - y) + 1;
            }

            //g.setColor(Color.red);
            //g.fillOval(x + desplazox, y + desplazoy, anchoDelPunto, anchoDelPunto);
            coordenadas.add(new Coordenadas(x, y));
            //System.out.println("x: " + x + ", y: " + y + "; Impreso( " + (x + desplazox) + "," + (y + desplazoy) + ")");
        }
        //Ya con esto tengo todas las coordenadas principales....
    }*/
}

class Coordenada {

    private int x, y;

    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String toStringCoordenada() {
        return ("(" + this.x + ", " + this.y + ")");
    }

}

class AlmacenDeCoordenadas {

    private ArrayList<Coordenada> almacen;

    public AlmacenDeCoordenadas() {
        almacen = new ArrayList<>();
    }

    public void agregarCoordenadas(Coordenada coordenada) {
        almacen.add(coordenada);
    }

    public Coordenada getCoordenada(int posicion) {
        return this.almacen.get(posicion);
    }

    public ArrayList<Coordenada> getAlmacen() {
        return this.almacen;
    }

    public void imprimeLasCoordenadas() {
        System.out.println("Coordenadas: (x , y)");
        for (int x = 0; x < this.almacen.size(); x++) {
            System.out.println("Punto: " + x + ": " + this.almacen.get(x).toStringCoordenada());
        }
        System.out.println(" ");
    }

}
