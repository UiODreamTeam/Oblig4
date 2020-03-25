abstract class Legemiddel {

  static int idTeller;  //Teller for å skille de ulike legemiddelene.
  protected int id;
  protected double pris, virkestoff;
  protected String navn;

  public Legemiddel(String navn, double pris, double virkestoff){
    this.navn = navn;
    this.pris = pris;
    this.virkestoff = virkestoff;
    id = idTeller;  //Setter naavaerende idteller som legemiddelets id.
    idTeller++;   //Øker id teller.
  }

  public int hentId(){  //Metode som returnerer legemiddelets id
    return id;
  }
  public String hentNavn(){
    return navn;
  }
  public double hentPris(){  //Metode som returnerer prisen på legemiddelet.
    return pris;
  }
  public String hentPrisString(){  //Metode som returnerer prisen som string med 2 desimaler.
    String stringPris = String.format("%.2f",pris);
    return stringPris;
  }
  public double hentVirkestoff(){  //Metode som returnerer virkestoffet i legemiddelet.
    return virkestoff;
  }
  public String hentVirkestoffString(){  //Metode som returnerer virkestoffet i legemiddelet.
    String stringVirkestoff = String.format("%.2f",virkestoff);
    return stringVirkestoff;
  }
  public void settNyPris(double pris){  //Metode som gir legemiddelet ny pris.
    this.pris = pris;
  }
  abstract public String hentType();  //Metode for å hente type, overskrives i alle legemiddeltypene.
  abstract public int hentStyrke();  //Metode for å hente styrken, overskrives i alle legemiddeltypene.
}
