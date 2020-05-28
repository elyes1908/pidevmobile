/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.codename1.ui.Button;

/**
 *
 * @author admin
 */
public class Plat {
    private int id,calories,likes,dislikes,res;
    private String libelle,description;
    
    
    public Plat(int id, int calories, int likes,int dislikes, int res,String libelle,String description) {
        this.id = id;
        this.calories = calories;
        this.likes = 0;
        this.dislikes=0;
        this.res=0;
        this.libelle=libelle;
        this.description=description;
    }

 

    public Plat() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   

  

    @Override
    public String toString() {
        return libelle +": likes = " + likes + "calories =" + calories;
    }
    
    
}

