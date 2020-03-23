import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;   //Importerer nødvendige bibliotek for bruk i koden.

class Legesystem{
    String filnavn;
    Lenkeliste<Pasient> pasientListe = new Lenkeliste<Pasient>();
    Lenkeliste<Legemiddel> legemiddelListe = new Lenkeliste<Legemiddel>();
    Lenkeliste<Resept> reseptListe = new Lenkeliste<Resept>();
    Lenkeliste<Lege> legeListe = new SortertLenkeliste<Lege>();  //Oppretter nødvendige lister for bruk i systemet.

    public Legesystem(){;}

    public Lenkeliste<Pasient> hentPasientListe(){
        return pasientListe;
    }
    public Lenkeliste<Legemiddel> hentLegemiddelListe(){
        return legemiddelListe;
    }
    public Lenkeliste<Resept> hentReseptListe(){
        return reseptListe;
    }
    public Lenkeliste<Lege> hentLegeListe(){  //Metoder for å hente de ulike listene.
        return legeListe;
    }

    public void lesFil(String filnavn) throws FileNotFoundException, UlovligUtskrift{
        this.filnavn = filnavn;
        File fil = new File(filnavn);
        Scanner seksjonScanner = new Scanner(fil).useDelimiter("\\s*#\\s*");
        String seksjon1pasienter = seksjonScanner.next();
        String seksjon2legemidler = seksjonScanner.next();
        String seksjon3leger = seksjonScanner.next();
        String seksjon4resepter = seksjonScanner.next();  //Deler dokumentet i fire deler, delt på "#".

        Scanner scan1 = new Scanner(seksjon1pasienter);  //Behandler første seksjon, pasienter.
        scan1.nextLine();
        while(scan1.hasNextLine()){
          String linje = scan1.nextLine();
          Scanner linjeScanner = new Scanner(linje).useDelimiter("\\s*,\\s*");
          ArrayList<String> linjeOrd = new ArrayList<String>(); //Deler linjene på "," og legger i ArrayList.
          while(linjeScanner.hasNext()){
            linjeOrd.add(linjeScanner.next()); // Legger navn på plass 0, fodselsnummer på plass 1 i ArrayLista
          }
          Pasient nyPasient = new Pasient(linjeOrd.get(0), linjeOrd.get(1));
          pasientListe.leggTil(nyPasient);  //Oppretter ny pasient og legger i lista over pasienter.
        }

        Scanner scan2 = new Scanner(seksjon2legemidler);  //Behandler seksjon 2, legemidler.
        scan2.nextLine();
        while(scan2.hasNextLine()){
          String linje = scan2.nextLine();
          Scanner linjeScanner = new Scanner(linje).useDelimiter("\\s*,\\s*");
          ArrayList<String> linjeOrd = new ArrayList<String>();  //Deler linjene på "," og legger i ArrayList.

          while(linjeScanner.hasNext()){
            linjeOrd.add(linjeScanner.next()); //Finner navn på plass 0, type på plass 1, pris på plass 2,
                                               //virkestoff på plass 3 og evt styrke på plass 4.
          }

          if (linjeOrd.size()>5){
                //Ignorerer ugyldige linjer i seksjonen.
          }else{
              try{
                  Double pris = Double.parseDouble(linjeOrd.get(2));
                  Double virkestoff = Double.parseDouble(linjeOrd.get(3));
                  String navn = linjeOrd.get(0);  //Lagrer de tre nødvendige tingene for alle legemidler.

                  if(linjeOrd.get(1).equals("vanlig")){  //Sjekker typen legemiddel.
                      Legemiddel nyttLegemiddel = new Vanlig(navn, pris, virkestoff);
                      legemiddelListe.leggTil(nyttLegemiddel);
                  }
                  else if(linjeOrd.get(1).equals("narkotisk")){ //Sjekker typen legemiddel.
                      int styrke = Integer.parseInt(linjeOrd.get(4));
                      Legemiddel nyttLegemiddel = new Narkotisk(navn, pris, virkestoff, styrke);
                      legemiddelListe.leggTil(nyttLegemiddel);
                  }
                  else if(linjeOrd.get(1).equals("vanedannende")){  //Sjekker typen legemiddel.

                      int styrke = Integer.parseInt(linjeOrd.get(4));
                      Legemiddel nyttLegemiddel = new Vanedannende(navn, pris, virkestoff, styrke);
                      legemiddelListe.leggTil(nyttLegemiddel);
                  }
              }catch(Exception e){}  //Ignorer linja om den ikke er gyldig.
           }
        }

        Scanner scan3 = new Scanner(seksjon3leger);  //Behandler seksjon 3, leger.
        scan3.nextLine();
        while(scan3.hasNextLine()){
          String linje = scan3.nextLine();
          Scanner linjeScanner = new Scanner(linje).useDelimiter("\\s*,\\s*");
          ArrayList<String> linjeOrd = new ArrayList<String>();  //Deler linjene på "," og legger i ArrayList.

          while(linjeScanner.hasNext()){
            linjeOrd.add(linjeScanner.next()); //Finner navn på plass 0 og evt kontrollid på plass 1.
          }

          if (linjeOrd.size()>2){
              System.out.println("Ugyldig Linje");  //Luker bort ugyldig linjer.
          }else{
            try{
              String navn = linjeOrd.get(0);
              int kontrollid = Integer.parseInt(linjeOrd.get(1));  //Legger navn og kontrollid i variabler.
              if (kontrollid==0){
                Lege nyLege = new Lege(navn);
                legeListe.leggTil(nyLege);  //Oppretter og legger til lege i legelista om kontrollid = 0.
              }else{
                Lege nySpesialist = new Spesialist(navn, kontrollid);
                legeListe.leggTil(nySpesialist);  //Oppretter og legger til spesialist i legelisa om kontrollid != 0.
              }
          }catch(Exception e){  //Skriver en feilmelding ved funn av ugyldig linjer.
              System.out.println("Fant ugyldig linje, går videre.");
           }
          }
       }

       Scanner scan4 = new Scanner(seksjon4resepter); //Behandler seksjon 4, resepter.
       scan4.nextLine();
       while(scan4.hasNextLine()){
         String linje = scan4.nextLine();
         Scanner linjeScanner = new Scanner(linje).useDelimiter("\\s*,\\s*");
         ArrayList<String> linjeOrd = new ArrayList<String>();  //Deler på komma, og legger i ArrayList.

         int lm = 0;
         int l = 0;
         int p = 0;
         int r = 0;  //Lager variabler til bruk senere.

         while(linjeScanner.hasNext()){
           linjeOrd.add(linjeScanner.next()); //Finner legemiddelnr på plass 0, lege på plass 1,
                                              //pasientId på plass 2, resepttype på plass 3 og evt styrke
                                              //på plass 4.
         }


           int legemiddelNummer = Integer.parseInt(linjeOrd.get(0));
           String legeNavn = linjeOrd.get(1);
           int pasientId = Integer.parseInt(linjeOrd.get(2));
           String reseptType = linjeOrd.get(3);  //Lagrer i enklere variabler.

           for(int i=0;i<legemiddelListe.stoerrelse();i++){  //Finner aktuelt legemiddel.
               if(legemiddelListe.hent(i).hentId() == legemiddelNummer){
                   lm = i;
               }
             }
              for(int i=0;i<legeListe.stoerrelse();i++){  //Finner akutuell lege.
                  if(legeListe.hent(i).hentNavn().equals(legeNavn)){
                      l = i;
                  }
                }
            for(int i=0;i<pasientListe.stoerrelse();i++){  //Finner aktuell pasient.
                if(pasientListe.hent(i).hentId() == pasientId){
                    p = i;
                }
              }

          if(reseptType.equals("blaa")){  //Sjekker resepttype
            int reseptReit = Integer.parseInt(linjeOrd.get(4));
              try{  //Prøver skrive ut, med forbehold om at legen muligens ikke er godkjent.
                  legeListe.hent(l).skrivBlaaResept(legemiddelListe.hent(lm), pasientListe.hent(p), reseptReit);
              }catch(UlovligUtskrift e){} //Kaster aktuell exception om legen ikke har lov til å skrive ut.

            }else if(reseptType.equals("militaer")){    //Sjekker resepttype
              int reseptReit = Integer.parseInt(linjeOrd.get(4));
            try{    //Prøver skrive ut, med forbehold om at legen muligens ikke er godkjent.
                legeListe.hent(l).skrivMilitaerResept(legemiddelListe.hent(lm), pasientListe.hent(p), reseptReit);
            }catch(UlovligUtskrift e){} //Kaster aktuell exception om legen ikke har lov til å skrive ut.

          }else if(reseptType.equals("hvit")){  //Sjekker resepttype
            int reseptReit = Integer.parseInt(linjeOrd.get(4));
            try{    //Prøver skrive ut, med forbehold om at legen muligens ikke er godkjent.
                legeListe.hent(l).skrivHvitResept(legemiddelListe.hent(lm), pasientListe.hent(p), reseptReit);
            }catch(UlovligUtskrift e){} //Kaster aktuell exception om legen ikke har lov til å skrive ut.

          }else if(reseptType.equals("p")){     //Sjekker resepttype
            try{    //Prøver skrive ut, med forbehold om at legen muligens ikke er godkjent.
                legeListe.hent(l).skrivPResept(legemiddelListe.hent(lm), pasientListe.hent(p));
            }catch(UlovligUtskrift e){} //Kaster aktuell exception om legen ikke har lov til å skrive ut.
          }
      }
    }
}
