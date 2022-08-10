package com.ledgerco.io.input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileInputParser {
    public List<String> parse(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        lines.removeIf(String::isEmpty);
        return lines;
    }
}
