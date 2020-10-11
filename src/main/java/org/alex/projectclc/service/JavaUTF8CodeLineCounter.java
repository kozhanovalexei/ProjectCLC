package org.alex.projectclc.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class JavaUTF8CodeLineCounter {
    public final static byte QUOTE = 34;
    public final static byte SLASH = 92;
    public final static byte NEWLINE = 10;
    public final static byte COMMENT_SLASH = 47;
    public final static byte STAR = 42;

    public static int countCodeLines(File file) {
        try {

            if(file.exists() && file.canRead()){
                FileInputStream fis = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                fis.read(data);
                fis.close();
                return countCodeLines(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int countCodeLines(byte[] bytes) {
        int totalLinesCount = 0;
        boolean quote = false;
        boolean slash = false;
        boolean lineCounted = false;
        boolean commentBlock = false;
        boolean endLineComment = false;
        boolean skipNext = false;

        byte prev;
        byte curr = 32;
        for (byte b : bytes) {
            prev = curr;
            curr = b;

            if (curr == NEWLINE) {
                lineCounted = false;
                endLineComment = false;
                skipNext = false;
                continue;
            }

            if (skipNext) {
                skipNext = false;
                continue;
            }

            if (commentBlock) {
                if (prev == STAR && curr == COMMENT_SLASH) {
                    commentBlock = false;
                    skipNext = true;
                }
                continue;
            }

            if (endLineComment) {
                continue;
            }

            if (quote) {
                if (curr == SLASH) {
                    slash = !slash;
                    continue;
                } else if (!slash && curr == QUOTE) {
                    quote = false;
                }
                continue;
            } else {
                if (prev == COMMENT_SLASH && curr == STAR) {
                    commentBlock = true;
                    skipNext = true;
                    continue;
                }

                if (prev == COMMENT_SLASH && curr == COMMENT_SLASH) {
                    endLineComment = true;
                    continue;
                }
            }

            if (curr == 32 || curr == 8 || curr == 9 || curr == 11 || curr == 12 || curr == 13 || curr == COMMENT_SLASH) {
                continue;
            }

            if (!lineCounted) {
                totalLinesCount++;
                lineCounted = true;
            }

            if (curr == QUOTE) {
                quote = true;
            }

        }

        return totalLinesCount;
    }

}
