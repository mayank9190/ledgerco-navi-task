package com.ledgerco.io.input;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FileInputParserTest {

    @Test
    void shouldParseFileAndFetchInputLines() throws IOException {
        List<String> inputLines = new FileInputParser()
                .parse(Paths.get("src/test/data/test-input-parser").toAbsolutePath().toString());

        assertThat(inputLines).hasSize(3);
        assertThat(inputLines).containsExactly("line1", "line2", "line3 word2");
    }
}