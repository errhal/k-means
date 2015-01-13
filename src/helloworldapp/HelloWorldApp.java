package helloworldapp;
import java.util.*;

/**
 *
 * @author errhal
 * Zaimplementowany algorytm k-średnich
 */
class Punkt
{
    public double x;
    public double y;
    public int reprezentant;
    public double odleglosc(double a,double b)
    {
       double wynik=(x-a)*(x-a) + (y-b)*(y-b);
       return wynik;
    }
}
class Reprezentant
{
    public double x;
    public double y;
    public double old_x;
    public double old_y;
    public void wczytaj(Reprezentant[] reprezentanci,int liczba)
    {
        Scanner rd = new Scanner(System.in);
        for(int i=0;i<liczba;i++)
        {
            System.out.println("Proszę podać początkowe współrzędne " + i + " reprezentanta:\n ");
            reprezentanci[i].x=rd.nextDouble();
            reprezentanci[i].y=rd.nextDouble();
        }
    }
}
class HelloWorldApp {
    public static void main(String[] args) {
               int n ; //Liczba poszukiwanych grup
               int m; //liczba punktów 
               double epsilon = 0.0001;
               Scanner wczytaj = new Scanner(System.in);
               System.out.println("Proszę podać liczbę punktów:\n" );
               m=wczytaj.nextInt();
               
               //////////tworzenie tablicy punktów oraz wczytywanie ich
               Punkt[] punkty;
               punkty=new Punkt[m];
               for(int i=0;i<m;i++)
               {
                   System.out.println("Proszę wpisać współrzędne "+i+ " punktu:\n");
                   double x=wczytaj.nextDouble();
                   double y=wczytaj.nextDouble();
                   punkty[i]=new Punkt();
                   punkty[i].x=x;
                   punkty[i].y=y;
               }
               System.out.println("Proszę podać liczbę grup (mniejszą niż liczbę punktów) :\n ");
               n=wczytaj.nextInt();
               
               
               
               ///////tworzenie tablicy reprezentantów (randomowanych)
               Reprezentant[] reprezentanci;
               reprezentanci = new Reprezentant[n];
               for(int i=0;i<n;i++)
               {
                   reprezentanci[i]=new Reprezentant();
                  // reprezentanci[i].x=punkty[i].x;
                   //reprezentanci[i].y=punkty[i].y;
                   //////System.out.println("OBIEG "+i + " : ("+punkty[i].x+","+punkty[i].y+"\n");
               }
               reprezentanci[0].wczytaj(reprezentanci,n);
               while(true)
               {
                   double min=10000000; /////wyliczanie najblizszego reprezentanta
                   double odl=10000000;
                   for(int i=0;i<m;i++)
                   {
                       min=1000000;
                       for(int j=0;j<n;j++)
                       {
                          odl=punkty[i].odleglosc(reprezentanci[j].x,reprezentanci[j].y);
                         System.out.println("REPREZENTANT DLA "+i+" PUNKTU ZNALEZIONY : "+j+" odlegosc " + odl+ "\n");
                          if(min>=odl)
                          {
                              min=odl;
                              punkty[i].reprezentant=j;
                          }
                       }
                       
                   }
                   /*System.out.println("UWAGA : "+punkty[0].reprezentant+"\n");
                   System.out.println("UWAGA : "+punkty[1].reprezentant+"\n");
                   System.out.println("UWAGA : "+punkty[2].reprezentant+"\n");
                   System.out.println("UWAGA : "+punkty[3].reprezentant+"\n");
                   System.out.println("UWAGA : "+punkty[4].reprezentant+"\n");
                   System.out.println("UWAGA : "+punkty[5].reprezentant+"\n");
                   System.out.println("UWAGA : "+punkty[6].reprezentant+"\n");
                   */
                   int stan=0;
                   for(int i=0;i<n;i++)
                   {
                       int licznik=0; //liczba punktów należących do reprezentanta
                       double sum_x=0;
                       double sum_y=0;
                       for(int j=0;j<m;j++)
                       {
                           if(punkty[j].reprezentant==i)
                           {
                               //System.out.println("REP DO PUNKTU : "+j+" "+i+"\n");
                               sum_x += punkty[j].x;
                               sum_y += punkty[j].y;
                               licznik++;
                           }
                       }

                       if(licznik!=0)
                       {
                           reprezentanci[i].old_x=reprezentanci[i].x;
                           reprezentanci[i].old_y=reprezentanci[i].y;
                           reprezentanci[i].x=sum_x/licznik;
                           reprezentanci[i].y=sum_y/licznik;
                        if(Math.abs(reprezentanci[i].x-reprezentanci[i].old_x)>epsilon || Math.abs(reprezentanci[i].y-reprezentanci[i].old_y)>epsilon){stan=1;}
                       }
                     System.out.println("REPREZENTANT "+ i + ": "+reprezentanci[i].x+":"+reprezentanci[i].y+"\n");

                   }
                   //System.out.println("STOP\n");
                   if(stan==0) break;
               }
          for(int i=0;i<n;i++)
          {
              System.out.println("Grupa "+i+" : Reprezentant ("+reprezentanci[i].x+","+reprezentanci[i].y+");\n");
              for(int j=0;j<m;j++)
              {
                  if(punkty[j].reprezentant==i) 
                  {
                      System.out.println("Punkt "+j+": ("+punkty[j].x+","+punkty[j].y+")\n");
                  }
              }
          }
    }
}
