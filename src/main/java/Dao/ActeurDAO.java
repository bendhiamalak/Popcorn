package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Models.Acteur;

import utile.conxBD;

public class ActeurDAO {
	// Connection
			private static Connection conn = conxBD.getInstance();
			
			///login
			public static boolean  login(String mail,String mdp) {
				PreparedStatement pstmt = null;
			    ResultSet rs = null;
			    try {
			    	String sql= "SELECT * FROM ACTEUR WHERE EMAIL=? AND MDP=?";
			    	pstmt = conn.prepareStatement(sql);
			        pstmt.setString(1, mail);
			        pstmt.setString(2, mdp);
			        rs = pstmt.executeQuery();
			        if (rs.next()) {
			            System.out.println("Login avec succès");
			            return true;
			        } else {
			            System.out.println("Veuillez vérifier vos coordonnées!");
			            return false;
			        }
			    } catch (SQLException ex) {
			        System.out.println(ex.getMessage());
			        return false;
			}
		}
			
							
			
			
			
			
			////************************Ajouter Acteur*********************************
			public static int ajouterActeur(Acteur acteur) {
				int ActeurId = 0;
				System.out.println("-------connexion is "+conn);
				PreparedStatement pstmt = null;
			    ResultSet rs = null;
		         
		        try {
		        	String sql = "INSERT INTO ACTEUR VALUES (?,?,?,?,?,?)";
	        		pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		        	System.out.println("coonexion is---------------------- "+conn);
		        	pstmt.setInt(1, acteur.getId_acteur());
		        	pstmt.setString(2, acteur.getNom_ac());
		        	pstmt.setString(3, acteur.getPrenom_ac());
		        	pstmt.setObject(4, acteur.getDate_naissance_ac());
		        	pstmt.setString(5, acteur.getMail_ac());
		        	pstmt.setString(6, acteur.getMdp_ac());
		        	pstmt.executeUpdate();
		        	
		            // 4- Recupérer l'Id généré par le SGBD
		        	rs = pstmt.getGeneratedKeys();
		            
		            if(rs.next())
		            	ActeurId = rs.getInt(1);
		            
		        }catch (SQLException ex) {
		            System.out.println(ex.getMessage());
		        }
				return ActeurId;
			}
			
			
			//****************************************************************************
			//Modification Acteurr
			public static int modifNomActeur(int id_Ac,String nom) {
				int ActId = 0;
				PreparedStatement pstmt = null;
			    ResultSet rs = null;
			    try {
		            String sql = "UPDATE ACTEUR SET NOM_AC=? WHERE ID_ACTEUR=?";
		            pstmt = conn.prepareStatement(sql);
		            pstmt.setString(1, nom);
		        	pstmt.setInt(2, id_Ac);
		        	pstmt.executeUpdate();
		            // 4- Recupérer l'Id généré par le SGBD
		        	rs = pstmt.getGeneratedKeys();

		        }catch (SQLException ex) {
		            System.out.println(ex.getMessage());
		        }
				return id_Ac;
			}
			
			//****************************************************************
			public static int modifPrenomActeur(int id_Ac,String prenom) {
				int ActId = 0;
				PreparedStatement pstmt = null;
			    ResultSet rs = null;
			    try {
		            String sql = "UPDATE ACTEUR SET PRENOM_AC=? WHERE ID_ACTEUR=?";
		            pstmt = conn.prepareStatement(sql);
		            pstmt.setString(1, prenom);
		        	pstmt.setInt(2, id_Ac);
		        	pstmt.executeUpdate();
		            // 4- Recupérer l'Id généré par le SGBD
		        	rs = pstmt.getGeneratedKeys();

		        }catch (SQLException ex) {
		            System.out.println(ex.getMessage());
		        }
				return id_Ac;
			}
			//**********************************************************
			public static int modifMdpActeur(int id_Ac,String mdp) {
				int ActId = 0;
				PreparedStatement pstmt = null;
			    ResultSet rs = null;
			    try {
		            String sql = "UPDATE ACTEUR SET MDP=? WHERE ID_ACTEUR=?";
		            pstmt = conn.prepareStatement(sql);
		            pstmt.setString(1, mdp);
		        	pstmt.setInt(2,id_Ac);
		        	pstmt.executeUpdate();
		            // 4- Recupérer l'Id généré par le SGBD
		        	rs = pstmt.getGeneratedKeys();

		        }catch (SQLException ex) {
		            System.out.println(ex.getMessage());
		        }
				return id_Ac;
			}
			
			//************************************************************
			public static int modifAnnifActeur(int id_Ac,LocalDate annif) {
				int ActId = 0;
				PreparedStatement pstmt = null;
			    ResultSet rs = null;
			    try {
		            String sql = "UPDATE ACTEUR SET DATE_NAISSANE_AC=? WHERE ID_ACTEUR=?";
		            pstmt = conn.prepareStatement(sql);
		            pstmt.setObject(1, annif);
		        	pstmt.setInt(2, id_Ac);
		        	pstmt.executeUpdate();
		            // 4- Recupérer l'Id généré par le SGBD
		        	rs = pstmt.getGeneratedKeys();

		        }catch (SQLException ex) {
		            System.out.println(ex.getMessage());
		        }
				return id_Ac;
			    }
			
			
		
			
			//FindALL
			public static List<Acteur> findAll() throws SQLException{
				
				Statement stmt = null;
			    ResultSet rs = null;
			    
				List<Acteur> acteurs = new ArrayList<>();

		        String SQL = "SELECT * FROM ACTEUR";
		        try {
		        	stmt = conn.createStatement();
		            rs = stmt.executeQuery(SQL);

		            while (rs.next()) {

		            	int id_ac = rs.getInt(1);
		                String Nom_ac = rs.getString(2);
		                String Prenom_ac = rs.getString(3);
		                String mail_ac = rs.getString(5);
		                String mdp_ac = rs.getString(6);
		                LocalDate annif_ac=rs.getObject(4,LocalDate.class);
		               

		                Acteur act = new Acteur(id_ac, Nom_ac, Prenom_ac,mail_ac,mdp_ac,annif_ac);
		                acteurs.add(act);
		            }
		        } catch (Exception e ) {};
		        
		        return acteurs;
			}
	
			
			
			
			//Suppression Acteur
			public static int supprimerActeur(int id_ac) {
				int ActId = 0;
				PreparedStatement pstmt = null;
			    ResultSet rs = null;
		        
		        try {
		            String sql = "DELETE FROM ACTEUR WHERE ID_ACTEUR=?";
		            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		            pstmt.setInt(1,id_ac );
		            pstmt.executeUpdate();
		         // 4- Recupérer l'Id généré par le SGBD
		        	rs = pstmt.getGeneratedKeys();
		            
		            if(rs.next())
		            	ActId = rs.getInt(1);
		            
		        	

		        }catch (SQLException ex) {
		            System.out.println(ex.getMessage());
		        }
				return id_ac;
			}
			
			
			//******************************ConsulterNB episode********************************
			public static int ConsulterNbEp(String titre, int numS) {
				int id= Dao.ShowDAO.findIdShow(titre);
					int nb=0;
				PreparedStatement pstmt = null;
			    ResultSet rs = null;
			if (id!=0) {
			    try {
		            String sql = "SELECT NB_EP_S FROM SAISON WHERE ID_SHOW=? AND NUM_SAISON=?";
		            pstmt = conn.prepareStatement(sql);
		            pstmt.setInt(1, id);
		            pstmt.setInt(2, numS);
		            rs = pstmt.executeQuery();
		            
		            while (rs.next()) {
		            	 nb = rs.getInt(1);
		            	}
		            if (nb == 0) {
		                System.out.println("Saison invalide");
		            }
		        }catch (SQLException ex) {
		            System.out.println(ex.getMessage());
		        }
			    return nb;
			    }
			else {
				System.out.println("Show n existe pas");
				return nb;
				}
			}
			
			
			
	//*******************rechercher par email****************************
			public static  int RechercherParEmail(String mail) {
				//Statement stmt = null;
				int id=0;
			    ResultSet rs = null;
			    PreparedStatement pstmt = null;

		        String SQL = "SELECT * FROM ACTEUR WHERE EMAIL=?";
		        try {
		        	pstmt = conn.prepareStatement(SQL);
			        pstmt.setString(1,mail);
			        rs = pstmt.executeQuery();
			        while (rs.next()) {
			        	 id= rs.getInt(1);
		              
		                }
			      
			    } catch (SQLException ex) {
			        System.out.println(ex.getMessage());
		        }
				return id;
		       }
			//*************verifier id**************************
			 public static boolean verifierId(int id) {
			        boolean existe = false;
			        PreparedStatement pstmt = null;
			        ResultSet rs = null;
			        try {
			            String sql = "SELECT * FROM ACTEUR WHERE ID_ACTEUR=?";
			            pstmt = conn.prepareStatement(sql);
			            pstmt.setInt(1, id);
			            rs = pstmt.executeQuery();
			            if (rs.next()) {
			                existe = true;
			            }
			        } catch (SQLException ex) {
			            System.out.println(ex.getMessage());
			        }
			        return existe;
			    }
			 
			 
		//*******************************************	 
			 public static  Acteur getActeur(String mail) {
					//Statement stmt = null;
					Acteur a= new Acteur();
					//int id=0;
				    ResultSet rs = null;
				    PreparedStatement pstmt = null;

			        String SQL = "SELECT * FROM ACTEUR WHERE EMAIL=?";
			        try {
			        	pstmt = conn.prepareStatement(SQL);
				        pstmt.setString(1,mail);
				        rs = pstmt.executeQuery();
				        while (rs.next()) {
				        	int id = rs.getInt(1);
			                String nom = rs.getString(2);
			                String prenom = rs.getString(3);
			                LocalDate annif=rs.getObject(4,LocalDate.class);

				            //a = new Acteur(id, nom, prenom,annif);
			              a.setId_acteur(id);
			              a.setNom_ac(nom);
			              a.setPrenom_ac(prenom);
			              a.setDate_naissance_ac(annif);
			                }
				        
				      
				    } catch (SQLException ex) {
				        System.out.println(ex.getMessage());
			        }
					return a;
			       }
			 
			 
			 
	//**********************************************************************
			 public static int trouverIdActeurS(String nomActeur) {
			        PreparedStatement pstmt = null;
			        ResultSet rs = null;
			        int idActeur = -1; // valeur par défaut en cas de non correspondance
			        try {
			            
			            String sql = "SELECT a.id_acteur FROM acteur a "
			                         + "JOIN role_principale rp ON a.id_acteur = rp.id_acteur "
			                         + "JOIN role_secondaire rs ON a.id_acteur = rs.id_acteur "
			                         + "WHERE a.nom_ac = ?";
			            pstmt = conn.prepareStatement(sql);
			            pstmt.setString(1, nomActeur);
			            
			            // Exécution de la requête SQL
			            rs = pstmt.executeQuery();
			            
			            // Traitement du résultat de la requête SQL
			            if (rs.next()) {
			                idActeur = rs.getInt("id_acteur");
			            }
			        } catch (SQLException e) {
			            e.printStackTrace();
			        }
					return idActeur; }

			 
			 
			 public static void ajouterFavoriS(int idUser, String nomActeur) {
			        // Trouver l'ID de l'acteur en fonction de son nom
			        int idActeur = trouverIdActeurS(nomActeur);
			        
			        // Si l'acteur n'existe pas, sortir de la fonction
			        if (idActeur == -1) {
			            System.out.println("Acteur introuvable !");
			            return;
			        }
			        
			        // Connexion à la base de données Oracle
			        Connection conn = null;
			        PreparedStatement pstmt = null;
			        try {
			            
			            // Préparation de la requête SQL pour ajouter l'entrée à la table favorisacteur
			            String sql = "INSERT INTO acteurfavoris (id_user, id_acteur) VALUES (?, ?)";
			            pstmt = conn.prepareStatement(sql);
			            pstmt.setInt(1, idUser);
			            pstmt.setInt(2, idActeur);
			            
			            // Exécution de la requête SQL
			            pstmt.executeUpdate();
			        } catch (SQLException e) {
			            e.printStackTrace();
			        } finally {
			            // Fermeture des ressources
			            if (pstmt != null) {
			                try {
			                    pstmt.close();
			                } catch (SQLException e) {
			                    e.printStackTrace();
			                }
			            }
			            if (conn != null) {
			                try {
			                    conn.close();
			                } catch (SQLException e) {
			                    e.printStackTrace();
			                }
			            }
			        }
			    }
			 
			 
			 
}

