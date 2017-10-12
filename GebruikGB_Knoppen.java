
/**
 *  Doordat Mohammad en Ahmad gestopt zijn met het project
 *  is deze klasse (in overleg met de senior) niet volgens de opdracht.
 *  Onze opdracht:
 *  Laat in het dotdisplay zien welke knop is ingedrukt.
 */

import java.lang.*;

public class GebruikGB_Knoppen
{
    public static GUIB_Final_Opdr3 eindopdracht;
    
    /**
     *  Maakt alles efficiënter
     *  schoon  
     */
    public static void O_0_maakSchoonBeter()
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
     *  Cleart alleen de 7-segment displays
     */
    public static void O_1_maakSchoonCijferDisplay()
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
     *  Laat zien welke knoppen er ingedrukt worden
     */
    public static void O_2_Knoppen()
    {
        eindopdracht = new GUIB_Final_Opdr3();
        int teller = 0;        
        while(teller < 999999999)
        {
            O_0_maakSchoonBeter();
            if(IO.readShort(0x80) != 0)
            {
                String t = "Scherm is schoongemaakt";
                eindopdracht.plaatsTekst(t);
                teller = 999999999;
                IO.delay(2000);
                IO.writeShort(0x40, 0xFE);
                IO.writeShort(0x40, 0x01);
                while(IO.readShort(0x80) == 1)
                {
                    IO.delay(1);
                }
            }
            else if(IO.readShort(0x90) != 0)
            {
                String t = "Blauwe knop links ingedrukt";
                eindopdracht.plaatsTekst(t);
                while(IO.readShort(0x90) == 1)
                {
                    IO.delay(1);
                }
            }
            else if(IO.readShort(0x100) != 0)
            {
                String t = "Blauwe knop rechts ingedrukt";
                eindopdracht.plaatsTekst(t);
                    while(IO.readShort(0x100) == 1)
                {
                    IO.delay(1);
                }
            }else{
                IO.delay(1);
            }
            teller++;
        }
    }
}
