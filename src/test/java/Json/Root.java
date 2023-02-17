package Json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Root {
    public static void main(String[] args) {
        String json = new Builder()
                .setContrAgent()
                .addContract()
                .build();
        System.out.println(json);
    }
}
