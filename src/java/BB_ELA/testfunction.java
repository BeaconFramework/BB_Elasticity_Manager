/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BB_ELA;

/**
 *
 * @author giuseppe
 */
public class testfunction {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Util u=new Util();
       String borrower="review";
       String vmid="43e2e554-de0a-494f-b5cb-481ddaae9ff5";
       String dc="UME";
       String template="0abfd269-504c-4c9a-8dd4-d603557d44c8";//attenzione alle multiple istanziazioni dei template
       String a="";
       a=u.getTwinVMsfromTemplate(borrower, template);
       System.out.println(a);
       a=u.getInfoVM(borrower, vmid);
        System.out.println(a);
       a=u.getTwin(borrower, vmid, dc);
        System.out.println(a);
       a=u.getTwinVMs(borrower, vmid);//comportameto errato
       System.out.println(a);
       
    }
    
}
