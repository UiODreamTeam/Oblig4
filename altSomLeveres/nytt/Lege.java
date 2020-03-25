class Lege implements Comparable<Lege> {
  protected String navn;
  private Lenkeliste<Resept> utskrevedeResepter = new Lenkeliste<Resept>(); //Oppretter en tom Lenkeliste
                                                                            //som skal inneholde resepter.


  public Lege(String navn){  //Konstruktør for lege-klassen
    this.navn = navn;
  }

  public String hentNavn(){  //Metode for å hente navn
    return navn;
  }

  public int hentKontrollId(){
      return 0;
  }

  public String toString(){  //Metode for enklere utskrift av objektet
    String lege = String.format("%-30s", "Navn: "+navn);
    return lege;
  }




  public int compareTo(Lege lege){  //Metode for å kunne sammenligne legers navn for alfabetisk sortering.
    return navn.compareTo(lege.hentNavn());
  }

  public Lenkeliste<Resept> hentReseptListe(){   //Metode for å hente ut lista med utskrevederesepter.
    return utskrevedeResepter;
  }

  public boolean sjekkLege(){  //Metode for å sjekke om vi driver med lege eller spesialist
    if (this instanceof Spesialist){
      return true;    //True hvis legen er spesialist.
    }
    return false;     //False om det er en vanlig lege.
  }

  public boolean sjekkLovlighet(Legemiddel legemiddel){  //Sjekk for utskrift av narkotisk.
    if(legemiddel instanceof Narkotisk){
      if(this.sjekkLege()){
        return true;   //Kun spesialist får skrive narkotisk
      }else{
        return false;  //Vanlig lege returnerer false.
      }
    }
    return true;  //Om stoffet ikke er narkotisk kan alle skrive ut
  }

  public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit)
  throws UlovligUtskrift {  //Metode for utskrift av hvite resepter.
    if(sjekkLovlighet(legemiddel)){
      HvitResept nyResept = new HvitResept(legemiddel, this, pasient, reit);
      utskrevedeResepter.leggTil(nyResept);  //Legger den nye resepten i utskrevedeResepter-lista
      return nyResept;  //returnerer den nye resepten.
    }else{
      throw new UlovligUtskrift(this, legemiddel);  //Om legen ikke har lov å skrive ut kastes exception.
    }
  }

  public MilitaerResept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit)
  throws UlovligUtskrift{  //Metode for utskrift av militaer resepter.
    if(sjekkLovlighet(legemiddel)){
      MilitaerResept nyResept = new MilitaerResept(legemiddel, this, pasient, reit);
      utskrevedeResepter.leggTil(nyResept);  //Legger den nye resepten i utskrevedeResepter-lista.
      return nyResept;      //returnere den nye resepten.
    }else{
      throw new UlovligUtskrift(this, legemiddel);  //Kaster exception om legen ikke er godkjent.
    }
  }

  public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit)
  throws UlovligUtskrift {  //Metode for utskrift av blaa resepter
    if(sjekkLovlighet(legemiddel)){
      BlaaResept nyResept = new BlaaResept(legemiddel, this, pasient, reit);
      utskrevedeResepter.leggTil(nyResept);   //Legger til den nye resepten i utskrevedeResepter-lista.
      return nyResept;      //Returnerer den nye resepten.
    }else{
      throw new UlovligUtskrift(this, legemiddel);   //Kaster exception om legen ikke er godkjent.
    }
  }

  public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient)
  throws UlovligUtskrift {  //Metode for utskrift av P-resepter.
    if(sjekkLovlighet(legemiddel)){
      PResept nyResept = new PResept(legemiddel, this, pasient);
      utskrevedeResepter.leggTil(nyResept);  //Legger den nye resepten i utskrevedeResepter-lista.
      return nyResept;  //Returnerer den ny resepten.
    }else{
      throw new UlovligUtskrift(this, legemiddel);  //Kaster exception om legen ikke er godkjent.
    }
  }
}
