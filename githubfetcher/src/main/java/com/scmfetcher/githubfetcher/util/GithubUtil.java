package com.scmfetcher.githubfetcher.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GithubUtil {
    public static int getSinceValue(String str) {
        if(str.contains("rel=\"next\"")) {
            try {
                String keyToSearch = "since";
                char[] strChar = str.toCharArray();
                int lastIndexOfKey = str.indexOf(keyToSearch) + keyToSearch.length();
                StringBuilder sb = new StringBuilder();
                for (int i = lastIndexOfKey + 1; strChar[i] != '&'; i++) {
                    sb.append(strChar[i]);
                }

                return Integer.parseInt(sb.toString());
            } catch (NumberFormatException ne) {
//                log.error("No Since data found : {}", ne.getMessage());
                return -1;
            }
        }

        return -1;
    }
}
