package Json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Persons {

    private final JsonArray personArray = new JsonArray();
    private final JsonObject personObject = new JsonObject();

    public JsonObject getPersonObject(){
        return personObject;
    }

    public Persons addBirthday(String birthday) {
        personObject.addProperty("Birthday", birthday);
        return this;
    }

    public Persons addGender(String gender) {
        personObject.addProperty("Gender", gender);
        return this;
    }

    public Persons isResident(boolean isResident) {
        personObject.addProperty("IsResident", isResident);
        return this;
    }

    public Persons addFirstNameRus(String firstNameRus) {
        personObject.addProperty("FirstNameRus", firstNameRus);
        return this;
    }

    public Persons addLastNameRus(String lastNameRus) {
        personObject.addProperty("LastNameRus", lastNameRus);
        return this;
    }

    public Persons addMiddleNameRus(String middleNameRus) {
        personObject.addProperty("MiddleNameRus", middleNameRus);
        return this;
    }

    public Persons addEmails() {
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

    public Persons addPhones() {
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

    public JsonArray build() {
        personArray.add(personObject);
        return personArray;
    }
}
