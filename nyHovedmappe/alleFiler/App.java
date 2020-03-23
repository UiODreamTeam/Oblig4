import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.FileNotFoundException;

class App{
  static Legesystem nyttSystem = new Legesystem();  //Oppretter et legesystem for bruk i appen.
  public static void main(String[] args) throws InputMismatchException, UlovligUtskrift, FileNotFoundException{
    int menyValg = 0;
    filLesing("inndata.txt");               //Lar fil opprette litt leger og midler for bruk i programmet mens vi tester. !!!!FJERN FoR LEVERING!!!!
    while(menyValg!=6){                    //Innvalg 6 er Exit, saa programmet kjorer til 6 blir valgt i hovedmenyen.
      int valg = hovedmeny();              //Metoden hovedmeny ligger under, og tar seg av utskrift til, og input fra, bruker.
      if (valg==1){
        skrivUtAlt();  //kjor Metode for innvalg 1 Rimelig ferdig, mangler pynt!
      }else if (valg==2){
        opprett();     //kjor Metode for innvalg 2 Rimelig ferdig, sikkert mulig med litt pynt.
      }else if (valg==3){
        brukResept();  //kjor Metode for innvalg 3 Rimelig ferdig, med forbehold.
      }else if (valg==4){
        skrivStatistikk();//kjor Metode for innvalg 4 IKKE FERDIG
      }else if (valg==5){
        skrivTilFil();//kjor metode for innvalg 5 IKKE FERDIG
      }
      menyValg=valg;
    }
  }

  private static void filLesing(String filnavn) throws FileNotFoundException, UlovligUtskrift{  //Metode for aa lese filer etter gitte monster.
      nyttSystem.lesFil(filnavn);
  }

  private static void skrivUtAlt(){  //Metode for innvalg 1 Skriver ut alle
       System.out.println("Oversikt over alle elementer.");
       if(nyttSystem.hentLegeListe().stoerrelse() == 0){
           System.out.println("\nIngen registrerte leger.");
       }else{
           System.out.println("\nAlle registrerte leger:");
           for(Lege l : nyttSystem.hentLegeListe()){
              System.out.println(l);
           }
       }

       if(nyttSystem.hentPasientListe().stoerrelse() == 0){
           System.out.println("\nIngen registrerte pasienter.");
       }else{
           System.out.println("\nAlle registrerte pasienter:");
           for(Pasient p : nyttSystem.hentPasientListe()){
               System.out.println(p);
           }
       }
       if(nyttSystem.hentLegemiddelListe().stoerrelse() == 0){
           System.out.println("\nIngen registrerte legemidler.");
       }else{
           System.out.println("\nAlle registrerte legemidler:");
           for(Legemiddel m : nyttSystem.hentLegemiddelListe()){
               System.out.println(m);
           }
       }
       if(nyttSystem.hentReseptListe().stoerrelse() == 0){
           System.out.println("\nIngen registrerte resepter.");
       }else{
           System.out.println("\nAlle registrerte resepter:");
           for(Resept r : nyttSystem.hentReseptListe()){
               System.out.println(r);
           }
       }
   }

  private static void opprett() throws InputMismatchException, UlovligUtskrift{  //Metode for innvalg 2 - DELOPPGAVE E4
    Scanner oppretterScanner = new Scanner(System.in);
    int oppretteValg = 0;
    while(oppretteValg<1||oppretteValg>4){
        System.out.println("\nHva onsker du aa opprette? ");
        System.out.printf("%-12s" + "Tast 1","Lege?");
        System.out.printf("\n%-12s" + "Tast 2","Legemiddel?");
        System.out.printf("\n%-12s" + "Tast 3","Resept? ");
        System.out.printf("\n%-12s" + "Tast 4\n>","Pasient? ");
        try{  //Prover om bruker oppgir gyldig input.
            oppretteValg = oppretterScanner.nextInt();  //Sjekker etter int fra bruker.
            if(oppretteValg<1||oppretteValg>4){
               System.out.println("\nUgyldig input! Prov igjen.\n");
              }
            }catch(InputMismatchException e){
               System.out.println("\nUgyldig input! Prov igjen.");
               oppretterScanner.next();  //Pusher scanner forbi den ugyldige linja.
             }
        }



    if(oppretteValg==1){ //metode for aa opprette lege
        opprettNyLege();
    }
    else if(oppretteValg==2){ //metode for aa opprette legemiddel
        opprettNyttLegemiddel();


    }else if(oppretteValg==3){ //Gir bruker valg for aa opprette ulike typer resept, HVIS det finnes
                               //elementer av lege, legemiddel og pasient.
      Scanner typeReseptScanner = new Scanner(System.in);
      int typeReseptValg=0;
      if(nyttSystem.hentLegeListe().stoerrelse() == 0 || nyttSystem.hentLegemiddelListe().stoerrelse() == 0
        || nyttSystem.hentPasientListe().stoerrelse() == 0){
            System.out.println("\nKan ikke opprette resept fordi det mangler elementer.");
        }else{
            while(typeReseptValg<1||typeReseptValg>4){    //DELOPPGAVE E7
              System.out.println("\n Hvilken type resept?");
              System.out.printf("\n%-17s" + " Tast 1", "Hvit resept?");
              System.out.printf("\n%-17s" + " Tast 2", "Blaa resept?");
              System.out.printf("\n%-17s" + " Tast 3", "P-resept?");
              System.out.printf("\n%-17s" + " Tast 4\n>", "Militaer-resept?");
              try{
                  typeReseptValg = typeReseptScanner.nextInt();
                  if(typeReseptValg<1||typeReseptValg>4){
                    System.out.println("\n\nUgyldig input! Prov igjen\n");
                  }
              }catch(InputMismatchException e){
                  System.out.println("\nUgyldig input! Prov igjen.");
                  typeReseptScanner.next();
              }
            }
        }



      if(typeReseptValg==1){ //Metode for aa opprette hvitresept
          opprettNyHvitResept();
      }

      else if(typeReseptValg==2){ //Metode for aa opprette Blaaresept
          opprettNyBlaaResept();
      }

      else if(typeReseptValg==3){ //Metode for aa opprette P-resept
          opprettNyPResept();
      }

      else if(typeReseptValg==4){//Metode for aa opprette Militaer-resept
          opprettNyMilitaerResept();
      }


  }else if(oppretteValg==4){  //Metode for aa opprette pasient
        opprettNyPasient();
    }

} // Slutten paa metoden Opprett





  private static void opprettNyPasient(){  //Metode for aa opprette ny pasient.
      System.out.print("\nVennligst oppgi navnet paa pasienten du vil opprette.\n>");
      String pasientNavn = navnSjekk();
      System.out.print("\nVennligst oppgi fodselsnummeret paa pasienten.\n>");
      String pasientFnr = fnrSjekk();
      Pasient nyPasient = new Pasient(pasientNavn, pasientFnr);
      nyttSystem.hentPasientListe().leggTil(nyPasient);
  }



  private static void opprettNyHvitResept() throws UlovligUtskrift{  //Metode for aa opprette hvitresept.
      Lege aktuellLege = hentAktuellLege();     //Henter den aktuelle legen.
      Legemiddel aktueltLegemiddel = hentAktueltLegemiddel();   //Henter det aktuelle legemiddelet.
      if (autoriserLege(aktuellLege, aktueltLegemiddel)){  //Sjekker om legen kan skrive ut onsket legemiddel paa resept, for evt aa gaa videre.
          Pasient aktuellPasient = hentAktuellPasient();    //Henter den aktuelle pasienten.
          System.out.print("\nVennligst oppgi antall reit paa resepten\n>");
          int aktuellReit = intSjekk();
          HvitResept nyHvitResept = aktuellLege.skrivHvitResept(aktueltLegemiddel, aktuellPasient, aktuellReit); //For aa legge til i reseptListe
          nyttSystem.hentReseptListe().leggTil(nyHvitResept);  //Legger den nye resepten i reseptlista.
          aktuellPasient.leggTilResept(nyHvitResept);  //Registrer resepten paa pasienten det gjelder.
      }else{    //Printer en melding til bruker om legen ikke er godkjent til utskrift av oppgitte legemiddel.
          System.out.println("\nDenne legen er ikke godkjent til aa skrive ut narkotiske legemidler.");
      }
  }

  private static void opprettNyBlaaResept() throws UlovligUtskrift{  //Metode for aa opprette blaaresept.
      Lege aktuellLege = hentAktuellLege();     //Henter den aktuelle legen.
      Legemiddel aktueltLegemiddel = hentAktueltLegemiddel();     //Henter det aktuelle legemiddelet.
      if(autoriserLege(aktuellLege, aktueltLegemiddel)){    //Sjekker om legen kan skrive ut onsket legemiddel paa resept, for evt aa gaa videre.
          Pasient aktuellPasient = hentAktuellPasient();    //Henter den aktuelle pasienten.
          System.out.print("\nVennligst oppgi antall reit paa resepten\n>");
          int aktuellReit = intSjekk();
          BlaaResept nyBlaaResept = aktuellLege.skrivBlaaResept(aktueltLegemiddel, aktuellPasient, aktuellReit); //For aa legge til i reseptListe
          nyttSystem.hentReseptListe().leggTil(nyBlaaResept);  //Legger den nye resepten i reseptlista.
          aktuellPasient.leggTilResept(nyBlaaResept);  //Registrer resepten paa pasienten det gjelder.
      }else{     //Printer en melding til bruker om legen ikke er godkjent til utskrift av oppgitte legemiddel.
          System.out.println("\nDenne legen er ikke godkjent til aa skrive ut narkotiske legemidler.");
      }
  }

  private static void opprettNyMilitaerResept() throws UlovligUtskrift{  //Metode for aa opprette militaerresept.
      Lege aktuellLege = hentAktuellLege();     //Henter den aktuelle legen.
      Legemiddel aktueltLegemiddel = hentAktueltLegemiddel();   //Henter det akutelle legemiddelet.
      if(autoriserLege(aktuellLege, aktueltLegemiddel)){    //Sjekker om legen kan skrive ut onsket legemiddel paa resept, for evt aa gaa videre.
          Pasient aktuellPasient = hentAktuellPasient();    //Henter den aktuelle pasienten.
          System.out.print("\nVennligst oppgi antall reit paa resepten\n>");
          int aktuellReit = intSjekk();
          MilitaerResept nyMilitaerResept = aktuellLege.skrivMilitaerResept(aktueltLegemiddel, aktuellPasient, aktuellReit); //For aa legge til i reseptListe
          nyttSystem.hentReseptListe().leggTil(nyMilitaerResept);  //Legger den nye resepten i reseptlista.
          aktuellPasient.leggTilResept(nyMilitaerResept);  //Registrer resepten paa pasienten det gjelder.
      }else{     //Printer en melding til bruker om legen ikke er godkjent til utskrift av oppgitte legemiddel.
          System.out.println("\nDenne legen er ikke godkjent til aa skrive ut narkotiske legemidler.");
      }
  }

  private static void opprettNyPResept() throws UlovligUtskrift {  //Metode for aa opprette p-resept.
      Lege aktuellLege = hentAktuellLege();     //Henter den aktuelle legen.
      Legemiddel aktueltLegemiddel = hentAktueltLegemiddel();   //Henter det aktuelle legemiddelet.
      if(autoriserLege(aktuellLege, aktueltLegemiddel)){    //Sjekker om legen kan skrive ut onsket legemiddel paa resept, for evt aa gaa videre.
          Pasient aktuellPasient = hentAktuellPasient();    //Henter den aktuelle pasienten.
          PResept nyPResept = aktuellLege.skrivPResept(aktueltLegemiddel, aktuellPasient); //For aa legge til i reseptListe
          nyttSystem.hentReseptListe().leggTil(nyPResept);  //Legger den nye resepten i reseptlista.
          aktuellPasient.leggTilResept(nyPResept);  //Registrer resepten paa pasienten det gjelder.
      }else{     //Printer en melding til bruker om legen ikke er godkjent til utskrift av oppgitte legemiddel.
          System.out.println("\nDenne legen er ikke godkjent til aa skrive ut narkotiske legemidler.");
      }
  }



  private static Lege hentAktuellLege() throws UlovligUtskrift{  //Metode for aa hente lege til reseptutskrift.
      System.out.println("\nVennligst oppgi hvilken lege som skal opprette resepten");
      Lege aktuellLege = nyttSystem.hentLegeListe().hent(0);
      int listeTeller = 0;
      for(Lege lege : nyttSystem.hentLegeListe()){
          if(lege instanceof Spesialist){
              System.out.println(listeTeller + ": Navn: " + lege.hentNavn() + "(Spesialist)");

          }else{
                System.out.println(listeTeller + ": Navn: " + lege.hentNavn());
          }
          listeTeller += 1;
      }
      System.out.print(">");
      int valgtLege = intSjekk();
      boolean registrert = false;
          while(!registrert){
              if(valgtLege >= 0 && valgtLege < nyttSystem.hentLegeListe().stoerrelse()){
                  aktuellLege = nyttSystem.hentLegeListe().hent(valgtLege);
                  registrert = true;
              }else{
                System.out.println("\nVennligst skriv et tall mellom 0 og " + (nyttSystem.hentLegeListe().stoerrelse()-1));
                System.out.print(">");
                valgtLege = intSjekk();
              }
          }
      return aktuellLege;
  }

  private static Legemiddel hentAktueltLegemiddel() throws UlovligUtskrift{  //Metode for aa hente legemiddelet til reseptutskrift.
      System.out.println("\nVennligst oppgi hvilket legemiddel som skal paa resepten");
      int listeTeller = 0;
      Legemiddel aktueltLegemiddel = nyttSystem.hentLegemiddelListe().hent(0);
      for(Legemiddel middel : nyttSystem.hentLegemiddelListe()){
          System.out.println(listeTeller + ": Navn: " + middel.hentNavn());
          listeTeller += 1;
      }
      System.out.print(">");
      int valgtLegemiddel = intSjekk();
      boolean finsMiddelet = false;
      while (!finsMiddelet){
              if(valgtLegemiddel >= 0 && valgtLegemiddel < nyttSystem.hentLegemiddelListe().stoerrelse()){
                  aktueltLegemiddel = nyttSystem.hentLegemiddelListe().hent(valgtLegemiddel);
                  finsMiddelet = true;
              }else{
                  System.out.println("\nVennligst skriv et tall mellom 0 og " + (nyttSystem.hentLegemiddelListe().stoerrelse() -1));
                  System.out.print(">");
                  valgtLegemiddel = intSjekk();
              }
          }
      return aktueltLegemiddel;
  }

  private static Pasient hentAktuellPasient() throws UlovligUtskrift{  //Metode for aa hente pasient til reseptutskrift.
      System.out.println("\nVennligst oppgi id paa pasienten som skal motta resepten");
      int listeTeller = 0;
      Pasient aktuellPasient = nyttSystem.hentPasientListe().hent(0);
      for(Pasient pasient : nyttSystem.hentPasientListe()){
          System.out.println(listeTeller + ": Navn: " + pasient.hentNavn());
          listeTeller += 1;
      }
      System.out.print(">");
      int valgtPasient = intSjekk();
      boolean finsPasienten = false;
      while (!finsPasienten){
              if(valgtPasient >= 0 && valgtPasient < nyttSystem.hentPasientListe().stoerrelse()){
                  aktuellPasient = nyttSystem.hentPasientListe().hent(valgtPasient);
                  finsPasienten = true;
              }else{
                  System.out.println("\nVennligst skriv et tall mellom 0 og " + (nyttSystem.hentPasientListe().stoerrelse() -1));
                  System.out.print(">");
                  valgtPasient = intSjekk();
              }
          }
      return aktuellPasient;
  }

  private static boolean autoriserLege(Lege lege, Legemiddel legemiddel){  //Metode som gir tilbakemelding paa om legen kan skrive ut
                                                                           //onskede legemiddel paa resept.
      boolean autorisasjon = false;
      if (!(legemiddel instanceof Narkotisk)){
          autorisasjon = true;
      }else if (legemiddel instanceof Narkotisk && lege instanceof Spesialist){
          autorisasjon = true;
      }else if (legemiddel instanceof Narkotisk && (!(lege instanceof Spesialist))){
          autorisasjon = false;
      }
      return autorisasjon;
  }


  private static void brukResept(){ //Metode for innvalg 3 - aa bruke en resept.
      if(nyttSystem.hentReseptListe().stoerrelse() == 0){
          System.out.println("\nDet finnes ingen resepter aa bruke.");  //Skriver ut om det ikke er noen resepter registrert.
      }else{
          System.out.println("Hvilken pasient vil du se resepter for?");
          int listeTeller = 0;
          for(Pasient pasient : nyttSystem.hentPasientListe()){
              System.out.println(listeTeller + ": " + pasient.hentNavn() + " (fnr " + pasient.hentFnr() + ")");
              listeTeller += 1;
          }                     //Skriver ut alle registrerte pasienter.
          System.out.print(">");
          Pasient valgtPasient = velgPasient();
          if(valgtPasient.hentAlleResept().stoerrelse() == 0){  //Sjekker om pasienten har noen registrerte resepter.
              System.out.println("\nDen valgte pasienten har ingen registrerte resepter aa bruke.");  //Skriver ut om det ikke finnes noen resepter
                                                                                                     //registrert paa pasienten.
          }else{          //Fins det resepter aa bruke blir bruker presentert med disse.
              System.out.println("\nValgt pasient: " + valgtPasient.hentNavn() + " (fnr " + valgtPasient.hentFnr() + ")");
              System.out.println("Hvilken resept vil du bruke?");
              listeTeller = 0;
              for(Resept resept : valgtPasient.hentAlleResept()){
                  System.out.println(listeTeller + ": " + resept.hentLegemiddel() + " (" + resept.hentReit() + " reit)");
                  listeTeller += 1;
              }                     //Skriver ut alle reseptene.
              System.out.print(">");
              Resept valgtResept = velgResept(valgtPasient);  //Lar bruker velge resept.
              if(valgtResept.hentReit() == 0){          //Sjekker om det er gjenstaaende reit paa resepten.
                  System.out.println("Kunne ikke bruke valgte resept (ingen gjenvaerende reit).");
              }else{
                  valgtResept.bruk();
                  System.out.println("Brukte resept paa " + valgtResept.hentLegemiddel() + ". Antall gjenvaerende reit: "
                  + valgtResept.hentReit());
              }
          }
      }
  }

  // // METODE FOR aa SKRIVE DIV STATISTIKK:
  private static void skrivStatistikk() throws InputMismatchException, UlovligUtskrift{ //Metode for innvalg 4 - DELOPPGAVE E6
      Scanner typeStatistikkScanner = new Scanner(System.in);
      int typeStatistikkValg = 0;

      if(nyttSystem.hentLegeListe().stoerrelse() == 0 || nyttSystem.hentLegemiddelListe().stoerrelse() == 0
        || nyttSystem.hentPasientListe().stoerrelse() == 0 || nyttSystem.hentReseptListe().stoerrelse() == 0){
            System.out.println("\nKan ikke vise statistikk fordi det mangler elementer.");
        }
        else {
          while(typeStatistikkValg < 1 || typeStatistikkValg > 3){   //Lager en undermeny for aa velge hva slags statistikk man onsker aa se.
            System.out.println("\nHvilken type statistikk?");
            System.out.printf("%-80s"+"%s", "\nTotalt antall utskrevne resepter paa vanedannende legemidler?", "Tast 1");
            System.out.printf("%-80s"+"%s", "\nTotalt antall utskrevne resepter paa narkotiske legemidler?","Tast 2");
            System.out.printf("%-80s"+"%s", "\nStatistikk om mulig misbruk av narkotika?","Tast 3\n>");
            try{
                typeStatistikkValg = typeStatistikkScanner.nextInt();
                if(typeStatistikkValg < 1 || typeStatistikkValg > 3){
                  System.out.println("\n\nUgyldig input! Prov igjen\n");
                }
            }catch(InputMismatchException e){
                System.out.println("\nUgyldig input! Prov igjen.");
                typeStatistikkScanner.next();
            }
          }
       }
       if (typeStatistikkValg == 1){
           int antall = totaltVanedannendeResepter();
           System.out.println("\nAntall resepter med vanedannende legemidler: " + antall);
       }
       else if (typeStatistikkValg == 2){
           int antall = totaltNarkotiskeResepter();
           System.out.println("\nAntall resepter med narkotiske legemidler: " + antall);
       }
       else if (typeStatistikkValg == 3){
           System.out.println("\nLeger med registrerte narkotiske resepter, og antallet av disse.");
           for (Lege l : muligNarkotikaMisbrukLeger()){
               System.out.println(l + "Antall narkotiske resepter: " + antallNarkotiskeResepter(l));
           }
           System.out.println("\nPasienter med gyldig resept paa narkotiske legemidler, og antallet av disse.");
           for (Pasient p : muligNarkotikaMisbrukPasienter()){
               System.out.println(p + "Antall narkotiske resepter: " + antallNarkotiskeResepter(p));
           }
       }
  }

  private static int totaltVanedannendeResepter(){
      int teller = 0;
      for (Resept r : nyttSystem.hentReseptListe()){
          if(r.hentLegemiddel() instanceof Vanedannende){
              teller++;
          }
      }
      return teller;
   }

   private static int totaltNarkotiskeResepter(){
       int teller = 0;
       for (Resept r : nyttSystem.hentReseptListe()){
           if(r.hentLegemiddel() instanceof Narkotisk){
               teller++;
           }
       }
       return teller;
  }

   private static Lenkeliste<Lege> muligNarkotikaMisbrukLeger(){
       Lenkeliste<Lege> legerMedNarkotisk = new SortertLenkeliste<>();
       for (Resept r : nyttSystem.hentReseptListe()){
           if (r.hentLegemiddel() instanceof Narkotisk){
               legerMedNarkotisk.leggTil(r.hentLege());
           }
       }
       return legerMedNarkotisk;
  }

  private static Lenkeliste<Pasient> muligNarkotikaMisbrukPasienter(){
      Lenkeliste<Pasient> pasienterMedNarkotisk = new Lenkeliste<>();
      for (Resept r : nyttSystem.hentReseptListe()){
          if (r.hentLegemiddel() instanceof Narkotisk){
              pasienterMedNarkotisk.leggTil(r.hentPasient());
          }
      }
      return pasienterMedNarkotisk;
  }


  private static int antallNarkotiskeResepter(Lege lege){
      int teller = 0;
      for(Resept r : lege.hentReseptListe()){
          if(r.hentLegemiddel() instanceof Narkotisk){
              teller ++;
          }
      }
      return teller;
  }

  private static int antallNarkotiskeResepter(Pasient pasient){
      int teller = 0;
      for(Resept r : pasient.hentAlleResept()){
          if(r.hentLegemiddel() instanceof Narkotisk){
              teller ++;
          }
      }
      return teller;
  }

  // METODE FOR aa SKRIVE TIL FIL:
  private static void skrivTilFil(){;  //Metode for innvalg 5 - DELOPPGAVE E8

  }

  static private int hovedmeny() throws InputMismatchException{
    int status = 0;
    int input1 = 0;
    while(status != 1){
      Scanner inputScanner1 = new Scanner(System.in);
      System.out.printf("%s"+"%-80s"+"%s", "\nHva onsker du aa gjore?", "\nSkrive ut fullstendig oversikt over pasienter, leger, legemidler og resepter?", "Tast 1");
      System.out.printf("%-80s"+"%s", "\nOpprette og legge til nye elementer i systemet?", "Tast 2");
      System.out.printf("%-80s"+"%s", "\nBruke en gitt resept fra listen til en pasient?","Tast 3");
      System.out.printf("%-80s"+"%s", "\nSkrive ut forskjellige former for statistikk?","Tast 4");
      System.out.printf("%-80s"+"%s", "\nSkrive alle data til fil?","Tast 5");
      System.out.printf("%-80s"+"%s", "\nAvslutt","Tast 6\n>");
      try{  //Prover lese brukers input.
          input1 = inputScanner1.nextInt();
          if (input1>0 && input1<7){
            return input1;
          }else{
            System.out.println("\nUgyldig Input! Prov igjen\n");
          }
      }catch(InputMismatchException e){  //Kaster exception om bruker skriver noe annet enn int.
          System.out.println("\nUgyldig Input! Prov igjen\n");
      }
    }
    return 0;
  }

  private static Pasient velgPasient(){  //Metode for aa velge pasient til reseptbruk.
      int pasientId = intSjekk();
      Pasient valgtPasient = nyttSystem.hentPasientListe().hent(0);
      int listeStoerrelse = nyttSystem.hentPasientListe().stoerrelse() - 1;
      boolean innenforListeIndex = false;
      while(!innenforListeIndex){
          try{
              valgtPasient = nyttSystem.hentPasientListe().hent(pasientId);
              innenforListeIndex = true;
          }catch(UgyldigListeIndeks e){
          System.out.println("Oppgitt pasientID er ikke i pasientlista. Vennligst oppgi et tall mellom 0 og "
          + listeStoerrelse);
          System.out.print(">");
          pasientId = intSjekk();
          }
      }
      return valgtPasient;
  }

  private static Resept velgResept(Pasient valgtPasient){  //Metode for aa velge resept til reseptbruk.
      int reseptNr = intSjekk();
      Resept valgtResept = valgtPasient.hentAlleResept().hent(0);
      int listeStoerrelse = valgtPasient.hentAlleResept().stoerrelse()-1;
      boolean innenforListeIndex = false;
      while(!innenforListeIndex){
          try{
              valgtResept = valgtPasient.hentAlleResept().hent(reseptNr);
              innenforListeIndex = true;
          }catch(UgyldigListeIndeks e){
              System.out.println("Oppgitt reseptnr er ikke innenfor reseptlisteindeksen. Vennligst oppgi et tall mellom 0 og "
              + listeStoerrelse);
              System.out.print(">");
              reseptNr = intSjekk();
          }
      }
      return valgtResept;
  }



  private static void opprettNyttLegemiddel(){  //Metode for aa legge til legemiddel.
      System.out.print("\nVennligst skriv hvilken type legemiddel du vil opprette.\n>");
      String type = navnSjekk();  //Sjekker om bruker oppgir gyldig input.
      int styrke = 0;
      boolean finsTypen = typeLegemiddelSjekk(type);  //Sjekker at typen oppgitt er en legemiddeltype.
      while(finsTypen != true){
          System.out.print("\nUgyldig type oppgitt! Typene kan vaere vanedannende, vanlig eller narkotisk.\n");
          System.out.print("\nVennligst skriv hvilken type legemiddel du vil opprette.\n>");
          type = navnSjekk();
          finsTypen = typeLegemiddelSjekk(type);  //Sjekker brukers nye input.
      }
      if(type.equals("vanlig")){  //Hopper over sporsmaal om styrke om typen er vanlig.

      }else{  //Sjekker styrken til de to andre typene.
          System.out.print("\nHva er legemiddelets styrke?\n>");
          styrke = intSjekk();
      }

      System.out.print("\nVennligst skriv navnet paa legemiddelet du vil opprette.\n>");
      String navn = navnSjekk();  //Ber om og sjekker at navnet er gyldig input.

      System.out.print("\nVennligst skriv prisen paa legemiddelet.\n>");
      double pris = doubleSjekk();  //Ber om og sjekker at prisen er gyldig input.

      System.out.print("\nVennligst oppgi mengde virkestoff til legemiddelet.\n>");
      double virkestoff = doubleSjekk();  //Ber om og sjekker at virkestoff er gyldig input.


      if(styrke == 0){  //Sjekker type legemiddel og oppretter det riktige.
          Legemiddel nyttLegemiddel = new Vanlig(navn, pris, virkestoff);  //Oppretter vanlig om styrke = 0.
          nyttSystem.hentLegemiddelListe().leggTil(nyttLegemiddel);
          System.out.println("Opprettet nytt vanlig legemiddel.");  //Kontrollutskrift, FJERN FoR LEVERING!
      }
      else if(type.equals("narkotisk")){  //Sjekker om oppgitt type er narkotisk.
          Legemiddel nyttLegemiddel = new Narkotisk(navn, pris, virkestoff, styrke);
          nyttSystem.hentLegemiddelListe().leggTil(nyttLegemiddel);
          System.out.println("Opprettet nytt narkotisk legemiddel");    //Kontrollutskrift, FJERN FoR LEVERING!
      }
      else if(type.equals("vanedannende")){  //Sjekker om oppgitt type er vanedannende.
          Legemiddel nyttLegemiddel = new Vanedannende(navn, pris, virkestoff, styrke);
          nyttSystem.hentLegemiddelListe().leggTil(nyttLegemiddel);
          System.out.println("Opprettet nytt vanedannende legemiddel");  //Kontrollutskrift, FJERN FoR LEVERING!
      }
  }


  private static void opprettNyLege(){  //Metode for aa opprette ny lege.
      System.out.print("\nVennligst skriv navn paa legen du vil opprette.\n>");
      String nyLegeNavn = navnSjekk();  //Sjekker om brukers input er gyldig.

      System.out.print("\nVennligst oppgi legens kontrollID.\n>");
      int kontrollId = intSjekk();  //Sjekker om brukers input er gyldig.

      if(kontrollId != 0){  //Sjekker om spesialist eller vanlig lege skal opprettes.
          Spesialist nySpesialist = new Spesialist(nyLegeNavn, kontrollId);
          nyttSystem.hentLegeListe().leggTil(nySpesialist);
      }else{
          Lege nyLege = new Lege(nyLegeNavn);
          nyttSystem.hentLegeListe().leggTil(nyLege);
      }
  }

  private static boolean typeLegemiddelSjekk(String type){  //Metode for aa sjekke at bruker
                                                            //oppgir en av tre typer legemiddel.
      if (type.equals("narkotisk")){
          return true;
      }else if(type.equals("vanedannende")){
          return true;
      }else if (type.equals("vanlig")){
          return true;
      }
      return false;  //Om bruker ikke har oppgitt en gyldig type returnes false.
  }

  private static String navnSjekk(){  //Metode for aa sjekke brukers input hvor det blir spurt om navn/tekst.
      Scanner nyttNavnScanner = new Scanner(System.in);
      String navn = nyttNavnScanner.nextLine();
      boolean sjekk = false;
      while(!sjekk){
          try{
              int ikkeFunk = Integer.parseInt(navn);  //Faar vi tall som input gir vi feilmelding.
              System.out.print("\nUgyldig input! Forventet er et svar bestaaende av tekst.\n>");
              navn = nyttNavnScanner.next();
          }catch(Exception e){
              sjekk = true;
          }
      }
      return navn;   //Returnerer det lovlige navnet
  }

  private static String fnrSjekk(){  //Metode for aa sjekke brukers input hvor det blir spurt om navn/tekst.
      Scanner nyttFnrScanner = new Scanner(System.in);
      String fnr = nyttFnrScanner.next();
      boolean sjekk = false;
      while(!sjekk){
          try{
              Long funk = Long.parseLong(fnr);  //Faar vi tall som input er alt som det skal!
              sjekk = true;
          }catch(Exception e){
              System.out.print("\nUgyldig input! Forventet er et svar bestaaende av tall.\n>");
              fnr = nyttFnrScanner.next();
          }
      }
      return fnr;   //Returnerer det lovlige fodselsnummeret
  }

  private static double doubleSjekk(){  //Metode for aa sjekke brukers double-inputs.
      Scanner doubleScanner = new Scanner(System.in);
      boolean sjekk = true;
      double doubleTall = 0;
      while(sjekk){
          try{
              double nyDouble = doubleScanner.nextDouble();
              doubleTall = nyDouble;
              sjekk = false;
          }catch(InputMismatchException e){
              System.out.print("\nUgyldig input! Forventet er et svar bestaaende av et tall.\n>");
              doubleScanner.next();  //Pusher scanner til aa be om ny input.
              }
      }
      return doubleTall;   //Returnerer den gyldige inputen.
  }

  private static int intSjekk(){   //Metode for aa sjekke brukers int-inputs.
      Scanner intScanner = new Scanner(System.in);
      boolean sjekk = true;
      int intTall = 0;
      while(sjekk){
          try{
              int nyttTall = intScanner.nextInt();
              intTall = nyttTall;
              sjekk = false;
          }catch(InputMismatchException e){
              System.out.print("\nUgyldig input! Forventet er et svar bestaaende av et heltall.\n>");
              intScanner.next();  //Pusher scanner til aa be om ny input.
              }
      }
      return intTall;  //Returnerer den gyldige inputen.
  }
}
