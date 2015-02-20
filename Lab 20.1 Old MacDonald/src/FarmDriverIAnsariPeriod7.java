/*
Ibrahim Ansari
Period 7
1/25/2015

Lab 20.1 Old MacDonald

Time Spent: 15 minutes

Reflection:
This was a very easy lab. I was confused as to why we are doing this again because I thought we already
covered this in an earlier chapter. I was tired and hurriedly finished up this lab after coming from
Hacking Generation Y. Overall an easy lab.
 */

import java.util.ArrayList;

public class FarmDriverIAnsariPeriod7 {
    public static void main(String[] args){
        Farm f = new Farm();
        f.animalSounds();
    }
}

class Farm {
    private ArrayList <Animal> myFarm;

    public Farm() {
        myFarm = new ArrayList <Animal>();
        myFarm.add(new Cow());
        myFarm.add(new Chick());
        myFarm.add(new Pig());
        myFarm.add(new NamedCow("Elsie"));
    }

    public void animalSounds(){
        Animal temp;
        for(int i = 0; i < myFarm.size(); i++){
            temp = myFarm.get(i);
            System.out.println(temp.getType() + " goes " + temp.getSound());
        }

        NamedCow named = (NamedCow)myFarm.get(3);
        System.out.println(named.getName());
    }
}

interface Animal {
    public String getSound();
    public String getType();
}

class Cow implements Animal{
    private String myType;
    private String mySound;

    Cow(){
        myType = "cow";
        mySound = "moo";
    }

    public String getSound(){
        return mySound;
    }

    public String getType(){
        return myType;
    }
}

class Chick implements Animal {
    private String myType;
    private String mySound;
    private int x = (Math.random() < 0.5) ? 0 : 1;

    Chick(){
        myType = "chicken";
        if (x == 0)
            mySound = "cheep";
        else
            mySound = "cluck";
    }

    public String getSound() {
        return mySound;
    }

    public String getType() {
        return myType;
    }
}

class Pig implements Animal {
    private String myType;
    private String mySound;

    Pig(){
        myType = "pig";
        mySound = "oink";
    }

    public String getSound() {
        return mySound;
    }

    public String getType() {
        return myType;
    }
}

class NamedCow extends Cow {
    private String myType;
    private String mySound;
    private String myName;

    NamedCow(String name) {
        myType = "cow";
        mySound = "moo";
        myName = name;
    }

    public String getSound() {
        return mySound;
    }

    public String getType() {
        return myType;
    }

    public String getName() {
        return myName;
    }
}