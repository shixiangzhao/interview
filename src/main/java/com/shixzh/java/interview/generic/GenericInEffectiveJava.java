package com.shixzh.java.interview.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class GenericInEffectiveJava {

    private static final Collection stamps = new ArrayList();
    private static final Collection<Stamp> stamps1 = new ArrayList();

    public static void main(String[] args) {
        // Erroneous insertion of coin into stamp collection
        stamps.add(new Coin());
        // Not applicable for the arguments (Coin)
        //stamps1.add(new Coin());

        // Now a raw iterator type - don't do this!
        for (Iterator i = stamps.iterator(); i.hasNext();) {
            Stamp s = (Stamp) i.next();// Throws ClassCaseException
        }
        // for-each loop over a parameterized collection - typesafe
        for (Stamp s : stamps1) { //no cast

        }
        // for loop with parameterized iterator declaration - typesafe
        for (Iterator<Stamp> i = stamps.iterator(); i.hasNext();) {
            Stamp s = i.next();//no cast necessary
        }
    }

}

class Coin {

}

class Stamp {

}
