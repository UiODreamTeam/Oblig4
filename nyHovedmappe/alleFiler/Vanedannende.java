class Vanedannende extends Legemiddel {
  private int styrke;

  public Vanedannende(String navn, double pris, double virkestoff, int styrke){
    super(navn, pris, virkestoff);  //Kaller på legemiddels konstruktør
    this.styrke = styrke;  //Gir det vanedannende legemiddelet en styrke.
  }
  public int hentVanedannendeStyrke(){  //Henter styrken på legemiddelet
    return styrke;
  }

  public String toString(){  //Metode for penere utskrift
    String vanedannendeMiddel = String.format("%-60s" + "%-20s"+"%-30s"+"%-20s", "Navn: "+navn, "Pris:" + this.hentPrisString() +"kr ", "Virkestoffmengde: "+hentVirkestoffString()+"mg ", "Styrke: "+styrke);
      return vanedannendeMiddel;
  }
}
