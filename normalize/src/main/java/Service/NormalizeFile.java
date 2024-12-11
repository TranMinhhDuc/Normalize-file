package Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class NormalizeFile {
    public String normalize(InputStream fileContent) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));
        StringBuilder file = new StringBuilder();
        
        String line;
        while ((line = reader.readLine()) != null) {
            file.append(line).append("\n");
        }
        String str = file.toString();
        System.out.println(normalize(str));
        return normalize(str);
    }

    public static String normalize(String str) {
        StringBuffer content = new StringBuffer();
        boolean checkComma = false;
        boolean checkDot = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if(checkComma){
                checkComma=false;
                content.append(' ').append(c);
            }else if (checkDot) {
                checkDot = false;
                c = Character.toUpperCase(c);
                content.append(' ').append(c);
            } else content.append(c);
            if(c == ',' || c == ':') {
                checkComma = true;
            }
            if(c == '.') {
                checkDot = true;
            }
        }
        String sContent = content.toString();
        sContent = sContent.trim().replaceAll("\\s+", " ");
        sContent = Character.toUpperCase(str.charAt(0)) + sContent.substring(1);
        return sContent;
    }
}
