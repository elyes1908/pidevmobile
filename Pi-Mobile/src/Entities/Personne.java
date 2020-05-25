/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author ASUS
 */
public class Personne {
    int id;
    int IdUser;

    public Personne() {
    }

    public Personne(int IdUser) {
        this.IdUser = IdUser;
       
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

   
    @Override
    public String toString() {
        return  "id=" + id + ", IdUser=" + IdUser + "\n" ;
    }
    
}
