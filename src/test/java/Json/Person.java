package Json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private JsonObject personObject = new JsonObject();

    public Person addEmails() {
        JsonObject emailTypeId = TypeId.createId();

        JsonArray emailArray = new JsonArray();
        JsonObject email = new JsonObject();
        email.addProperty("EmailAddress", "test@yandex.ru");
        email.add("EmailTypeId", emailTypeId);
        emailArray.add(email);

        JsonObject emails = new JsonObject();
        emails.add("Email", emailArray);

        personObject.add("Emails", emails);
        return this;
    }

    public Person addPhones() {
        JsonObject phoneTypeId = TypeId.createId();

        JsonArray phoneArray = new JsonArray();
        JsonObject phone = new JsonObject();
        phone.addProperty("FullNumber", "9800000000");
        phone.addProperty("CountryCode", "7");
        phone.add("TypeId", phoneTypeId);
        phoneArray.add(phone);

        JsonObject phones = new JsonObject();
        phones.add("Phone", phoneArray);

        personObject.add("Phones", phones);
        return this;
    }

    public JsonObject build(){
        personObject.addProperty("Birthday", "1979-06-23");
        personObject.addProperty("Gender", "M");
        personObject.addProperty("IsResident", true);
        personObject.addProperty("FirstNameRus", "Клиент");
        personObject.addProperty("LastNameRus", "Тестовый");
        personObject.addProperty("MiddleNameRus", "Тестович");
        return personObject;
    }
}
