class Vanedannende extends Legemiddel {
  private int styrke;

  public Vanedannende(String navn, double pris, double virkestoff, int styrke){
    super(navn, pris, virkestoff);  //Kaller på legemiddels konstruktør
    this.styrke = styrke;  //Gir det vanedannende legemiddelet en styrke.
  }
  public int hentVanedannendeStyrke(){  //Henter styrken på legemiddelet
    return styrke;
  }
  public String toString(){  //Metode for å gi lesbar info om legemiddelet.
    return ("Navn: " + navn + "\tPris: " + pris +
    "kr\tVirkestoffmengde: " + virkestoff + "mg\tStyrke: " + styrke);
  }
}
