package co.wordywordy.wordywordy.web;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import co.wordywordy.wordywordy.Constants;

/**
 * Created by salaz on 8/8/2016.
 */
public class JSONParser {

    public static final String TAG = JSONParser.class.getName();

    public static JSONObject getJSONObjectFromURL(String url) {

        HttpURLConnection httpURLConnection = null;

        try {

            URL urlToRequest = new URL(url);

            httpURLConnection = (HttpURLConnection) urlToRequest.openConnection();
            httpURLConnection.setConnectTimeout(Constants.CONNECTION_TIMEOUT);
            httpURLConnection.setReadTimeout(Constants.READ_TIMEOUT);

            int statusCode = httpURLConnection.getResponseCode();

            if(statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                Log.d(TAG, "Unauthorized Access");
            } else if(statusCode == HttpURLConnection.HTTP_NOT_FOUND) {
                Log.d(TAG, "404 Page Not Fount");
            } else if(statusCode != HttpURLConnection.HTTP_OK) {
                Log.d(TAG, "url Response Code Error");
            }

            //new BufferedInputStream is assigned to an InputStream type.
            //We pass in the InputStream of the HttpURLConnection
            InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            return new JSONObject(convertInputStreamToString(inputStream));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }  finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

        return null;

    };

    private static String convertInputStreamToString(InputStream inputStream) {
        //new StringBuilder object to convert the InputStream to a String object
        StringBuilder stringBuilder = new StringBuilder();
        //BufferedReader to read the inputStream's characters efficiently
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String responseText;

        try {
            while ((responseText = bufferedReader.readLine()) != null) {
                stringBuilder.append(responseText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //we return the StringBuilder with all the appended responseText
        return stringBuilder.toString();
    }


    public static boolean hasInternetConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // return if device has network connection. all properties must not return null or true.
        return connectivityManager != null &&
                connectivityManager.getActiveNetworkInfo() != null &&
                connectivityManager.getActiveNetworkInfo().isConnected();
    }

}


