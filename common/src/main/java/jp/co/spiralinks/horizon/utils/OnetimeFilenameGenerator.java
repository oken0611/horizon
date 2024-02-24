package jp.co.spiralinks.horizon.utils;

import java.util.UUID;

public class OnetimeFilenameGenerator {

    private String GenerateFilePath(String fileExt) {
        UUID uuid = UUID.randomUUID();
        return String.format("%s.%s", uuid.toString().replaceAll("-", ""), fileExt);
    }

    private String GenerateFilePath(String prefix, String fileExt) {
        UUID uuid = UUID.randomUUID();
        return String.format("%s%s.%s", prefix, uuid.toString().replaceAll("-", ""), fileExt);
    }

    public static String Generate(String fileExt) {
        OnetimeFilenameGenerator gen = new OnetimeFilenameGenerator();
        return gen.GenerateFilePath(fileExt);
    }

    public static String Generate(String prefix, String fileExt) {
        OnetimeFilenameGenerator gen = new OnetimeFilenameGenerator();
        return gen.GenerateFilePath(prefix, fileExt);
    }
}
