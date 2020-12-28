package repository;

import card.QuizCard;

import java.util.ArrayList;
import java.util.List;

public class CardRepository {
    private List<QuizCard> quizCards = new ArrayList<>();

    public CardRepository() {
        // For testing, create some testing cards
        QuizCard quizCard1 = new QuizCard();
        quizCard1.setTitle("Testing Quiz Card 1312312312312312312");
        QuizCard quizCard2 = new QuizCard();
        quizCard2.setTitle("Testing Quiz Card 2");
        addQuizCard(quizCard1);
        addQuizCard(quizCard2);
    }

    public List<QuizCard> getQuizCards() {
        return quizCards;
    }

    public void addQuizCard(QuizCard quizCard) {
        quizCards.add(quizCard);
    }
}
