class UlovligUtskrift extends Exception{
  UlovligUtskrift(Lege L, Legemiddel middel){
    super("Legen " + L.hentNavn() + " har ikke lov til å skrive ut " + middel.hentNavn());
  }
}
