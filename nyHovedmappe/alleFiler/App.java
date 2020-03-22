import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.FileNotFoundException;

class App{
  static Legesystem nyttSystem = new Legesystem();  //Oppretter et legesystem for bruk i appen.
  public static void main(String[] args) throws InputMismatchException, UlovligUtskrift{
    int menyValg = 0;
    while(menyValg!=6){                    //Innvalg 6 er Exit, så programmet kjører til 6 blir valgt i hovedmenyen.
      int valg = hovedmeny();              //Metoden hovedmeny ligger under, og tar seg av utskrift til, og input fra, bruker.
      if (valg==1){
        skrivUtAlt();  //kjør Metode for innvalg 1 Rimelig ferdig, mangler pynt!
      }else if (valg==2){
        opprett();     //kjør Metode for innvalg 2 Rimelig ferdig, sikkert mulig med litt pynt.
      }else if (valg==3){
        brukResept();  //kjør Metode for innvalg 3 IKKE FERDIG
      }else if (valg==4){
        skrivStatistikk();//kjør Metode for innvalg 4 IKKE FERDIG
      }else if (valg==5){
        skrivTilFil();//kjør metode for innvalg 5 IKKE FERDIG
      }
      menyValg=valg;
    }
  }

  private static void filLesing(String filnavn) throws FileNotFoundException, UlovligUtskrift{
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
        System.out.println("\nHva ønsker du å opprette? ");
        System.out.printf("%-12s" + "Tast 1","Lege?");
        System.out.printf("\n%-12s" + "Tast 2","Legemiddel?");
        System.out.printf("\n%-12s" + "Tast 3","Resept? ");
        System.out.printf("\n%-12s" + "Tast 4\n>","Pasient? ");
        try{  //Prøver om bruker oppgir gyldig input.
            oppretteValg = oppretterScanner.nextInt();  //Sjekker etter int fra bruker.
            if(oppretteValg<1||oppretteValg>4){
               System.out.println("\nUgyldig input! Prøv igjen\n");
              }
            }catch(InputMismatchException e){
               System.out.println("\nUgyldig input! Prøv igjen.");
               oppretterScanner.next();  //Pusher scanner forbi den ugyldige linja.
             }
        }



    if(oppretteValg==1){ //metode for å opprette lege
        opprettNyLege();
    }
    else if(oppretteValg==2){ //metode for å opprette legemiddel
        opprettNyttLegemiddel();


    }else if(oppretteValg==3){ //Gir bruker valg for å opprette ulike typer resept, HVIS det finnes
                               //elementer av lege, legemiddel og pasient.
      Scanner typeReseptScanner = new Scanner(System.in);
      int typeReseptValg=0;
      if(nyttSystem.hentLegeListe().stoerrelse() == 0 || nyttSystem.hentLegeListe().stoerrelse() == 0
        || nyttSystem.hentPasientListe().stoerrelse() == 0){
            System.out.println("\nKan ikke opprette resept fordi det mangler elementer.");
        }else{
            while(typeReseptValg<1||typeReseptValg>4){    //DELOPPGAVE E7
              System.out.println("\n Hvilken type resept?");
              System.out.printf("\n%-17s" + " Tast 1", "Hvit resept?");
              System.out.printf("\n%-17s" + " Tast 2", "Blå resept?");
              System.out.printf("\n%-17s" + " Tast 3", "P-resept?");
              System.out.printf("\n%-17s" + " Tast 4\n>", "Militær-resept?");
              try{
                  typeReseptValg = typeReseptScanner.nextInt();
                  if(typeReseptValg<1||typeReseptValg>4){
                    System.out.println("\n\nUgyldig input! Prøv igjen\n");
                  }
              }catch(InputMismatchException e){
                  System.out.println("\nUgyldig input! Prøv igjen.");
                  typeReseptScanner.next();
              }
            }
        }



      if(typeReseptValg==1){ //Metode for å opprette hvitresept
          opprettNyHvitResept();
      }

      else if(typeReseptValg==2){ //Metode for å opprette Blåresept
          opprettNyBlaaResept();
      }

      else if(typeReseptValg==3){ //Metode for å opprette P-resept
          opprettNyPResept();
      }

      else if(typeReseptValg==4){//Metode for å opprette Militær-resept
          opprettNyMilitaerResept();
      }


  }else if(oppretteValg==4){  //Metode for å opprette pasient
        opprettNyPasient();
    }

} // Slutten på metoden Opprett





  private static void opprettNyPasient(){  //Metode for å opprette ny pasient.
      System.out.print("\nVennligst oppgi navnet på pasienten du vil opprette.\n>");
      String pasientNavn = navnSjekk();
      System.out.print("\nVennligst oppgi fødselsnummeret på pasienten.\n>");
      String pasientFnr = fnrSjekk();
      Pasient nyPasient = new Pasient(pasientNavn, pasientFnr);
      nyttSystem.hentPasientListe().leggTil(nyPasient);
  }

  private static void opprettNyHvitResept() throws UlovligUtskrift{  //Metode for å opprette hvitresept.
      Lege aktuellLege = hentAktuellLege();
      Legemiddel aktueltLegemiddel = hentAktueltLegemiddel();
      Pasient aktuellPasient = hentAktuellPasient();
      System.out.print("\nVennligst oppgi antall reit på resepten\n>");
      int aktuellReit = intSjekk();
      HvitResept nyHvitResept = aktuellLege.skrivHvitResept(aktueltLegemiddel, aktuellPasient, aktuellReit); //For å legge til i reseptListe
      nyttSystem.hentReseptListe().leggTil(nyHvitResept);  //Legger den nye resepten i reseptlista.
      aktuellPasient.leggTilResept(nyHvitResept);  //Registrer resepten på pasienten det gjelder.
  }

  private static void opprettNyBlaaResept() throws UlovligUtskrift{  //Metode for å opprette blaaresept.
      Lege aktuellLege = hentAktuellLege();
      Legemiddel aktueltLegemiddel = hentAktueltLegemiddel();
      Pasient aktuellPasient = hentAktuellPasient();
      System.out.print("\nVennligst oppgi antall reit på resepten\n>");
      int aktuellReit = intSjekk();
      BlaaResept nyBlaaResept = aktuellLege.skrivBlaaResept(aktueltLegemiddel, aktuellPasient, aktuellReit); //For å legge til i reseptListe
      nyttSystem.hentReseptListe().leggTil(nyBlaaResept);  //Legger den nye resepten i reseptlista.
      aktuellPasient.leggTilResept(nyBlaaResept);  //Registrer resepten på pasienten det gjelder.
  }

  private static void opprettNyMilitaerResept() throws UlovligUtskrift{  //Metode for å opprette militaerresept.
      Lege aktuellLege = hentAktuellLege();
      Legemiddel aktueltLegemiddel = hentAktueltLegemiddel();
      Pasient aktuellPasient = hentAktuellPasient();
      System.out.print("\nVennligst oppgi antall reit på resepten\n>");
      int aktuellReit = intSjekk();
      MilitaerResept nyMilitaerResept = aktuellLege.skrivMilitaerResept(aktueltLegemiddel, aktuellPasient, aktuellReit); //For å legge til i reseptListe
      nyttSystem.hentReseptListe().leggTil(nyMilitaerResept);  //Legger den nye resepten i reseptlista.
      aktuellPasient.leggTilResept(nyMilitaerResept);  //Registrer resepten på pasienten det gjelder.
  }

  private static void opprettNyPResept() throws UlovligUtskrift {  //Metode for å opprette p-resept.
      Lege aktuellLege = hentAktuellLege();
      Legemiddel aktueltLegemiddel = hentAktueltLegemiddel();
      Pasient aktuellPasient = hentAktuellPasient();
      PResept nyPResept = aktuellLege.skrivPResept(aktueltLegemiddel, aktuellPasient); //For å legge til i reseptListe
      nyttSystem.hentReseptListe().leggTil(nyPResept);  //Legger den nye resepten i reseptlista.
      aktuellPasient.leggTilResept(nyPResept);  //Registrer resepten på pasienten det gjelder.
  }

  private static Lege hentAktuellLege() throws UlovligUtskrift{  //Metode for å hente lege til reseptutskrift.
      System.out.print("\nVennligst oppgi navnet på legen som skal opprette resepten\n>");
      String valgtLege = navnSjekk();
      Lege aktuellLege = nyttSystem.hentLegeListe().hent(0);
      boolean finsLegen = false;
      while (!finsLegen){
          for(Lege lege : nyttSystem.hentLegeListe()){
              if(lege.hentNavn().equals(valgtLege)){
                  finsLegen = true;
                  aktuellLege = lege;
              }
          }
          if(!finsLegen){
              System.out.print("\nLegenavnet du oppga er ikke registrert. Vennligst prøv igjen.\n>");
              valgtLege = navnSjekk();
          }
      }
      return aktuellLege;
  }

  private static Legemiddel hentAktueltLegemiddel() throws UlovligUtskrift{  //Metode for å hente legemiddelet til reseptutskrift.
      System.out.print("\nVennligst oppgi navnet på legemiddelet som skal på resepten\n>");
      String valgtLegemiddel = navnSjekk();
      Legemiddel aktueltLegemiddel = nyttSystem.hentLegemiddelListe().hent(0);
      boolean finsMiddelet = false;
      while (!finsMiddelet){
          for(Legemiddel legemiddel : nyttSystem.hentLegemiddelListe()){
              if(legemiddel.hentNavn().equals(valgtLegemiddel)){
                  finsMiddelet = true;
                  aktueltLegemiddel = legemiddel;
              }
          }
          if(!finsMiddelet){
              System.out.print("\nLegemiddelet du oppga er ikke registrert. Vennligst prøv igjen.\n>");
              valgtLegemiddel = navnSjekk();
          }
      }
      return aktueltLegemiddel;
  }

  private static Pasient hentAktuellPasient() throws UlovligUtskrift{  //Metode for å hente pasient til reseptutskrift.
      System.out.print("\nVennligst oppgi id på pasienten som skal motta resepten\n>");
      int valgtPasient = intSjekk();
      Pasient aktuellPasient = nyttSystem.hentPasientListe().hent(0);
      boolean finsPasienten = false;
      while (!finsPasienten){
          for(Pasient pasient : nyttSystem.hentPasientListe()){
              if(pasient.hentId() == valgtPasient){
                  finsPasienten = true;
                  aktuellPasient = pasient;
              }
          }
          if(!finsPasienten){
              System.out.print("\nPasienten du oppga er ikke registrert. Vennligst prøv igjen.\n>");
              valgtPasient = intSjekk();
          }
      }
      return aktuellPasient;
  }


  private static void brukResept(){ //Metode for innvalg 3 - Å bruke en resept.
      if(nyttSystem.hentReseptListe().stoerrelse() == 0){
          System.out.println("\nDet finnes ingen resepter å bruke.");  //Skriver ut om det ikke er noen resepter registrert.
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
              System.out.println("\nDen valgte pasienten har ingen registrerte resepter å bruke.");  //Skriver ut om det ikke finnes noen resepter
                                                                                                     //registrert på pasienten.
          }else{          //Fins det resepter å bruke blir bruker presentert med disse.
              System.out.println("\nValgt pasient: " + valgtPasient.hentNavn() + " (fnr " + valgtPasient.hentFnr() + ")");
              System.out.println("Hvilken resept vil du bruke?");
              listeTeller = 0;
              for(Resept resept : valgtPasient.hentAlleResept()){
                  System.out.println(listeTeller + ": " + resept.hentLegemiddel() + " (" + resept.hentReit() + " reit)");
                  listeTeller += 1;
              }                     //Skriver ut alle reseptene.
              System.out.print(">");
              Resept valgtResept = velgResept(valgtPasient);  //Lar bruker velge resept.
              if(valgtResept.hentReit() == 0){          //Sjekker om det er gjenstående reit på resepten.
                  System.out.println("Kunne ikke bruke valgte resept (ingen gjenværende reit).");
              }else{
                  valgtResept.bruk();
                  System.out.println("Brukte resept på " + valgtResept.hentLegemiddel() + ". Antall gjenværende reit: "
                  + valgtResept.hentReit());
              }
          }
      }
  }

  // METODE FOR Å SKRIVE DIV STATISTIKK:
  private static void skrivStatistikk(){; //Metode for innvalg 4 - DELOPPGAVE E6

  }
  // METODE FOR Å SKRIVE TIL FIL:
  private static void skrivTilFil(){;  //Metode for innvalg 5 - DELOPPGAVE E8

  }

  static private int hovedmeny() throws InputMismatchException{
    int status = 0;
    int input1 = 0;
    while(status != 1){
      Scanner inputScanner1 = new Scanner(System.in);
      System.out.printf("%s"+"%-80s"+"%s", "\nHva ønsker du å gjøre?", "\nSkrive ut fullstendig oversikt over pasienter, leger, legemidler og resepter?", "Tast 1");
      System.out.printf("%-80s"+"%s", "\nOpprette og legge til nye elementer i systemet?", "Tast 2");
      System.out.printf("%-80s"+"%s", "\nBruke en gitt resept fra listen til en pasient?","Tast 3");
      System.out.printf("%-80s"+"%s", "\nSkrive ut forskjellige former for statistikk?","Tast 4");
      System.out.printf("%-80s"+"%s", "\nSkrive alle data til fil?","Tast 5");
      System.out.printf("%-80s"+"%s", "\nAvslutt","Tast 6\n>");
      try{  //Prøver lese brukers input.
          input1 = inputScanner1.nextInt();
          if (input1>0 && input1<7){
            return input1;
          }else{
            System.out.println("\nUgyldig Input! Prøv igjen\n");
          }
      }catch(InputMismatchException e){  //Kaster exception om bruker skriver noe annet enn int.
          System.out.println("\nUgyldig Input! Prøv igjen\n");
      }
    }
    return 0;
  }

  private static Pasient velgPasient(){  //Metode for å velge pasient til reseptbruk.
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

  private static Resept velgResept(Pasient valgtPasient){  //Metode for å velge resept til reseptbruk.
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

  private static void opprettNyttLegemiddel(){  //Metode for å legge til legemiddel.
      System.out.print("\nVennligst skriv hvilken type legemiddel du vil opprette.\n>");
      String type = navnSjekk();  //Sjekker om bruker oppgir gyldig input.
      int styrke = 0;
      boolean finsTypen = typeLegemiddelSjekk(type);  //Sjekker at typen oppgitt er en legemiddeltype.
      while(finsTypen != true){
          System.out.print("\nUgyldig type oppgitt! Typene kan være vanedannende, vanlig eller narkotisk.\n");
          System.out.print("\nVennligst skriv hvilken type legemiddel du vil opprette.\n>");
          type = navnSjekk();
          finsTypen = typeLegemiddelSjekk(type);  //Sjekker brukers nye input.
      }
      if(type.equals("vanlig")){  //Hopper over spørsmål om styrke om typen er vanlig.

      }else{  //Sjekker styrken til de to andre typene.
          System.out.print("\nHva er legemiddelets styrke?\n>");
          styrke = intSjekk();
      }

      System.out.print("\nVennligst skriv navnet på legemiddelet du vil opprette.\n>");
      String navn = navnSjekk();  //Ber om og sjekker at navnet er gyldig input.

      System.out.print("\nVennligst skriv prisen på legemiddelet.\n>");
      double pris = doubleSjekk();  //Ber om og sjekker at prisen er gyldig input.

      System.out.print("\nVennligst oppgi mengde virkestoff til legemiddelet.\n>");
      double virkestoff = doubleSjekk();  //Ber om og sjekker at virkestoff er gyldig input.


      if(styrke == 0){  //Sjekker type legemiddel og oppretter det riktige.
          Legemiddel nyttLegemiddel = new Vanlig(navn, pris, virkestoff);  //Oppretter vanlig om styrke = 0.
          nyttSystem.hentLegemiddelListe().leggTil(nyttLegemiddel);
          System.out.println("Opprettet nytt vanlig legemiddel.");  //Kontrollutskrift, FJERN FØR LEVERING!
      }
      else if(type.equals("narkotisk")){  //Sjekker om oppgitt type er narkotisk.
          Legemiddel nyttLegemiddel = new Narkotisk(navn, pris, virkestoff, styrke);
          nyttSystem.hentLegemiddelListe().leggTil(nyttLegemiddel);
          System.out.println("Opprettet nytt narkotisk legemiddel");    //Kontrollutskrift, FJERN FØR LEVERING!
      }
      else if(type.equals("vanedannende")){  //Sjekker om oppgitt type er vanedannende.
          Legemiddel nyttLegemiddel = new Vanedannende(navn, pris, virkestoff, styrke);
          nyttSystem.hentLegemiddelListe().leggTil(nyttLegemiddel);
          System.out.println("Opprettet nytt vanedannende legemiddel");  //Kontrollutskrift, FJERN FØR LEVERING!
      }
  }


  private static void opprettNyLege(){  //Metode for å opprette ny lege.
      System.out.print("\nVennligst skriv navn på legen du vil opprette.\n>");
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

  private static boolean typeLegemiddelSjekk(String type){  //Metode for å sjekke at bruker
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

  private static String navnSjekk(){  //Metode for å sjekke brukers input hvor det blir spurt om navn/tekst.
      Scanner nyttNavnScanner = new Scanner(System.in);
      String navn = nyttNavnScanner.next();
      boolean sjekk = false;
      while(!sjekk){
          try{
              int ikkeFunk = Integer.parseInt(navn);  //Får vi tall som input gir vi feilmelding.
              System.out.print("\nUgyldig input! Forventet er et svar bestående av tekst.\n>");
              navn = nyttNavnScanner.next();
          }catch(Exception e){
              sjekk = true;
          }
      }
      return navn;   //Returnerer det lovlige navnet
  }

  private static String fnrSjekk(){  //Metode for å sjekke brukers input hvor det blir spurt om navn/tekst.
      Scanner nyttFnrScanner = new Scanner(System.in);
      String fnr = nyttFnrScanner.next();
      boolean sjekk = false;
      while(!sjekk){
          try{
              Long funk = Long.parseLong(fnr);  //Får vi tall som input er alt som det skal!
              sjekk = true;
          }catch(Exception e){
              System.out.print("\nUgyldig input! Forventet er et svar bestående av tall.\n>");
              fnr = nyttFnrScanner.next();
          }
      }
      return fnr;   //Returnerer det lovlige fødselsnummeret
  }

  private static double doubleSjekk(){  //Metode for å sjekke brukers double-inputs.
      Scanner doubleScanner = new Scanner(System.in);
      boolean sjekk = true;
      double doubleTall = 0;
      while(sjekk){
          try{
              double nyDouble = doubleScanner.nextDouble();
              doubleTall = nyDouble;
              sjekk = false;
          }catch(InputMismatchException e){
              System.out.print("\nUgyldig input! Forventet er et svar bestående av et tall.\n>");
              doubleScanner.next();  //Pusher scanner til å be om ny input.
              }
      }
      return doubleTall;   //Returnerer den gyldige inputen.
  }

  private static int intSjekk(){   //Metode for å sjekke brukers int-inputs.
      Scanner intScanner = new Scanner(System.in);
      boolean sjekk = true;
      int intTall = 0;
      while(sjekk){
          try{
              int nyttTall = intScanner.nextInt();
              intTall = nyttTall;
              sjekk = false;
          }catch(InputMismatchException e){
              System.out.print("\nUgyldig input! Forventet er et svar bestående av et heltall.\n>");
              intScanner.next();  //Pusher scanner til å be om ny input.
              }
      }
      return intTall;  //Returnerer den gyldige inputen.
  }
}
