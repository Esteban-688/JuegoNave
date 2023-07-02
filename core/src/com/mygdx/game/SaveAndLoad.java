package com.mygdx.game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class SaveAndLoad {
	private Perfil perfil;

	// Constructor
	public SaveAndLoad(Perfil miPerfil) {
		perfil = miPerfil;
	}

	// Método para guardar los datos del perfil
	public static void save(Perfil perfil) {
		try {
			// Abriendo el archivo con los datos "partida.txt";
			FileHandle fileHandle = Gdx.files.internal("partida.txt");
			File file = fileHandle.file();

			// Si el archivo no existe, es creado
			if (!file.exists()) {
				file.createNewFile();
			}

			// Escribiendo en el archivo "partida.txt"
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);

			// ArrayList donde son almacenados los datos del perfil
			ArrayList<String> contenido = new ArrayList<>();

			// Datos: nombre, monedas
			contenido.add(perfil.getNombre());
			contenido.add(Integer.toString(perfil.getEduCoins()));

			// False si la skin está bloqueada. True en el caso opuesto
			for (int i = 0; i < perfil.size(); i++) {
				contenido.add(Boolean.toString(perfil.getSkinDesbloqueada(i)));
			}

			// Escribiendo los datos
			for (int i = 0; i < contenido.size(); i++) {
				bw.write(contenido.get(i) + "\n");
			}

			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Método para cargar los datos de un perfil existente
	public static void load(Perfil perfil) {
		// Gestión del archivo con los datos
		FileHandle fileHandle = Gdx.files.local("partida.txt");
		String text = fileHandle.readString();

		String[] lines = text.split("\\r?\\n");

		if (lines.length >= 3) {
			// Datos: nombre, monedas
			perfil.setNombre(lines[0]);
			perfil.setEduCoins(Integer.parseInt(lines[1]));

			// Importación de las skins disponibles
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
