package br.ucb.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {

	public static void upload(String pasta, String nomeDoArquivo, InputStream arquivoCarregado) {
		String caminhoArquivo = pasta + "/" + nomeDoArquivo;
		File novoArquivo = new File(caminhoArquivo);
		FileOutputStream saida = null;
		try {
			saida = new FileOutputStream(novoArquivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		copiar(arquivoCarregado, saida);
	}

	private static void copiar(InputStream origem, OutputStream destino) {
	    int bite = 0;
	    byte[] tamanhoMaximo = new byte[1024 * 8]; // 8KB
	    try {
	      // enquanto bytes forem sendo lidos
	      while((bite = origem.read(tamanhoMaximo)) >= 0) {
	          // pegue o byte lido e escreva no destino
	          destino.write(tamanhoMaximo, 0, bite);
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}
	
}
