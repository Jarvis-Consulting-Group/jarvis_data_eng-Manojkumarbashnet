package com.manoj.jdbc;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Hello world!
 *
 */
public class jDBCExecutor
{
    public static void main(String... args){
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "hplussport", "postgres", "password");
        try{
            Connection connection = dcm.getConnection();
            CustomerDAO customerDAO= new CustomerDAO(connection);
            /*Customer customer=new Customer();
            customer.setFirstname("Manoj");
            customer.setLastname("Kumar");
            customer.setEmail("manoj@gmail.com");
            customer.setPhone("4372200256");
            customer.setAddress("29 Brewton Rd");
            customer.setCity("Toronto");
            customer.setState("Punjab");
            customer.setZipcode("M1G1W2");
            customerDAO.create(customer);*/

            Customer customer=customerDAO.findById(999);
            System.out.println(customer.getFirstName()+" "+customer.getLastName()+""+customer.getEmail());
            /*customer.setEmail("bash@gmail.com");
            customer=customerDAO.update(customer);
            System.out.println(customer.getFirstName()+" "+customer.getLastName()+""+customer.getEmail());*/
//            customerDAO.delete(customer.getId());
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

}
