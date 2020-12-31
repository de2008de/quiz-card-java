package repository;

import card.ConceptCard;
import card.QuizCard;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CardRepository {
    private List<QuizCard> quizCards = new ArrayList<>();
    private static CardRepository cardRepository;

    private CardRepository() {
        // For testing, create some testing cards
        for (int i = 0; i < 1; i++) {
            QuizCard quizCard = new QuizCard();
            ConceptCard cc = new ConceptCard();
            cc.setTerm("term");
            cc.setDefinition("definition");
            quizCard.addConceptCard(cc);
            quizCard.addConceptCard(cc);
            quizCard.setTitle("Testing Quiz Card " + i);
            addQuizCard(quizCard);
        }
    }

    public static CardRepository getInstance() {
        if (cardRepository != null) {
            return cardRepository;
        }
        cardRepository = new CardRepository();
        return cardRepository;
    }

    public QuizCard getQCbyUUID(UUID uuid) {
        if (uuid == null) {
            return null;
        }
        for (QuizCard qc : quizCards) {
            if (qc.getUuid().compareTo(uuid) == 0) {
                return qc;
            }
        }
        return null;
    }

    public void clearRepository() {
        quizCards.clear();
    }

    public List<QuizCard> getQuizCards() {
        return quizCards;
    }

    public void addQuizCard(QuizCard quizCard) {
        quizCards.add(quizCard);
    }
}
