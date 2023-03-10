import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public final class CodeFormatter {
	private static final String COLON = ":";
	private static final String EXCEPTION_TEXT = "Exception";

	private CodeFormatter() {
	}


	public static String formatToJSON(String jsonString) {
		try {
			JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
			return new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject);
		} catch (Exception e) {
			String message = e.getMessage();
			if (message.contains(EXCEPTION_TEXT)) {
				return message.split(COLON)[1];
			}
			return message;
		}
	}

}
