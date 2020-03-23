class Vanlig extends Legemiddel {

  public Vanlig(String navn, double pris, double virkestoff){
    super(navn, pris, virkestoff);  //Kaller på legemiddels konstruktør
  }

  public String toString(){  //Metode for penere utskrift
    String vanligMiddel = String.format("%-60s" + "%-20s"+"%-30s", "Navn: "+navn, "Pris:" + this.hentPrisString() +"kr ", "Virkestoffmengde: "+hentVirkestoffString()+"mg ");
      return vanligMiddel;
  }
}
