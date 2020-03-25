class Narkotisk extends Legemiddel {
  private int styrke;

  public Narkotisk(String navn, double pris, double virkestoff, int styrke){
    super(navn, pris, virkestoff);  //Kaller på legemiddels konstruktør.
    this.styrke = styrke;  //Gir det narkotiske legemiddelet en styrke.
  }
  public int hentNarkotiskStyrke(){  //Metode som returnerer styrken på legemiddelet
    return styrke;
  }
  public String toString(){  //Metode for å gi lesbar info om legemiddelet.
    return ("Navn: " + navn + "\tPris: " + pris +
    "kr\tVirkestoffmengde: " + virkestoff + "mg\tStyrke: " + styrke);
  }
}
