class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T> {

  public void leggTil(T x){   //Metode for å legge til nytt element i lista.
    if(stoerrelse() == 0){   //Om lista er tom legges elementet rett i lista.
      super.leggTil(x);
    }else{                  //Hvis ikke finner vi plassen dens.
        Node bytte = start.neste;
        int teller = 0;
        boolean lagtTil = false;        //Boolean for å ikke legge til dobbelt
        while(teller < stoerrelse()){
          if(bytte.innhold.compareTo(x) > 0){   //Sammenligner elementene i lista med det nye
            super.leggTil(teller, x);         //Er det nye mindre legger vi det til her.
            teller = stoerrelse();            //Og ender loopen
            lagtTil = true;
          }else{               //Er det nye større går vi til neste plass i lista.
            bytte = bytte.neste;
            teller++;
          }
        }
        if (lagtTil != true){  //Sjekker så vi ikke legger til to ganger
          super.leggTil(x);     //Var det nye elementet størst legger vi det bakerst.
        }
      }
    }

  public T fjern(){   //Metode for å fjerne det største/siste elementet i lista
    if(stoerrelse() == 0){
      throw new UgyldigListeIndeks(-1);
    }
    return super.fjern(stoerrelse()-1);
  }

  public void sett(int pos, T x){   //Gir feilmelding om noen prøver legge til på spesifikk plass
    throw new UnsupportedOperationException();
  }
  public void leggTil(int pos, T x){  //Gir feilmelding om noen prøver legge til på spesifikk plass.
    throw new UnsupportedOperationException();
  }
}
