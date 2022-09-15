package hu.modeldriven.cameo;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class;

public class WhateverRecord {

    private final Class magicDrawClass;

    public WhateverRecord(Class magicDrawClass){
        this.magicDrawClass = magicDrawClass;
    }

    public String getName(){
        return magicDrawClass.getHumanName();
    }

}
