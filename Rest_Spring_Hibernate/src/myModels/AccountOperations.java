package myModels;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import myEntitites.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AccountOperations {
	
	public ArrayList<Account> getAllAccount(){
		ArrayList<Account> list = new ArrayList<Account>();
		try {
			Session Ses;
			Configuration cfg=new Configuration().configure();
			SessionFactory sf=cfg.addAnnotatedClass(Account.class).buildSessionFactory();
			Ses=sf.getCurrentSession();
			Ses.beginTransaction();
			Query q=Ses.createQuery("from Account");
			list=(ArrayList<Account>) q.getResultList();
			
		}
		catch(Exception e) {
			System.out.print(e);
		}
		
		return list;
	}
	
   public Account getAccountinfobyno(String accno) {
	   Account a=new Account();
	   
	   try {
		   Session Ses;
			Configuration cfg=new Configuration().configure();
			SessionFactory sf=cfg.addAnnotatedClass(Account.class).buildSessionFactory();
			Ses=sf.getCurrentSession();
			Ses.beginTransaction();
			Query q=Ses.createQuery("from Account where accno=:no");
			q.setParameter("no", accno);
			List lst=q.getResultList();
			if(lst.size()>0) {
			 a=(Account) lst.get(0);
			}
	   }
	   catch(Exception e) {
		   System.out.print(e);
	   }
	   
	   return a;
   }
   
   public ArrayList<Account> getAllAccountbytype(String acctype){
		ArrayList<Account> list = new ArrayList<Account>();
		try {
			Session Ses;
			Configuration cfg=new Configuration().configure();
			SessionFactory sf=cfg.addAnnotatedClass(Account.class).buildSessionFactory();
			Ses=sf.getCurrentSession();
			Ses.beginTransaction();
			Query q=Ses.createQuery("from Account where acctype=:type");
			q.setParameter("type", acctype);
			list=(ArrayList<Account>) q.getResultList();
			
		}
		catch(Exception e) {
			System.out.print(e);
		}
		
		return list;
	}
	
   public String deleteAccount(String accno){
		String Stat="";
		try {
			Session Ses;
			Configuration cfg=new Configuration().configure();
			SessionFactory sf=cfg.addAnnotatedClass(Account.class).buildSessionFactory();
			Ses=sf.getCurrentSession();
			Ses.beginTransaction();
			Query q=Ses.createQuery("delete from Account where accno=:id");
			q.setParameter("id", accno);
			int result=q.executeUpdate();
			Ses.getTransaction().commit();
			if(result>0) {
				Stat="Success";
			}
			else {
				Stat="Failed";
			}
			
		}
		catch(Exception e) {
			System.out.print(e);
		}
		
		return Stat;
	}
	
 public String createAccount(Account obj) {
	 String Stat="";
	 try {
		 Session Ses;
			Configuration cfg=new Configuration().configure();
			SessionFactory sf=cfg.addAnnotatedClass(Account.class).buildSessionFactory();
			
			Ses=sf.getCurrentSession();
			Ses.beginTransaction();
			Account obj2=new Account();
			obj2.setAccno(obj.getAccno());
			obj2.setAccnm(obj.getAccnm());
			obj2.setAcctype(obj.getAcctype());
			obj2.setBalance(obj.getBalance());
			Ses.save(obj2);
		    Ses.getTransaction().commit();
		    Stat="success";
		    
		   
		    
			
			
			
		 
	 }
	 catch(Exception e) {
		 System.out.print(e);
		 Stat="failed";
	 }
	 
	return Stat;
	 
 }
 
 public String TransferAmount(Transactions obj) {
	 String stat="";
	 try {
		 Session Ses;
			Configuration cfg=new Configuration().configure();
			SessionFactory sf=cfg.addAnnotatedClass(Transactions.class).buildSessionFactory();
			 SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
			    Date date = new Date();
			    String dt=formatter.format(date);
			    date=formatter.parse(dt);
			
			Ses=sf.getCurrentSession();
			Ses.beginTransaction();
			Transactions obj2=new Transactions();
			obj2.setFromacno(obj.getFromacno());
			obj2.setToacno(obj.getToacno());
			obj2.setAmount(obj.getAmount());
			obj2.setTransdt(date);
			Ses.save(obj2);
		    Ses.getTransaction().commit();
		    
		    Session Ses1;
		    Configuration cfg1=new Configuration().configure();
		    SessionFactory sf1=cfg1.addAnnotatedClass(Account.class).buildSessionFactory();
		    Ses1=sf1.getCurrentSession();
			Ses1.beginTransaction();
			Query q1=Ses1.createQuery("update Account set balance=balance-:bal where accno=:faccno");
			q1.setParameter("bal", obj.getAmount());
			q1.setParameter("faccno", obj.getFromacno());
			int res=q1.executeUpdate();
			Ses1.getTransaction().commit();
			
			
			
			 Session Ses2;
			    Configuration cfg2=new Configuration().configure();
			    SessionFactory sf2=cfg2.addAnnotatedClass(Account.class).buildSessionFactory();
			    Ses2=sf2.getCurrentSession();
				Ses2.beginTransaction();
				Query q2=Ses2.createQuery("update Account set balance=balance+:bal where accno=:taccno");
				q2.setParameter("bal", obj.getAmount());
				q2.setParameter("taccno", obj.getToacno());
				q2.executeUpdate();
				Ses2.getTransaction().commit();
				
				if(res>0) {
					 stat="Amount transfered Succesfully";
					    
				}
		    
		   
		   
		    
			
			
			
		 
	 }
	 catch(Exception e) {
		 System.out.print(e);
		 stat="failed";
	 }
	 
	return stat;
	 
 }
 

   
	

}
