package com.example.databaseio;

public class Contacts {
    private int id;
    private String Name;
    private String Contactno;

    public Contacts() {
    }
    public Contacts(int id){
        this.id = id;
    }
    public Contacts(String name, String contactno){
        this.Name = name;
        this.Contactno = contactno;
    }
    public Contacts(int id,String name, String contactno){
        this.id = id;
        this.Name = name;
        this.Contactno = contactno;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getContactno() {
        return Contactno;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setContactno(String contactno) {
        Contactno = contactno;
    }
}
