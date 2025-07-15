package proje_02;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;

public class Administrator extends Human {

    private final String job = "Admin";
    private final Logger logger = Logger.getInstance(System.getProperty("user.dir") + "/src/Administrator_Log.txt"); 
    Scanner scan = new Scanner(System.in);

    private String Log_File = "C:\\Users\\Berk\\Documents\\NetBeansProjects\\Proje_02\\src\\Administrator_Log.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    ArrayList<User> users = new ArrayList<>();
    public User user;

    /*public Administrator() { liv2
        super();
    }
    public Administrator(String name, String surname, int age) {
        super();
    }*/
    
    /*public Administrator(String name, String surname, int age) {
        super(name, surname, "Admin", age);
        logger.logCreatedOn(getId(), name, surname, this.job);
    }*/
    public Administrator(String name, String surname, int age) {
        super(name, surname, "Admin" , age);
        logger.logCreatedOn(getId(), name, surname, this.job);
}

   public void userList() {
        System.out.println();
        System.out.println("----------------User List----------------");
        for (int i = 0; i < users.size(); i++) {
            
        User currentUser = users.get(i); //liv2+
        System.out.println(currentUser.getName() + " " + currentUser.getSurname());
            
            
        }
    }

   public int userNumber() {
        return users.size();
    }

  public  void seeLogHistory() {
      System.out.println(this.getName()+" "+this.getSurname() + " called Log History");
        logger.readLog();
        logger.copyLog("C:\\Users\\livan\\OneDrive\\Belgeler\\NetBeansProjects\\Proje_02\\src\\Log_Copy.txt");
    }

   public void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
            System.out.println(user.getName() + " " + user.getSurname() + " has been added.");
            logger.logAction(getId(), getName(), getSurname(), this.job, "added user " + user.getName() + " " + user.getSurname());
        } else {
            System.out.println(user.getName() + " " + user.getSurname() + " is already added.");
        }
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getSurname() {
        return super.getSurname();
    }

    @Override
    public void setSurname(String surname) {
        super.setSurname(surname);
    }

    @Override
    public int getAge() {
        return super.getAge();
    }

    @Override
    public void setAge(int age) {
        super.setAge(age);
    }

    public String getUsersId(User user) {
        return user.getId();
    }

    void deleteUser(User user) {
        if (users.contains(user)) {
            users.remove(user);
            Logger.getInstance(Log_File).logLogout(user.getId(), user.getName(), user.getSurname(), user.getJob_description());
        }
    }
}
