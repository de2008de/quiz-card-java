package repository;

import card.QuizCard;

import java.util.ArrayList;
import java.util.List;

public class CardRepository {
    private List<QuizCard> quizCards = new ArrayList<>();

    public CardRepository() {
        // For testing, create some testing cards
        for (int i = 0; i < 1; i++) {
            QuizCard quizCard = new QuizCard();
            quizCard.setTitle("Testing Quiz Card " + i);
            addQuizCard(quizCard);
        }
    }

    public List<QuizCard> getQuizCards() {
        return quizCards;
    }

    public void addQuizCard(QuizCard quizCard) {
        quizCards.add(quizCard);
    }
}
