package me.zihluwang.surgeconverter.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UriUtil {

    public static String encodeURIComponent(String content) {
        // 使用 UTF-8 编码，将字符串转换成URL编码格式
        return URLEncoder.encode(content, StandardCharsets.UTF_8)
                // URLEncoder 会把空格转成 +，而 encodeURIComponent 转成 %20，所以需要替换
                .replaceAll("\\+", "%20")
                .replaceAll("%21", "!")
                .replaceAll("%27", "'")
                .replaceAll("%28", "(")
                .replaceAll("%29", ")")
                .replaceAll("%7E", "~");
    }
}
