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
public class Progress {
private int id;
private User user;
private Jeu jeu;

    @Override
    public String toString() {
        return "Progress{" + "id=" + id + ", user=" + user + ", jeu=" + jeu + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    public Progress() {
    }

    public Progress(User user, Jeu jeu) {
        this.user = user;
        this.jeu = jeu;
    }

}
