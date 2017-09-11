package phr33ze.google.com.paladins.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Des. Android on 05/09/2017.
 */

public class StringData {

    private final String string;

    /**
     * String Data
     * @param string string
     */
    public StringData(String string) {
        this.string = string;
    }

    /**
     * Getting data to {@link String}
     * @return {@link String}
     */
    @Override
    public String toString() {
        return string;
    }

    /**
     * Getting data to {@link JSONArray}
     * @return {@link JSONArray}
     */
    public JSONArray toJsonArray() throws JSONException {
        return new JSONArray(string);
    }

    /**
     * Getting data to {@link JSONObject}
     * @return {@link JSONObject}
     */
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject(string);
    }
}
