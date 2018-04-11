package S6.voyage.dao;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import S6.voyage.utilitaire.Fonction;
import S6.voyage.baseModel.*;



public class GeneriqueDao 
{
	private String nomClasse;
	private Field[] attributs;
	public GeneriqueDao(){}
	public void setAttribute(BaseModel baseM)
	{
		this.attributs= baseM.getClass().getDeclaredFields();
		this.nomClasse=baseM.getClass().getSimpleName();
	}
	//--------------------------------------- Insert Generique update() ------------------------------------------------//
	public String saveRequest(BaseModel baseM)
	{
		 String req = "INSERT INTO "+nomClasse.toLowerCase();
       
         req += "(";
         //------------ ajout des attributs dans la requete ------------------//
         for(int i=0;i<attributs.length;i++)
         {
        	 if(Fonction.isPrimitif(attributs[i].getType().getSimpleName()))
        	 {
        		if(i!=0) req += ",";
        		req += attributs[i].getName(); 
        	 }	
         }
         req += ") values (";
         for(int i=0;i<attributs.length;i++)
         {
        	 if(Fonction.isPrimitif(attributs[i].getType().getSimpleName()))
        	 {
        		if(i!=0) req+=",";
        		req += "?";
        	 }	
 		 }
 		 req += ")";
 		 System.out.println(req);
		 return req;
	}
	public void executeSave(BaseModel baseM,PreparedStatement stmt) throws Exception
	{
		for(int i=0;i<attributs.length;i++){
			 if(Fonction.isPrimitif(attributs[i].getType().getSimpleName()))
			 {
				Object o = getValue(baseM,attributs[i]);
				if(o instanceof java.util.Date){
					stmt.setDate(i+1, new java.sql.Date(((java.util.Date)o).getTime()));
				}else{
					stmt.setObject(i+1, o);
				}
			 }
		}
		stmt.executeUpdate();
	}
	public void add(BaseModel baseM) throws Exception
	{
		setAttribute(baseM);
		Connection c = null;
        PreparedStatement stmt = null;
        try {
            c = ConnectBase.getCon();
            String req=saveRequest(baseM);
            stmt = c.prepareStatement(req);
            executeSave(baseM,stmt);	
        }catch (Exception e){
            throw e;
        }finally {

            if(stmt != null){
                stmt.close();
            }
            if(c != null){
                c.close();
            }
        }
	}
	public int getLastInsertedId(BaseModel baseM) throws Exception {
		setAttribute(baseM);
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        try {
            c = ConnectBase.getCon();
            String req = "SELECT max(id_"+nomClasse.toLowerCase()+") FROM "+nomClasse.toLowerCase();
            stmt = c.prepareStatement(req);
            res = stmt.executeQuery();
            res.next();
            return res.getInt(1);
        }catch (Exception e){
            throw e;
        }finally {
            if(res != null){
                res.close();
            }
            if(stmt != null){
                stmt.close();
            }
            if(c != null){
                c.close();
            }
        }
    }

	//--------------------------------------- Update Generique update() ------------------------------------------------//
	public String updateRequest(BaseModel baseM)
	{
		 String req = "UPDATE "+nomClasse.toLowerCase()+" SET ";
         //------------ ajout des attributs dans la requete ------------------//
         for(int i=0;i<attributs.length;i++)
         {
        	 if(Fonction.isPrimitif(attributs[i].getType().getSimpleName()))
        	 {
        		if(i!=0) req += ",";
        		req += attributs[i].getName()+"=? "; 
        	 }	
         }
         req+="WHERE id_"+nomClasse.toLowerCase()+"=?";
 		 System.out.println(req);
		 return req;
	}
	private void executeUpdate(BaseModel baseM,PreparedStatement stmt)throws Exception {
		int nbrPrimitiveField=0;
		for(int i=0;i<attributs.length;i++){
			 if(Fonction.isPrimitif(attributs[i].getType().getSimpleName()))
			 {
				Object o = getValue(baseM,attributs[i]);
				if(o instanceof java.util.Date){
					stmt.setDate(i+1, new java.sql.Date(((java.util.Date)o).getTime()));
				}else{
					stmt.setObject(i+1, o);
				}
				nbrPrimitiveField++;
			 }
		}
		stmt.setInt(nbrPrimitiveField+1, baseM.getId());
		stmt.executeUpdate();
	}
	public void update(BaseModel baseM) throws Exception {
		setAttribute(baseM);
	    Connection c = null;
	    PreparedStatement stmt = null;
	    try {
	        c = ConnectBase.getCon();
	        String req = this.updateRequest(baseM);
	        stmt = c.prepareStatement(req);
	        this.executeUpdate(baseM, stmt);
	    }catch (Exception e){
	        throw e;
	    }finally {
	        if(stmt != null){
	            stmt.close();
	        }
	        if(c != null){
	            c.close();
	        }
	    }
	}
	//--------------------------------------- Delete Generique delete() ------------------------------------------------//
	public String deleteRequest(BaseModel baseM)
	{
		 String req = "DELETE FROM "+nomClasse.toLowerCase();
         req+=" WHERE id_"+nomClasse.toLowerCase()+"=?";
 		 System.out.println(req);
		 return req;
	}
	public void delete(int id,BaseModel baseM) throws Exception {
		setAttribute(baseM);
	    Connection c = null;
	    PreparedStatement stmt = null;
	    try {
	        c = ConnectBase.getCon();
	        String req = this.deleteRequest(baseM);
	        stmt = c.prepareStatement(req);
            stmt.setInt(1, id);
            stmt.executeUpdate();
	    }catch (Exception e){
	        throw e;
	    }finally {
	        if(stmt != null){
	            stmt.close();
	        }
	        if(c != null){
	            c.close();
	        }
	    }
	}
	//--------------------------------------- FindById Generique findById() ------------------------------------------------//
	public String findByIdRequest(BaseModel baseM)
	{
		 String req = "SELECT * FROM "+nomClasse.toLowerCase();
         req+=" WHERE id_"+nomClasse.toLowerCase()+"=?";
 		 System.out.println(req);
		 return req;
	}
	public int getNbrArgument()
	{
		int taille=0;
		for(int i=0;i<attributs.length;i++){
			 if(Fonction.isPrimitif(attributs[i].getType().getSimpleName()))taille++;
		}
		return taille;
	}
	public Class instanceAttribut(String attribut) throws ClassNotFoundException
	{
		switch(attribut){
		case "int" :	
			return int.class;
		case "double" :
			return double.class;
		case "float" :
			return float.class;
		}
		return Class.forName(attribut);
	}
	public Object getValueOfRes(ResultSet res,String type,int arg) throws Exception{
		Method m = res.getClass().getMethod("get"+Fonction.toUpperFirst(type),int.class);
		Object o = m.invoke(res,arg);
		return o;
	}
	public BaseModel returnFindById(BaseModel baseM,ResultSet res) throws Exception
	{
		int taille = getNbrArgument()+1;
		Class[] arguments=new Class[taille];
		Object[] allData = new Object[taille];
		
		arguments[0]=int.class;
		//remplir arguments[]
		for(int i=0;i<attributs.length;i++){
			 if(Fonction.isPrimitif(attributs[i].getType().getSimpleName())){
				 arguments[i+1]=this.instanceAttribut(attributs[i].getType().getName());
			 }
		}
		//remplir allData[]
		for(int i=0;i<taille;i++){
			Object o=this.getValueOfRes(res, arguments[i].getSimpleName(),i+1);
			 allData[i]=o;
		}
		Constructor constructeur= baseM.getClass().getConstructor(arguments);
		BaseModel data=(BaseModel)constructeur.newInstance(allData);
		return data;
	}
	public void setValue(BaseModel baseM,Field field,Object arg) throws Exception{
		Method m = baseM.getClass().getMethod("set"+Fonction.toUpperFirst(field.getName()),field.getType());
		Object o = m.invoke(baseM,arg);
	}
	public Object getValue(BaseModel baseM,Field field) throws Exception{
		Method m = baseM.getClass().getMethod("get"+Fonction.toUpperFirst(field.getName()));
		Object o = m.invoke(baseM);
		return o;
	}
	public Object getIdRecursive(BaseModel baseM,Field field) throws Exception{
		Method m = baseM.getClass().getMethod("getId_"+field.getName().toLowerCase());
		Object o = m.invoke(baseM);
		return o;
	}
	public BaseModel findById(int id,BaseModel baseM,boolean findZanany) throws Exception {
		setAttribute(baseM);
		ArrayList<Field> otherAttribut=Fonction.getOtherAttribut(attributs);
		ArrayList<Field> attributList=Fonction.getAttributList(attributs);
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        try {
            c = ConnectBase.getCon();
            String req =this.findByIdRequest(baseM);
            stmt = c.prepareStatement(req);
            stmt.setInt(1,id);
            res = stmt.executeQuery();
            res.next();
    		BaseModel pere=this.returnFindById(baseM, res);
    		 if(findZanany)
             {
             	//remplir otherAttribut de baseModel
         		for(Field attribut : otherAttribut)
         		{
         			//new Fils()
         			BaseModel basem=(BaseModel)attribut.getType().newInstance();
         			//idHotel
         			int o=(int)this.getIdRecursive(pere, attribut);
         			BaseModel fils=this.findById(o, basem, false);
         			this.setValue(pere, attribut,fils);
         		}
         		//remplir attributList de baseModel
         		for(Field attribut : attributList)
         		{
         			BaseModel instanceBaseFils=(BaseModel)Fonction.instanceListObject(attribut).newInstance();
         			List<BaseModel> baseFils=this.findAllById(instanceBaseFils, pere.getClass().getSimpleName(),pere.getId());
         			this.setValue(pere, attribut,baseFils);
         		}
             }
           
            return pere;
        }catch (Exception e){
            throw e;
        }finally {
            if(res != null){
                res.close();
            }
            if(stmt != null){
                stmt.close();
            }
            if(c != null){
                c.close();
            }
        }
    }
	//--------------------------------------- FindAll Generique findAll() ------------------------------------------------//
	public String findAllRequest(BaseModel baseM)
	{
		 String req = "SELECT * FROM "+nomClasse.toLowerCase();
 		 System.out.println(req);
		 return req;
	}
	// get List<Object> into Object
	public String findAllByIdRequest(BaseModel baseM,String objectPere)
	{
		 String req = "SELECT * FROM "+nomClasse.toLowerCase();
		 req+=" WHERE id_"+objectPere+"=?";
 		 System.out.println(req);
		 return req;
	}
	public List<BaseModel> returnFindAll(BaseModel baseM,ResultSet res) throws Exception
	{
		List<BaseModel> allBaseM=new ArrayList<BaseModel>();
		while(res.next()){
			allBaseM.add(this.returnFindById(baseM, res));
		}
		return allBaseM;
		
	}
	//-------------------------------- find zanany -------------------------------//
	public List<BaseModel> findAllById(BaseModel baseM,String objectPere,int idPere) throws Exception {
		setAttribute(baseM);
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        try {
            c = ConnectBase.getCon();
            String req =this.findAllByIdRequest(baseM,objectPere);
            stmt = c.prepareStatement(req);
            stmt.setInt(1,idPere);
            res = stmt.executeQuery();
            List<BaseModel> allBaseM=this.returnFindAll(baseM, res);
            return allBaseM;
        }catch (Exception e){
            throw e;
        }finally {
            if(res != null){
                res.close();
            }
            if(stmt != null){
                stmt.close();
            }
            if(c != null){
                c.close();
            }
        }
    }

	public List<BaseModel> findAll(BaseModel baseM,boolean findZanany) throws Exception {
		setAttribute(baseM);
		ArrayList<Field> otherAttribut=Fonction.getOtherAttribut(attributs);
		ArrayList<Field> attributList=Fonction.getAttributList(attributs);
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        try {
            c = ConnectBase.getCon();
            String req =this.findAllRequest(baseM);
            stmt = c.prepareStatement(req);
            res = stmt.executeQuery();
            List<BaseModel> allBaseM=this.returnFindAll(baseM, res);
            for(BaseModel pere : allBaseM)
            {
            	 if(findZanany)
                 {
                 	//remplir otherAttribut de baseModel
             		for(Field attribut : otherAttribut)
             		{
             			//new Fils()
             			BaseModel basem=(BaseModel)attribut.getType().newInstance();
             			//idHotel
             			int o=(int)this.getIdRecursive(pere, attribut);
             			BaseModel fils=this.findById(o, basem, false);
             			this.setValue(pere, attribut,fils);
             		}
             		//remplir attributList de baseModel
             		for(Field attribut : attributList)
             		{
             			BaseModel instanceBaseFils=(BaseModel)Fonction.instanceListObject(attribut).newInstance();
             			List<BaseModel> baseFils=this.findAllById(instanceBaseFils, pere.getClass().getSimpleName(),pere.getId());
             			this.setValue(pere, attribut,baseFils);
             		}
                 }
            }
            return allBaseM;
        }catch (Exception e){
            throw e;
        }finally {
            if(res != null){
                res.close();
            }
            if(stmt != null){
                stmt.close();
            }
            if(c != null){
                c.close();
            }
        }
    }
//------------------------------------------------ recherche Tout et tout ----------------------------------------//
	public String baseModelIsEmpty(BaseModel baseM)
	{
		Field[] attributs=baseM.getClass().getDeclaredFields();
		return null;
	}
	
	public static void main(String[] args) throws Exception {
        GeneriqueDao gen = new GeneriqueDao(); 
        Commodite baseM=new Commodite("MiniBar");
        //gen.add(baseM);
        //gen.update(baseM);
        //gen.delete(7,new Commodite());
        
        Destination dest =(Destination)gen.findById(4, new Destination(),true);
        System.out.print(dest.getId()+" ");
        System.out.println(dest.getNom_destination()+" ");
       /* List<Hotel>listHot=dest.getList_hotel();
       for(Hotel hot : dest.getList_hotel()){
    	   System.out.print(hot.getId()+" ");
    	   System.out.println(hot.getNom_hotel());
        }*/
       
        
       /* Chambre com =(Chambre)gen.findById(8, new Chambre(),true);
        System.out.print(com.getId()+" ");
    	System.out.print(com.getId_hotel()+" ");
    	System.out.print(com.getNbr_grand_lit()+" ");
    	System.out.print(com.getNbr_petit_lit()+" ");
    	System.out.print(com.getNom_chambre()+" ");
    	System.out.println(com.getPrix());
    	System.out.println(com.getHotel().getNom_hotel());*/
    	
       /* List<BaseModel> allCom=gen.findAll(baseM);
        for(BaseModel com : allCom)
        {
        	Commodite co=(Commodite)com;
        	System.out.println(co.getNom_commodite());
        }*/
        
        /*List<BaseModel> allCom1=gen.findAll(new Chambre(),true);
        for(BaseModel com1 : allCom1)
        {
        	Chambre co1=(Chambre)com1;
        	System.out.print(co1.getId()+" ");
        	System.out.print(co1.getId_hotel()+" ");
        	System.out.print(co1.getNbr_grand_lit()+" ");
        	System.out.print(co1.getNbr_petit_lit()+" ");
        	System.out.print(co1.getNom_chambre()+" ");
        	System.out.println(co1.getPrix());
        	System.out.println(co1.getHotel().getDescription());
        }*/
    }
}
