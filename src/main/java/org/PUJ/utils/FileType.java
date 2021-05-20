package org.PUJ.utils;

public enum FileType {
    CSV("*.csv"),
    XML("*.xml"),
    JSON("*.json");

    private String filter;

    private FileType(String filter){
        this.filter = filter;
    }

    public String getFilter(){
        return this.filter;
    }
}

