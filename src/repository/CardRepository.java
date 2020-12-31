package repository;

import card.QuizCard;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CardRepository {
    private List<QuizCard> quizCards = new ArrayList<>();
    private static CardRepository cardRepository;

    private CardRepository() {

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
