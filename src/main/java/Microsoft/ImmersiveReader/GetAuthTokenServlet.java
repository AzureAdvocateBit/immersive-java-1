package Microsoft.ImmersiveReader;

import javax.servlet.http.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetAuthTokenServlet extends HttpServlet {

    public static final long serialVersionUID = 6604416900591185960L;

    // #region for Auth
    public static String TENANT_ID = System.getenv("TENANT_ID");
    public static String CLIENT_ID = System.getenv("CLIENT_ID");
    public static String CLIENT_SECRET = System.getenv("CLIENT_SECRET");
    public static String SUBDOMAIN = System.getenv("SUBDOMAIN");
    // #endregion

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException {

        if (isNullOrEmpty(TENANT_ID) || isNullOrEmpty(CLIENT_ID) || isNullOrEmpty(CLIENT_SECRET)
                || isNullOrEmpty(SUBDOMAIN)) {
            throw new IllegalArgumentException("Azure Authentication information missing. Did you add "
                    + "TENANT_ID, CLIENT_ID, CLIENT_SECRET and SUBDOMAIN to Application properties?");
        }

        String token = getToken();
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(token);
        writer.flush();
    }

    /**
     * Returns the token for the Immersive Reader
     *
     * @return the token for the Immersive Reader
     *
     */
    private String getToken() throws IOException {

        URL tokenUrl = new URL("https://login.windows.net/" + TENANT_ID + "/oauth2/token");
        String form = "grant_type=client_credentials&resource=https://cognitiveservices.azure.com/&client_id="
                + CLIENT_ID + "&client_secret=" + CLIENT_SECRET;

        HttpURLConnection connection = (HttpURLConnection) tokenUrl.openConnection();
        connection.setRequestMethod("POST");

        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        connection.setDoOutput(true);
        DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
        writer.writeBytes(form);
        writer.flush();
        writer.close();

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader readerIn = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = readerIn.readLine()) != null) {
                response.append(inputLine);
            }
            readerIn.close();

            // Return token
            return response.toString();
        } else {
            throw new IOException();
        }
    }

    private static boolean isNullOrEmpty(String s) {
        return (s == null || s.isEmpty());
    }
}
