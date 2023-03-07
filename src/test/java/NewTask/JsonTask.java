package NewTask;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.report.ProcessingReport;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;

public class JsonTask {
    static ObjectMapper objectMapper = new ObjectMapper();
    static File jsonFile = new File("src/test/java/NewTask/min-json.json");
    static File jsonScheme = new File("src/test/java/NewTask/vzr-1.0-json-shema.txt");
    static File parseJsonFile = new File("src/test/java/NewTask/resp.txt");
    static File expectedJson = new File("src/test/java/NewTask/qwe.json");
    static File outputFile = new File("src/test/java/NewTask/ParsedJson.json");

    @Test
    public void compareJsonTest() {
        parseValue(parseJsonFile, expectedJson);
    }

    public static void parseValue(File parseJsonFile, File expectedJson) {
        try {
            // Прочитать JSON из файла
            JsonNode rootNode = objectMapper.readTree(parseJsonFile);
            JsonNode expectedNode = objectMapper.readTree(expectedJson);

            // Достать Contract из BusinessData
            JsonNode contractNode = rootNode.path("BusinessData").path("Contract").get(0);

            // Извлечь ContractBody JSON строку из Contract
            String contractBodyJsonString = contractNode.path("ContractBody").asText();

            // Спарсить ContractBody JSON строку в Json
            JsonNode contractBodyNode = objectMapper.readTree(contractBodyJsonString);

            //Сравнить ожидаемый и полученный JSON
            assertJsonEquals(expectedNode, contractBodyNode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void verify() throws ProcessingException, IOException {

        // Загрузить JSON и JSON Schema
        JsonNode jsonNode = JsonLoader.fromFile(jsonFile);
        JsonNode schemaNode = JsonLoader.fromFile(jsonScheme);

        // Создать JSON Schema
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.byDefault();
        JsonSchema schema = schemaFactory.getJsonSchema(schemaNode);

        // Сравнить JSON и JSON Schema
        ProcessingReport report = schema.validate(jsonNode);
        if (report.isSuccess()) {
            System.out.println("JSON document is valid");
        } else {
            System.out.println("JSON document is not valid");
        }
    }
}
