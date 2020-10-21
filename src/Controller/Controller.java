package Controller;

import Model.Model;
import View.View;

import java.util.ArrayList;

public class Controller {
    public static void main(String[] args) {
//        Appel fonction static entrer dans le package view et la class View et recuperation de la valeur entrer par l utilisateur
        String a = View.entrer();
        a = about(a);
        System.out.print(a);

    }
    public static String about(String a){
        ArrayList<String> tab =new ArrayList<String>();
        String val = "";
        String lav="";
        try {
            a=a.replace(" ","");
            String [] l =a.split(" ");
            ArrayList<String> ta =new ArrayList<String>();
            for(int i=0;i<l.length;i++) {
                ta.add(l[i]);
            }
            for(int i=0;i<ta.size()-1;i++){
                Double.parseDouble(ta.get(i));
            }
        } catch (Exception e) {
            View.error(e.getMessage()+" ???not hear??? ");
        }

        a=a.replace(" ","");
        a=a.replace("+", " + ");
        a=a.replace("-", " - ");
        a=a.replace("*", " * ");
        a=a.replace("/", " / ");
        a=a.replace("(", " ( ");
        a=a.replace(")", " ) ");

        String [] t =a.split(" ");
        for(int i=0;i<t.length;i++) {
            tab.add(t[i]);
        }
        tab.add(")");
        do {
            ArrayList<String> cal =new ArrayList<String>();
            int x=0,y=tab.size();
            for(int i=tab.size()-1;i>0;i--) {
                if (tab.get(i).contains("(")){
                    x = i;
                    tab.remove(i);
                    break;
                }
            }
            for (int i = x; i < tab.size(); i++) {
                if (tab.get(i).contains(")")){
                    y = i;
                    tab.remove(i);
                    break;
                }
            }
            lav="";
            for(int i=y-1;i>=x ;i--){
                try {//pour parenteze i.e mettre entre parentez un double negatif de facon a emprisonner le signe "-" pour trop turbulence causé dans le programe
                    if (Double.parseDouble(tab.get(x)) < 0) {
                        lav += "(" + tab.get(x)+")";
                    } else {
                        lav += tab.get(x);
                    }

                }
                catch (Exception E){
                    lav += tab.get(x);

                }
                tab.remove(x);
            }
            System.out.print(lav+" = ");
            double m = calcul(lav);
            tab.add(x, String.valueOf(m));
        }while(tab.contains("(")||tab.contains(")"));
        for (String v:tab) {
            val+=v;
        }

        return val;
    }
    public static double calcul(String a){
        double result=0;
        String tamp="";
        ArrayList<String> tabTamp=new ArrayList<String>();

        for(int i=0;i<a.length();i++){		//ajout dans le tableau string
            if(i==0&&(a.charAt(0)=='-'||a.charAt(0)=='+')){// les sign au debut
                tamp+=Character.toString(a.charAt(0));
                continue;
            }else if(a.charAt(i)=='('){//en gros ici on recupere le double negatif qui a ete emprisonne dans le try catch ci dessus
                String parentez="";
                i++;//pour sauter le ("
                while (a.charAt(i)!=')'){
                    parentez+=a.charAt(i);
                    i++;
                }
                tabTamp.add(parentez);
                if(a.charAt(i)==')'){
                    continue;//pour sauter ")" vue qu'on s'en fout des parentez
                }
            }else if(a.charAt(i)=='+'||a.charAt(i)=='-'||a.charAt(i)=='*'||a.charAt(i)=='/'){
                tabTamp.add(String.valueOf(a.charAt(i)));
                continue;
            }
            tamp+=Character.toString(a.charAt(i));
            if(i==a.length()-1||a.charAt(i+1)=='+'||a.charAt(i+1)=='-'||a.charAt(i+1)=='*'||a.charAt(i+1)=='/'){
                tabTamp.add(tamp);
                tamp="";
            }
        }
        // les calculs
        if(tabTamp.size()==1){
            result=Double.parseDouble(tabTamp.get(0));
        }

        do{
            for(int i=0;i<tabTamp.size();i++){	//Multiplication et division
                if(tabTamp.get(i).contains("*")) {
                    result =(Double.parseDouble(tabTamp.get(i-1)))*(Double.parseDouble(tabTamp.get(i+1)));
                    String expres = String.valueOf(result);
                    tabTamp.set(i-1, expres);
                    tabTamp.remove(i);
                    tabTamp.remove(i);
                }else if(tabTamp.get(i).contains("/")) {
                    result =(Double.parseDouble(tabTamp.get(i-1)))/(Double.parseDouble(tabTamp.get(i+1)));
                    String expres = String.valueOf(result);
                    tabTamp.set(i-1, expres);
                    tabTamp.remove(i);
                    tabTamp.remove(i);
                }
            }
        }while(tabTamp.contains("*")||tabTamp.contains("/"));


        do {	//addition et soustraction
            for(int i=0;i<tabTamp.size();i++) {
                if(i==0&&(tabTamp.get(i).contains("-")||tabTamp.get(i).contains("+"))){
                    continue;
                }
                if(tabTamp.get(i).contains("+")) {
                    result =(Double.parseDouble(tabTamp.get(i-1)))+(Double.parseDouble(tabTamp.get(i+1)));
                    String expres = String.valueOf(result);
                    tabTamp.set(i-1, expres);
                    tabTamp.remove(i);
                    tabTamp.remove(i);
                }else if(tabTamp.get(i).contains("-")) {
                    result =(Double.parseDouble(tabTamp.get(i-1)))-(Double.parseDouble(tabTamp.get(i+1)));
                    String expres = String.valueOf(result);
                    tabTamp.set(i-1, expres);
                    tabTamp.remove(i);
                    tabTamp.remove(i);
                }
            }
        }while(tabTamp.contains("+")||tabTamp.contains("-"));
        return  result;
    }
}

