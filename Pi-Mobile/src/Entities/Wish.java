/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entities;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Wish {
private int id;
private Cours id_c;
private User id_u;

    public Wish(Cours id_c, User id_u) {
        this.id_c = id_c;
        this.id_u = id_u;
    }

    public Wish() {
    }

    public Wish(int id, Cours id_c, User id_u) {
        this.id = id;
        this.id_c = id_c;
        this.id_u = id_u;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cours getId_c() {
        return id_c;
    }

    public void setId_c(Cours id_c) {
        this.id_c = id_c;
    }

    public User getId_u() {
        return id_u;
    }

    public void setId_u(User id_u) {
        this.id_u = id_u;
    }

    @Override
    public String toString() {
        return "Wish{" + "id=" + id + ", id_c=" + id_c + ", id_u=" + id_u + '}';
    }


}
