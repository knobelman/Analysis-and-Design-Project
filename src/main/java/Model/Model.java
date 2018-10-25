package Model;

import javafx.scene.control.*;

/**
 * Created by Maor on 10/24/2018.
 */
public class Model {
    private DBconnection dBconnection;
    protected String current_user;

    public Model(){
        dBconnection = new DBconnection();
    }

    public boolean insert_form(String username_to_string, String password_to_string, String fname_to_string, String lname_to_string, String date_to_string, String city_to_string){
        User user = new User(username_to_string, password_to_string, fname_to_string, lname_to_string, date_to_string, city_to_string);
        boolean done = dBconnection.insertUser(user);
        if(!done){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Username taken");
            alert.setContentText("\"" +username_to_string + "\" - This username is already taken. Try a diffenet one!");

            alert.showAndWait();
            return false;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Welcome!");
            alert.setHeaderText("User Created!");
            alert.setContentText("Welcome \"" +username_to_string + "\" to Vacation4U!");

            alert.showAndWait();
            return true;
        }
    }

    public void setCurrent_user(String currentuser) {
        this.current_user = currentuser;
    }

    public boolean checkValidUser(String userName, String pw){
        if(this.dBconnection.validateUser(userName,pw)){
            this.current_user = userName;
            return true;
        }
        else{
            return false;
        }
    }

    public User getUserInfo(String username){
        return dBconnection.readUser(username);
    }

    public String getCurrentuser() {
        return current_user;
    }

    public void updateUserInfo(String userName, User newUser){
        dBconnection.updateUser(userName,newUser);
    }

    public void deleteUser(String username){
        dBconnection.removeUser(username);
    }

    public boolean checkifExists(String username) {
        if(this.dBconnection.readUser(username) == null){
            return false;
        }
        else{
            return true;
        }
    }
}