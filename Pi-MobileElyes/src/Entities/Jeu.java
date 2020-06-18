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
public class Jeu {
private int id;

    public Jeu() {
    }

    public Jeu(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Jeu{" + "id=" + id + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
