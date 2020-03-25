class Vanlig extends Legemiddel {

  public Vanlig(String navn, double pris, double virkestoff){
    super(navn, pris, virkestoff);  //Kaller på legemiddels konstruktør
  }
  public String toString(){  //Metode for å gi lesbar info om legemiddelet.
    return ("Navn: " + navn + "\tPris: " + pris +
            "kr\tVirkestoffmengde: " + virkestoff + "mg");
  }
}
//Endringer, vil det gå?
