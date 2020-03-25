class Narkotisk extends Legemiddel {
  private int styrke;

  public Narkotisk(String navn, double pris, double virkestoff, int styrke){
    super(navn, pris, virkestoff);  //Kaller på legemiddels konstruktør.
    this.styrke = styrke;  //Gir det narkotiske legemiddelet en styrke.
  }
  public int hentNarkotiskStyrke(){  //Metode som returnerer styrken på legemiddelet
    return styrke;
  }

  public String hentType(){
      return "narkotisk";
  }

  public int hentStyrke(){
      return hentNarkotiskStyrke();
  }

  public String toString(){  //Metode for penere utskrift
    String narkotiskMiddel = String.format("%-60s" + "%-20s"+"%-30s"+"%-20s", "Navn: "+navn, "Pris:" + this.hentPrisString() +"kr ", "Virkestoffmengde: "+hentVirkestoffString()+"mg ", "Styrke: "+styrke);
      return narkotiskMiddel;
  }

}
