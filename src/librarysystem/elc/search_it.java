/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem.elc;

/**
 *
 * @author Lenovo
 */
public class search_it {
    
    
        
   private static String book_code;
   private static String author_code;  
   
   public void setBook(String cd){
       this.book_code = cd;
   }
  
   
   public String getBook(){
       return this.book_code;
   }
     public void setAuthor(String ac){
       this.author_code = ac;
   }
  
   
   public String getAuthor(){
       return this.author_code;
   } 
    
}
