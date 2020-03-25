class Spesialist extends Lege implements Godkjenningsfritak {
  private int kontrollID;

  public Spesialist(String navn, int kontrollID){
    super(navn);  //Sender navn til superklassen
    this.kontrollID = kontrollID;
  }

  @Override
  public int hentKontrollId(){  //Metode for å hente kontrollID
    return kontrollID;
  }
  public String toString(){
    String spesi = String.format("%-30s" + "%-7s", "Navn: "+navn, "KontrollID: " + kontrollID);
    return spesi;
  }
}
