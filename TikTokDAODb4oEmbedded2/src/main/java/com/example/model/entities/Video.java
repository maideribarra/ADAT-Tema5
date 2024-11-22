package com.example.model.entities;

import java.io.Serializable;


public class Video implements Serializable {
    
    public int id;
    public String nombre_video;
    public float duracion;
    public String autor;
    public int likes;
    public int fecha_subida;
    public String etiquetas;
    
    
    public Video() {
    }
    public Video(String nombre_video,float duracion,String autor,int likes,int fecha_subida,String etiquetado){
        this.nombre_video=nombre_video;
        this.duracion=duracion;
        this.autor=autor;
        this.likes=likes;
        this.fecha_subida=fecha_subida;
        this.etiquetas=etiquetado;
       
    }

    @Override
    public String toString() {
        return "Video: "+this.nombre_video+" "+this.duracion+" "+this.autor+" "+this.likes+" "+this.fecha_subida+" "+"["+this.etiquetas+"]";
    }

    public String getNombre_video() {
        return nombre_video;
    }
    public void setNombre_video(String nombre_video) {
        this.nombre_video = nombre_video;
    }
    public float getDuracion() {
        return duracion;
    }
    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public int getLikes() {
        return likes;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }
    public int getFecha_subida() {
        return fecha_subida;
    }
    public void setFecha_subida(int fecha_subida) {
        this.fecha_subida = fecha_subida;
    }
    public String getEtiquetas() {
        return etiquetas;
    }
    public void setEtiquetas(String etiquetas) {
        this.etiquetas = etiquetas;
    }
    

}
