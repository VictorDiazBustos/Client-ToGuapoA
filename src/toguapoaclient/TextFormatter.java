/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package toguapoaclient;

/**
 *
 * @author vdiazbus
 */
public class TextFormatter {
    private static final String UNDERLINE = "\033[0;4m";
    private static final String BOLD = "\033[0;1m";
    private static final String RESET = "\033[0;0m";
    private static final String ITALIC = "\033[3m";

    public TextFormatter() {

    }

    /**
     * Change text format
     * @param text Text to format
     * @return formatted text
     */
    public static String formatText(String text) {
        // Convert text between '/_' '/_' in underlined text
        text = underlineText(text);

        // Convert text between '/@' '/@' in bold text
        text = boldfaceText(text);

        // Convert text between '~' '~' in italic text
        text = italicText(text);
        
        return text;
    }

    /**
     * Convert text between '/_' '/_' in underlined text
     * @param text Text to format
     * @return formatted text
     */
    public static String underlineText(String text) {
        String result_text = "";
        String[] splitted_text = text.split("/_");

        // Elements in odd positions (1,3,5,...) convert to underline,
        // except if the last element is in an odd position ('/_' is not closed)
        for (int i = 0; i < splitted_text.length; i++) {
            if ((i + 1) % 2 == 0){
                if (i == splitted_text.length-1){
                    if(text.charAt(text.length()-1) == '_' && text.charAt(text.length()-2) == '/')
                        result_text += UNDERLINE + splitted_text[i] + RESET;
                    else
                        result_text += splitted_text[i];
                }
                else
                    result_text += UNDERLINE + splitted_text[i] + RESET;
            }
            else
                result_text += splitted_text[i];
        }

        return result_text;
    }

    // The broken comdom
    /*public static String boldfaceText(String text) {
        // Spit the text by spaces
        String[] words = text.split(" ");
        String formatedText = " ";
        for (int i = 0; i < words.length; i++) {
            if (words[i].charAt(0) == '*' && words[i].charAt(words[i].length() - 1) == '*') {
                words[i] = words[i].replace('*',' ');
                words[i] = BOLD + words[i] + RESET ;
            }
        }

        for (int i = 0; i < words.length; i++) {
            formatedText += words[i]+" ";
        }

        return formatedText;
    }*/


    /**
     * Convert text between '/*' '/*' in underlined text
     * @param text Text to format
     * @return formatted text
     */
    public static String boldfaceText(String text) {
        String result_text = "";
        String[] splitted_text = text.split("/@");

        // Elements in odd positions (1,3,5,...) convert to bold text
        for (int i = 0; i < splitted_text.length; i++) {
            if ((i + 1) % 2 == 0){
                if (i == splitted_text.length-1){
                    if(text.charAt(text.length()-1) == '@' && text.charAt(text.length()-2) == '/')
                        result_text += BOLD + splitted_text[i] + RESET;
                    else
                        result_text += splitted_text[i];
                }
                else
                    result_text += BOLD + splitted_text[i] + RESET;
            }
            else
                result_text += splitted_text[i];
        }
        
        return result_text;
    }

    /**
     * Convert text between '~' '~' in italic text
     * @param text Text to format
     * @return formatted text
     */
    public static String italicText(String text) {
        String result_text = "";
        String[] splitted_text = text.split("~");

        // Elements in odd positions (1,3,5,...) convert to Italic text
        for (int i = 0; i < splitted_text.length; i++) {
            if ((i + 1) % 2 == 0){
                if (i == splitted_text.length-1){
                    if(text.charAt(text.length()-1) == '~')
                        result_text += ITALIC + splitted_text[i] + RESET;
                    else
                        result_text += splitted_text[i];
                }
                else
                    result_text += ITALIC + splitted_text[i] + RESET;
            }
            else
                result_text += splitted_text[i];
        }

        return result_text;
    }
}
