package com.datawings.app.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.apache.commons.lang.StringUtils;

public abstract class BatchUtil {
	public static void run(String cmde){
		try 
		{	
			System.out.println("cmde ["+cmde+" lance.");
			if (StringUtils.isBlank(cmde)){
				System.err.println("no command !");
				System.exit(-1);
			}
			Runtime runtime = Runtime.getRuntime();			
			final Process p = runtime.exec(cmde);
			// traiter les sorties du process en cours
			new Thread() {
				public void run() {
					//System.out.println("traiter les sorties du process en cours");
					processIO(p.getInputStream(), false);
				}
			}.start();;

			// traiter les erreurs du process en cours
			new Thread() {
				public void run() {
					//System.out.println("traiter les sorties du process en cours");
					processIO(p.getErrorStream(), true);
				}
			}.start();;
			System.out.println("cmde ["+cmde+" en cours d execution ...");
		}
		catch(Exception e){ e.printStackTrace(); }	

	}

	public static void runx(String cmde){
		try 
		{			
			System.out.println("cmde ["+cmde+" lance.");
			if (StringUtils.isBlank(cmde)){
				System.err.println("no command !");
				System.exit(-1);
			}
			ProcessBuilder pb = new ProcessBuilder("cmd");
			// le repertoir en cours de l'invite de commande est celui du programme en cours d'exécution
			// pour modifier ce répertoir utliser :
			// pb.directory("path complet");			
			// pb.directory();			
			pb.directory(new File("c:/projetsdatawings/bin/"));
			//Map<String, String> env = pb.environment();
			//env.putAll(System.getenv());
			final Process cmd = pb.start();				
			// traiter les sorties du process en cours
			new Thread() {
				public void run() {
					try {
							BufferedReader reader = new BufferedReader(new InputStreamReader(cmd.getInputStream()));
							String line = "";
						try {
							while((line = reader.readLine()) != null) {									
								System.err.println("->"+line);
							}
						} finally {
							reader.close();
						}
					} catch(IOException ioe) {
						ioe.printStackTrace();
					}
				}
			}.start();
 
			// traiter les erreurs
			new Thread() {
				public void run() {
					try {
						BufferedReader reader = new BufferedReader(new InputStreamReader(cmd.getErrorStream()));
						String line = "";
						try {
							while((line = reader.readLine()) != null) {
								System.out.println("!->"+ line);									
							}
						} finally {
							reader.close();
						}
					} catch(IOException ioe) {
						ioe.printStackTrace();
					}
				}
			}.start();
								
	     	PrintStream ps = new PrintStream(cmd.getOutputStream());
		    
		    // placer ici les commandes
		    //ps.println("monTreeTagger FichierCible.txt"); 
			//ps.println("ping.exe developpez.com -n 1");
	     	// ps.println("cd c:/projetsdatawings/bin/");
//	     	ps.println("java -jar  batchreqdyna.jar 055 PH test_trnc");
//	     	String str = "c:/projetsdatawings/bin/reqdyna.bat 055 PH test_trnc";
//	     	String str = "reqdyna.bat 055 PH test_trnc";
	     	ps.println(cmde);
			ps.close();		
			
		}
		catch(Exception e){ e.printStackTrace(); }	
	
	    
	}
    
    private  static void processIO(InputStream is, boolean stderr){
    	String str = stderr ? "stderr: " : "stdin: ";
    	
    	try {
    		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    		String line = "";
    		try {
    			while((line = reader.readLine()) != null) {
    				System.out.println(str+line);
    			}
    		} finally {
    			reader.close();
    		}
    	} catch(IOException ioe) {
    		ioe.printStackTrace();
    	}	
    } 
}
