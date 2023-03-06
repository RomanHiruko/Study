package NewTask;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.report.ProcessingReport;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.io.File;
import java.io.IOException;

import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;

public class JsonTask {
    static ObjectMapper objectMapper = new ObjectMapper();
    static File jsonFile = new File("src/test/java/NewTask/min-json.json");
    static File jsonScheme = new File("src/test/java/NewTask/vzr-1.0-json-shema.txt");

    public static void main(String[] args) throws IOException, ProcessingException {
        verify();
    }

    public static void parseValue() {
        try {
            // Прочитать JSON из файла
            JsonNode rootNode = objectMapper.readTree(jsonFile);

            // Достать Contract из BusinessData
            JsonNode contractNode = rootNode.path("BusinessData").path("Contract").get(0);

            // Извлечь ContractBody JSON строку из Contract
            String contractBodyJsonString = contractNode.path("ContractBody").asText();

            // Спарсить ContractBody JSON строку в Json
            JsonNode contractBodyNode = objectMapper.readTree(contractBodyJsonString);
            System.out.println(contractBodyNode.toString());

            // Достать Header из Contract
            JsonNode headerNode = contractBodyNode.path("Contract").get(0).path("Header");

            // Использовать JsonPath чтобы извлечь поле Number из Header
            DocumentContext jsonContext = JsonPath.parse(headerNode.toString());
            String numberField = jsonContext.read("$.Number");

            // Вывести значение Number
            System.out.printf("\"Number\": \"%s\"", numberField);
            System.out.println();

            String expectedJsonString = "\"Number\": \"21323207\"";
            JsonNode expectedJson = objectMapper.readTree(expectedJsonString);
            assertJsonEquals(expectedJson, headerNode);


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
