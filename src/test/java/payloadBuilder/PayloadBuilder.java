package payloadBuilder;

import org.json.simple.JSONObject;

public class PayloadBuilder {

 public static JSONObject Loginpayload(String email,String password){
     JSONObject UserLogin = new JSONObject();
     UserLogin.put("email",email);
     UserLogin.put("password",password);
     return  UserLogin;

 }

 public static JSONObject RegisterPayload(String firstName, String lastName, String email, String password,String confirmPassword,String groupId){

     JSONObject UserRegistration = new JSONObject();
     UserRegistration.put("firstName",firstName);
     UserRegistration.put("lastName",lastName);
     UserRegistration.put("email",email);
     UserRegistration.put("password",password);
     UserRegistration.put("confirmPassword",confirmPassword);
     UserRegistration.put("groupId",groupId);
     return UserRegistration;

 }

 public static  JSONObject UpdateUserRolePayload(String role){
     JSONObject UpdateRole = new JSONObject();
     UpdateRole.put("role",role);
     return UpdateRole;
 }



}
