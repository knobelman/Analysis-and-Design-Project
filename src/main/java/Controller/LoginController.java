package Controller;

public class LoginController extends AController{

    public boolean checkValidUser(String username , String password) {
        if(myModel.checkValidUser(username,password) == true) {
            myModel.setCurrent_user(username);
            return true;
        }else
            return false;
    }
    
}
