public class DatabaseUtil {

    // Requires com.google.json
    
    /* Example Json File

        [
            {
                "uuid": "ExampleUUID",
                "example-boolean-key": "true",
                "example-string-key": "Ikr?",
                "example-int-key": "1234"
            },
            {
                "uuid": "ExampleUUID2",
                "example-boolean-key2": "true",
                "example-string-key2": "Woooooah! No one will have carrot!",
                "example-int-key2": "4321"
            }
        ]

     */

    // Url of the File. Quick reminder: Only Raw Files! Any HTML Code will break it!

    private static String stringurl = "Your-Json-File-URL";

    // Read da name `\_(^-^)_/´

    private static JsonArray data;

    // Loads the Data of the URL and parses it into a JsonArray.

    public static void parseData() {
        URL url = null;
        try {
            url = new URL(stringurl);

            Scanner scanner = new Scanner(url.openStream(), "UTF-8").useDelimiter("\\A");

            String result = scanner.next();

            JsonParser parser = new JsonParser();
            data = parser.parse(result).getAsJsonArray();

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Gets a boolean

    public static boolean getBoolean(String playerUUID, String key) throws IOException {
        for (int i = 0; i < data.size(); i++) {
            JsonObject jsonObject = data.get(i).getAsJsonObject();
            
            if (jsonObject.get("uuid").getAsString().equals(playerUUID)) {
                if (jsonObject.get(key).getAsBoolean()) {
                    return true;
                }
            }
        }

        return false;
    }

    // Gets a string
    
    public static String getString(String playerUUID, String key) throws IOException {
        for (int i = 0; i < data.size(); i++) {
            JsonObject jsonObject = data.get(i).getAsJsonObject();

            if (jsonObject.get("uuid").getAsString().equals(playerUUID)) {
                return jsonObject.get(key).getAsString();
            }
        }

        return null;
    }

    // Gets a int
    
    public static Integer getInt(String playerUUID, String key) throws IOException {
        for (int i = 0; i < data.size(); i++) {
            JsonObject jsonObject = data.get(i).getAsJsonObject();

            if (jsonObject.get("uuid").getAsString().equals(playerUUID)) {
                return jsonObject.get(key).getAsInt();
            }
        }

        return null;
    }
}
