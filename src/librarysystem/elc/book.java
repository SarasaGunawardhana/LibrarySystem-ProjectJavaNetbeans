/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package librarysystem.elc;

/**
 *
 * @author Toshiba
 */
public class book {
 private static int id;
    private static String name;
    private static String description;
    private static String author;
    
    public void setid(int id){
        this.id = id;
    }
    public void setname(String name){
        this.name = name;
    }
    public void setauthor(String author)
    {
        this.author=author;
    }
    public void setdescription(String description){
        this.description = description;
    }
    
    public int getid(){
        return this.id;
    }

    public String getname(){
        return this.name;
    }
        public String getauthor(){
        return this.author;
    }
    public String getdescription(){
        return this.description;
    }

       
}
