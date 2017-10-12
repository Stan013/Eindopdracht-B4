
/**
 * Write a description of class GebruikGB_DotDisplay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;
public class GebruikGB_DotDisplay
{
    private static ArrayList<Integer> waarden;

    /**
     *  Plaatst een cijfer 
     *  in een bepaald display
     */
    public static void O_1_plaatsenVanEenCijfer(int cijfer, int bit)
    {
        IO.writeShort(bit, cijfer);
    }

    /**
     *  Maakt de 7 segment-displays
     *  schoon
     */
    public static void O_2a_maakSchoon()
    {
        IO.writeShort(0x10, 0x100);
        IO.writeShort(0x12, 0x100);
        IO.writeShort(0x14, 0x100);
        IO.writeShort(0x16, 0x100);
        IO.writeShort(0x18, 0x100);
        IO.writeShort(0x20, 0x100);
        IO.writeShort(0x22, 0x100);
        IO.writeShort(0x24, 0x100);
        IO.writeShort(0x30, 0x100);
        IO.writeShort(0x32, 0x100);
        IO.writeShort(0x34, 0x100);
    }

    /**
     *  Maakt alles efficiënter
     *  schoon  
     */
    public static void O_2b_maakSchoonBeter()
    {
        int bit = 0x10;
        while (bit <= 0x34)
        {
            IO.writeShort(bit, 0x100);
            bit = bit + 0x2;
        }
        IO.writeShort(0x40, 0xFE);
        IO.writeShort(0x40, 0x01);
    }

    /**
     *  Voorbeeld uit de opdracht
     */
    public static void O_1_voorbeeld()
    {
        IO.writeShort(0x40,'T');
        IO.writeShort(0x40,'I');
        IO.writeShort(0x40,'\n');
        IO.writeShort(0x40,'2');
        IO.writeShort(0x40,'\n');
        IO.writeShort(0x40,'3');
    }
    
    /**
     *  Test een voor een de pixels
     */
    public static void O_3_pixelTest()
    {
        for(int y = 0; y < 32; y++)
        {
            int x = 0;
            for(x = 0; x < 128; x++)
            {
                IO.writeShort(0x42, 1 << 12 | x << 5 | y);
                IO.delay(10);
                IO.writeShort(0x40, 0xFE);
                IO.writeShort(0x40, 0x01);
            }
        }
    }

    /**
     *  Voorbeeld uit de opdracht
     */
    public static void O_2_doeDitMaarEensNa()
    {
        IO.writeShort(0x40, 'D');
        IO.writeShort(0x40, 'O');
        IO.writeShort(0x40, 'E');
        IO.writeShort(0x40, ' ');
        IO.writeShort(0x40, 'D');
        IO.writeShort(0x40, 'I');
        IO.writeShort(0x40, 'T');
        IO.writeShort(0x40, '\n');
        IO.writeShort(0x40, 'M');
        IO.writeShort(0x40, 'A');
        IO.writeShort(0x40, 'A');
        IO.writeShort(0x40, 'R');
        IO.writeShort(0x40, '\n');
        IO.writeShort(0x40, 'E');
        IO.writeShort(0x40, 'E');
        IO.writeShort(0x40, 'N');
        IO.writeShort(0x40, 'S');
        IO.writeShort(0x40, ' ');
        IO.writeShort(0x40, 'N');
        IO.writeShort(0x40, 'A');
        IO.writeShort(0x40, '!');
    }

    /**
     *  Cleart het display
     *  Tekent de x-as in  het midden
     */
    public static void o_4A_tekenAsXMetSchoonmaken()
    {
        IO.writeShort(0x40, 0xFE);
        IO.writeShort(0x40, 0x01);
        int x = 0;
        for(x = 0; x < 128; x++)
        {
            IO.writeShort(0x42, 1 << 12 | x <<5 | 16);
        }   
    }

    /**
     *  Cleart het display
     *  Tekent de y-as in het midden
     */
    public static void O_4b_tekenAsYMetSchoonMaken()
    {
        IO.writeShort(0x40, 0xFE);
        IO.writeShort(0x40, 0x01);
        int y = 0;
        for(y = 0; y < 32; y++)
        {
            IO.writeShort(0x42, 1 << 12 | 64 << 5 | y);
        }
    }

    /**
     *  Tekent de x-as in het midden
     */
    public static void O_4c_tekenAsXZonderSchoonmaken()
    {
        int x = 0;
        for(x = 0; x < 128; x++)
        {
            IO.writeShort(0x42, 1 << 12 | x << 5 | 16);
        }
    }

    /**
     *  Tekent de y-as in het midden
     */
    public static void O_4d_tekenAsYZonderSchoonmaken()
    {
        int y = 0;
        for(y = 0; y < 32; y++)
        {
            IO.writeShort(0x42, 1 << 12 | 64 << 5 | y);
        }
    }

    /**
     *  Tekent zowel de x-as als de y-as
     */
    public static void O_5a_tekenAsXenY()
    {
        int x = 0;
        int y = 0;
        for (x = 0; x < 128; x++)
        {
            IO.writeShort(0x42, 1 << 12 | x << 5 | 16);
        }
        for (y = 0; y < 32; y++)
        {
            IO.writeShort(0x42, 1 << 12 | 64 << 5 | y);
        }
    }

    /**
     *  Cleart het display
     *  Tekent zowel x-as als y-as
     */
    public static void tekenAsXenYmetSchoonmaken()
    {
        IO.writeShort(0x40, 0xFE);
        IO.writeShort(0x40, 0x01);
        int x = 0;
        int y = 0;
        for (x = 0; x < 128; x++)
        {
            IO.writeShort(0x42, 1 << 12 | x << 5 | 16);
        }
        for (y = 0; y < 32; y++)
        {
            IO.writeShort(0x42, 1 << 12 | 64 << 5 | y);
        }
    }

    /**
     *  Tekent punt (x,y)
     *  Tekent assen
     */
    public static void O_5b_tekenXYwaardenMetAssen(int xWaarde, int yWaarde)
    {
        IO.writeShort(0x40, 0xFE);
        IO.writeShort(0x40, 0x01);
        int x = 0;
        int y = 0;
        for (x = 0; x < 128; x++)
        {
            IO.writeShort(0x42, 1 << 12 | x << 5 | 16);
        }
        for (y = 0; y < 32; y++)
        {
            IO.writeShort(0x42, 1 << 12 | 64 << 5 | y);
        }
        IO.writeShort(0x42, 1 << 12 | xWaarde << 5 | yWaarde);
    }

    /**
     *  Tekent punt (x,y)
     *  Zonder assen
     */
    public static void O_5c_tekenXYwaardenZonderAssen(int xWaarde, int yWaarde)
    {
        IO.writeShort(0x40, 0xFE);
        IO.writeShort(0x40, 0x01);
        IO.writeShort(0x42, 1 << 12 | xWaarde << 5 | yWaarde);
    }

    /**
     *  Tekent de parabool
     *  y = 0.1*x^2 - 10
     */
    public static void O_5d_tekenParabool()
    {
        tekenAsXenYmetSchoonmaken();
        int x = -64;
        int vervangX = 0;
        int vervangY = 0;
        for(x = -64; x <= 64; x++)
        {
            vervangX = x + 64;
            double y = 0.1*(x*x)- 10  ;
            int yGoed = (int) y;
            if(y > 16)
            {
                int dY = yGoed - 16;
                dY = dY * 2;
                yGoed = yGoed - dY;
            }
            else{
                int dY = 16 - yGoed;
                dY = dY * 2;
                yGoed = yGoed + dY;
            }

            vervangY = yGoed / 32 + 15;
            IO.writeShort(0x42, 1 << 12 | vervangX << 5 | vervangY);
        }
    }

    /**
     *  Zet fictieve temperatuurwaarden in een Arraylist
     */
    public static void vulWaarden()
    {
        waarden = new ArrayList<Integer>();

        waarden.add(new Integer(22)); waarden.add(new Integer(22)); waarden.add(new Integer(22));
        waarden.add(new Integer(22)); waarden.add(new Integer(21)); waarden.add(new Integer(21));
        waarden.add(new Integer(21)); waarden.add(new Integer(21)); waarden.add(new Integer(21));
        waarden.add(new Integer(20)); waarden.add(new Integer(20)); waarden.add(new Integer(20));
        waarden.add(new Integer(20)); waarden.add(new Integer(20)); waarden.add(new Integer(19));

        waarden.add(new Integer(19)); waarden.add(new Integer(19)); waarden.add(new Integer(19));
        waarden.add(new Integer(19)); waarden.add(new Integer(19)); waarden.add(new Integer(19));
        waarden.add(new Integer(18)); waarden.add(new Integer(18)); waarden.add(new Integer(18));
        waarden.add(new Integer(18)); waarden.add(new Integer(18)); waarden.add(new Integer(18));
        waarden.add(new Integer(17)); waarden.add(new Integer(18)); waarden.add(new Integer(18));

        waarden.add(new Integer(18)); waarden.add(new Integer(18)); waarden.add(new Integer(18));
        waarden.add(new Integer(19)); waarden.add(new Integer(19)); waarden.add(new Integer(20));
        waarden.add(new Integer(20)); waarden.add(new Integer(21)); waarden.add(new Integer(21));
        waarden.add(new Integer(22)); waarden.add(new Integer(22)); waarden.add(new Integer(23));
        waarden.add(new Integer(23)); waarden.add(new Integer(24)); waarden.add(new Integer(24));

        waarden.add(new Integer(24)); waarden.add(new Integer(25)); waarden.add(new Integer(25));
        waarden.add(new Integer(25)); waarden.add(new Integer(25)); waarden.add(new Integer(25));
        waarden.add(new Integer(26)); waarden.add(new Integer(26)); waarden.add(new Integer(27));
        waarden.add(new Integer(26)); waarden.add(new Integer(26)); waarden.add(new Integer(26));
        waarden.add(new Integer(27)); waarden.add(new Integer(27)); waarden.add(new Integer(27));

        waarden.add(new Integer(27)); waarden.add(new Integer(27)); waarden.add(new Integer(27));
        waarden.add(new Integer(26)); waarden.add(new Integer(27)); waarden.add(new Integer(27));
        waarden.add(new Integer(27)); waarden.add(new Integer(27)); waarden.add(new Integer(27));
        waarden.add(new Integer(27)); waarden.add(new Integer(25)); waarden.add(new Integer(23));
        waarden.add(new Integer(24)); waarden.add(new Integer(24)); waarden.add(new Integer(24));

        waarden.add(new Integer(24)); waarden.add(new Integer(24)); waarden.add(new Integer(23));
        waarden.add(new Integer(23)); waarden.add(new Integer(23)); waarden.add(new Integer(23));
        waarden.add(new Integer(22)); waarden.add(new Integer(22)); waarden.add(new Integer(22));
        waarden.add(new Integer(21)); waarden.add(new Integer(21)); waarden.add(new Integer(21));
        waarden.add(new Integer(21)); waarden.add(new Integer(20)); waarden.add(new Integer(20));

        waarden.add(new Integer(20)); waarden.add(new Integer(20)); waarden.add(new Integer(20));
        waarden.add(new Integer(20)); waarden.add(new Integer(19)); waarden.add(new Integer(19));

        waarden.add(new Integer(17)); waarden.add(new Integer(15)); waarden.add(new Integer(13));
        waarden.add(new Integer(11)); waarden.add(new Integer( 9)); waarden.add(new Integer( 7));
        waarden.add(new Integer( 5)); waarden.add(new Integer( 3)); waarden.add(new Integer( 1));
    }

    /**
     *  Tekent de grafiek van de temperatuur
     */
    public static void O_5e_tekenTemperatuurGrafiek()
    {
        vulWaarden();
        int xAs = 26;
        O_6a_tekenXas(xAs);
        int x ;
        int y ;
        int xMax = waarden.size();
        for(x = 0; x < xMax; x++)
        {
            y = waarden.get(x);
            IO.writeShort(0x42, 1 << 12 | x << 5 | y);
        }
    }

    /**
     *  Tekent de x-as op een bepaalde positie
     *  (0 <= positie < 32)
     */
    public static void O_6a_tekenXas(int positieXas)
    {
        IO.writeShort(0x40, 0xFE);
        IO.writeShort(0x40, 0x01);
        for(int x = 0; x < 128; x++)
        {
            IO.writeShort(0x42, 1 << 12 | x << 5 | positieXas);
        }
    }

    /**
     *  Tekent de y-as op een bepaalde positie
     *  (0 <= positie < 128)
     */
    public static void O_6b_tekenYas(int positieYas)
    {
        IO.writeShort(0x40, 0xFE);
        IO.writeShort(0x40, 0x01);
        for(int y = 0; y < 128; y++)
        {
            IO.writeShort(0x42, 1 << 12 | positieYas << 5 | y);
        }
    }

    /**
     *  Tekent de x-as en y-as op aangegeven posities
     *  (0 <= positie X < 32)
     *  (0 <= positie Y < 128)
     */
    public static void O_6c_TekenXasEnYas(int posXas, int posYas)
    {
        IO.writeShort(0x40, 0xFE);
        IO.writeShort(0x40, 0x01);
        for(int y = 0; y < 128; y++)
        {
            IO.writeShort(0x42, 1 << 12 | posYas << 5 | y);
        }
        for(int x = 0; x < 128; x++)
        {
            IO.writeShort(0x42, 1 << 12 | x << 5 | posXas);
        }
    }

    /**
     *  Bereken een punt ten opzichte van de nieuwe y-as
     */
    public static void O_6d_berekenPositieX(int waardeX, int positieXas)
    {
        int xNieuw = positieXas + waardeX;
    }

    /**
     *  Bereken een punt ten opzichte van de nieuwe x-as
     */
    public static void O_6e_berekenPositieY(int waardeY, int positieYas)
    {
        int yNieuw = positieYas - waardeY;
    }

    /**
     *  Tekent de temperatuurgrafiek met omgerekende waardes
     */
    public static void O_6f_tekenTemparetuur()
    {
        vulWaarden();
        int xAs = 26;
        O_6a_tekenXas(xAs);
        int x ;
        int y ;
        int xMax = waarden.size();
        for(x = 0; x < xMax; x++)
        {
            y = waarden.get(x);
            int yNieuw = xAs - y;
            IO.writeShort(0x42, 1 << 12 | x << 5 | yNieuw);
        }
    }
}
