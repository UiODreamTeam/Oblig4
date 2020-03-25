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
  public double hentPris(){  //Metode som returnerer prisen på legemiddelet.
    return pris;
  }
  public double hentVirkestoff(){  //Metode som returnerer virkestoffet i legemiddelet.
    return virkestoff;
  }
  public void settNyPris(double pris){  //Metode som gir legemiddelet ny pris.
    this.pris = pris;
  }
}
