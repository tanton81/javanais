package dsi.javanaise.model;

import java.util.*;

public class Translator
{

    private Set<Character> consonnes;
    private Set<Character> voyelles;

    public Translator()
    {
        consonnes = new HashSet<>();
        voyelles = new HashSet<>();

        consonnes.add('b'); consonnes.add('c'); consonnes.add('d'); consonnes.add('f');
        consonnes.add('j'); consonnes.add('g'); consonnes.add('h'); consonnes.add('k');
        consonnes.add('l'); consonnes.add('m'); consonnes.add('n'); consonnes.add('p');
        consonnes.add('r'); consonnes.add('q'); consonnes.add('s'); consonnes.add('t');
        consonnes.add('v'); consonnes.add('w'); consonnes.add('x'); consonnes.add('z');


        voyelles.add('a'); voyelles.add('e'); voyelles.add('o');
        voyelles.add('u'); voyelles.add('i'); voyelles.add('y');
    }


//    public static void main(String[] args)
//    {
//
//        Translator t = new Translator();
//
//        String motFrancais1 = "bonjour";
//        String motFrancais2 = "chante";
//        String motFrancais3 = "moyen";
//        String motFrancais4 = "exemple";
//        String motFrancais5 = "au";
//
//        System.out.println(motFrancais1 + " - " + t.translate(motFrancais1));
//        System.out.println(motFrancais2 + " - " + t.translate(motFrancais2));
//        System.out.println(motFrancais3 + " - " + t.translate(motFrancais3));
//        System.out.println(motFrancais4 + " - " + t.translate(motFrancais4));
//        System.out.println(motFrancais5 + " - " + t.translate(motFrancais5));
//
//    }

    public String translate(String motFrancais)
    {
        String tmp = "";
        String next = "";
        char last = '_';

        for(int i = 0; i < motFrancais.length(); i++)
        {
            if(i+1 >= motFrancais.length())
            {

                tmp += motFrancais.charAt(i);
                //System.out.println("1 Result " + tmp);

                return tmp;
            }

            if(voyelles.contains(motFrancais.charAt(i)))
            {
                //System.out.println("voyelles " + motFrancais.charAt(i));


                if(!voyelles.contains(last))
                {
                    tmp += "av" + motFrancais.charAt(i);
                    last = motFrancais.charAt(i);
                }
                else
                {
                    tmp += motFrancais.charAt(i);
                    last = motFrancais.charAt(i);
                }
            }
            else
            //if(consonnes.contains(motFrancais.charAt(i)))
            {

                //System.out.println("consonne " + motFrancais.charAt(i));

                if(voyelles.contains(motFrancais.charAt(i+1)))
                {
                    tmp += motFrancais.charAt(i) + "av" + motFrancais.charAt(i+1);

                    last = motFrancais.charAt(i+1);

                    i++;
                }
                else
                {
                    tmp += motFrancais.charAt(i);
                    last = motFrancais.charAt(i);
                }
            }
        }

        //System.out.println("2 Result " + tmp);

        return tmp;

    }

}

