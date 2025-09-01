package tech.hariprasath.quizApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;

// {"contents": [{"parts": [{"text": "Explain how AI works if few words"}]}]}

@Data
@AllArgsConstructor
public class GeminiRequestPayload {
    private List<Content> contents;

    public GeminiRequestPayload(String prompt) {
        Part part = new Part(prompt);
        Content content = new Content(Collections.singletonList(part));
        this.contents = Collections.singletonList(content);
    }

    @Data
    @AllArgsConstructor
    public static class Content {
        private List<Part> parts;
    }

    @Data
    @AllArgsConstructor
    public static class Part {
        private String text;
    }
}
