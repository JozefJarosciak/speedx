package io.rong.imageloader.cache.disc.naming;

public class HashCodeFileNameGenerator implements FileNameGenerator {
    public String generate(String str) {
        return String.valueOf(str.hashCode());
    }
}
