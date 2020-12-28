package card;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuizCard {
    private UUID uuid;
    private String title;
    private String description;
    private List<ConceptCard> conceptCards;

    public QuizCard() {
        uuid = UUID.randomUUID();
        conceptCards = new ArrayList<>();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ConceptCard> getConceptCards() {
        return conceptCards;
    }

    public void addConceptCard(ConceptCard cCard) {
        conceptCards.add(cCard);
    }
}
