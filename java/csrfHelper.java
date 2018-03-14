package hb.fwu;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class csrfHelper {
    private static List<String> _csrfTokenList = new ArrayList<String>();

    public static String Generate() {
        try {
            if (_csrfTokenList == null)
                _csrfTokenList = new ArrayList<String>();
            UUID uuid = UUID.randomUUID();
            String token = SecurityUtility.base64Encode(String.valueOf(uuid));
            _csrfTokenList.add(token);
            return token;
        } catch (Exception ex) {
            return "ERROR";
        }
    }

    public static boolean isValid(String token) {
        try {
            if (_csrfTokenList != null && _csrfTokenList.contains(token)) {
                _csrfTokenList.remove(token);
                return true;
            } else
                return false;
        } catch (Exception ex) {
            return false;
        }
    }
}
