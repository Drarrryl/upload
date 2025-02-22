package data_access;

import com.google.gson.*;
import entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccess implements DataAccessInterface{

    private final String dataPath = "src/data_access/data.json";
    private final String fileName = "data.json";
    private JsonObject data;
    private final ImageKitIoAPIInterface ImageKitIOAPI = new ImageKitIoAPI();

    public DataAccess() {
        LoadData();
    }

    public JsonObject getData() {
        return data;
    }

    private void LoadData() {

        try {
            ImageKitIOAPI.download(dataPath, fileName);
        } catch (Exception e) {
            // try again
            try {
                Thread.sleep(2000);
                ImageKitIOAPI.download(dataPath, fileName);
            } catch (Exception e2) {
                // try again
                throw new RuntimeException("Error Downloading from Image Kit, Try again a bit later");
            }
        }

        Gson gson = new Gson();

        try {
            BufferedReader br = new BufferedReader(new FileReader(dataPath));
            data = gson.fromJson(br, JsonObject.class);
            System.out.println(gson.fromJson(br, JsonObject.class));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    void SaveData() {
        Gson gson = new Gson();

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(dataPath));
            String json = gson.toJson(data);
            bw.write(json);
            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            ImageKitIOAPI.upload(dataPath, fileName);
        } catch (Exception e) {
            // try again
            try {
                Thread.sleep(2000);
                ImageKitIOAPI.upload(dataPath, fileName);
            } catch (Exception e2) {
                // try again
                throw new RuntimeException("Error Uploading to Image Kit, Try again a bit later");
            }
        }

    }

    @Override
    public void createUser(User user) {

        //checks for existence
        try {
            data.get("USER_" + user.getUsername()).getAsJsonObject();

        } catch(Exception e) {
            String strUserData = "{password:" + user.getPassword() + "}";

            JsonElement userData = JsonParser.parseString(strUserData).getAsJsonObject();
            data.add("USER_" + user.getUsername(), userData);

            SaveData();
            return;
        }
        throw new RuntimeException();
    }

    @Override
    public User readUser(String username) {
        LoadData();

        //checks for existence
        try {
            JsonObject userData = data.get("USER_" + username).getAsJsonObject();
            String password = userData.get("password").getAsString();

            return new User(username, password);

        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public void updateUser(User user) {
        LoadData();

        try {
            data.remove("USER_" + user.getUsername());
            createUser(user);  // contains SaveData()

        } catch(Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteUser(String username) {
        LoadData();

        //checks for existence
        try {
            data.remove("USER_" + username);
            SaveData();

        } catch(Exception e) {
            throw new RuntimeException();
        }
    }
}
