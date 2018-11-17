import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;

class Schrankwand extends Moebel {
    
    private int anzahlDerEinheiten;
    private int breite;
    private int tiefe;
    static GUIOption[] optionen = {
        new GUIOption("X-Position"),
        new GUIOption("Y-Position"),
        new GUIOption("Farbe"),
        new GUIOption("Orientierung"),
        new GUIOption("Anzahl der Einheiten"),
        new GUIOption("Breite"),
        new GUIOption("Tiefe"),
        new GUIOption("Art")
    };
    static GUIOption[] wichtigeOptionen = {
        new GUIOption("Anzahl der Einheiten"),
        new GUIOption("Breite"),
        new GUIOption("Tiefe")
    };
    
    Schrankwand(int xPosition, int yPosition, String farbe, int orientierung, int anzahlDerEinheiten, int breite, int tiefe) {
        super(xPosition, yPosition, farbe, orientierung);
        this.anzahlDerEinheiten = anzahlDerEinheiten;
        this.breite = breite;
        this.tiefe = tiefe;
        this.art = "Schrankwand";
    }
       
    Schrankwand(int anzahlDerEinheiten, int breite, int tiefe) {
        this(0, 0, "schwarz", 0, anzahlDerEinheiten, breite, tiefe);
    }
    
    protected Shape gibAktuelleFigur() {
        GeneralPath schrankwand = new GeneralPath();
        
        for(int i = 0; i < anzahlDerEinheiten; i++) {
            Schrank schrank = new Schrank(0, 0, "schwarz", 0, breite, tiefe);
            schrank.bewegeHorizontal(i * schrank.getBreite());
            schrankwand.append(schrank.gibAktuelleFigur(), false);
        }
        
        AffineTransform t = new AffineTransform();
        t.translate(xPosition, yPosition);
        Rectangle2D umriss = schrankwand.getBounds2D();
        t.rotate(Math.toRadians(orientierung),umriss.getX()+umriss.getWidth()/2,umriss.getY()+umriss.getHeight()/2);
        return  t.createTransformedShape(schrankwand);
    }

    String gibWert(String attributName) {
        for (int i = 0; i < optionen.length; i++) {
            if (attributName == optionen[i].name) {
                switch (i) {
                    case 0:
                        return Integer.toString(xPosition);
                    case 1:
                        return Integer.toString(yPosition);
                    case 2:
                        return farbe;
                    case 3:
                        return Integer.toString(orientierung);
                    case 4:
                        return Integer.toString(anzahlDerEinheiten);
                    case 5:
                        return Integer.toString(breite);
                    case 6:
                        return Integer.toString(tiefe);
                    case 7:
                        return art;
                }
            }
        }
        return ""; //bzw throw error, that attribut doesnt exist
    }
    
    GUIOption[] getOptionen() {
        GUIOption[] optionen = {
            new GUIOption("X-Position"),
            new GUIOption("Y-Position"),
            new GUIOption("Farbe"),
            new GUIOption("Orientierung"),
            new GUIOption("Anzahl der Einheiten"),
            new GUIOption("Breite"),
            new GUIOption("Tiefe"),
            new GUIOption("Art")
        };
        return optionen;
    }
}
