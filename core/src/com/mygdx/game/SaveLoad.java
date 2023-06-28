package com.mygdx.game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class SaveLoad {
	private Perfil perfil;
	
	
	public SaveLoad(Perfil miPerfil) {
		perfil = miPerfil;
	}
	
	public static void saved(Perfil perfil){
        try {
            //String ruta = "partida.txt";
        	FileHandle fileHandle = Gdx.files.internal("partida.txt");
            File file = fileHandle.file();
        	
        	ArrayList<String> contenido= new ArrayList<>();
            
            contenido.add(perfil.getNombre());
            contenido.add(Integer.toString(perfil.getEduCoins()));
            
            
            for(int i = 0 ; i < perfil.size() ; i++) {
            	 contenido.add(Boolean.toString(perfil.getSkinDesbloqueada(i)));
            }
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            
            for(int i = 0 ; i < contenido.size() ; i++) {
            	bw.write(contenido.get(i)+"\n");
            }
            
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	public static void load(Perfil perfil) {
		 FileHandle fileHandle = Gdx.files.local("partida.txt");
		 String text = fileHandle.readString();

		 String[] lines = text.split("\\r?\\n");
		 
		 if (lines.length >= 3) {
		     perfil.setNombre(lines[0]);
		     perfil.setEduCoins(Integer.parseInt(lines[1]));

		 for (int i = 2; i < lines.length; i++) {
		     boolean skinDesbloqueada = Boolean.parseBoolean(lines[i]);
		      perfil.setSkinDesbloqueada(i - 2, skinDesbloqueada);
		       }
		   }	
	}
	
	
	
	public Perfil getPerfil() {
		return perfil;
	}
}
