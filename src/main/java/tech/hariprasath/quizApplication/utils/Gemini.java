package tech.hariprasath.quizApplication.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tech.hariprasath.quizApplication.dto.GeminiRequestPayload;
import tech.hariprasath.quizApplication.entity.QuizQuestion;

import java.util.List;

@Service
public class Gemini {

    @Value("${gemini.api.url}")
    private String geminiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public Gemini(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.build();
        this.objectMapper = objectMapper;
    }

    public List<QuizQuestion> createQuiz(int noOfQuestions, String title) {
        String prompt = String.format("""
            Generate a quiz with %d multiple-choice questions on the topic "%s".

            Return the result as a JSON array in the following format:
            [
              {
                "id": 1,
                "question": "Sample question?",
                "options": {
                  "a": "Option A",
                  "b": "Option B",
                  "c": "Option C",
                  "d": "Option D"
                },
                "correctOption": "a"
              }
            ]

            Only return the JSON array. The questions should be clear and relevant to the topic.
            Each question must have exactly four options labeled 'a' through 'd', and only one correct answer.
            """, noOfQuestions, title);

        GeminiRequestPayload payload = new GeminiRequestPayload(prompt);

        String response = webClient.post()
                .uri(geminiUrl)
                .header("Content-Type", "application/json")
                .header("X-goog-api-key", geminiApiKey)
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return extractContent(response, new TypeReference<List<QuizQuestion>>() {});
    }

    private <T> T extractContent(String response, TypeReference<T> typeReference) {
        try {
            JsonNode root = objectMapper.readTree(response);
            String rawText = root
                    .path("candidates").get(0)
                    .path("content").path("parts").get(0)
                    .path("text").asText();

            String cleanedJson = rawText
                    .replaceAll("^```json\\s*", "")
                    .replaceAll("```$", "")
                    .trim();

            return objectMapper.readValue(cleanedJson, typeReference);

        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            return null;
        }
    }
}
