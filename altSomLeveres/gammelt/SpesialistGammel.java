class Spesialist extends Lege implements Godkjenningsfritak {
  private int kontrollID;

  public Spesialist(String navn, int kontrollID){
    super(navn);  //Sender navn til superklassen
    this.kontrollID = kontrollID;
  }
  public int hentKontrollID(){  //Metode for å hente kontrollID
    return kontrollID;
  }
  public String toString(){
    return "Navn: " + navn + "\nKontrollID: " + kontrollID;
  }
}
